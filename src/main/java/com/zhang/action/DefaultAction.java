package com.zhang.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.service.UserService;


@Controller
@RequestMapping("/*")
public class DefaultAction {

	@Resource
	private UserService userService;
	
	
	@RequestMapping("index")
	public ModelAndView go2Index(){
		ModelAndView result = new ModelAndView("index");
		return result;
	}
	
}
