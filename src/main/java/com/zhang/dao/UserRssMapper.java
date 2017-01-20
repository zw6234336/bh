package com.zhang.dao;

import com.zhang.model.UserRss;

/**
 * 
 * 用户订阅信息关系
 * 
 * @author zhangwei
 *
 *         2017年1月20日
 */
public interface UserRssMapper {
	
	int deleteByPrimaryKey(Integer id);

	int insert(UserRss record);

	int insertSelective(UserRss record);

	UserRss selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserRss record);

	int updateByPrimaryKey(UserRss record);
}