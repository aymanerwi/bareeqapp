package com.exdev.cc.web.dto;

import java.util.Date;

public class ServiceRequestStatusDTO extends BaseDTO {

	public static final int CLOSED = 0;
	public static final int PENDING = 1;
	public static final int IN_PROGRESS = 2;
	public static final int COMPLETED = 3;
	public static final int CANCELLED = -1;

	private int status;
	private Date statusDate;

	public ServiceRequestStatusDTO() {
		super();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

}
