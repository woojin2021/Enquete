package model.dto;

import java.sql.Date;

public class EnqueteGroupDTO {
    private int groupId;
    private String groupName;
    private int maxEnquete;
    private Date expireDate;
    private String creater;
    private Date regDate;
    private String createrName;
    
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getMaxEnquete() {
		return maxEnquete;
	}
	public void setMaxEnquete(int maxeEnquete) {
		this.maxEnquete = maxeEnquete;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	
	public boolean getExpired() {
		if (expireDate == null || expireDate.compareTo(new java.sql.Date(new java.util.Date().getTime())) > 0) {
			return false;
		}
		return true;
	}
    
}
