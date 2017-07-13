package com.coderli.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.coderli.entity.User;
import com.coderli.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//属性封装获取参数
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 用户登录
	 * @return 返回
	 */
	public String login(){
		//封装到实体类对象
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User userExist = userService.login(user);
		//判断
		if(userExist!=null){
			//使用session保存登录状态
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("user", userExist);
			System.out.println("登录成功");
			//登录成功
			return "success";
		}else{
			System.out.println("登录失败");
			//登录失败
			return "failed";
		}
	}
}
