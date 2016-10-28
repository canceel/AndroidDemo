package com.shenme.androiddemo.bean;

/**
 * Created by CANC on 2016/9/10.
 */
public class Pagination {
    //当前页
    public int currentPage;
    //每页订单数量
    public int pageSize;
    //总页数
    public int numberOfPages;
    //订单总数
    public int totalNumberOfResults;
    //大学城
    public String page;
    public String size;
    private int totalPages;
    public String totalElements;
    private int totalRecords;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getTotalNumberOfResults() {
        return totalNumberOfResults;
    }

    public void setTotalNumberOfResults(int totalNumberOfResults) {
        this.totalNumberOfResults = totalNumberOfResults;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.numberOfPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalNumberOfResults = totalRecords;
    }

    /**
     * 对比是否是最后一页
     *
     * @return
     */
    public boolean isLastPage() {
        return currentPage >= numberOfPages || currentPage == numberOfPages - 1 || (currentPage == 0 && numberOfPages == 0);
    }

    /**
     * 商品列表最后一个判断
     */

    public boolean isLastPlPPage() {
        return currentPage == numberOfPages || (currentPage == 0 && numberOfPages == 0);
    }
}
