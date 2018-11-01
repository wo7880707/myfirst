package com.ry.szq.pojo;

import java.util.Date;

public class EmailDate {
	private int id;
	private String date;
	public EmailDate(){}
	public EmailDate(int id, String date) {
		super();
		this.id = id;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "EmailDate [id=" + id + ", date=" + date + "]";
	}
	
	
}
