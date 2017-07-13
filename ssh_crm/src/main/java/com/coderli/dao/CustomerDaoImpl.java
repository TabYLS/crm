package com.coderli.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.coderli.entity.Customer;

@SuppressWarnings("all")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	/**
	 * 客户添加功能
	 */
	// public void add(Customer customer) {
	// this.getHibernateTemplate().save(customer);
	// }

	/**
	 * 客户列表的功能
	 */
	// public List<Customer> findAll() {
	// return (List<Customer>) this.getHibernateTemplate().find("from
	// Customer");
	// }

	// 根据id进行查询
	// public Customer findOne(int cid) {
	// return this.getHibernateTemplate().get(Customer.class, cid);
	// }

	// 根据用户信息删除用户
	// public void delete(Customer c) {
	// this.getHibernateTemplate().delete(c);
	// }

	// 根据用户信息修改用户信息
	// public void update(Customer customer) {
	// this.getHibernateTemplate().update(customer);
	// }

	// 查询总记录数
	public int findCount() {
		// 调用hibernateTemplate里面的find方法实现
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		// 从list中拿到值
		if (list != null && list.size() != 0) {
			Object obj = list.get(0);
			// 变成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	// 分页查询操作
	public List<Customer> findPage(int begin, int pageSize) {
		// 第一种，使用hibernate底层代码实现（了解）
		// 得到sessionFactory
		// SessionFactory sessionFactory =
		// this.getHibernateTemplate().getSessionFactory();
		// session对象
		// Session session = sessionFactory.getCurrentSession();
		// Query query = session.createQuery("from Customer");
		// 设置分页信息
		// query.setFetchSize(begin);
		// query.setFetchSize(pageSize);
		// List<Customer> list = query.list();

		// 第二种方式，使用离线对象和hibernateTemplate方法实现
		// 1.创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 调用hibernateTemplate的方法实现
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	// 条件查询
	public List<Customer> findCondition(Customer customer) {
		List<Customer> list = null;
		// 第一种方式
		// SessionFactory sessionFactory =
		// this.getHibernateTemplate().getSessionFactory();
		// 获取session对象
		// Session session = sessionFactory.getCurrentSession();
		// Query query = session.createQuery("from Customer where custName like
		// ?");
		// query.setParameter(0, "%"+customer.getCustName()+"%");
		// list= query.list();
		// 第二种方式,调用hibernateTemplate中的find方法实现
		list = (List<Customer>) this.getHibernateTemplate().find("from Customer where custName like ? ",
				"%" + customer.getCustName() + "%");
		// 第三种实现方式
		// 创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria ciCriteria = DetachedCriteria.forClass(Customer.class);
		// 通过离线对象获取数据的条数
		// ciCriteria.setProjection(Projections.rowCount());
		// 设置对试题类的哪个属性进行操作
		ciCriteria.add(Restrictions.ilike("custName", "%" + customer.getCustName() + "%"));
		// 调用hibernateTemplate中的方法获取到list集合
		list = (List<Customer>) this.getHibernateTemplate().findByCriteria(ciCriteria);
		return list;
	}

	// 多条件组合查询
	public List<Customer> findMoreCondition(Customer customer) {
		// 法一：利用底层sql实现
		// 法二：通过hibernate的find方法实现
		// 拼接hql
		// String hql = "from Customer where 1=1";
		// 创建list集合，如果值不为空，把值设置到list集合中去
		// List<Object> list = new ArrayList<>();
		// 判断值是否为空，不为空拼接hql
		// if(customer.getCustName()!=null&&!"".equals(customer.getCustName())){
		// hql+=" and custName=? ";
		// list.add(customer.getCustName());
		// }
		// if(customer.getCustLevel()!=null&&!"".equals(customer.getCustLevel())){
		// hql+=" and custLevel=?";
		// list.add(customer.getCustLevel());
		// }
		// if(customer.getCustSource()!=null&&!"".equals(customer.getCustSource())){
		// hql+=" and custSource=?";
		// list.add(customer.getCustSource());
		// }
		// return (List<Customer>) this.getHibernateTemplate().find("from
		// Customer", list.toArray());
		// 法三：利用离线对象实现
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if (customer.getCustName() != null && !"".equals(customer.getCustName())) {
			criteria.add(Restrictions.eq("custName", customer.getCustName()));
		}
//		if (customer.getCustLevel() != null && !"".equals(customer.getCustLevel())) {
//			criteria.add(Restrictions.eq("custLevel",customer.getCustLevel()));
//		}
		if(customer.getDictCustLevel().getDid()!=null&&Integer.parseInt(customer.getDictCustLevel().getDid())>0){
			criteria.add(Restrictions.eq("dictCustLevel.did", customer.getDictCustLevel().getDid()));
		}
		if (customer.getCustSource() != null && !"".equals(customer.getCustSource())) {
			criteria.add(Restrictions.eq("custSource",customer.getCustSource()));
		}
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List findCountSource() {
		//sql语句较复杂，hql无法实现，只能调用底层sql实现
		//得到sessionFactory对象
		Session session = this.getSessionFactory().getCurrentSession();
//		创建SQLQuery对象
		String sql="select count(*) as num,custSource from t_customer group by custSource";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		//放到实体类中去
//		sqlQuery.addEntity(arg0)
		//因为返回值有两个字段，一个字段是id,一个是名称，所以把返回数据转换为map结构
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		//调用方法得到结果 返回的list默认每部分是数组形式
		List list = sqlQuery.list();
		return list;
	}

	@Override
	public List findCountLevel() {
		//获取session对象
		Session session = this.getSessionFactory().getCurrentSession();
		//创建sqlquery对象
		String sql="select c.num,d.dname from(select count(*) as num,custLevel from t_customer group by custLevel) c ,t_dict d where c.custLevel=d.did ";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		//得到结果
		//转换得到结果
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = sqlQuery.list();
		return list;
	}
}
