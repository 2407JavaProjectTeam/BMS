package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.UserRole;
import org.example.service.UserRoleService;
import org.example.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




