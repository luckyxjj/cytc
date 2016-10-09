package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.BusinessImgMapper;
import com.ssm.pojo.BusinessImg;
import com.ssm.service.IBusinessImgService;

@Service("businessImgService")
public class BusinessImgService implements IBusinessImgService{

	@Resource
	private BusinessImgMapper businessImgDao;
	@Override
	public BusinessImg getBusinessImgById(int id) {
		return businessImgDao.selectByPrimaryKey(id);
	}

	@Override
	public int addBusinessImg(BusinessImg businessImg) {
		return businessImgDao.insertSelective(businessImg);
	}

	@Override
	public int updateBusinessImg(BusinessImg businessImg) {
		return businessImgDao.updateByPrimaryKeySelective(businessImg);
	}

	@Override
	public int deleteBusinessImg(int id) {
		return businessImgDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<BusinessImg> getByBusinessId(int id) {
		return businessImgDao.selectByBusinessId(id);
	}

	@Override
	public int deleteByBusinessId(int id) {
		return businessImgDao.deleteByBusinessId(id);
	}

}
