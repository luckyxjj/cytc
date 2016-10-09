package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.Message;

public interface MessageMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Message record);

	int insertSelective(Message record);

	Message selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKey(Message record);

	List<Message> selectBySendIdAndReceId(Message message);

	List<Message> selectMsgListById(String id);

}