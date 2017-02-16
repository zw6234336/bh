package com.zhang.dao;

import java.util.Map;

import com.zhang.model.Rss;

/**
 * 记录订阅信息
 * 
 * @author zhangwei
 *
 * 2017年1月20日
 */
public interface RssMapper {
	
    public int deleteByPrimaryKey(Integer id);

    public int insert(Rss record);

    public int insertSelective(Rss record);

    public Rss selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Rss record);

    public int updateByPrimaryKey(Rss record);
    
    /**
     * 通过订阅url更新编码
     * @param param
     * @return
     */
    public int updateEncodeByRssUrl(Map<String,String> param);
    
    /**
     * 通过rss url得到文章编码信息
     * 
     * @param url
     * @return
     */
    public String selectCodeByUrl(String url);
    
}