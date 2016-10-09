package com.ssm.pojo;

import java.util.List;

public class About {
    private Integer id;

    private String introduce;

    private String culture;

    private String createTime;

    private String updateTime;
    
    private List<AboutImg> aboutImgList;

    public List<AboutImg> getAboutImgList() {
		return aboutImgList;
	}

	public void setAboutImgList(List<AboutImg> aboutImgList) {
		this.aboutImgList = aboutImgList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture == null ? null : culture.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}