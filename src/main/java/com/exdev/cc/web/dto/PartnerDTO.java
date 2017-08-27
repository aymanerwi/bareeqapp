package com.exdev.cc.web.dto;

import java.util.List;

public class PartnerDTO extends BaseDTO {

	private String mobileNo;
	private List<UnitDTO> units;

	public PartnerDTO() {

	}

	public PartnerDTO(int id, String name, String mobileNo) {
		super(id, name);
		this.mobileNo = mobileNo;

	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public List<UnitDTO> getUnits() {
		return units;
	}

	public void setUnits(List<UnitDTO> units) {
		this.units = units;
	}

}
