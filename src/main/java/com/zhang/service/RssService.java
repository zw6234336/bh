package com.zhang.service;

import java.net.URL;
import java.util.List;

import com.sun.syndication.io.FeedException;

/**
 * 
 * Rss 订阅接口
 * @author zhangwei
 *
 * 2017年1月11日
 */
public interface RssService {

	
	/**
	 * 获取Rss编码格式
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public String getRssEncode(URL url) throws Exception;
	
	/**
	 * 通过url解析Rss内容
	 * 
	 * @param url
	 * @throws IllegalArgumentException
	 * @throws FeedException
	 */
	@SuppressWarnings("rawtypes")
	public List parseXml(URL url,String rssEncode) throws IllegalArgumentException,FeedException;

}
