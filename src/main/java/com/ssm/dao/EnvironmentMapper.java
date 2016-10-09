package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.Environment;

public interface EnvironmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Environment record);

    int insertSelective(Environment record);

    Environment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Environment record);

    int updateByPrimaryKey(Environment record);
    
    List<Environment> selectAll();
}