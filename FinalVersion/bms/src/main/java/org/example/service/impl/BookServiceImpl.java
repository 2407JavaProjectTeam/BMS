package org.example.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dto.BookQueryDto;
import org.example.entity.Book;
import org.example.service.BookService;
import org.example.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{
    @Autowired
    private BookMapper bookMapper;

    @Override
    public SaResult queryBookInfos(BookQueryDto bookQueryDto) {
        List<Book> books = bookMapper.selectBooks(bookQueryDto);
        Integer count = bookMapper.selectBooksCount(bookQueryDto);
        Map<String,Object> map = new HashMap<>();
        map.put("bookInfos",books);
        map.put("totals",count);
        return SaResult.get(200,"查询成功！",map);
    }
}




