package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.AboutMapper;
import com.ssm.pojo.About;
import com.ssm.service.IAboutService;

@Service("aboutService")
public class AboutService implements IAboutService{

	@Resource
	private AboutMapper aboutDao;
	
	@Override
	public About getAboutById(int id) {
		return aboutDao.selectByPrimaryKey(id);
	}

	@Override
	public int addAbout(About about) {
		return aboutDao.insertSelective(about);
	}

	@Override
	public int updateAbout(About about) {
		return aboutDao.updateByPrimaryKeySelective(about);
	}

	@Override
	public int deleteAbout(int id) {
		return aboutDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<About> getAllAbout() {
		return aboutDao.selectAll();
	}

}
