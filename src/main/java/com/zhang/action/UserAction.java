package com.zhang.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhang.model.Rss;
import com.zhang.model.User;
import com.zhang.querymodel.ActionModel;
import com.zhang.querymodel.RssAddModel;
import com.zhang.querymodel.UserQueryModel;
import com.zhang.service.RssService;
import com.zhang.service.UserService;


@Controller
@RequestMapping("/user/*")
public class UserAction {

	@Resource
	private UserService userService;
	@Resource
	private RssService rssService;
	
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public UserQueryModel initUser(String userName){
		return userService.getUserRssData(userName);
	}
	
	@RequestMapping("/getRssInfo")
	@ResponseBody
	public List<Rss> getRssInfo(int id){
		return rssService.selectRssByEmailId(id);
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
	
	/**
	 * 新增订阅信息
	 * 
	 * 包括邮箱订阅关系信息
	 * 
	 * @return
	 */
	@RequestMapping(path = "addRss",method=RequestMethod.POST)
	@ResponseBody
	public ActionModel AddRss(RssAddModel request){
		ActionModel result = new ActionModel();
		try {
			rssService.rssAdd(request);
			result.setCode(0);
			result.setMessage("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setMessage("修改失败");
		}
		return   result;
	}
	
}
