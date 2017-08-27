package com.exdev.cc.web.dto;

public class CustomerDTO extends BaseDTO {

	private UserDTO user;
	private String code;
	private String smscode;

	public CustomerDTO() {
	}

	public CustomerDTO(int id, String name) {
		super(id, name);
	}

	public CustomerDTO(int id, String name, String notes) {
		super(id, name, notes);
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSmscode() {
		return smscode;
	}

	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}

}
