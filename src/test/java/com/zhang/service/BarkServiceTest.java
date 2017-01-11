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
	public void testBarkJava() throws MalformedURLException, MessagingException {
		URL url = new URL("https://feeds.theguardian.com/theguardian/world/china/rss");
		boolean actual = service.barkJava("**@sina.cn", url);
		Assert.assertEquals(true, actual);
	}
}
