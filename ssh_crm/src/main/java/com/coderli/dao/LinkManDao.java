package com.coderli.dao;


import java.util.List;

import com.coderli.entity.LinkMan;

public interface LinkManDao extends BaseDao<LinkMan>{

	List<LinkMan> moreCondition(LinkMan linkMan);

//	void add(LinkMan linkMan);

//	List<LinkMan> findAll();

//	LinkMan findOne(int linkid);

//	void update(LinkMan linkMan);

}
