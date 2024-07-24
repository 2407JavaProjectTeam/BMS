package com.hqyj.gyq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_permission
 */
@TableName(value ="t_permission")
@Data
public class TPermission implements Serializable {
    /**
     * 权限ID
     */
    @TableId
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限icon样式
     */
    private String permissionIcon;

    /**
     * 权限url地址
     */
    private String permissionUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}