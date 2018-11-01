package com.ry.szq.common;

import org.springframework.stereotype.Component;

@Component
public class Pager {
	private int recordCount ;//总记录数
	private int pageSize = 20;//每页个数 自定义
	private int pageCount ; //总页数
	private int pageNum;//当前页数
	private int start;//起始记录数
	public Pager(){
	//改下页数 可能会有大的收获 然后对比邮箱系统
	}
	public Pager(int recordCount, int pageNum) {
		super();
		this.recordCount = recordCount;
		this.pageNum = pageNum;
		//总页数
		if(recordCount % pageSize == 0){
			pageCount = recordCount / pageSize;
		}else{
			pageCount = recordCount / pageSize + 1;
		}
		//当前页码处理
		if(pageNum < 1){
			pageNum = 1;
		}
		if(pageNum > pageCount){
			pageNum = pageCount;
		}
		//起始位置   原来是+1的  在mapper中还要减去1  现在这块不加了  那块不减了 正好对上
		start = (pageNum - 1) * pageSize ;
		
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "Pager [recordCount=" + recordCount + ", pageSize=" + pageSize + ", pageCount=" + pageCount + ", pageNum=" + pageNum
				+ ", start=" + start + "]";
	}	
	
}
