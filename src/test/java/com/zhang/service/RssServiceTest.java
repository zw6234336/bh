package com.zhang.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.syndication.io.FeedException;
import com.zhang.service.RssService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring-config.xml" })
public class RssServiceTest {

	@Autowired
	private RssService s;

	@Test
	public void testParseXml() throws MalformedURLException,
			IllegalArgumentException, FeedException {
		URL url = new URL("http://blog.csdn.net/abigfrog/rss/list");
		s.parseXml(url);
	};

}
