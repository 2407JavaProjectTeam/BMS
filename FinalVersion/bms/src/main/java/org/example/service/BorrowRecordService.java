package org.example.service;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.BorrowRecordDto;
import org.example.dto.RecordAddDto;
import org.example.dto.ReturnDto;
import org.example.entity.BorrowRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface BorrowRecordService extends IService<BorrowRecord> {
         SaResult queryRecordInfos(BorrowRecordDto borrowRecordDto);
         SaResult returnBook(ReturnDto returnDto);
         SaResult addRecord(RecordAddDto recordAddDto);
}
