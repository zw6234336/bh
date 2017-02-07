package com.zhang.service;

import com.zhang.model.User;
import com.zhang.querymodel.UserQueryModel;

/**
 * @author zhangwei
 *
 * 2017年1月21日
 */
public interface UserService {

	
	/**
	 * 更具用户名的到用户订阅信息
	 * 
	 * @param userName
	 * @return
	 */
	public UserQueryModel getUserRssData(String userName);
	
	/**
	 * 转发dao操作
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(User record);
}
