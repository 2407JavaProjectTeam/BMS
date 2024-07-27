package org.example.controller;

import org.example.entity.BorrowRecord;
import org.example.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowRecords")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @GetMapping
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordService.list();
    }

    @PostMapping
    public boolean addBorrowRecord(@RequestBody BorrowRecord borrowRecord) {
        return borrowRecordService.save(borrowRecord);
    }

    @PutMapping("/{id}")
    public boolean updateBorrowRecord(@PathVariable Integer id, @RequestBody BorrowRecord borrowRecord) {
        borrowRecord.setRecordId(id);
        return borrowRecordService.updateById(borrowRecord);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBorrowRecord(@PathVariable Integer id) {
        return borrowRecordService.removeById(id);
    }
}