package com.ry.szq.pojo;

public class Admin {
	private String userID;
	private String userPass;
	private String userGender;
	private String userName;
	private String userRemark;
	public Admin(){}
	public Admin(String userID, String userPass) {
		super();
		this.userID = userID;
		this.userPass = userPass;
		
	}
	public Admin(String userID, String userPass, String userGender, String userName, String userRemark) {
		super();
		this.userID = userID;
		this.userPass = userPass;
		this.userGender = userGender;
		this.userName = userName;
		this.userRemark = userRemark;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	@Override
	public String toString() {
		return "Admin [userID=" + userID + ", userPass=" + userPass + ", userGender=" + userGender + ", userName=" + userName
				+ ", userRemark=" + userRemark + "]";
	}
	

}
