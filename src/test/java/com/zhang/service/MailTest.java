package com.zhang.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.syndication.io.FeedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring-config.xml" })
public class MailTest {

	private static String to = "";
	private static String to1 = "";
	private static String from = "";

	@Autowired
	private MailService mailUtil;

	@Autowired
	private RssService rssService;

	@Test
	public void sendMassTest() throws MalformedURLException,IllegalArgumentException, FeedException {
		URL url = new URL("");
		rssService.parseXml(url, "utf-8");

		String[] recipients = { to, to1};
		mailUtil.send(recipients, from, "This is a test mass mail", "Hello Mass!");
	}
}
