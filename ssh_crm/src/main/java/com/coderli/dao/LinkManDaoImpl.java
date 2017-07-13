package com.coderli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.coderli.entity.LinkMan;

@SuppressWarnings("all")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {

	@Override
	public List<LinkMan> moreCondition(LinkMan linkMan) {
//		String hql ="from LinkMan where 1=1 ";
//		List<Object> list = new ArrayList<>();
//		if(linkMan.getLkmName()!=null&&!"".equals(linkMan.getLkmName())){
//			hql+=" and lkmName =? ";
//			list.add(linkMan.getLkmName());
//		}
//		if(linkMan.getCustomer().getCid()!=null&&linkMan.getCustomer().getCid()>0){
//			hql+=" and customer.cid=?";
//			list.add(linkMan.getCustomer().getCid());
//		}
//		return (List<LinkMan>) this.getHibernateTemplate().find(hql, list.toArray());
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkmName()!=null&&!"".equals(linkMan.getLkmName())){
			criteria.add(Restrictions.eq("lkmName", linkMan.getLkmName()));
		}
		if(linkMan.getCustomer().getCid()!=null&&linkMan.getCustomer().getCid()>0){
			criteria.add(Restrictions.eq("customer.cid", linkMan.getCustomer().getCid()));
		}
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	
//	public void add(LinkMan linkMan) {
//		this.getHibernateTemplate().save(linkMan);
//	}

//	public List<LinkMan> findAll() {
//		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
//	}

//	public LinkMan findOne(int linkid) {
//		return this.getHibernateTemplate().get(LinkMan.class, linkid);
//	}

//	public void update(LinkMan linkMan) {
//		this.getHibernateTemplate().update(linkMan);
//	}

}
