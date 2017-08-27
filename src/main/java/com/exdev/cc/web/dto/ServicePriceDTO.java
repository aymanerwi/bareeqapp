package com.exdev.cc.web.dto;

public class ServicePriceDTO extends BaseDTO {
	
	private double price;
	private double minPrice;
	private CarTypeDTO carType;
	
	
	
	public ServicePriceDTO() {
		super();
	}
	public ServicePriceDTO(int id, String name, String notes, double price, double minPrice, CarTypeDTO carType) {
		super(id, name, notes);
		this.price = price;
		this.minPrice = minPrice;
		this.carType = carType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public CarTypeDTO getCarType() {
		return carType;
	}
	public void setCarType(CarTypeDTO carType) {
		this.carType = carType;
	}
	
	
}
