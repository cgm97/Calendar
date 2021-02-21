package com.kyumin.calendar.domain;

import java.util.Date;

public class MemberDTO {
	private String loginId;
	private String loginPw;
	private String name;
	private String email;
	private Date regDate;
	private Date lastLogin;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "MemberDTO [loginId=" + loginId + ", loginPw=" + loginPw + ", name=" + name + ", email=" + email
				+ ", regDate=" + regDate + ", LastLogin=" + lastLogin + "]";
	}	
}
