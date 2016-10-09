package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Company;

public interface ICompanyService {
	public Company getCompanyById(int id);
	public int addCompany(Company company);
	public int updateCompany(Company company);
    public int deleteCompany(int id);	
    public List<Company> getAllCompany();
}
