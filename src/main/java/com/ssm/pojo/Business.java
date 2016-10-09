package com.ssm.pojo;

import java.util.List;

public class Business {
    private Integer id;

    private String description;

    private String createTime;

    private String updateTime;
    
    private List<BusinessImg> businessImgList;

    public List<BusinessImg> getBusinessImgList() {
		return businessImgList;
	}

	public void setBusinessImgList(List<BusinessImg> businessImgList) {
		this.businessImgList = businessImgList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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