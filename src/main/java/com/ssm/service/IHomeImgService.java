package com.ssm.service;

import java.util.List;

import com.ssm.pojo.HomeImg;

public interface IHomeImgService {
	public HomeImg getHomeImgById(int id);

	public int addHomeImg(HomeImg homeImg);

	public int updateHomeImg(HomeImg homeImg);

	public int deleteHomeImg(int id);

	public List<HomeImg> getAllHomeImg();
}
