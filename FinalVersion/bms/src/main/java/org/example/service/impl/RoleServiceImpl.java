package org.example.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Role;
import org.example.service.RoleService;
import org.example.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public SaResult queryRolesInfo() {
        List<Role> roles = roleMapper.selectList(null);
        return SaResult.get(200,"查询成功！",roles);
    }
}




