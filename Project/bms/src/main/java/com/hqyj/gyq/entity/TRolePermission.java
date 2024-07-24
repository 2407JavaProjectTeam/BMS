package com.hqyj.gyq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_role_permission
 */
@TableName(value ="t_role_permission")
@Data
public class TRolePermission implements Serializable {
    /**
     * 角色ID
     */
    @TableId
    private Integer roleId;

    /**
     * 权限ID
     */
    @TableId
    private Integer permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}