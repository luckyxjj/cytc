package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.HomeImgMapper;
import com.ssm.pojo.HomeImg;
import com.ssm.service.IHomeImgService;

@Service("homeImgService")
public class HomeImgService implements IHomeImgService {

	@Resource
	private HomeImgMapper homeImgDao;

	@Override
	public HomeImg getHomeImgById(int id) {
		return homeImgDao.selectByPrimaryKey(id);
	}

	@Override
	public int addHomeImg(HomeImg homeImg) {
		return homeImgDao.insertSelective(homeImg);
	}

	@Override
	public int updateHomeImg(HomeImg homeImg) {
		return homeImgDao.updateByPrimaryKeySelective(homeImg);
	}

	@Override
	public int deleteHomeImg(int id) {
		return homeImgDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<HomeImg> getAllHomeImg() {
		return homeImgDao.selectAll();
	}

}
