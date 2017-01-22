package com.zhang.dao;

import java.util.List;

import com.zhang.model.EmailRss;

/**
 * 邮箱和订阅信息设计成多对多
 * 可以降级为一对一
 * 或者一对多
 * 
 * @author zhangwei
 *
 * 2017年1月21日
 */
public interface EmailRssMapper {
	
	public int deleteByPrimaryKey(Integer id);

	public int insert(EmailRss record);

	public int insertSelective(EmailRss record);

	public EmailRss selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(EmailRss record);

    int updateByPrimaryKey(EmailRss record);
    
    /**
     * 
     * 通过邮箱查询订阅url
     * @param email
     * @return
     */
    List<EmailRss> selectByEmail(Integer email_id);
}