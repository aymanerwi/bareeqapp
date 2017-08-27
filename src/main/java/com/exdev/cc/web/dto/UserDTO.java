package com.exdev.cc.web.dto;

import java.util.Date;

public class UserDTO extends BaseDTO {

	private String email;
	private String password;
	private String mobileNo;
	private String username;
	private String token;
	private String uuid;
	private boolean disabled;
	private Date loginDate;
	private Date logoutDate;
	private boolean admin;
	private boolean staff;
	private boolean customer;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	public UserDTO(int id, String name, String notes) {
		super(id, name, notes);
	}

	public UserDTO(int id, String name, String notes, String email, String password, String mobileNo, String username) {
		super(id, name, notes);
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isStaff() {
		return staff;
	}

	public void setStaff(boolean staff) {
		this.staff = staff;
	}

	public boolean isCustomer() {
		return customer;
	}

	public void setCustomer(boolean customer) {
		this.customer = customer;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
