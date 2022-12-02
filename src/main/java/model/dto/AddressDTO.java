package model.dto;

import java.sql.Date;

public class AddressDTO {

    private String userid;
    private String password;
    private String name;
    private String mailaddress;
    private String extend00;
    private String extend01;
    private String extend02;
    private String extend03;
    private String extend04;
    private String extend05;
    private int admin;
    private Date lastlogindate;
    private Date regdate;
    
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	public String getExtend00() {
		return extend00;
	}
	public void setExtend00(String extend00) {
		this.extend00 = extend00;
	}
	public String getExtend01() {
		return extend01;
	}
	public void setExtend01(String extend01) {
		this.extend01 = extend01;
	}
	public String getExtend02() {
		return extend02;
	}
	public void setExtend02(String extend02) {
		this.extend02 = extend02;
	}
	public String getExtend03() {
		return extend03;
	}
	public void setExtend03(String extend03) {
		this.extend03 = extend03;
	}
	public String getExtend04() {
		return extend04;
	}
	public void setExtend04(String extend04) {
		this.extend04 = extend04;
	}
	public String getExtend05() {
		return extend05;
	}
	public void setExtend05(String extend05) {
		this.extend05 = extend05;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public Date getLastlogindate() {
		return lastlogindate;
	}
	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
    
	public String getAdminName() {
		String name = "";
		
		if (admin == 0) {
			name = "시스템 관리자";
		} else if (admin == 1) {
			name = "앙케이트 관리자";
		} else {
			name = "일반회원";
		}
		
		return name;
	}

}
