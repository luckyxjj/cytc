package com.ssm.service;

import java.util.List;

import com.ssm.pojo.About;

public interface IAboutService {
	public About getAboutById(int id);
	public int addAbout(About about);
	public int updateAbout(About about);
    public int deleteAbout(int id);	
    public List<About> getAllAbout();
}
