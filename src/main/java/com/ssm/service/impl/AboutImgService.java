package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.AboutImgMapper;
import com.ssm.pojo.AboutImg;
import com.ssm.service.IAboutImgService;

@Service("aboutImgService")
public class AboutImgService implements IAboutImgService {

	@Resource
	private AboutImgMapper aboutImgDao;

	@Override
	public AboutImg getAboutImgById(int id) {
		return aboutImgDao.selectByPrimaryKey(id);
	}

	@Override
	public int addAboutImg(AboutImg aboutImg) {
		return aboutImgDao.insertSelective(aboutImg);
	}

	@Override
	public int updateAboutImg(AboutImg aboutImg) {
		return aboutImgDao.updateByPrimaryKeySelective(aboutImg);
	}

	@Override
	public int deleteAboutImg(int id) {
		return aboutImgDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<AboutImg> getByAboutId(int id) {
		return aboutImgDao.selectByAboutId(id);
	}

	@Override
	public int deleteByAboutId(int id) {
		return aboutImgDao.deleteByAboutId(id);
	}

}
