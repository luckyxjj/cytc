package com.ssm.service;

import java.util.List;

import com.ssm.pojo.ActivityImg;

public interface IActivityImgService {
	public ActivityImg getActivityImgById(int id);

	public int addActivityImg(ActivityImg activityImg);

	public int updateActivityImg(ActivityImg activityImg);

	public int deleteActivityImg(int id);

	public List<ActivityImg> getByActivityId(int id);

	public int deleteByActivityId(int id);
}
