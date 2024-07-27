package org.example.dto;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString(callSuper = true)
public class BookQueryDto extends BaseQueryParamsDto{
    private String title;
    private String author;
    private String isbn;
    private String categoryName;
}
