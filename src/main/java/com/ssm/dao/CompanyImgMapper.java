package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.CompanyImg;

public interface CompanyImgMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CompanyImg record);

	int insertSelective(CompanyImg record);

	CompanyImg selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CompanyImg record);

	int updateByPrimaryKey(CompanyImg record);

	List<CompanyImg> selectByCompanyId(int id);

	int deleteByCompanyId(Integer id);
}