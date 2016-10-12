package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Message;

public interface IMessageService {
	public Message getMessageById(int id);

	public int addMessage(Message message);

	public int updateMessage(Message message);

	public int deleteMessage(int id);

	public List<Message> getBySendIdAndReceId(Message message);

	public List<Message> getMsgList();

	public int getUnreadNum(String userNo);

	public int updateFlag(String userNo);

}
