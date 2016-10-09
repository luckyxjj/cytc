package com.ssm.pojo;

import java.util.List;

public class Activity {
	private Integer id;

	private String imgUrl;

	private String introduce;

	private String detailedIntroduce;

	private String createTime;

	private String updateTime;

	private List<ActivityImg> activityImgList;

	public List<ActivityImg> getActivityImgList() {
		return activityImgList;
	}

	public void setActivityImgList(List<ActivityImg> activityImgList) {
		this.activityImgList = activityImgList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl == null ? null : imgUrl.trim();
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce == null ? null : introduce.trim();
	}

	public String getDetailedIntroduce() {
		return detailedIntroduce;
	}

	public void setDetailedIntroduce(String detailedIntroduce) {
		this.detailedIntroduce = detailedIntroduce == null ? null : detailedIntroduce.trim();
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