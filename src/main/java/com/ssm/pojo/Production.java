package com.ssm.pojo;

import java.util.List;

public class Production {
	private Integer id;

	private String category;

	private String description;

	private String createTime;

	private String updateTime;

	private List<ProductionImg> productionImgList;

	public List<ProductionImg> getProductionImgList() {
		return productionImgList;
	}

	public void setProductionImgList(List<ProductionImg> productionImgList) {
		this.productionImgList = productionImgList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
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