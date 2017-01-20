package com.zhang.dao;

import com.zhang.model.Rss;

/**
 * 记录订阅信息
 * 
 * @author zhangwei
 *
 * 2017年1月20日
 */
public interface RssMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Rss record);

    int insertSelective(Rss record);

    Rss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rss record);

    int updateByPrimaryKey(Rss record);
}