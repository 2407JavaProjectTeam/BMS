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

//    @Override
//    public SaResult addBook(BookAddDto bookAddDto){
//        Book book=new Book();
//
//        book.setTitle(bookAddDto.getTitle());
//        book.setAuthor(bookAddDto.getAuthor());
//        book.setPublicationDate(bookAddDto.getPublicationDate());
//        book.setIsbn(bookAddDto.getIsbn());
//        book.setCategoryId(bookAddDto.getCategoryId());
//        book.setBookNum(bookAddDto.getBookNum());
//        book.setBookId(bookAddDto.getBookBorrowNum());
//
//        int insert = bookMapper.insert(book);
//
//        return SaResult.ok("添加书籍"+bookAddDto.getTitle()+"成功！");
//    }

//    @Override
//    public SaResult removeBook(Integer bookId) {
//        int num= bookMapper.deleteById(bookId);
//
//        return SaResult.ok("删除书籍"+bookId+"成功！");
//    }

//    @Override
//    public SaResult removeBooks(Integer[] bookIds) {
//        int num = bookMapper.deleteBatchIds(Arrays.asList(bookIds));
//        return SaResult.ok("批量删除书籍成功！");
//    }

    @Override
    public SaResult queryBooks(BookQueryDto bookQueryDto)  {
        List<Object> books = bookMapper.selectBooks(bookQueryDto);
        Integer count = bookMapper.selectBooksCount(bookQueryDto);
        Map<String,Object> map = new HashMap<>();
        System.out.println(books);
        map.put("bookInfos",books);
        map.put("totals",count);
        return SaResult.get(200,"查询成功！",map);
    }

//    @Override
//    public SaResult updateBook(BookUpdateDto bookUpdateDto) {
//        UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("book_id",bookUpdateDto.getBook_id());
//        Book book=new Book();
//        book.setTitle(bookUpdateDto.getTitle());
//        book.setAuthor(bookUpdateDto.getAuthor());
//        book.setPublicationDate(bookUpdateDto.getPublicationDate());
//        book.setIsbn(bookUpdateDto.getIsbn());
//        book.setCategoryId(bookUpdateDto.getCategoryId());
//        book.setBookNum(bookUpdateDto.getBookNum());
//        book.setBookBorrowNum(bookUpdateDto.getBookBorrowNum());
//        int update = bookMapper.update(book, updateWrapper);
//        return SaResult.ok("修改成功！");
//    }


}




