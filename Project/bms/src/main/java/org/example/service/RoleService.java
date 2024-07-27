package org.example.service;

import cn.dev33.satoken.util.SaResult;
import org.example.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface RoleService extends IService<Role> {
    SaResult queryRolesInfo();
}
