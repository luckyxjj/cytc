package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.AboutImg;

public interface AboutImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AboutImg record);

    int insertSelective(AboutImg record);

    AboutImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AboutImg record);

    int updateByPrimaryKey(AboutImg record);
    
    List<AboutImg> selectByAboutId(Integer id);
    
    int deleteByAboutId(Integer id);
}