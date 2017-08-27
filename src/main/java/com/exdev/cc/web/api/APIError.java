package com.exdev.cc.web.api;

import java.util.logging.Level;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.exdev.cc.web.CCApp;

public class APIError extends APIMessage {

	private String details;
	
	public APIError() {
		
	}

	public APIError(String message, String details) {
		super(message);
		this.details = details;
	}

	public APIError(String message, Throwable e) {
		super(message);
		this.details = ExceptionUtils.getStackTrace(e);
		CCApp.LOGGER.log(Level.SEVERE, message, e);
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
}
