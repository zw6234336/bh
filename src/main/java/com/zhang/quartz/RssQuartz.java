package com.zhang.quartz;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhang.service.BarkService;

@Component
public class RssQuartz {

	@Resource
	private BarkService service;
	
	@Scheduled(cron = "3 * * * * ?")
	public void MyQuartzRSS(){
		URL url = null;
		try {
			url = new URL("https://feeds.theguardian.com/theguardian/world/china/rss");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		service.barkSpring("**@sina.cn", url);
	}
}
