package com.exdev.cc.model;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;


/**
 * The persistent class for the service_request_customer_car database table.
 * 
 */
@Entity
@Table(name="service_request_customer_car")
@NamedQuery(name="ServiceRequestCustomerCar.findAll", query="SELECT s FROM ServiceRequestCustomerCar s")
public class ServiceRequestCustomerCar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String notes;

	//bi-directional many-to-one association to CustomerCar
	@ManyToOne(cascade = ALL)
	@JoinColumn(name="customer_car_id")
	private CustomerCar customerCar;

	//bi-directional many-to-one association to ServiceRequest
	@ManyToOne
	@JoinColumn(name="service_request_id")
	private ServiceRequest serviceRequest;

	public ServiceRequestCustomerCar() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public CustomerCar getCustomerCar() {
		return this.customerCar;
	}

	public void setCustomerCar(CustomerCar customerCar) {
		this.customerCar = customerCar;
	}

	public ServiceRequest getServiceRequest() {
		return this.serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

}