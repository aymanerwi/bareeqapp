package com.exdev.cc.utils;

import java.util.HashMap;
import java.util.Map;

public class Notification {
	private Map<String,String> details  = new HashMap<>();
	private String path;
	private String to;
	
	
	public Notification() {
		super();
	}
	public Notification(Map<String, String> details, String path,String to) {
		super();
		this.details.putAll(details);
		this.path = path;
		this.to = to;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	public void put(String key,String value) {
		this.details.put(key, value);
	}
	
	public void remove(String key) {
		this.details.remove(key);
	}
	public Map<String, String> getDetails() {
		return details;
	}
	public void setDetails(Map<String, String> details) {
		this.details = details;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
