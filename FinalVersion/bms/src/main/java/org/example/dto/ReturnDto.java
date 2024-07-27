package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReturnDto {
        private String status;
        private int r_id;
        private int bookId;
        private int bookNum;
        private int bookBorrowNum;
        private Date date;
        private int userId;
}
