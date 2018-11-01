package com.ry.szq.pojo;

public class Count {
	private int id;
	private int count;
	public Count(){}
	public Count(int id, int count) {
		super();
		this.id = id;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Count [id=" + id + ", count=" + count + "]";
	}
	
	
}
