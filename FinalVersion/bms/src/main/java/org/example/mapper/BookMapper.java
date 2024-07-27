package org.example.mapper;

import org.example.dto.BookQueryDto;
import org.example.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.User;

import java.util.List;

/**
 * @Entity org.example.entity.Book
 */
public interface BookMapper extends BaseMapper<Book> {
    List<Object> selectBooks(BookQueryDto bookQueryDto);

    Integer selectBooksCount(BookQueryDto bookQueryDto);
}




