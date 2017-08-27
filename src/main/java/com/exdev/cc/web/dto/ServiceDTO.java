package com.exdev.cc.web.dto;

import java.util.List;

public class ServiceDTO extends BaseDTO {

	private String picture;
	private boolean disabled;
	private String description;
	private List<ServicePriceDTO> prices;

	public ServiceDTO() {

	}

	public ServiceDTO(int id, String name) {
		super(id, name);

	}

	public ServiceDTO(int id, String name, String notes) {
		super(id, name, notes);
	}

	public ServiceDTO(int id, String name, String notes, String picture) {
		super(id, name, notes);
		this.picture = picture;
	}
	
	

	public ServiceDTO(int id, String name, String notes, String picture, boolean disabled, String description,
			List<ServicePriceDTO> prices) {
		super(id, name, notes);
		this.picture = picture;
		this.disabled = disabled;
		this.description = description;
		this.prices = prices;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ServicePriceDTO> getPrices() {
		return prices;
	}

	public void setPrices(List<ServicePriceDTO> prices) {
		this.prices = prices;
	}

}
