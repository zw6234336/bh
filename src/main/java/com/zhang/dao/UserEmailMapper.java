package com.zhang.dao;

import com.zhang.model.UserEmail;

/**
 * 
 * 用户邮箱信息
 * @author zhangwei
 *
 * 2017年1月20日
 */
public interface UserEmailMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(UserEmail record);

    int insertSelective(UserEmail record);

    UserEmail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEmail record);

    int updateByPrimaryKey(UserEmail record);
}