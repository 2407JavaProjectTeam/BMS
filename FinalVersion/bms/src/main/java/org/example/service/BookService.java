package org.example.service;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.BookQueryDto;
import org.example.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface BookService extends IService<Book> {
   // SaResult addBook(BookAddDto bookAddDto);

   // SaResult removeBook(Integer bookId );

   // SaResult removeBooks(Integer[] bookIds);

    SaResult queryBooks(BookQueryDto bookQueryDto);


    //SaResult updateBook(BookUpdateDto bookUpdateDto);
}
