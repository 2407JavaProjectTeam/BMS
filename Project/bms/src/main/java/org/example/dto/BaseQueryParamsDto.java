package org.example.dto;

import lombok.Data;

@Data
public class BaseQueryParamsDto {
    //当前页码
    public Integer currentPage;
    //每页展示的条数
    public Integer pageSize;
    //查询的起始下标
    public Integer startIndex;

    public Integer getStartIndex() {
        return (this.currentPage-1)*this.pageSize;
    }

}
