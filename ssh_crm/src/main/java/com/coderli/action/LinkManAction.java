package com.coderli.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.coderli.entity.Customer;
import com.coderli.entity.LinkMan;
import com.coderli.service.CustomerService;
import com.coderli.service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private static final long serialVersionUID = 1L;
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	//注入customerService
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/**
	 * 需要上传的文件（流）
	 * 需要上传的文件名称
	 * （1）在action里面成员变量位置定义变量
	 * 		-一个代表上传文件
	 * 		-一个代表上传文件名称
	 * （2）生成变量的get和set方法
	 */
	//1.上传文件
	//变量名称需要是表单里面文件上传的name值
	private File upload;
	//2.上传文件名称，表单里面文件上传项的name值FileName
	private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	
	//到新增联系人的方法
	public String toAddPage() {
		//查询所有的用户，把所有用户list集合传递到页面显示（放到域对象）
		List<Customer> list = customerService.findAll();
		//放到域对象
		ServletActionContext.getRequest().setAttribute("listCustomer", list);
		return "toAddPage";
	}
	
	public String toAddLinkMan(){
		if(upload!=null){
			//上传代码
			//在服务器文件夹创建文件
			File serverFile = new File("D:/fileTest/"+uploadFileName);
			try {
				FileUtils.copyFile(upload, serverFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		linkManService.add(linkMan);
		return "toAddLinkMan";
	}
	
	public String toErrorPage(){
		return "input";
	}
	/**
	 * 联系人列表
	 * @return
	 */
	public String list(){
		List<LinkMan> list = linkManService.listLinkMan();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	/**
	 * 到修改人页面
	 * @return
	 */
	public String showLinkMan(){
		//使用模型驱动获取id值
		int linkid=linkMan.getLinkid();
		//根据id查询对象
		LinkMan link = linkManService.findOneById(linkid);
		//需要所有客户的list集合
		List<Customer> listCustomer = customerService.findAll();
		//放到域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("linkman", link);
		request.setAttribute("listCustomer", listCustomer);
		
		return "showLinkMan";
	}
	/**
	 * 修改联系人信息
	 * @return
	 */
	public String updateLinkMan(){
		linkManService.updateLinkMan(linkMan);
		return "updateLinkMan";
	}
	public String delLinkMan(){
		linkManService.deleteLinkMan(linkMan);
		return "delLinkMan";
	}
	/**
	 * 跳转到联系人多条件查询页面
	 * @return
	 */
	public String toSelectLinkManPage(){
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectLinkManPage";
	}
	
	public String moreCondition(){
		System.out.println(linkMan);
		List<LinkMan> list = linkManService.moreCondition(linkMan);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
}
