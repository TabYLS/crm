package com.coderli.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.coderli.dao.LinkManDao;
import com.coderli.entity.LinkMan;

@Transactional
public class LinkManService {
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	public void add(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	public List<LinkMan> listLinkMan() {
		return linkManDao.findAll();
	}

	public LinkMan findOneById(int linkid) {
		return linkManDao.findOne(linkid);
	}

	public void updateLinkMan(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	public void deleteLinkMan(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}

	public List<LinkMan> moreCondition(LinkMan linkMan) {
		return linkManDao.moreCondition(linkMan);
	}

	
}
