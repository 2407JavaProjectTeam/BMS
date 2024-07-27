package org.example.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
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

    /**
     * ISBN号
     */
    private String isbn;

    /**
     * 分类ID
     */
    private Integer categoryId;
    /**
     * 出版日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date publicationDate;

    /**
     * 图书现有数量
     */
    private Integer bookNum;

    /**
     * 图书已借阅数量
     */
    private Integer bookBorrowNum;

    private String categoryName;
}
