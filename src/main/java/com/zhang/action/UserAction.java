package com.zhang.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhang.model.Rss;
import com.zhang.model.User;
import com.zhang.querymodel.ActionModel;
import com.zhang.querymodel.RssAddModel;
import com.zhang.querymodel.UserQueryModel;
import com.zhang.service.RssService;
import com.zhang.service.UserService;


@RestController
@RequestMapping("/user/*")
@Api(value="UserAction",description="用户数据管理")
public class UserAction {

	@Resource
	private UserService userService;
	@Resource
	private RssService rssService;
	
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	@ApiOperation(value="UserQueryModel",httpMethod="POST",response=UserQueryModel.class)
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
	
	/**
	 * 订阅信息删除
	 * 
	 * @return
	 */
	@RequestMapping(path="deleteRss",method=RequestMethod.POST)
	@ResponseBody
	public ActionModel deleteRss(String rssIds,int emailId){
		ActionModel result = new ActionModel();
		
		try {
			rssService.deleteRss(rssIds, emailId);
			result.setCode(0);
			result.setMessage("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setMessage("删除失败");
		}
		return result;
	}
}
