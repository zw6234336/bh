package com.zhang.service.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.io.FeedException;
import com.zhang.service.BarkService;
import com.zhang.service.MailService;
import com.zhang.service.RssService;

@Service
public class BarkServiceImpl implements BarkService {

	private static final Logger logger = LoggerFactory.getLogger(BarkServiceImpl.class); 
	
	@Resource
	private MailService mailService;

	@Resource
	private RssService rssService;

	@Override
	public boolean barkSpring(String to,String from, URL url) {
		
		logger.info("发送订阅信息参数邮件接收人{},邮件发送人{},rssUrl{}",to,from,url.toString());
		@SuppressWarnings("rawtypes")
		List contendList = new ArrayList();
		String rssEncode = "";
		try {
			rssEncode = rssService.getRssEncode(url);
		} catch (Exception e) {
			logger.warn("not fount rss xml encode");
			rssEncode = "gbk";
		}
		try {
			contendList = rssService.parseXml(url, rssEncode);
		} catch (IllegalArgumentException | FeedException e) {
			logger.error("解析邮件订阅内容出错{}",e.toString());
		}
		if (contendList.size() == 0) {
			logger.error("解析邮件订阅内容为空");
			return false;
		}
		SyndContentImpl temp = (SyndContentImpl) contendList.get(0);
		logger.info("得到订阅最新内容");

		logger.info(temp.getValue());
		String contend = temp.getValue();
		mailService.send(to, from,"kindle订阅服务", contend);
		return true;
	}
}
