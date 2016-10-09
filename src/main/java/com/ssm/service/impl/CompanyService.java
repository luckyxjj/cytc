package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.CompanyMapper;
import com.ssm.pojo.Company;
import com.ssm.service.ICompanyService;

@Service("companyService")
public class CompanyService implements ICompanyService{

	@Resource
	private CompanyMapper companyDao;
	@Override
	public Company getCompanyById(int id) {
		return companyDao.selectByPrimaryKey(id);
	}

	@Override
	public int addCompany(Company company) {
		return companyDao.insertSelective(company);
	}

	@Override
	public int updateCompany(Company company) {
		return companyDao.updateByPrimaryKeySelective(company);
	}

	@Override
	public int deleteCompany(int id) {
		return companyDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Company> getAllCompany() {
		return companyDao.selectAll();
	}

}
