package com.exdev.cc.web.dto;

public class ServiceRequestCustomerCarDTO extends BaseDTO {

	private CustomerCarDTO customerCar;

	
	public ServiceRequestCustomerCarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceRequestCustomerCarDTO(int id, String name, String notes, CustomerCarDTO customerCar) {
		super(id, name, notes);
		this.customerCar = customerCar;
	}

	public CustomerCarDTO getCustomerCar() {
		return customerCar;
	}

	public void setCustomerCar(CustomerCarDTO customerCar) {
		this.customerCar = customerCar;
	}

}
