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
	 * 通过url解析Rss内容
	 * 
	 * @param url
	 * @throws IllegalArgumentException
	 * @throws FeedException
	 */
	@SuppressWarnings("rawtypes")
	public List parseXml(URL url) throws IllegalArgumentException,FeedException;

	/**
	 * 
	 * 
	 * @throws Exception
	 */
	public void createXml() throws Exception;

}
