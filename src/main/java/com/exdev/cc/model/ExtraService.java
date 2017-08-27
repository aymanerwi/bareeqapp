package com.exdev.cc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the extra_service database table.
 * 
 */
@Entity
@Table(name="extra_service")
@NamedQuery(name="ExtraService.findAll", query="SELECT e FROM ExtraService e")
public class ExtraService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	private String notes;

	private double price;

	//bi-directional many-to-one association to ServiceRequestExtraService
	@OneToMany(mappedBy="extraService")
	private List<ServiceRequestExtraService> serviceRequestExtraServices;

	public ExtraService() {
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

	public List<ServiceRequestExtraService> getServiceRequestExtraServices() {
		return this.serviceRequestExtraServices;
	}

	public void setServiceRequestExtraServices(List<ServiceRequestExtraService> serviceRequestExtraServices) {
		this.serviceRequestExtraServices = serviceRequestExtraServices;
	}

	public ServiceRequestExtraService addServiceRequestExtraService(ServiceRequestExtraService serviceRequestExtraService) {
		getServiceRequestExtraServices().add(serviceRequestExtraService);
		serviceRequestExtraService.setExtraService(this);

		return serviceRequestExtraService;
	}

	public ServiceRequestExtraService removeServiceRequestExtraService(ServiceRequestExtraService serviceRequestExtraService) {
		getServiceRequestExtraServices().remove(serviceRequestExtraService);
		serviceRequestExtraService.setExtraService(null);

		return serviceRequestExtraService;
	}

}