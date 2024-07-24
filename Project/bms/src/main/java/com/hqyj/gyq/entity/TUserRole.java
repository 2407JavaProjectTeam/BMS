package com.hqyj.gyq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_user_role
 */
@TableName(value ="t_user_role")
@Data
public class TUserRole implements Serializable {
    /**
     * 用户ID
     */
    @TableId
    private Integer userId;

    /**
     * 角色ID
     */
    @TableId
    private Integer roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}