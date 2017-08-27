package com.exdev.cc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the car_type database table.
 * 
 */
@Entity
@Table(name="car_type")
@NamedQuery(name="CarType.findAll", query="SELECT c FROM CarType c")
public class CarType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to CustomerCar
	@OneToMany(mappedBy="carType")
	private List<CustomerCar> customerCars;

	//bi-directional many-to-one association to ServicePrice
	@OneToMany(mappedBy="carType")
	private List<ServicePrice> servicePrices;



	public CarType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CustomerCar> getCustomerCars() {
		return this.customerCars;
	}

	public void setCustomerCars(List<CustomerCar> customerCars) {
		this.customerCars = customerCars;
	}

	public CustomerCar addCustomerCar(CustomerCar customerCar) {
		getCustomerCars().add(customerCar);
		customerCar.setCarType(this);

		return customerCar;
	}

	public CustomerCar removeCustomerCar(CustomerCar customerCar) {
		getCustomerCars().remove(customerCar);
		customerCar.setCarType(null);

		return customerCar;
	}

	public List<ServicePrice> getServicePrices() {
		return this.servicePrices;
	}

	public void setServicePrices(List<ServicePrice> servicePrices) {
		this.servicePrices = servicePrices;
	}

	public ServicePrice addServicePrice(ServicePrice servicePrice) {
		getServicePrices().add(servicePrice);
		servicePrice.setCarType(this);

		return servicePrice;
	}

	public ServicePrice removeServicePrice(ServicePrice servicePrice) {
		getServicePrices().remove(servicePrice);
		servicePrice.setCarType(null);

		return servicePrice;
	}


}