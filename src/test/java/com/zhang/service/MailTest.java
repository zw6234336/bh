package com.zhang.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.syndication.io.FeedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring-config.xml" })
public class MailTest {

	@Autowired
	private SpringMailService mailUtil;

	@Autowired
	private RssService rssService;

	@Test
	public void sendMassTest() throws MalformedURLException,
			IllegalArgumentException, FeedException {
		URL url = new URL("");
		rssService.parseXml(url);

		List<String> recipients = new ArrayList<String>();
		recipients.add("**@sina.cn");
		recipients.add("**@qq.com");
		mailUtil.send(recipients, "This is a test mass mail", "Hello Mass!");
	}

	public static void main(String[] args) throws Exception {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost("smtp.126.com");

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

		// 设置收件人，寄件人
		messageHelper.setTo("**@sina.cn");
		messageHelper.setFrom("**@126.com");
		messageHelper.setSubject("测试HTML邮件！");
		// true 表示启动HTML格式的邮件
		messageHelper
				.setText(
						"<html><head></head><body><h1>hello!!spring html Mail</h1></body></html>",
						true);

		senderImpl.setUsername("**@126.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("**"); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);

		System.out.println("邮件发送成功..");
	}
}
