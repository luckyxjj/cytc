package com.ssm.pojo;

import java.util.List;

public class Message {
	private Integer id;

	private String sendNo;

	private String receiverNo;

	private String content;

	private String flag;

	private String createTime;

	private String updateTime;

	private int unreadNum;

	private List<Integer> unreadId;

	private int[] unId;

	public int[] getUnId() {
		return unId;
	}

	public void setUnId(int[] unId) {
		this.unId = unId;
	}

	public List<Integer> getUnreadId() {
		return unreadId;
	}

	public void setUnreadId(List<Integer> unreadId) {
		this.unreadId = unreadId;
	}

	public int getUnreadNum() {
		return unreadNum;
	}

	public void setUnreadNum(int unreadNum) {
		this.unreadNum = unreadNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSendNo() {
		return sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo == null ? null : sendNo.trim();
	}

	public String getReceiverNo() {
		return receiverNo;
	}

	public void setReceiverNo(String receiverNo) {
		this.receiverNo = receiverNo == null ? null : receiverNo.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
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