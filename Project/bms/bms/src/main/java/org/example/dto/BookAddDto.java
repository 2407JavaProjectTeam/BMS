package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookAddDto {
    private String title;
    private String author;
    private Date publicationDate;
    private String isbn;
    private Integer categoryId;
    private Integer bookNum;
    private Integer bookBorrowNum;
}
