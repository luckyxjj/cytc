package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.BusinessMapper;
import com.ssm.pojo.Business;
import com.ssm.service.IBusinessService;

@Service("businessService")
public class BusinessService implements IBusinessService{

	@Resource
	private BusinessMapper businessDao;

	@Override
	public Business getBusinessById(int id) {
		return businessDao.selectByPrimaryKey(id);
	}

	@Override
	public int addBusiness(Business business) {
		return businessDao.insertSelective(business);
	}

	@Override
	public int updateBusiness(Business business) {
		return businessDao.updateByPrimaryKeySelective(business);
	}

	@Override
	public int deleteBusiness(int id) {
		return businessDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Business> getAllBusiness() {
		return businessDao.selectAll();
	}
}
