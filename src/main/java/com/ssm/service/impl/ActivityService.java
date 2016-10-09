package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.ActivityMapper;
import com.ssm.pojo.Activity;
import com.ssm.service.IActivityService;

@Service("activityService")
public class ActivityService implements IActivityService {

	@Resource
	private ActivityMapper activityDao;

	@Override
	public Activity getActivityById(int id) {
		return activityDao.selectByPrimaryKey(id);
	}

	@Override
	public int addActivity(Activity activity) {
		return activityDao.insertSelective(activity);
	}

	@Override
	public int updateActivity(Activity activity) {
		return activityDao.updateByPrimaryKeySelective(activity);
	}

	@Override
	public int deleteActivity(int id) {
		return activityDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Activity> getAllActivity() {
		return activityDao.selectAll();
	}

}
