package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_role
 */
@TableName(value ="t_role")
@Data
public class Role implements Serializable {
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}