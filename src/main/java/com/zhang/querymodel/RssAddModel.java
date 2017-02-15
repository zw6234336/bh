package com.zhang.querymodel;

import java.io.Serializable;

public class RssAddModel implements Serializable {

	private static final long serialVersionUID = -113381346363367475L;

	private int emailId;
	private String url;
	private String des;
	
	public int getEmailId() {
		return emailId;
	}
	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	
}
