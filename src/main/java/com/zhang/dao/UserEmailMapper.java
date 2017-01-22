package com.zhang.dao;

import java.util.List;

import com.zhang.model.UserEmail;

/**
 * 
 * 用户邮箱信息
 * @author zhangwei
 *
 * 2017年1月20日
 */
public interface UserEmailMapper {
	
	public int deleteByPrimaryKey(Integer id);

	public int insert(UserEmail record);

	public int insertSelective(UserEmail record);

	public UserEmail selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(UserEmail record);

	public int updateByPrimaryKey(UserEmail record);
    
    /**
     * 通过用户id得到所有的用户邮箱
     * 
     * @param userId
     * @return
     */
	public List<UserEmail> selectByUserId(Integer userId);
}