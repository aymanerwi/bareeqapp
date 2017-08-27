package com.exdev.cc.web.dto;

public class ServiceRequestExtraServiceDTO extends BaseDTO {
	
	private double price;
	
	private ExtraServiceDTO extraService;

	
	public ServiceRequestExtraServiceDTO() {
		super();
	}


	public ServiceRequestExtraServiceDTO(int id, String name, String notes, double price,
			ExtraServiceDTO extraService) {
		super(id, name, notes);
		this.price = price;
		this.extraService = extraService;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public ExtraServiceDTO getExtraService() {
		return extraService;
	}


	public void setExtraService(ExtraServiceDTO extraService) {
		this.extraService = extraService;
	}
	
	
	
}
