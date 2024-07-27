package org.example.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class BookCategoryVo {
    /**
     * 图书编号
     */
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
     * 图书现有数量
     */
    private Integer bookNum;

    /**
     * 图书已借阅数量
     */
    private Integer bookBorrowNum;

    private Integer categoryName;
}
