package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.ActivityImgMapper;
import com.ssm.pojo.ActivityImg;
import com.ssm.service.IActivityImgService;

@Service("activityImgService")
public class ActivityImgService implements IActivityImgService {

	@Resource
	private ActivityImgMapper activityImgDao;

	@Override
	public ActivityImg getActivityImgById(int id) {
		return activityImgDao.selectByPrimaryKey(id);
	}

	@Override
	public int addActivityImg(ActivityImg activityImg) {
		return activityImgDao.insertSelective(activityImg);
	}

	@Override
	public int updateActivityImg(ActivityImg activityImg) {
		return activityImgDao.updateByPrimaryKeySelective(activityImg);
	}

	@Override
	public int deleteActivityImg(int id) {
		return activityImgDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<ActivityImg> getByActivityId(int id) {
		return activityImgDao.selectByActivityId(id);
	}

	@Override
	public int deleteByActivityId(int id) {
		return activityImgDao.deleteByActivityId(id);
	}

}
