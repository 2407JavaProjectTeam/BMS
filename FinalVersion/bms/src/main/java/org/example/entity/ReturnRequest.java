package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName t_return_request
 */
@TableName(value ="t_return_request")
@Data
public class ReturnRequest implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer returnRequestId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer bookId;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date requestDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}