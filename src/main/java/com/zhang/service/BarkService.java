package com.zhang.service;

import java.net.URL;

import javax.mail.MessagingException;

/**
 * 传递Rss内容
 * 
 * @author zhangwei
 *
 * 2017年1月11日
 */
public interface BarkService {

	
	/**
	 * 得到信息通知用户
	 * 狗叫
	 * 
	 * @param recipient 收件人
	 * @param url 订阅Rss
	 * @return
	 */
	
	public boolean barkSpring(String recipient,URL url);
	
	/**
	 * @param recipient
	 * @param url
	 * @return
	 * @throws MessagingException
	 */
	public boolean barkJava(String recipient,URL url)throws MessagingException;
}
