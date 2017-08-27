package com.exdev.cc.web.dto;

public class UnitDTO extends BaseDTO {

	private String mobileNo;
	private String plateNo;
	private String model;
	private PartnerDTO partner;

	public UnitDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnitDTO(int id, String name, String notes) {
		super(id, name, notes);
		// TODO Auto-generated constructor stub
	}

	public UnitDTO(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	public UnitDTO(int id, String name, String notes, String mobileNo, String plateNo, String model) {
		super(id, name, notes);
		this.mobileNo = mobileNo;
		this.plateNo = plateNo;
		this.model = model;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public PartnerDTO getPartner() {
		return partner;
	}

	public void setPartner(PartnerDTO partner) {
		this.partner = partner;
	}
}
