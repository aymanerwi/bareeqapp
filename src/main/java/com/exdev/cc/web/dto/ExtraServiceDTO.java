package com.exdev.cc.web.dto;

public class ExtraServiceDTO extends BaseDTO {
	
	private double price;

	
	public ExtraServiceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtraServiceDTO(int id, String name, String notes, double price) {
		super(id, name, notes);
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
