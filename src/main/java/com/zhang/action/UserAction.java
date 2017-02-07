package com.zhang.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.model.User;
import com.zhang.querymodel.ActionModel;
import com.zhang.querymodel.UserQueryModel;
import com.zhang.service.UserService;


@Controller
@RequestMapping("/user/*")
public class UserAction {

	@Resource
	private UserService userService;
	
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public UserQueryModel initUser(String userName){
		System.out.println("aaaaaaaaaaaa");
		return userService.getUserRssData(userName);
	}
	
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public ActionModel updateUser(User user){
		ActionModel result = new ActionModel();
		int code;
		try {
			code = userService.updateByPrimaryKeySelective(user);
			if(code==1){
				result.setCode(0);
				result.setMessage("修改成功");
			}else{
				result.setCode(1);
				result.setMessage("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setMessage("修改失败");
		}
		
		return result;
	}
	
}
