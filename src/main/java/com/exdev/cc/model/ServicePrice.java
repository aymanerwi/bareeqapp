package com.exdev.cc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the service_price database table.
 * 
 */
@Entity
@Table(name = "service_price")
@NamedQuery(name = "ServicePrice.findAll", query = "SELECT s FROM ServicePrice s")
public class ServicePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean disabled;

	@Column(name = "min_price")
	private double minPrice;

	private String name;

	private String notes;

	@Lob
	private byte[] pic;

	private double price;

	// bi-directional many-to-one association to CarType
	@ManyToOne
	@JoinColumn(name = "car_type_id")
	private CarType carType;

	// bi-directional many-to-one association to Service
	@ManyToOne
	private Service service;

	public ServicePrice() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getDisabled() {
		return this.disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public double getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
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

	public byte[] getPic() {
		return this.pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CarType getCarType() {
		return this.carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}