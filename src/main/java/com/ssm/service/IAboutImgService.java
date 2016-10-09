package com.ssm.service;

import java.util.List;

import com.ssm.pojo.AboutImg;

public interface IAboutImgService {
	public AboutImg getAboutImgById(int id);
	public int addAboutImg(AboutImg aboutImg);
	public int updateAboutImg(AboutImg aboutImg);
    public int deleteAboutImg(int id);	
    public List<AboutImg> getByAboutId(int id);
    public int deleteByAboutId(int id);	
}
