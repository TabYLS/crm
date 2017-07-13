package com.coderli.dao;


import java.util.List;

import com.coderli.entity.Visit;

public interface VisitDao extends BaseDao<Visit>{

	List<Visit> moreCondition(Visit visit);

//	void add(Visit visit);

//	List<Visit> findAll();

//	void update(Visit visit);

//	void delete(Visit visit);

//	Visit findOne(int vid);

}
