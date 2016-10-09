package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.CompanyImgMapper;
import com.ssm.pojo.CompanyImg;
import com.ssm.service.ICompanyImgService;

@Service("companyImgService")
public class CompanyImgService implements ICompanyImgService {

	@Resource
	private CompanyImgMapper companyImgDao;

	@Override
	public CompanyImg getCompanyImgById(int id) {
		return companyImgDao.selectByPrimaryKey(id);
	}

	@Override
	public int addCompanyImg(CompanyImg companyImg) {
		return companyImgDao.insertSelective(companyImg);
	}

	@Override
	public int updateCompanyImg(CompanyImg companyImg) {
		return companyImgDao.updateByPrimaryKeySelective(companyImg);
	}

	@Override
	public int deleteCompanyImg(int id) {
		return companyImgDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<CompanyImg> getByCompanyId(int id) {
		return companyImgDao.selectByCompanyId(id);
	}

	@Override
	public int deleteByCompanyId(int id) {
		return companyImgDao.deleteByCompanyId(id);
	}

}
