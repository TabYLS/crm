package com.coderli.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coderli.dao.UserDao;
import com.coderli.entity.User;

@Transactional
public class UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 传递一个用户对象，检查用户账号和密码是否正确
	 * @param user传入的用户对象
	 * @return返回数据库中的用户对象
	 */
	public User login(User user) {
		//调用dao里面的方法
		return userDao.login(user);
	}
	public List<User> findAll() {
		return userDao.findAll();
	}
	
}
