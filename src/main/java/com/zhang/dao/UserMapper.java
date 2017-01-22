package com.zhang.dao;

import com.zhang.model.User;

public interface UserMapper {
	
	public int deleteByPrimaryKey(Integer id);

	public int insert(User record);

	public int insertSelective(User record);

	public User selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(User record);

	public int updateByPrimaryKey(User record);
    
    /**
     * 根据用户名查询用户信息
     * 
     * @param userName
     * @return
     */
	public User selectByUserName(String userName);
}