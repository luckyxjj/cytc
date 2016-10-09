package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.ProductionImg;

public interface ProductionImgMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductionImg record);

	int insertSelective(ProductionImg record);

	ProductionImg selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProductionImg record);

	int updateByPrimaryKey(ProductionImg record);

	List<ProductionImg> selectByProductionId(Integer id);

	int deleteByProductionId(Integer id);
}