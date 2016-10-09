package com.ssm.service;

import java.util.List;

import com.ssm.pojo.User;

public interface IUserService {
	public User getUserById(int id);
	public int addUser(User user);
	public int updateUser(User user);
    public int deleteUser(int id);	
    public List<User> getAllUser();
    public int loginValidate(User user);
}
