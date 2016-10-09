package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.EnvironmentMapper;
import com.ssm.pojo.Environment;
import com.ssm.service.IEnvironmentService;

@Service("environmentService")
public class EnvironmentService implements IEnvironmentService{
	
	@Resource
	private EnvironmentMapper environmentDao;

	@Override
	public Environment getEnvironmentById(int id) {
		return environmentDao.selectByPrimaryKey(id);
	}

	@Override
	public int addEnvironment(Environment environment) {
		return environmentDao.insertSelective(environment);
	}

	@Override
	public int updateEnvironment(Environment environment) {
		return environmentDao.updateByPrimaryKeySelective(environment);
	}

	@Override
	public int deleteEnvironment(int id) {
		return environmentDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Environment> getAllEnvironment() {
		return environmentDao.selectAll();
	}

}
