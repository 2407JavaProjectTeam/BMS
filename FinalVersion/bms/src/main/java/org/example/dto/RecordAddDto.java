package org.example.dto;

import lombok.Data;

@Data
public class RecordAddDto {
   private int  userId;
   private int bookId;
   private int bookNum;
   private int bookBorrowNum;
}
