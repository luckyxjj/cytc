package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Environment;

public interface IEnvironmentService {
	public Environment getEnvironmentById(int id);
	public int addEnvironment(Environment environment);
	public int updateEnvironment(Environment environment);
    public int deleteEnvironment(int id);	
    public List<Environment> getAllEnvironment();
}
