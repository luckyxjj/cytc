package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.BusinessImg;

public interface BusinessImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessImg record);

    int insertSelective(BusinessImg record);

    BusinessImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessImg record);

    int updateByPrimaryKey(BusinessImg record);
    
    List<BusinessImg> selectByBusinessId(Integer id);
    
    int deleteByBusinessId(Integer id);
}