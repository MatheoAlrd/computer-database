package com.excilys.cdb.model;

import java.util.List;

public class Page<T> {
	
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
    //Sorting parameter
    private String sort;
    //Ascendant or descendant order by
    private boolean asc;
    
    public Page() {
    	super();
    }
    
    public Page(int pageNum, int pageSize, int totalRecord, String sort, boolean asc) {
    	
    	this.sort = sort;
    	this.asc = asc;
    	
        //Total number of records
        this.totalRecord = totalRecord;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
}
