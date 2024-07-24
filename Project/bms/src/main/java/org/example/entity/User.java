package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 账号名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 头像
     */
    private String userImg;

    /**
     * 姓名
     */
    private String userRname;

    /**
     * 性别
     */
    private String userGender;

    /**
     * 联系电话
     */
    private String userTel;

    /**
     * 电子邮箱
     */
    private String userEmail;

    /**
     * 账户创建时间
     */
    private Date registrationDate;

    /**
     * 状态（1：未冻结 2：冻结）
     */
    private Byte userStatus;

    @TableField(exist = false)
    private List<Role> roleList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}