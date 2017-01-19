package com.zhang.service;

import java.net.URL;

/**
 * 传递Rss内容
 * 
 * @author zhangwei
 *
 *         2017年1月11日
 */
public interface BarkService {

	/**
	 * 得到信息通知用户
	 * 
	 * @param to
	 *            收件人
	 * @param from
	 *            发件人
	 * @param url
	 *            订阅Rss
	 * @return
	 */

	public boolean barkSpring(String to, String from, URL url);

}
