package com.zhang.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.querymodel.UserQueryModel;


@Transactional(rollbackForClassName="UserServiceTest")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-config-db.xml","classpath:config/sqlMapConfig.xml"  })
public class UserServiceTest {

	@Autowired
	private UserService s;
	
	
	@Test
	public void testGetUserRssData(){
		UserQueryModel model = s.getUserRssData("zhang");
		System.out.println(model);
	}
}
