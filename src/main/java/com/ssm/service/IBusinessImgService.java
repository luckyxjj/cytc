package com.ssm.service;

import java.util.List;

import com.ssm.pojo.BusinessImg;

public interface IBusinessImgService {
	public BusinessImg getBusinessImgById(int id);
	public int addBusinessImg(BusinessImg businessImg);
	public int updateBusinessImg(BusinessImg businessImg);
    public int deleteBusinessImg(int id);	
    public List<BusinessImg> getByBusinessId(int id);
    public int deleteByBusinessId(int id);	
}
