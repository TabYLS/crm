package com.coderli.dao;



import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.coderli.entity.Visit;

public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao{

	@Override
	public List<Visit> moreCondition(Visit visit) {
//		String hql="from Visit where 1=1 ";
//		List<Object> list =new ArrayList<>();
//		if(visit.getCustomer().getCid()!=null&&visit.getCustomer().getCid()>0){
//			hql+="and customer.cid = ? ";
//			list.add(visit.getCustomer().getCid());
//		}
//		if(visit.getUser().getUid()!=null&&visit.getUser().getUid()>0){
//			hql+="and user.uid=?";
//			list.add(visit.getUser().getUid());
//		}
//		return (List<Visit>) this.getHibernateTemplate().find(hql, list.toArray());
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		if(visit.getCustomer().getCid()!=null&&visit.getCustomer().getCid()>0){
			criteria.add(Restrictions.eq("customer.cid", visit.getCustomer().getCid()));
		}
		if(visit.getUser().getUid()!=null&&visit.getUser().getUid()>0){
			criteria.add(Restrictions.eq("user.uid", visit.getUser().getUid()));
		}
		return (List<Visit>) this.getHibernateTemplate().findByCriteria(criteria);
	}

//	public void add(Visit visit) {
//		this.getHibernateTemplate().save(visit);
//	}

//	public List<Visit> findAll() {
//		return (List<Visit>) this.getHibernateTemplate().find("from Visit");
//	}

//	public void update(Visit visit) {
//		this.getHibernateTemplate().update(visit);
//	}

//	public void delete(Visit visit) {
//		this.getHibernateTemplate().delete(visit);
//	}

//	public Visit findOne(int vid) {
//		return this.getHibernateTemplate().get(Visit.class, vid);
//	}
	
}
