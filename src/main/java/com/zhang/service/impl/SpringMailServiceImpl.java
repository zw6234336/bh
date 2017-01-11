package com.zhang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zhang.service.SpringMailService;

@Service
public class SpringMailServiceImpl implements SpringMailService {

	@Resource
	private MailSender mailSender;

	@Resource
	private SimpleMailMessage simpleMailMessage;

	@Override
	public void send(String recipient, String subject, String content) {
		simpleMailMessage.setTo(recipient);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(content);
		simpleMailMessage.setSentDate(new Date());
		mailSender.send(simpleMailMessage);
	}

	@Override
	public void send(List<String> recipients, String subject, String content) {
		simpleMailMessage
				.setTo(recipients.toArray(new String[recipients.size()]));
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(content);
		mailSender.send(simpleMailMessage);
	}

	@Override
	public void sendJava(String recipient, String subject, String content) throws MessagingException {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost("smtp.126.com");

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

		// 设置收件人，寄件人
		messageHelper.setTo("**@sina.cn");
		messageHelper.setFrom("**@126.com");
		messageHelper.setSubject("kindle订阅服务");
		// true 表示启动HTML格式的邮件
		
		//乱码
		 
         
		
		messageHelper.setText(
						"<html><head></head><body>"+content+"</body></html>",
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
