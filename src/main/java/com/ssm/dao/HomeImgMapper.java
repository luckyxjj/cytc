package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.HomeImg;

public interface HomeImgMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(HomeImg record);

	int insertSelective(HomeImg record);

	HomeImg selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(HomeImg record);

	int updateByPrimaryKey(HomeImg record);

	List<HomeImg> selectAll();
}