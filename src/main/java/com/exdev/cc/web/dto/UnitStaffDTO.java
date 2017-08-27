package com.exdev.cc.web.dto;

import java.util.Date;

public class UnitStaffDTO extends BaseDTO {

	private Date assignDate;
	private double longitude;
	private double latitude;

	private StaffDTO staff;
	private UnitDTO unit;

	public UnitStaffDTO() {
		// TODO Auto-generated constructor stub
	}

	public UnitStaffDTO(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	public UnitStaffDTO(int id, String name, String notes) {
		super(id, name, notes);
		// TODO Auto-generated constructor stub
	}

	public UnitStaffDTO(int id, String name, String notes, Date assignDate, double longitude, double latitude) {
		super(id, name, notes);
		this.setAssignDate(assignDate);
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Date getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	public StaffDTO getStaff() {
		return staff;
	}

	public void setStaff(StaffDTO staff) {
		this.staff = staff;
	}

	public UnitDTO getUnit() {
		return unit;
	}

	public void setUnit(UnitDTO unit) {
		this.unit = unit;
	}

}
