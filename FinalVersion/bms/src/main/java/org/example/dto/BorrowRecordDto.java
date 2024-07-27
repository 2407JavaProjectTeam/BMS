package org.example.dto;

import cn.dev33.satoken.util.SaResult;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(callSuper = true)
public class BorrowRecordDto extends BaseQueryParamsDto{
    private String title;
    private String author;
    private String cName;
    private String name;
    private int bookId;
    private int bookNum;
    private int bookBorrowNum;
    private String rb;
    private int r_id;
}
