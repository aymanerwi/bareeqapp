package com.exdev.cc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@Table(name = "service")
@NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	private boolean disabled;

	private String name;

	private String notes;

	@Lob
	private byte[] pic;

	// bi-directional many-to-one association to PartnerService
	@OneToMany(mappedBy = "service")
	private List<PartnerService> partnerServices;

	// bi-directional many-to-one association to ServiceRequest
	@OneToMany(mappedBy = "service")
	private List<ServiceRequest> serviceRequests;

	// bi-directional many-to-one association to ServicePrice
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	private List<ServicePrice> servicePrices;

	public Service() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getDisabled() {
		return this.disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
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

	public List<PartnerService> getPartnerServices() {
		return this.partnerServices;
	}

	public void setPartnerServices(List<PartnerService> partnerServices) {
		this.partnerServices = partnerServices;
	}

	public PartnerService addPartnerService(PartnerService partnerService) {
		getPartnerServices().add(partnerService);
		partnerService.setService(this);

		return partnerService;
	}

	public PartnerService removePartnerService(PartnerService partnerService) {
		getPartnerServices().remove(partnerService);
		partnerService.setService(null);

		return partnerService;
	}

	public List<ServiceRequest> getServiceRequests() {
		return this.serviceRequests;
	}

	public void setServiceRequests(List<ServiceRequest> serviceRequests) {
		this.serviceRequests = serviceRequests;
	}

	public ServiceRequest addServiceRequest(ServiceRequest serviceRequest) {
		getServiceRequests().add(serviceRequest);
		serviceRequest.setService(this);

		return serviceRequest;
	}

	public ServiceRequest removeServiceRequest(ServiceRequest serviceRequest) {
		getServiceRequests().remove(serviceRequest);
		serviceRequest.setService(null);

		return serviceRequest;
	}

	public List<ServicePrice> getServicePrices() {
		return servicePrices;
	}

	public void setServicePrices(List<ServicePrice> servicePrices) {
		this.servicePrices = servicePrices;
	}

	public ServicePrice addServicePrice(ServicePrice servicePrice) {
		getServicePrices().add(servicePrice);
		servicePrice.setService(this);

		return servicePrice;
	}

	public ServicePrice removeServicePrice(ServicePrice servicePrice) {
		getServicePrices().remove(servicePrice);
		servicePrice.setService(null);

		return servicePrice;
	}

}