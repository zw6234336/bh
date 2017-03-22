package com.zhang.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.model.EmailRss;

@Transactional(rollbackForClassName="EmailRssMapperTest")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-config-db.xml","classpath:config/sqlMapConfig.xml"  })
public class EmailRssMapperTest{
  
	@Autowired
	private EmailRssMapper dao;
	
	
	@Test
	public void testInsert(){
		EmailRss record = new EmailRss();
		record.setCreatetime(new Date().toLocaleString());
		record.setEmailId(1);
		record.setRssId(3);
		
		int actual = dao.insert(record);
		Assert.assertEquals(1, actual);
	}
	
	@Test
	public void testUpdate(){
		EmailRss record = new EmailRss();
		record.setRssId(2);
		record.setId(1);
		dao.updateByPrimaryKeySelective(record);
	}
	
	@Test
	public void testDelete(){
		dao.deleteByPrimaryKey(13);
	}
	
}