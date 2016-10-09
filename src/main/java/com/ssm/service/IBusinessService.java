package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Business;

public interface IBusinessService {
	public Business getBusinessById(int id);
	public int addBusiness(Business business);
	public int updateBusiness(Business business);
    public int deleteBusiness(int id);	
    public List<Business> getAllBusiness();
}
