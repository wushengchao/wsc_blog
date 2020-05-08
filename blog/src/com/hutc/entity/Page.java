package com.hutc.entity;

import java.io.Serializable;

public class Page implements Serializable{
	private int currentPage;
	private int prePage;
	private int totalPage;
	private int nextPage;
	private int firstPage;
	private int lastPage;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPrePage() {
		if(this.currentPage>1){
			prePage=this.currentPage-1;
		}else{
			prePage=1;
		}
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNextPage() {
		if(this.currentPage<this.lastPage){
			nextPage=this.currentPage+1;
		}else{
			nextPage=this.lastPage;
		}
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	

}
