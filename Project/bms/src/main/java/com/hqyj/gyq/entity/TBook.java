package com.hqyj.gyq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_book
 */
@TableName(value ="t_book")
@Data
public class TBook implements Serializable {
    /**
     * 图书编号
     */
    @TableId(type = IdType.AUTO)
    private Integer bookId;

    /**
     * 图书标题
     */
    private String title;

    /**
     * 图书封面
     */
    private String bookImg;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版日期
     */
    private Date publicationDate;

    /**
     * ISBN号
     */
    private String isbn;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 状态（1：未借阅 2：已借阅）
     */
    private Integer bookStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}