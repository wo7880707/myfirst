package com.ry.szq.pojo;

import java.util.Date;

public class Query_info {
	private int id;
	private String userID;
	private Date time;
	private String  startTime;
	private String  endTime;
	private String  tradeCounts;
	private String  clNames;
	private String  contracts;
	private int type;
	public Query_info(){}
	public Query_info(int id, String userID, Date time, String startTime, String endTime, String tradeCounts, String clNames,
			String contracts,int type) {
		super();
		this.id = id;
		this.userID = userID;
		this.time = time;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tradeCounts = tradeCounts;
		this.clNames = clNames;
		this.contracts = contracts;
		this.type = type;
	}
	public Query_info(String userID, Date time, String startTime, String endTime, String tradeCounts, String clNames,
			String contracts,int type) {
		super();
		this.userID = userID;
		this.time = time;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tradeCounts = tradeCounts;
		this.clNames = clNames;
		this.contracts = contracts;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTradeCounts() {
		return tradeCounts;
	}
	public void setTradeCounts(String tradeCounts) {
		this.tradeCounts = tradeCounts;
	}
	public String getClNames() {
		return clNames;
	}
	public void setClNames(String clNames) {
		this.clNames = clNames;
	}
	public String getContracts() {
		return contracts;
	}
	public void setContracts(String contracts) {
		this.contracts = contracts;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Query_info [id=" + id + ", userID=" + userID + ", time=" + time + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", tradeCounts=" + tradeCounts + ", clNames=" + clNames + ", contracts=" + contracts + ", type=" + type + "]";
	}

	
	
	
}
