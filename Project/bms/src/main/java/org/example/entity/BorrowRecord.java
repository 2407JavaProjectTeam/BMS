package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_borrow_record
 */
@TableName(value ="t_borrow_record")
@Data
public class BorrowRecord implements Serializable {
    /**
     * 借阅记录编号
     */
    @TableId(type = IdType.AUTO)
    private Integer recordId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 图书编号
     */
    private Integer bookId;

    /**
     * 借书日期
     */
    private Date borrowDate;

    /**
     * 还书日期
     */
    private Date returnDate;

    /**
     * 状态（1：未归还 2：已归还）
     */
    private Byte status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}