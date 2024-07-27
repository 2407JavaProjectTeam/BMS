package org.example.mapper;

import org.example.dto.BookQueryDto;
import org.example.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author ZacharyZhou
* @description 针对表【t_book】的数据库操作Mapper
* @createDate 2024-07-26 20:15:18
* @Entity org.example.entity.Book
*/
public interface BookMapper extends BaseMapper<Book> {
    List<Book> selectBooks(BookQueryDto bookQueryDto);

    Integer selectBooksCount(BookQueryDto bookQueryDto);

}




