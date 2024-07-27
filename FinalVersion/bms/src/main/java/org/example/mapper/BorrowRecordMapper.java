package org.example.mapper;

import org.example.dto.BaseQueryParamsDto;
import org.example.dto.BorrowRecordDto;
import org.example.entity.BorrowRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity org.example.entity.BorrowRecord
 */
public interface BorrowRecordMapper extends BaseMapper<BorrowRecord> {
    List<Object> selectRecords(BorrowRecordDto borrowRecordDto);
    Integer   selectRecordsCount(BorrowRecordDto borrowRecordDto);

}




