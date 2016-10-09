package com.ssm.pojo;

public class BusinessImg {
    private Integer id;

    private Integer businessId;

    private String imgUrl;

    private String createTiem;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getCreateTiem() {
        return createTiem;
    }

    public void setCreateTiem(String createTiem) {
        this.createTiem = createTiem == null ? null : createTiem.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}