package com.coderli.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coderli.dao.DictDao;
import com.coderli.entity.Dict;

@Transactional
public class DictService {
	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}
	
	public List<Dict> findAll(){
		return dictDao.findAll();
	}
	
}
