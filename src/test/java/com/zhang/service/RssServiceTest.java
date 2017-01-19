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

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.zhang.httpclient.HttpConnectionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring-config.xml" })
public class RssServiceTest {

	@Autowired
	private RssService s;

	@Test
	public void testParseXml() throws Exception {
		URL url = new URL("http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss");
		String encode = s.getRssEncode(url);
		s.parseXml(url,encode);
	}
	
	@Test
	public void testGetRssEncode() throws Exception{
		URL url = new URL("http://beckrabbit.iteye.com/rss");
		String actual = s.getRssEncode(url);
		Assert.assertEquals("utf-8", actual);
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
