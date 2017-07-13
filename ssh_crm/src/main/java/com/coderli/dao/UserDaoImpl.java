package com.coderli.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.coderli.entity.User;

//public class UserDaoImpl implements UserDao {
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	// private HibernateTemplate hibernateTemplate;
	// public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	// this.hibernateTemplate = hibernateTemplate;
	// }

	@Override
	@SuppressWarnings("all")
	public User login(User user) {
		// 调用方法得到hibernateTemplate对象
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		// 登录的查询操作
		// 根据用户名和密码查询
		System.out.println("用户输入的用户和密码:"+user);
		List<User> list = (List<User>) hibernateTemplate.find("from User where username=? and password=?",
				user.getUsername(), user.getPassword());
		// 返回user对象
		// 判断
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

//	public List<User> findAll() {
//		return (List<User>) this.getHibernateTemplate().find("from User");
//	}

}
