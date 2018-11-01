package com.ry.szq.pojo;

public class Email {
	private int id;
	private String emailName;
	private String emailTime;
	private String emailTheme;
	private String emailContent;
	private String emailFile;
	private String file;
	public Email(){}
	public Email(int id, String emailName, String emailTime, String emailTheme, String emailContent, String emailFile, String file) {
		super();
		this.id = id;
		this.emailName = emailName;
		this.emailTime = emailTime;
		this.emailTheme = emailTheme;
		this.emailContent = emailContent;
		this.emailFile = emailFile;
		this.file = file;
	}
	public Email(String emailName, String emailTime, String emailTheme, String emailContent, String emailFile, String file) {
		super();
		this.emailName = emailName;
		this.emailTime = emailTime;
		this.emailTheme = emailTheme;
		this.emailContent = emailContent;
		this.emailFile = emailFile;
		this.file = file;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailName() {
		return emailName;
	}
	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}
	public String getEmailTime() {
		return emailTime;
	}
	public void setEmailTime(String emailTime) {
		this.emailTime = emailTime;
	}
	public String getEmailTheme() {
		return emailTheme;
	}
	public void setEmailTheme(String emailTheme) {
		this.emailTheme = emailTheme;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public String getEmailFile() {
		return emailFile;
	}
	public void setEmailFile(String emailFile) {
		this.emailFile = emailFile;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "Email [id=" + id + ", emailName=" + emailName + ", emailTime=" + emailTime + ", emailTheme=" + emailTheme
				+ ", emailContent=" + emailContent + ", emailFile=" + emailFile + ", file=" + file + "]";
	}
	
	

	
}
