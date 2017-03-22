package com.zhang.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.zhang.dao.EmailRssMapper;
import com.zhang.dao.RssMapper;
import com.zhang.model.EmailRss;
import com.zhang.model.Rss;
import com.zhang.querymodel.RssAddModel;
import com.zhang.service.RssService;

@Service
public class RssServiceImpl implements RssService {

	private static final Logger logger = LoggerFactory.getLogger(RssServiceImpl.class);
	
	@Resource
	private RssMapper rssDao;
	
	@Resource
	private EmailRssMapper emailRssDao;
	

	@Override
	public String getRssEncode(URL url) {
		String s;
		String rssXmlEncode = "utf-8";
		logger.info("获取订阅文章编码信息开始,默认文章编码为{}",rssXmlEncode);
		
		//得到rss文章的编码是否存在
		Map<String,String> tempCodeMap = rssDao.selectCodeByUrl(url.toString());
		String tempCode = tempCodeMap.get("rss_encode").toLowerCase();
		if(StringUtils.isNotBlank(tempCode)){
			logger.info("订阅文章编码已经存在数据库为{}",tempCode);
			return tempCode;
		}
		logger.info("订阅文章编码不存在分心文章编码信息开始");
		
		try {
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-agent", "Mozilla/4.0");
			InputStream in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,rssXmlEncode)); // 实例化输入流，并获取网页代码
			while ((s = reader.readLine()) != null) {
				if (s.startsWith("<?xml")) {
					String strEncode = s.substring(
							s.indexOf("<?xml"),
							s.indexOf("?>"));
					String[] encodeArray = strEncode.split("\"");
					rssXmlEncode = encodeArray[encodeArray.length - 1];
					logger.debug("RSS encode is ：" + rssXmlEncode);
				}
			}
		} catch (IOException e) {
			logger.error("解析文章编码出错错误信息是{}",e.toString());
		}
		
		//更新到数据库中
		updateEncode(url.toString(),rssXmlEncode);
		logger.info("更新编码信息到订阅url中{}",rssXmlEncode);
		return rssXmlEncode;

	}
	
	/**
	 * 
	 * 数据库更新订阅文章编码
	 * @param url
	 * @param rssXmlEncode
	 * @return
	 */
	private int updateEncode(String url,String rssXmlEncode){
		Map<String,String> param = new HashMap<String, String>();
		param.put("rssEncode", rssXmlEncode);
		param.put("url", url);
		return rssDao.updateEncodeByRssUrl(param);
		
	}

	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List parseXml(URL url, String rssEncode) {
		
		logger.info("获取订阅邮件内容");
		List result = new ArrayList();
		try {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = null;
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-agent", "Mozilla/4.0");
			String content_encoding = conn.getHeaderField("Content-Encoding");
			logger.debug("rss contend encode is " + content_encoding);

			InputStream in = conn.getInputStream();
			if (content_encoding != null && content_encoding.contains("gzip")) {
				logger.debug("conent encoding is gzip");
				GZIPInputStream gzin = new GZIPInputStream(in);
				try {
					feed = input.build(new XmlReader(gzin));
				} catch (IllegalArgumentException | FeedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					String xmlStr = hanlRssInput(in, rssEncode);
					StringReader sr = new StringReader(xmlStr);
					InputSource is = new InputSource(sr);
					Document doc = (new SAXBuilder()).build(is);
					feed = input.build(doc);
				} catch (Exception e) {
					hanlRssInput(in, rssEncode);
				}
			}

			if (feed == null) {
				return result;
			}
			List<?> entries = feed.getEntries();// 得到所有的标题<title></title>

			SyndEntry entry = null;
			if (entries != null && entries.size() >= 1) {
				entry = (SyndEntry) entries.get(0);
				if (entry.getContents().size() != 0) {
					result = entry.getContents();
				} else {
					String syndContentValue = entry.getDescription().getValue();
					SyndContentImpl syndContentImpl = new SyndContentImpl();
					syndContentImpl.setValue(syndContentValue);
					result.add(syndContentImpl);
				}
			}
			logger.debug(entry.getContents().toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 处理订阅xml字符串
	 * 
	 * @param in
	 * @param encode
	 * @return
	 * @throws IOException
	 */
	private String hanlRssInput(InputStream in, String encode)throws IOException {
		
		String s;
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,encode)); // 实例化输入流，并获取网页代码
		StringBuilder sb = new StringBuilder();
		while ((s = reader.readLine()) != null) {
			sb.append(s);
		}
		logger.info("根据订阅邮件内容编码解析邮件内容{}",sb.toString());
		return sb.toString();

	}

	@Override
	public List<Rss> selectRssByEmailId(Integer id) {
		List<Rss> rsult = new ArrayList<Rss>();
		
		 List<EmailRss> emailRsss = emailRssDao.selectByEmail(id);
		 for(EmailRss model:emailRsss){
			 Rss tempRss = rssDao.selectByPrimaryKey(model.getRssId());
			 rsult.add(tempRss);
		 }
		return rsult;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int rssAdd(RssAddModel record) {
		
		Rss rssModel = new Rss();
		rssModel.setCreatetime(new Date().toLocaleString());
		rssModel.setUrl(record.getUrl());
		rssModel.setDes(record.getDes());
		rssDao.insert(rssModel);
		
		EmailRss emailrecord  = new EmailRss(); 
		emailrecord.setCreatetime(new Date().toLocaleString());
		emailrecord.setEmailId(record.getEmailId());
		emailrecord.setRssId(rssModel.getId());
		return emailRssDao.insert(emailrecord);
	}

	@Override
	public void deleteRss(String rssIds, int eamilId) {
		String[] idArray = getRssIds(rssIds);
		
		for(String rssId:idArray){
			rssDao.deleteByPrimaryKey(Integer.parseInt(rssId));
			
			Map<String,Integer> param = new HashMap<String, Integer>();
			param.put("emailId", eamilId);
			param.put("rssId", Integer.parseInt(rssId));
			emailRssDao.deleteByRssIdEmailId(param);
		}
	}
	
	/**
	 * 字符串转数组
	 * 
	 * @param rssIds
	 * @return
	 */
	private String[] getRssIds(String rssIds){
		String[] idArray = null;
		try {
			idArray = rssIds.split(",");
		} catch (Exception e) {
			e.printStackTrace();
			idArray[0] = rssIds;
		}
		return idArray;
	}
	
	

}
