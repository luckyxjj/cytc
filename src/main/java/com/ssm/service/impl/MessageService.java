package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.MessageMapper;
import com.ssm.pojo.Message;
import com.ssm.service.IMessageService;

@Service("messageService")
public class MessageService implements IMessageService {

	@Resource
	private MessageMapper messageDao;

	@Override
	public Message getMessageById(int id) {
		return messageDao.selectByPrimaryKey(id);
	}

	@Override
	public int addMessage(Message message) {
		return messageDao.insertSelective(message);
	}

	@Override
	public int updateMessage(Message message) {
		return messageDao.updateByPrimaryKeySelective(message);
	}

	@Override
	public int deleteMessage(int id) {
		return messageDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Message> getBySendIdAndReceId(Message message) {
		return messageDao.selectBySendIdAndReceId(message);
	}

	@Override
	public List<Message> getMsgList() {
		return messageDao.selectMsgList();
	}

	@Override
	public List<Integer> getUnreadNum(String userNo) {
		return messageDao.selectUnreadNum(userNo);
	}

	@Override
	public int updateFlag(Message message) {
		return messageDao.updateFlag(message);
	}

}
