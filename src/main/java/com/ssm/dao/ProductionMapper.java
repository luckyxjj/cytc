package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.Production;

public interface ProductionMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Production record);

	int insertSelective(Production record);

	Production selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Production record);

	int updateByPrimaryKey(Production record);

	List<Production> selectAll();
}