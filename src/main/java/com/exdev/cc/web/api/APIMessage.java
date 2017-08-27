package com.exdev.cc.web.api;

public class APIMessage {

	protected String message;

	public APIMessage() {
		
	}

	public APIMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
