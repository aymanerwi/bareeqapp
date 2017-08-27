package com.exdev.cc.web.dto;

public class PartnerServiceDTO extends BaseDTO {

	private double price;
	private ServiceDTO service;
	private PartnerDTO provider;

	public PartnerServiceDTO() {
	}

	public PartnerServiceDTO(int id, String name) {
		super(id, name);
	}

	public PartnerServiceDTO(int id, String name, String notes) {
		super(id, name, notes);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ServiceDTO getService() {
		return service;
	}

	public void setService(ServiceDTO service) {
		this.service = service;
	}

	public PartnerDTO getProvider() {
		return provider;
	}

	public void setProvider(PartnerDTO provider) {
		this.provider = provider;
	}

}
