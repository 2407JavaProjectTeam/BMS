package org.example.service;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.BookAddDto;
import org.example.dto.BookQueryDto;
import org.example.dto.BookUpdateDto;
import org.example.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Random;

/**
* @author ZacharyZhou
* @description 针对表【t_book】的数据库操作Service
* @createDate 2024-07-26 20:15:18
*/
public interface BookService extends IService<Book> {

    SaResult addBook(BookAddDto bookAddDto);

    SaResult removeBook(Integer bookId );

    SaResult removeBooks(Integer[] bookIds);

    SaResult queryBooks(BookQueryDto bookQueryDto);


    SaResult updateBook(BookUpdateDto bookUpdateDto);

}
