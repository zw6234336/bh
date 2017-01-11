package com.zhang.service;

import java.util.List;

import javax.mail.MessagingException;

public interface SpringMailService {

	
	/**
	 * 单发
	 * 
	 * @param recipient 收件人
	 * @param subject 主题
	 * @param content 内容
	 */
	public void send(String recipient, String subject, String content);
	
	
	public void sendJava(String recipient, String subject, String content)throws MessagingException;
	
	
	/**
	 * 群发
	 * 
	 * @param recipients
	 * @param subject
	 * @param content
	 */
	public void send(List<String> recipients, String subject, String content);
}
