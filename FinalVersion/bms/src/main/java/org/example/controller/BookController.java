package org.example.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.BookQueryDto;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public SaResult queryBookInfo(BookQueryDto bookQueryDto){
        return bookService.queryBookInfos(bookQueryDto);
    }
}
