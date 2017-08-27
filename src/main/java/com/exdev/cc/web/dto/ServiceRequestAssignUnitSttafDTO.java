package com.exdev.cc.web.dto;

import java.util.Date;

public class ServiceRequestAssignUnitSttafDTO extends BaseDTO {
	private UnitStaffDTO unitStaff;
	private String unitStaffToken;
	private Date assignedDate;

	public ServiceRequestAssignUnitSttafDTO() {
		super();
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public UnitStaffDTO getUnitStaff() {
		return unitStaff;
	}

	public void setUnitStaff(UnitStaffDTO unitStaff) {
		this.unitStaff = unitStaff;
	}

	public String getUnitStaffToken() {
		return unitStaffToken;
	}

	public void setUnitStaffToken(String unitStaffToken) {
		this.unitStaffToken = unitStaffToken;
	}

}
