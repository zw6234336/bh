package com.zhang.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhang.model.Rss;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-config-db.xml","classpath:config/sqlMapConfig.xml"  })
public class RssMapperTest {
	
	@Autowired
	private RssMapper dao;

	
	@Test
    public void testInsert(){
    	Rss record = new Rss();
    	record.setCreatetime(new Date().toLocaleString());
    	record.setDes("my test rss");
    	record.setRssEncode("utf-8");
    	record.setUrl("https://feeds.theguardian.com/theguardian/world/china/rss,http://blog.csdn.net/abigfrog/rss/list");
    	
    	int actual = dao.insert(record);
    	Assert.assertEquals(1, actual);
    }
	
	@Test
	public void testDelete(){
		int actual = dao.deleteByPrimaryKey(1);
		Assert.assertEquals(1, actual);
	}

}