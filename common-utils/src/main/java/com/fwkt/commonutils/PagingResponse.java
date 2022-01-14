package com.fwkt.commonutils;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagingResponse<T> {

    public PagingResponse() {}

    public PagingResponse(List<T> list, int currentPage, long totalNumber, int pageSize) {
        this.list = list;
        this.currentPage = currentPage;
        this.totalNumber = totalNumber;
        this.pageSize = pageSize;
    }

    private List<T> list;
    private int currentPage;
    private long totalNumber;// 总数
    private int pageSize;//

    public PagingResponse(Page<T> page) {
        this.list = page.getContent();
        this.currentPage = page.getNumber()+1;
        this.totalNumber = page.getTotalElements();
        this.pageSize = page.getTotalPages();
    }
}
