package com.zhang.quartz;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.zhang.service.BarkService;

@Component
public class RssQuartz {

	@Resource
	private BarkService service;
	
	@Value("#{configProperties['rss.resources']}")
	private String rsss;
	
	/**
	 * 暂定只有一个接收用户
	 */
	@Value("#{configProperties['mail.recipient.username']}")
	private String recipients;
	
	@Resource
	private ThreadPoolTaskExecutor pool;
	
	/**
	 * 每天早上六点
	 */
//	@Scheduled(cron = "0 0 6 * * ?")
	@Scheduled(cron = "3 0 0 * * ?")
	public void MyQuartzRSS(){
		String[] rssArray = getArray(rsss);
		for(String str:rssArray){
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					URL url = null;
					try {
						url = new URL(str);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					service.barkSpring(recipients, url);
				}
			});
		}
	}
	
	/**
	 * 得到数组资源
	 * 
	 * @param data
	 * @return
	 */
	private String[] getArray(String data){
		String [] dataArray = {};
		try {
			dataArray = data.split(",");
		} catch (Exception e1) {
			e1.printStackTrace();
			dataArray[0] = data;
		}
		return dataArray;
	}
}
