package com.exdev.cc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the service_request_extra_service database table.
 * 
 */
@Entity
@Table(name="service_request_extra_service")
@NamedQuery(name="ServiceRequestExtraService.findAll", query="SELECT s FROM ServiceRequestExtraService s")
public class ServiceRequestExtraService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String notes;

	private double price;

	//bi-directional many-to-one association to ExtraService
	@ManyToOne
	@JoinColumn(name="extra_service_id")
	private ExtraService extraService;

	//bi-directional many-to-one association to ServiceRequest
	@ManyToOne
	@JoinColumn(name="service_request_id")
	private ServiceRequest serviceRequest;

	public ServiceRequestExtraService() {
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

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ExtraService getExtraService() {
		return this.extraService;
	}

	public void setExtraService(ExtraService extraService) {
		this.extraService = extraService;
	}

	public ServiceRequest getServiceRequest() {
		return this.serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

}