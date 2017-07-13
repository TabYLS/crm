package com.coderli.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coderli.dao.CustomerDao;
import com.coderli.entity.Customer;
import com.coderli.entity.PageBean;

@Transactional
public class CustomerService {
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * 添加客户的功能
	 * 
	 * @param customer
	 *            客户的信息
	 */
	public void add(Customer customer) {
		this.customerDao.add(customer);
	}

	/**
	 * 客户列表的功能
	 * 
	 * @return 返回所有的用户信息
	 */
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public Customer finOne(int cid) {
		return customerDao.findOne(cid);
	}

	public void delete(Customer c) {
		customerDao.delete(c);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}

	// 封装分页数据到pagebean对象中
	public PageBean listpage(Integer currentPage) {
		// 创建pagebean的一个对象
		PageBean pageBean = new PageBean();
		// 当前页
		pageBean.setCurrentPage(currentPage);
		// 总的记录数
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);

		// 每页显示的记录数
		int pageSize = 3;
		
		//总页数 总的记录数/每页记录数
		int totalPage=0;
		if(totalCount%pageSize==0){
			//能整除
			totalPage=totalCount/pageSize;
		}else{
			totalPage=totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		
		//开始的位置
		int begin =(currentPage-1)*pageSize;
		
		//每页的list集合
		List<Customer> list = customerDao.findPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	public List<Customer> findCondition(Customer customer) {
		return customerDao.findCondition(customer);
	}

	public List<Customer> findMoreCondition(Customer customer) {
		return customerDao.findMoreCondition(customer);
	}

	public List findCountSource() {
		return customerDao.findCountSource();
	}

	public List findCountLevel() {
		return customerDao.findCountLevel();
	}

}
