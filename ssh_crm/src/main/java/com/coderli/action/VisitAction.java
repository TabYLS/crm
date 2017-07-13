package com.coderli.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.coderli.entity.Customer;
import com.coderli.entity.User;
import com.coderli.entity.Visit;
import com.coderli.service.CustomerService;
import com.coderli.service.UserService;
import com.coderli.service.VisitService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{

	private static final long serialVersionUID = 1L;
	//注入拜访的service
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	//注入客户的serivce
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//注入用户的service
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Visit visit=new Visit();
	@Override
	public Visit getModel() {
		// TODO Auto-generated method stub
		return visit;
	}
	/**
	 * 跳转到增加拜访记录的页面
	 * @return
	 */
	public String toAddPage(){
		//查询所有的客户
		List<Customer> listCustomer = customerService.findAll();
		//查询所有的用户
		List<User> listUser = userService.findAll();
		//放到域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		
		return "toAddPage";
	}
	/**
	 * 增加拜访记录
	 * @return
	 */
	public String addVisit(){
		visitService.addVisit(visit);
		return "addVisit";
	}
	/**
	 * 拜访记录列表
	 * @return
	 */
	public String list(){
		List<Visit> list = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	/**
	 * 跳转到修改拜访信息页面
	 * @return
	 */
	public String toUpdateVisit(){
		int vid= visit.getVid();
		Visit visit = visitService.findOne(vid);
		List<Customer> listCustomer = customerService.findAll();
		List<User> listUser = userService.findAll();
		//放到域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("visit", visit);
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		return "toUpdateVisit";
	}
	/**
	 * 修改记录信息
	 * @return
	 */
	public String updateVisit(){
		visitService.update(visit);
		return "updateVisit";
	}
	/**
	 * 删除记录信息
	 * @return
	 */
	public String deleteVisit(){
		visitService.delete(visit);
		return "deleteVisit";
	}
	public String toSelectLinkManPage(){
		//获取所有的客户信息
		List<Customer> listCustomer = customerService.findAll();
		//获取所有的用户信息
		List<User> listUser = userService.findAll();
		//放到域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		return "toSelectLinkManPage";
	}
	public String moreCondition(){
		List<Visit> list = visitService.moreCondition(visit);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
}
