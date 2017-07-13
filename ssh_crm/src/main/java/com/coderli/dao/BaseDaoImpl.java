package com.coderli.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	private Class pClass;
	
	public BaseDaoImpl() {
		//得到当前运行类的class
		Class clazz = this.getClass();
		//得到当前类的父类的参数化类型
		Type type = clazz.getGenericSuperclass();
		ParameterizedType ptype=(ParameterizedType) type;
		//得到实际类型参数
		Type[] types = ptype.getActualTypeArguments();
		//type接口的实现类是class
		Class tClass = (Class) types[0];
		this.pClass=tClass;
	}
	
	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findOne(int id) {
		return (T) this.getHibernateTemplate().get(this.pClass, id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+pClass.getSimpleName());
	}

}
