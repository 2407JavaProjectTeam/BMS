package org.example.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dto.BorrowRecordDto;
import org.example.dto.RecordAddDto;
import org.example.dto.ReturnDto;
import org.example.entity.Book;
import org.example.entity.BorrowRecord;
import org.example.entity.ReturnRequest;
import org.example.mapper.BookMapper;
import org.example.mapper.ReturnRequestMapper;
import org.example.service.BorrowRecordService;
import org.example.mapper.BorrowRecordMapper;
import org.example.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class BorrowRecordServiceImpl extends ServiceImpl<BorrowRecordMapper, BorrowRecord>
    implements BorrowRecordService{
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ReturnRequestMapper returnRequestMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private PermissionService permissionService;
    @Override
    public SaResult queryRecordInfos(BorrowRecordDto borrowRecordDto) {
        System.out.println(borrowRecordDto);
       List<Object> records =  borrowRecordMapper.selectRecords(borrowRecordDto);
        System.out.println(records);
       Integer count =  borrowRecordMapper.selectRecordsCount(borrowRecordDto);
       Map<String,Object> map = new HashMap<>();
       map.put("recordInfo",records);
       map.put("totals",count);
       return SaResult.get(200,"查询成功！！",map);
    }

    @Override
    public SaResult returnBook(ReturnDto returnDto) {
//        UpdateWrapper updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("record_id",returnDto.getR_id());
//        BorrowRecord br = new BorrowRecord();
//        br.setStatus(1);
//        Date date = new Date(System.currentTimeMillis());
//        br.setReturnDate(date);
//        int update = borrowRecordMapper.update(br, updateWrapper);
//        UpdateWrapper updateWrapper1 = new UpdateWrapper<>();
//        updateWrapper1.eq("book_id",returnDto.getBookId());
//        Book book = new Book();
//        System.out.println(returnDto.getBookNum());
//        System.out.println(returnDto.getBookBorrowNum());
//        book.setBookNum(returnDto.getBookNum()+1);
//        book.setBookBorrowNum(returnDto.getBookBorrowNum()-1);
//        int update1 = bookMapper.update(book, updateWrapper1);
        ReturnRequest returnRequest = new ReturnRequest();
        returnRequest.setBookId(returnDto.getBookId());
        returnRequest.setUserId(returnDto.getUserId());
        Date date = new Date(System.currentTimeMillis());
        returnRequest.setRequestDate(date);
        int insert = returnRequestMapper.insert(returnRequest);
        return  SaResult.get(200,"归还申请成功！！",insert);

    }

    @Override
    public SaResult addRecord(RecordAddDto recordAddDto) {
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(recordAddDto.getBookId());
        borrowRecord.setUserId(recordAddDto.getUserId());
        borrowRecord.setStatus(2);
        Date date = new Date(System.currentTimeMillis());
        borrowRecord.setBorrowDate(date);
        int insert = borrowRecordMapper.insert(borrowRecord);
        Book book = new Book();
        book.setBookNum(recordAddDto.getBookNum()-1);
        book.setBookBorrowNum(recordAddDto.getBookBorrowNum()+1);
//        System.out.println("===================================================================");
//        System.out.println(book);
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("book_id",recordAddDto.getBookId());

        int update = bookMapper.update(book, updateWrapper);
        return  SaResult.get(200,"借阅成功！！",insert);
    }
}




