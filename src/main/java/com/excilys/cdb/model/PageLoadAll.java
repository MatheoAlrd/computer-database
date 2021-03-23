package com.excilys.cdb.model;

import java.util.List;

public class PageLoadAll<T> {

    //How many records are displayed per page
    private int pageSize;
    //Current Page Data
    private int currentPage;
    //How many records are there altogether?
    private int totalRecord;
    //How many pages of records are there?
    private int totalPage;
    //To display data, use generics
    private List<T> dataList;

    public PageLoadAll() {
        super();
    }

    public PageLoadAll(int pageSize, int currentPage, int totalRecord, int totalPage, List<T> dataList) {
        super();
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.dataList = dataList;
    }

    public PageLoadAll(int pageNum, int pageSize, List<T> sourceList){
    	
        if (sourceList == null){
            return;
        }
        //Total number of records
        this.totalRecord = sourceList.size();
        //How many records are displayed per page
        this.pageSize = pageSize;
        //Get the total number of pages
        this.totalPage = this.totalRecord / this.pageSize;
        if (this.totalRecord % this.pageSize != 0) {
            this.totalPage += 1;
        }
        if(this.totalPage == 0) {
        	this.totalPage = 1;
        }

        //Current Page Data
        this.currentPage = this.totalPage < pageNum ? this.totalPage : pageNum;

        //Initial index
        int fromIndex = this.pageSize * (this.currentPage - 1);
        //End index
        int toIndex =this.pageSize * this.currentPage > this.totalRecord ?  this.totalRecord :  this.pageSize * this.currentPage;

        this.dataList = sourceList.subList(fromIndex, toIndex);
    }
    
    public int previousPage() {
    	return this.currentPage	== 1 ? this.currentPage : this.currentPage - 1;
    }
    
    public int nextPage() {
    	return this.currentPage	== this.totalPage ? this.totalPage : this.currentPage + 1;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getTotalRecord() {
        return totalRecord;
    }
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public List<T> getDataList() {
        return dataList;
    }
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}