package com.zhang.dao;

import java.util.List;
import java.util.Map;

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

    public int updateByPrimaryKey(EmailRss record);
    
    /**
     * 
     * 通过邮箱查询订阅url
     * @param email
     * @return
     */
   public  List<EmailRss> selectByEmail(Integer email_id);
    
    /**
     * 根据订阅信息邮箱id删除邮箱订阅关系表信息
     * 
     * @param rssId
     * @return
     */
    public int deleteByRssIdEmailId(Map<String,Integer> param);
}