package model.dto;

import java.sql.Date;

import common.Configure;

public class EnqueteDTO {
	private int enqueteId;
	private String enqueteName;
	private int groupId;
    private Date beginDate;
    private Date endDate;
    private int openStatus;
    private String jsonData;
    private Date regDate;
    
	public int getEnqueteId() {
		return enqueteId;
	}

	public void setEnqueteId(int enqueteId) {
		this.enqueteId = enqueteId;
	}

	public String getEnqueteName() {
		return enqueteName;
	}

	public void setEnqueteName(String enqueteName) {
		this.enqueteName = enqueteName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(int openStatus) {
		this.openStatus = openStatus;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getStatusName() {
		String statusName = "준비";
		if (openStatus == Configure.ENQUETE_STATUS_OPEN) {
			statusName = "공개";
		} else if (openStatus == Configure.ENQUETE_STATUS_OPEN) {
			statusName = "종료";
		}
		return statusName;
	}
	
	public String getInnerHTML() {
		return null;
	}
}
