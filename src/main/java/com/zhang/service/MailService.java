package com.zhang.service;


public interface MailService {

	/**
	 * 单发
	 * 
	 * @param recipient
	 *            收件人
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 */
	public void send(String to, String from, String subject, String content);

	/**
	 * 群发
	 * 
	 * @param recipients
	 * @param subject
	 * @param content
	 */
	public void send(String[] recipients, String from, String subject,String content);
}
