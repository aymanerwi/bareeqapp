package com.exdev.cc.web.dto;

public class CustomerCarDTO extends BaseDTO {

	private CustomerDTO customer;
	private String plateNo;
	private CarTypeDTO carType;
	private String make;
	private String model;
	private int modelYear;

	public CustomerCarDTO() {
	}

	public CustomerCarDTO(int id, String name, String notes, CustomerDTO customer, String plateNo, CarTypeDTO carType) {
		super(id, name, notes);
		this.customer = customer;
		this.plateNo = plateNo;
		this.carType = carType;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public CarTypeDTO getCarType() {
		return carType;
	}

	public void setCarType(CarTypeDTO carType) {
		this.carType = carType;
	}

}
