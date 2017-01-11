package com.zhang.service.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.io.FeedException;
import com.zhang.service.BarkService;
import com.zhang.service.SpringMailService;
import com.zhang.service.RssService;

@Service
public class BarkServiceImpl implements BarkService {

	@Resource
	private SpringMailService mailService;
	
	@Resource
	private RssService rssService;
	
	@Override
	public boolean barkSpring(String recipient, URL url) {
		@SuppressWarnings("rawtypes")
		List contendList = new ArrayList();
		try {
			contendList = rssService.parseXml(url);
		} catch (IllegalArgumentException | FeedException e) {
			e.printStackTrace();
		}
		
		System.out.println(contendList.size());
		if(contendList.size()==0){
			return false;
		}
		SyndContentImpl temp = (SyndContentImpl)contendList.get(0);
		
		System.out.println(temp.getValue());
		String contend = temp.getValue();
		mailService.send(recipient, "狗耳朵订阅服务",  "<html><head></head><body>"+contend+"</body></html>");
		return true;
	}

	@Override
	public boolean barkJava(String recipient, URL url) throws MessagingException {
		@SuppressWarnings("rawtypes")
		List contendList = new ArrayList();
		try {
			contendList = rssService.parseXml(url);
		} catch (IllegalArgumentException | FeedException e) {
			e.printStackTrace();
		}
		
		System.out.println(contendList.size());
		if(contendList.size()==0){
			return false;
		}
		SyndContentImpl temp = (SyndContentImpl)contendList.get(0);
		
		String contend = temp.getValue();
		mailService.sendJava(recipient, "狗耳朵订阅服务",  "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body>"+contend+"</body></html>");
		return true;
	}
	
	/**
	 * 处理编码问题
	 * 
	 * @param contend
	 */
	private void handlerCode(String contend){
		
	}

}
