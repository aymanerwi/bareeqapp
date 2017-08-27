package com.exdev.cc.model;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQueries;

/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name = "customer")
@NamedQueries({ 
	@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"), 
	@NamedQuery(name = "Customer.findByMobileNo", query = "SELECT c FROM Customer c where c.user.mobileNo = :mobileNo"),
	@NamedQuery(name = "Customer.search", query = "SELECT c FROM Customer c where concat('',c.user.name,'%',c.user.mobileNo,'%') like :query")
})
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String notes;

	private String code;

	// bi-directional many-to-one association to User
	@ManyToOne(cascade = ALL)
	private User user;

	// bi-directional many-to-one association to CustomerCar
	@OneToMany(mappedBy = "customer")
	private List<CustomerCar> customerCars;

	// bi-directional many-to-one association to ServiceRequest
	@OneToMany(mappedBy = "customer")
	private List<ServiceRequest> serviceRequests;

	public Customer() {
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CustomerCar> getCustomerCars() {
		return this.customerCars;
	}

	public void setCustomerCars(List<CustomerCar> customerCars) {
		this.customerCars = customerCars;
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ServiceRequest> getServiceRequests() {
		return serviceRequests;
	}

	public void setServiceRequests(List<ServiceRequest> serviceRequests) {
		this.serviceRequests = serviceRequests;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer c = (Customer) obj;
		return c.id == this.id;
	}
}