package com.javays.base.bean;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageInfo<T> {

    private Integer currentPage;// 当前页，页面出传递到servlet，servlet传到PageInfo
    private Integer pageSize;// 每页显示几条数据，页面出传递到servlet，servlet传到PageInfo
    private Integer total;// 总条数，查询数据获取到的
    private Integer startIndex;// 起始索引，计算的
    private List<T> dataList;// 每个表查询到的分页数据

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    /**
     * 构造函数
     *
     * @param pageSizeStr    每页显示几条数据
     * @param currentPageStr 当前页
     * @param total          总条数
     */
    public PageInfo(String pageSizeStr, String currentPageStr, Integer total) {
        // 将 pageSizeStr、currentPageStr 转为 Integer
        Integer currentPage = currentPageStr != null && !"".equals(currentPageStr) ? Integer.valueOf(currentPageStr) : 1;
        Integer pageSize = pageSizeStr != null && !"".equals(pageSizeStr) ? Integer.valueOf(pageSizeStr) : 5;

        // 赋值
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;

        // 验证当前页是否符合要求：1-sumPage
        // 总页数
        Integer sumPage = this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;
        // 当前页不能大于 总页数
        this.currentPage = this.currentPage > sumPage ? sumPage : this.currentPage;
        // 当前页不能小于0
        this.currentPage = this.currentPage <= 0 ? 1 : this.currentPage;


        // 计算startIndex
        this.startIndex = (this.currentPage - 1) * this.pageSize;
    }


}
