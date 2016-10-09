package com.ssm.service;

import java.util.List;

import com.ssm.pojo.CompanyImg;

public interface ICompanyImgService {
	public CompanyImg getCompanyImgById(int id);

	public int addCompanyImg(CompanyImg companyImg);

	public int updateCompanyImg(CompanyImg companyImg);

	public int deleteCompanyImg(int id);

	public List<CompanyImg> getByCompanyId(int id);

	public int deleteByCompanyId(int id);
}
