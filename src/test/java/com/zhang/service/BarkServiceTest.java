package com.zhang.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring-config.xml" })
public class BarkServiceTest {

	@Autowired
	private BarkService service;

	
	@Test
	public void testBarkSpring() throws MalformedURLException, MessagingException {
		URL url = new URL("http://www.ftchinese.com/rss/feed");
//		URL url = new URL("http://coolshell.cn/feed");
		boolean actual = service.barkSpring("zhangwei0404@sina.cn","fh_jenkins@126.com", url);
		Assert.assertEquals(true, actual);
	}
}
