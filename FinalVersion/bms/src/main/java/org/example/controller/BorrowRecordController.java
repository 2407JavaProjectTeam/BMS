package org.example.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.BorrowRecordDto;
import org.example.dto.RecordAddDto;
import org.example.dto.ReturnDto;
import org.example.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @GetMapping("/record")
    public SaResult queryRecordInfo(BorrowRecordDto borrowRecordDto){
        return borrowRecordService.queryRecordInfos(borrowRecordDto);
    }
    @PutMapping("/record")
    public SaResult returnBook(@RequestBody ReturnDto returnDto){

        return borrowRecordService.returnBook(returnDto);
    }
    @PutMapping("/borrow")
    public SaResult addRecord(@RequestBody RecordAddDto recordAddDto){
        return borrowRecordService.addRecord(recordAddDto);
    }

}
