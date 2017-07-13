package com.coderli.dao;


import com.coderli.entity.User;

public interface UserDao extends BaseDao<User>{

	User login(User user);

//	List<User> findAll();

}
