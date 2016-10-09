package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.IUserService;

@Service("userService")
public class UserService implements IUserService {
	@Resource
	private UserMapper userDao;

	@Override
	public User getUserById(int id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public int addUser(User user) {
		return userDao.insertSelective(user);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int deleteUser(int id) {
		return userDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.selectAll();
	}

	@Override
	public int loginValidate(User user) {
		Object object =  userDao.loginValidate(user);
		if (object == null) {
			return 0;
		}
		return Integer.parseInt(object.toString());
	}

}
