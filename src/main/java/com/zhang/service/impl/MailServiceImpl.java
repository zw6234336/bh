package com.zhang.service.impl;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zhang.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Resource
	private MailSender mailSender;

	@Resource
	private JavaMailSender javaMailSender;

	@Override
	public void send(String to, String from, String subject, String content) {
		sendHtml(to, from, subject, content);
	}

	/**
	 * 单发
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param content
	 */
	private void sendHtml(String to, String from, String subject, String content) {

		logger.info("发送邮件");
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom(from);
			helper.setText(content, true); // 邮件内容，注意加参数true，表示启用html格式
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			logger.error("发送邮件出错{}",e.toString());
		}

	}

	@Override
	public void send(String[] to, String from, String subject, String content) {
		sendHtml(to, from, subject, content);
	}

	/**
	 * 群发
	 * 
	 * @param recipient
	 * @param subject
	 * @param content
	 */
	private void sendHtml(String[] to, String from, String subject,String content) {

		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom(from);
			helper.setText(content, true); // 邮件内容，注意加参数true，表示启用html格式
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			logger.warn("MessagingException throws exception");
		}

	}

}
