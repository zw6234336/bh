package com.zhang.querymodel;

import java.util.List;

import com.zhang.model.EmailRss;
import com.zhang.model.User;
import com.zhang.model.UserEmail;

public class UserQueryModel {

	/**
	 * 用户信息
	 */
	private User user;
	
	/**
	 * 用户对应邮箱信息
	 */
	private List<UserEmail> userEmails;
	
	/**
	 * 邮箱对应订阅信息
	 */
	private List<EmailRss> emailRsss;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserEmail> getUserEmails() {
		return userEmails;
	}

	public void setUserEmails(List<UserEmail> userEmails) {
		this.userEmails = userEmails;
	}

	public List<EmailRss> getEmailRsss() {
		return emailRsss;
	}

	public void setEmailRsss(List<EmailRss> emailRsss) {
		this.emailRsss = emailRsss;
	}

	@Override
	public String toString() {
		return "UserQueryModel [user=" + user + ", userEmails=" + userEmails
				+ ", emailRsss=" + emailRsss + "]";
	}
	
}
