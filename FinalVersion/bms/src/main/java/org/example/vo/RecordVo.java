package org.example.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RecordVo {
    /**
     * 借阅记录编号
     */
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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date borrowDate;

    /**
     * 还书日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date returnDate;

    /**
     * 状态（1：未归还 2：已归还）
     */
    private Byte status;

    private String title;

    private String author;

    private String cName;

    private String name;

    private  String rb;

    private int r_id;

    private int bookNum;

    private int bookBorrowNum;
}
