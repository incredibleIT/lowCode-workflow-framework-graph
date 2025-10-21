package com.lowcode.workflow.runner.graph.result;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult<T> extends Result<List<T>> {

    private long page;

    private long size;

    private long total;

    private long pages;

    private PageResult() {}

    private PageResult(int code, String msg, List<T> data, long page, long size, long total, long pages) {
        super(code, msg, data);
        this.page = page;
        this.size = size;
        this.total = total;
        this.pages = pages;
    }

    public static <T> PageResult<T> from(List<T> data, long page, long size, long total) {
        long pages = total / size + (total % size > 0 ? 1 : 0);
        return new PageResult<>(200, "success", data, page, size, total, pages);
    }


    // 从IPage构建PageResult
    public static <T> PageResult<T> from(IPage<T> ipage) {
        return from(ipage.getRecords(), ipage.getCurrent(), ipage.getSize(), ipage.getTotal());
    }
}
