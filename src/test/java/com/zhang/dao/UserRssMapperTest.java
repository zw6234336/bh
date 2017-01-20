package com.zhang.dao;

import java.util.Date;



import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhang.model.UserRss;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-config-db.xml","classpath:config/sqlMapConfig.xml"  })
public class UserRssMapperTest {
	
	
	@Autowired
	private UserRssMapper dao;

	public void testInsert(){
		UserRss record = new UserRss();
		record.setUserId(1);
		record.setRssId(1);
		record.setCreatetime(new Date().toLocaleString());
		int actual = dao.insert(record);
		Assert.assertEquals(1, actual);
		
	}

	

}