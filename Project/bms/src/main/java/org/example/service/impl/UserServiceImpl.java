package org.example.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dto.UserLoginDto;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.mapper.UserRoleMapper;
import org.example.service.PermissionService;
import org.example.service.UserService;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
                //将satoken取出返回到客户端
                StpUtil.login(user.getUserId());
                String tokenValue = StpUtil.getTokenValue();
                //将该账号对应的角色对应的权限菜单查询出来，放入redis
                User u = userMapper.selectByUserName(userLoginDto.getUserName());
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
                return SaResult.get(200, "登录成功！", map);
            } else {//false：密码错误
                return SaResult.error("密码错误！");
            }
        }
    }
}




