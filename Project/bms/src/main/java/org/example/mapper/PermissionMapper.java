package org.example.mapper;

import org.example.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity org.example.entity.Permission
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectByRoleId(Long roleId);
}




