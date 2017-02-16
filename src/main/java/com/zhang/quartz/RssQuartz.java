package com.zhang.quartz;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.zhang.dao.RssMapper;
import com.zhang.model.EmailRss;
import com.zhang.querymodel.UserQueryModel;
import com.zhang.service.BarkService;
import com.zhang.service.UserService;
import com.zhang.service.impl.BarkServiceImpl;

@Component
public class RssQuartz {
	
	private static final Logger logger = LoggerFactory.getLogger(BarkServiceImpl.class); 

	
	@Resource
	private BarkService service;
	
	@Resource
	private UserService userService;
	
	@Resource
	private RssMapper rssDao;
	
	@Resource
	private ThreadPoolTaskExecutor pool;
	
	@Value("#{configProperties['mail.username']}")
 	private String rsss;
	
	/**
	 * 每天早上六点
	 */
	@Scheduled(cron = "0 0 6 * * ?")
//	@Scheduled(cron = "3 * * * * ?")
	public void MyQuartzRSS(){
		final UserQueryModel model = userService.getUserRssData("zhang");
		
		logger.info("定时任务开始时间:{}.用户信息{}",new Date().toLocaleString(),model.toString());
		List<EmailRss> emailRsss = model.getEmailRsss();
		for(EmailRss EmailRssModel:emailRsss){
			
			final String rssUrl = rssDao.selectByPrimaryKey(EmailRssModel.getRssId()).getUrl();
			pool.execute(new Runnable() {
				@Override
				public void run() {
					URL url = null;
					try {
						url = new URL(rssUrl);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					
					service.barkSpring(model.getUserEmails().get(0).getEmail(),rsss, url);
				}
			});
		}
	}
}
