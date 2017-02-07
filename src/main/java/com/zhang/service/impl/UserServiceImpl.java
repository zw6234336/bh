package com.zhang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhang.dao.EmailRssMapper;
import com.zhang.dao.UserEmailMapper;
import com.zhang.dao.UserMapper;
import com.zhang.model.EmailRss;
import com.zhang.model.User;
import com.zhang.model.UserEmail;
import com.zhang.querymodel.UserQueryModel;
import com.zhang.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserMapper userDao;
	
	@Resource
	private UserEmailMapper userEmailDao;
	
	@Resource
	private EmailRssMapper emailRssDao;

	@Override
	public UserQueryModel getUserRssData(String userName) {
		UserQueryModel result = new UserQueryModel();
		
		User user = userDao.selectByUserName(userName);
		if(user==null){
			return result;
		}
		logger.info("当前用户信息：{}",user.toString());
		result.setUser(user);
		
		List<UserEmail> userEmails = userEmailDao.selectByUserId(user.getId());
		if(userEmails==null){
			return result;
		}
		logger.info("当前用户email信息：{}",userEmails.toString());
		result.setUserEmails(userEmails);
		
		for(UserEmail model:userEmails){
			 List<EmailRss> emailRsss = emailRssDao.selectByEmail(model.getId());
			 if(emailRsss!=null){
				 result.setEmailRsss(emailRsss);
			 }
		}
		
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userDao.updateByPrimaryKeySelective(record);
	}   
	
}
