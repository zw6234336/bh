package com.zhang.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zhang.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-config-db.xml","classpath:config/sqlMapConfig.xml"  })
public class UserMapperTest {

	@Autowired
	private UserMapper dao;
	
	@Test
	public void testInsert() {
		User record = new User();
		record.setUserName("zhang");
		record.setPhone("13426567677");
		record.setPwd("123456");
		record.setCreatetime(new Date().toLocaleString());
		
		dao.insert(record);
	}

	
	@Test
	public void testSelect(){
		User user = dao.selectByPrimaryKey(1);
		System.out.println(user);
	}
	
	@Test
	public void testDelete(){
		int actual = dao.deleteByPrimaryKey(3);
		Assert.assertEquals(1, actual);
		
	}
	
}