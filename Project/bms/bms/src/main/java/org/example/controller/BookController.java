package org.example.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.BookAddDto;
import org.example.dto.BookQueryDto;
import org.example.dto.BookUpdateDto;
import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/bookM")
    public List<Book> getAllBooks() {
        return bookService.list();
    }

    @RequestMapping("/bookM")
    public SaResult queryBooks(@RequestBody BookQueryDto bookQueryDto) {return bookService.queryBooks(bookQueryDto);}

    @PostMapping("/bookM")
    public SaResult addBook(@RequestBody BookAddDto bookAddDto) {return bookService.addBook(bookAddDto);}

    @PutMapping("/bookM")
    public SaResult updateBook(@RequestBody BookUpdateDto bookUpdateDto) {return bookService.updateBook(bookUpdateDto);}

    @DeleteMapping("/bookM/{id}")
    public SaResult deleteBook(@PathVariable Integer id) {return bookService.removeBook(id);}

    @DeleteMapping("/bookM/{ids}")
    public SaResult deleteBooks(@PathVariable Integer[] ids) {return bookService.removeBooks(ids);}
}
