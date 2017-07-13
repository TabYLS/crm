package com.coderli.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coderli.dao.VisitDao;
import com.coderli.entity.Visit;

@Transactional
public class VisitService {
	private VisitDao visitDao;
	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}
	public void addVisit(Visit visit) {
		visitDao.add(visit);
	}
	public List<Visit> findAll() {
		return visitDao.findAll();
	}
	public void update(Visit visit) {
		visitDao.update(visit);
	}
	public void delete(Visit visit) {
		visitDao.delete(visit);
	}
	public Visit findOne(int vid) {
		return visitDao.findOne(vid);
	}
	public List<Visit> moreCondition(Visit visit) {
		return visitDao.moreCondition(visit);
	}
	
}
