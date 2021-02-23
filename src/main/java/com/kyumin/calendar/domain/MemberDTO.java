package com.kyumin.calendar.domain;

public class MemberDTO {
	private String loginId;
	private String loginPw;
	private String name;
	private String email;
	private String regDate;
	private String lastLogin;
	
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "MemberDTO [loginId=" + loginId + ", loginPw=" + loginPw + ", name=" + name + ", email=" + email
				+ ", regDate=" + regDate + ", LastLogin=" + lastLogin + "]";
	}	
}
