package com.exdev.cc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the customer_car database table.
 * 
 */
@Entity
@Table(name = "customer_car")
@NamedQuery(name = "CustomerCar.findAll", query = "SELECT c FROM CustomerCar c")

public class CustomerCar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String make;

	private String model;

	@Column(name = "model_year")
	private int modelYear;

	private String name;

	private String notes;

	@Column(name = "plate_no")
	private String plateNo;

	// bi-directional many-to-one association to CarType
	@ManyToOne
	@JoinColumn(name = "car_type_id")
	private CarType carType;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	// bi-directional many-to-one association to ServiceRequestCustomerCar
	@OneToMany(mappedBy = "customerCar")
	private List<ServiceRequestCustomerCar> serviceRequestCustomerCars;

	public CustomerCar() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getModelYear() {
		return this.modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPlateNo() {
		return this.plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<ServiceRequestCustomerCar> getServiceRequestCustomerCars() {
		return this.serviceRequestCustomerCars;
	}

	public void setServiceRequestCustomerCars(List<ServiceRequestCustomerCar> serviceRequestCustomerCars) {
		this.serviceRequestCustomerCars = serviceRequestCustomerCars;
	}

	public ServiceRequestCustomerCar addServiceRequestCustomerCar(ServiceRequestCustomerCar serviceRequestCustomerCar) {
		getServiceRequestCustomerCars().add(serviceRequestCustomerCar);
		serviceRequestCustomerCar.setCustomerCar(this);

		return serviceRequestCustomerCar;
	}

	public ServiceRequestCustomerCar removeServiceRequestCustomerCar(
			ServiceRequestCustomerCar serviceRequestCustomerCar) {
		getServiceRequestCustomerCars().remove(serviceRequestCustomerCar);
		serviceRequestCustomerCar.setCustomerCar(null);

		return serviceRequestCustomerCar;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof CustomerCar))
			return false;
		CustomerCar cc = (CustomerCar) obj;
		return cc.id == this.id;
	}
}