package com.bl.ep.utils;

import java.util.ArrayList;
import java.util.List;


public class JqGridPage<T>{

	/**
	 * 总页数
	 */
	private int total;
	/**
	 * 当前页数
	 */
	private int page;
	/**
	 * 总条数
	 */
	private int records;
	/**
	 * 返回消息内容
	 */
	private List<T> rows = new ArrayList<T>();
	/**
	 * 总页数
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * 总页数
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @param count 总条数
	 * @param rows 一页几条
	 */
	public void setTotal(int count,int rows) {
		this.total = count%rows==0?count/rows:(count/rows+1);
	}
	/**
	 * 当前页数
	 */
	public int getPage() {
		return page;
	}
	/**
	 * 当前页数
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * 总条数
	 */
	public int getRecords() {
		return records;
	}
	/**
	 * 总条数
	 */
	public void setRecords(int records) {
		this.records = records;
	}
	/**
	 * 返回消息内容
	 */
	public List<T> getRows() {
		return rows;
	}
	/**
	 * 返回消息内容
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
