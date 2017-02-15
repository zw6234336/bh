package com.zhang.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.zhang.httpclient.HttpConnectionManager;
import com.zhang.querymodel.RssAddModel;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-config-db.xml","classpath:config/sqlMapConfig.xml"  })
public class RssServiceTest {

	@Autowired
	private RssService s;

	@Test
	public void testParseXml() throws Exception {
		URL url = new URL("http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss");
		String actual = s.getRssEncode(url);
		List list = s.parseXml(url,actual);
	}
	
	@Test
	public void testGetRssEncode() throws Exception{
		URL url = new URL("http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss");
		String actual = s.getRssEncode(url);
		Assert.assertEquals("utf-8", actual);
	}
	
	/**
	 * 新增订阅信息测试
	 * 
	 */
	@Test
	public void TestRssAdd(){
		RssAddModel record = new RssAddModel();
		record.setDes("测试描述");
		record.setEmailId(1);
		record.setUrl("http://ceshi.com");
		s.rssAdd(record);
	}
	
	public static void main(String[] args) throws IllegalArgumentException, FeedException, IOException {
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = null;
		
		HttpConnectionManager http = new HttpConnectionManager();
    	InputStream result =  http.getExecute("https://raw.githubusercontent.com/RickyWong33/Machine_Learning_RSS/master/weixin/AI/jiqizhixin.xml");
    	
    	feed = input.build(new XmlReader(result));
    	List<?> entries = feed.getEntries();// 得到所有的标题<title></title>
    	SyndEntry entry = (SyndEntry) entries.get(0);
    	System.out.println(entry.getContents());
	}

}
