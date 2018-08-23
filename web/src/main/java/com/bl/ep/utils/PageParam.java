package com.bl.ep.utils;

public class PageParam {
	/**
	 * 查询条数
	 */
	private int rows;
	/**
	 * 查询页数
	 */
	private int page;
	/**
	 * 排序字段
	 */
	private String sidx;
	/**
	 * 排序方法
	 */
	private String sord;
	
	/**
	 * 获取查询条数
	 */
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 获取当前页码
	 */
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * 获取排序字段
	 */
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	/**
	 * 获取排序方法
	 */
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	
	public int getStart(){
		
		return(getPage()-1)*getRows()+1;
	}
	
	public int getEnd(){
		
		return getPage()*getRows();
	}
	@Override
	public String toString() {
		return "JqGridParam [rows=" + rows + ", page=" + page + ", sidx=" + sidx + ", sord=" + sord + "]";
	}
}
