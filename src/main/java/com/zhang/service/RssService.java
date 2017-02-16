package com.zhang.service;

import java.net.URL;
import java.util.List;

import com.sun.syndication.io.FeedException;
import com.zhang.model.Rss;
import com.zhang.querymodel.RssAddModel;

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
	
	 /**
	  * 转发操作
	  * 
	 * @param id
	 * @return
	 */
	public List<Rss> selectRssByEmailId(Integer id); 
	
	 /**
	  * 新增订阅信息
	  * 
	 * @param record
	 * @return
	 */
	public int rssAdd(RssAddModel record);
	
	/**
	 * 删除订阅id和邮箱关系
	 * 
	 * @param rssIds
	 * @param eamilId
	 */
	public void deleteRss(String rssIds,int eamilId);

}
