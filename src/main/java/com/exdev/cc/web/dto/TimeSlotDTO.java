package com.exdev.cc.web.dto;

import java.util.Date;

public class TimeSlotDTO extends BaseDTO {

	private String description;
	private Date startTime;
	private Date endTime;
	private boolean disabled;
	private boolean available;
	private int freeUnitStaff;
	public TimeSlotDTO() {

	}

	public TimeSlotDTO(int id, String name, String notes) {
		super(id, name, notes);
	}

	public TimeSlotDTO(int id, String name) {
		super(id, name);
	}

	public TimeSlotDTO(int id, String name, String notes, String description, Date startTime, Date endTime,
			boolean disabled) {
		super(id, name, notes);
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.disabled = disabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getFreeUnitStaff() {
		return freeUnitStaff;
	}

	public void setFreeUnitStaff(int freeUnitStaff) {
		this.freeUnitStaff = freeUnitStaff;
	}

}
