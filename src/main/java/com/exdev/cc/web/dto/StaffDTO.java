package com.exdev.cc.web.dto;

public class StaffDTO extends BaseDTO {

	private String idNo;
	private PartnerDTO partner;
	private UserDTO user;

	public StaffDTO() {

	}

	public StaffDTO(int id, String name) {
		super(id, name);
	}

	public StaffDTO(int id, String name, String notes) {
		super(id, name, notes);
	}

	public StaffDTO(int id, String name, String notes, String idNo) {
		super(id, name, notes);
		this.idNo = idNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public PartnerDTO getPartner() {
		return partner;
	}

	public void setPartner(PartnerDTO partner) {
		this.partner = partner;
	}

}
