package com.zhang.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhang.model.UserEmail;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-config-db.xml","classpath:config/sqlMapConfig.xml"  })
public class UserEmailMapperTest{
  
	@Autowired
	private UserEmailMapper dao;
	
	
	@Test
	public void testInsert(){
		UserEmail record = new UserEmail();
		record.setCreatetime(new Date().toLocaleString());
		record.setEmail("zhangwei0404@sina.cn");
		record.setUserId(1);
		
		int actual = dao.insert(record);
		Assert.assertEquals(1, actual);
	}
}