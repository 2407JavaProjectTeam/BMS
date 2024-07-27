package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName t_book
 */
@TableName(value ="t_book")
@Data
public class Book implements Serializable {
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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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
    private Byte bookStatus;


    private Integer bookNum;

    private Integer bookBorrowNum;

    @TableField(exist = false)
    private List<Category> categoryList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}