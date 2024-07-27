package org.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class BookQueryDto extends BaseQueryParamsDto{
    private String title;
    private String author;
    private String isbn;
}
