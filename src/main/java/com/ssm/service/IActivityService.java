package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Activity;

public interface IActivityService {
	public Activity getActivityById(int id);

	public int addActivity(Activity activity);

	public int updateActivity(Activity activity);

	public int deleteActivity(int id);

	public List<Activity> getAllActivity();
}
