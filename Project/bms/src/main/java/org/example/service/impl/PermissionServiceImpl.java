package org.example.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Permission;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.PermissionService;
import org.example.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public List<Map<String, Object>> queryPremission(String userName) {
        //通过账号查询出用户的信息，包含账号对应的角色信息
        User user = userMapper.selectByUserName(userName);
        List<Role> roles = user.getRoleList();
        LinkedHashSet menuSet = new LinkedHashSet<>();
        for (Role role : roles) {
            //查询所有的菜单
            List<Permission> permiss = permissionMapper.selectByRoleId((long)(role.getRoleId()));
            for (int i = 0; i < permiss.size(); i++) {
                if (permiss.get(i).getPid() == 0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", permiss.get(i).getPermissionName());
                    map.put("url", permiss.get(i).getPermissionUrl());
                    map.put("icon", permiss.get(i).getPermissionIcon());
                    List<Map<String, Object>> ls = getChild((long)(permiss.get(i).getPermissionId()), permiss);
                    if (ls != null && ls.size() > 0) {
                        map.put("child", ls);
                    }
                    menuSet.add(map);
                }
            }
        }
        return new ArrayList<>(menuSet);
    }

    /**
     * 递归查询每个菜单下面的子菜单
     * @param id
     * @param rootMenu
     * @return
     */
    public List<Map<String,Object>> getChild(Long id, List<Permission> rootMenu) {
//        List<Permission> childs = new ArrayList<>();
        // 子菜单
        List<Map<String,Object>> childList = new ArrayList<>();
        for (Permission permission : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较

            if (permission.getPid()==id) {
                Map<String,Object> map1 = new HashMap<>();
                map1.put("name",permission.getPermissionName());
                map1.put("url",permission.getPermissionUrl());
                map1.put("icon",permission.getPermissionIcon());
                List<Map<String,Object>> lss = getChild((long)(permission.getPermissionId()),rootMenu);
                if(lss!=null&&lss.size()>0){
                    map1.put("child",lss);
                    childList.add(map1);
                }
                childList.add(map1);
            }
        }
        return childList;
    }

    /**
     * 查询登录用户对应的权限
     * @param userName
     * @return
     */
    @Override
    public SaResult queryPermissions(String userName) {
        User user = userMapper.selectByUserName(userName);
        List<Role> roles = user.getRoleList();
        String roleKey = roles.stream().map(Role::getRoleName).collect(Collectors.joining("-"));
        List<Map<String,Object>> permissions = (List<Map<String, Object>>) redisTemplate.opsForValue().get(roleKey);
        return SaResult.get(200,"操作成功！",permissions);
    }
}




