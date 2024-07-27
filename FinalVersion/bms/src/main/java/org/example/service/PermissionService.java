package org.example.service;

import cn.dev33.satoken.util.SaResult;
import org.example.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface PermissionService extends IService<Permission> {
    List<Map<String,Object>> queryPremission(String userName);
    SaResult queryPermissions(String userName);
}
