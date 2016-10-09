package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.ActivityImg;

public interface ActivityImgMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ActivityImg record);

	int insertSelective(ActivityImg record);

	ActivityImg selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ActivityImg record);

	int updateByPrimaryKey(ActivityImg record);

	List<ActivityImg> selectByActivityId(Integer id);

	int deleteByActivityId(Integer id);
}