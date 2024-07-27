package org.example.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dto.*;
import org.example.entity.Result;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.mapper.RoleMapper;
import org.example.mapper.UserRoleMapper;
import org.example.service.PermissionService;
import org.example.service.UserService;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 登录的业务逻辑
     *
     * @param userLoginDto
     * @return
     */
    @Override
    public SaResult login(UserLoginDto userLoginDto) {

        //根据页面传递的账号去数据库中查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userLoginDto.getUserName());
        User user = userMapper.selectOne(queryWrapper);
        //先判断账号是否正确，如果不正确就提示账号错误
        if (Objects.isNull(user)) {//user为null,说明数据库的表中没有这个账号，提示账号错误
            return SaResult.error("账号错误！");
        } else {//user不为null,说明账号正确，判断密码
            boolean checkpw = BCrypt.checkpw(userLoginDto.getUserPassword(), user.getUserPassword());
            if (checkpw) {//true：密码正确
                User u = userMapper.selectByUserName(userLoginDto.getUserName());
                if (u.getUserStatus() == 1) {
                    //将satoken取出返回到客户端
                    StpUtil.login(user.getUserId());
                    String tokenValue = StpUtil.getTokenValue();
                    //将该账号对应的角色对应的权限菜单查询出来，放入redis
                    List<Role> roleList = u.getRoleList();
                    String roleNamesKey = roleList.stream().map(Role::getRoleName).collect(Collectors.joining("-"));
                    //判断当前角色对应的权限菜单信息是否在redis已经存在了，如果存在了则不用再存放
                    Boolean aBoolean = redisTemplate.hasKey(roleNamesKey);
                    if (!aBoolean) {//没有存在，将该账号对应的权限菜单查询出来放入redis中
                        List<Map<String, Object>> maps = permissionService.queryPremission(userLoginDto.getUserName());
                        redisTemplate.opsForValue().set(roleNamesKey, maps);
                    }
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", tokenValue);
                    map.put("imgUrl", u.getUserImg());
                    map.put("userId", u.getUserId());
                    return SaResult.get(200, "登录成功！", map);
                } else {
                    return SaResult.error("账号冻结！");
                }
            } else {//false：密码错误
                return SaResult.error("密码错误！");
            }
        }
    }

    @Override
    public SaResult register(UserRegisterDto userRegisterDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userRegisterDto.getUserName());
        User user = this.getOne(queryWrapper);
        if (Objects.isNull(user)) {//user为null,说明数据库的表中没有这个账号
            User user1 = new User();
            user1.setUserName(userRegisterDto.getUserName());
            String pw_hash = BCrypt.hashpw(userRegisterDto.getUserPassword(), BCrypt.gensalt(12));
            user1.setUserPassword(pw_hash);
            int insert = userMapper.insert(user1);
            UserRole userRole = new UserRole();
            userRole.setUserId(user1.getUserId());
            userRole.setRoleId(3);
            int insert1 = userRoleMapper.insert(userRole);
            return SaResult.get(200,"注册成功！",null);
        }
        else{
            return SaResult.error("用户名已存在！");
        }
    }

    @Override
    public SaResult queryUserInfos(UserQueryDto userQueryDto) {
        List<User> users = userMapper.selectUsers(userQueryDto);
        Integer count = userMapper.selectUsersCount(userQueryDto);
        Map<String,Object> map = new HashMap<>();
        map.put("userInfos",users);
        map.put("totals",count);
        return SaResult.get(200,"查询成功！",map);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public SaResult removeUser(Integer userId) {
        int num = userMapper.deleteById(userId);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        int num1 = userRoleMapper.delete(queryWrapper);
        return SaResult.ok("删除成功！");
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public SaResult removeUsers(Integer[] userIds) {
        int num = userMapper.deleteBatchIds(Arrays.asList(userIds));
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", Arrays.asList(userIds));
        int num1 = userRoleMapper.delete(queryWrapper);
        return SaResult.ok("删除成功！");
    }

    @Override
    public SaResult queryUserNameIsExist(String userName) {
        User user = userMapper.selectByUserName(userName);
        if(user!=null){
            return SaResult.get(402,"该账号已存在！",null);
        }else {
            return SaResult.ok("该账号不存在！");
        }
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public SaResult addUser(UserAddDto userAddDto) {
        User user = new User();
        Integer roleId = 3;
        user.setUserName(userAddDto.getUserName());
        //密码加密后再存入数据库中
        String pw_hash = BCrypt.hashpw(userAddDto.getUserPassword(), BCrypt.gensalt(12));
        user.setUserPassword(pw_hash);
        user.setUserRname(userAddDto.getUserRname());
        user.setUserGender(userAddDto.getUserGender());
        user.setUserTel(userAddDto.getUserTel());
        user.setUserEmail(userAddDto.getUserEmail());
        user.setRegistrationDate(new Date());
        int insert = userMapper.insert(user);
        List<Role> roles = roleMapper.selectList(null);
        for(int i = 0;i < roles.size();i++) {
            if(roles.get(i).getRoleName() == userAddDto.getRoleName()) {
                roleId = roles.get(i).getRoleId();
                break;
            }
        }
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(roleId);
        int insert1 = userRoleMapper.insert(userRole);
        return SaResult.ok("添加成功！");
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public SaResult updateUser(UserUpdateDto userUpdateDto) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",userUpdateDto.getUserId());
        User user = new User();
        Integer roleId = 3;
        user.setUserName(userUpdateDto.getUserName());
        user.setUserRname(userUpdateDto.getUserRname());
        user.setUserGender(userUpdateDto.getUserGender());
        user.setUserTel(userUpdateDto.getUserTel());
        user.setUserEmail(userUpdateDto.getUserEmail());
        if(userUpdateDto.getUserStatus().equals("正常")) user.setUserStatus((byte)(1));
        else user.setUserStatus((byte)(2));
        user.setMaxNum(userUpdateDto.getMaxNum());
        int update = userMapper.update(user, updateWrapper);
        List<Role> roles = roleMapper.selectList(null);
        for(int i = 0;i < roles.size();i++) {
            if(roles.get(i).getRoleName().equals(userUpdateDto.getRoleName())) {
                roleId = roles.get(i).getRoleId();
                break;
            }
        }
        UpdateWrapper<UserRole> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.eq("user_id",userUpdateDto.getUserId());
        UserRole userRole = new UserRole();
        userRole.setUserId(userUpdateDto.getUserId());
        userRole.setRoleId(roleId);
        int update1 = userRoleMapper.update(userRole, updateWrapper1);
        return SaResult.ok("修改成功！");
    }

    @Override
    public SaResult checkUserPwd(UserPasswordDto userPasswordDto) {
        User user = userMapper.selectById((String)StpUtil.getLoginId());
        boolean checkpw = BCrypt.checkpw(userPasswordDto.getOldPassword(), user.getUserPassword());
        if(checkpw){
            return SaResult.ok("密码正确！");
        }else {
            return SaResult.error("密码错误！");
        }
    }

    @Override
    public SaResult updateUserPwd(UserPasswordDto userPasswordDto) {
        User user = new User();
        //密码加密后再存入数据库中
        String pw_hash = BCrypt.hashpw(userPasswordDto.getNewPassword(), BCrypt.gensalt(12));
        user.setUserPassword(pw_hash);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", StpUtil.getLoginId());
        int i = userMapper.update(user, updateWrapper);
        return SaResult.ok("密码修改成功！");
    }

    @Override
    public int updateUserImg(String userId, String imgUrl) {
        User user = new User();
        user.setUserImg(imgUrl);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId);
        int i = userMapper.update(user, updateWrapper);
        return i;
    }
}




