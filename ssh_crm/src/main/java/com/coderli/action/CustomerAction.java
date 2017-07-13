package com.coderli.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.coderli.entity.Customer;
import com.coderli.entity.Dict;
import com.coderli.entity.PageBean;
import com.coderli.service.CustomerService;
import com.coderli.service.DictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
	//注入customerService
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	private DictService dictService;
	//注入dictService
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	//用于模型驱动接收传递参数的值
	private Customer customer = new Customer();
	//模型驱动方法
	public Customer getModel() {
		return customer;
	}
	//当前页数,此处利用struts2的值栈传值
	private Integer currentPage;
	//值栈所需的set和get方法 
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	//展示用户的信息（分页显示）
	public String listpage(){
		//调用service的方法实现封装
		PageBean pageBean = customerService.listpage(currentPage);
		//放到域对象中
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listpage";
	}
	
	// 跳转添加客户页面
	public String toAddPage() {
		//获取所有用户的等级
		List<Dict> list = dictService.findAll();
		//放到域对象
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toAddPage";
	}

	// 添加客户逻辑处理
	public String add() {
		//添加逻辑操作
		customerService.add(customer);
		return "add";
	}

	// 客户列表（不分页）
	public String list() {
		List<Customer> list =customerService.findAll();
		//放到域对象中
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	//模糊匹配逻辑处理
	public String listcondition(){
		List<Customer> list = null;
		//如果输入用户的名称，则根据客户的名称进行检索
		//如果无任何输出，则查询所有
		if(customer.getCustName()!=null&&!"".equals(customer.getCustName())){
			//不为空
			list = customerService.findCondition(customer);
		}else{
			//为空
			list=customerService.findAll();
		}
		ServletActionContext.getRequest().setAttribute("list", list);
		return "listcondition";
	}

	// 删除客户信息
	public String delete(){
		//使用模型驱动获取cid的值
		int cid = customer.getCid();
		//删除规范写法，现根据id查询然后调用方法删除
		//根据id查询
		Customer c = customerService.finOne(cid);
		//根据id查询对象是否为空
		if(c!=null){
			//调用方法删除
			customerService.delete(c);
		}
		return "delete";
	}
	//跳转到修改客户信息的页面（加载需要修改的用户信息）
	public String showCustomer(){
		//获取所有用户的等级
		List<Dict> list = dictService.findAll();
		//使用模型驱动获取cid的值
		int cid= customer.getCid();
		//根据id查询信息
		Customer c=customerService.finOne(cid);
		//域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("customer", c);
		request.setAttribute("list", list);
		return "showCustomer";
	}
	//修改用户信息的逻辑处理
	public String update(){
		customerService.update(customer);
		return "update";
	}
	
	 //跳转到客户信息综合查询页面
	public String toSelectCustomerPage(){
		//获取所有用户的等级
		List<Dict> list = dictService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectCustomerPage";
	}
	// 多条件子查询
	public String moreCondition(){
		List<Customer> list = customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}

	//根据来源统计客户的数量
	public String countSource(){
		List list = customerService.findCountSource();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	//根据用户级别统计客户的数量
	public String countLevel(){
		List list = customerService.findCountLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}
}
