package com.exdev.cc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the client_request database table.
 * 
 */
@Entity
@Table(name = "service_request")
@NamedQueries({

		@NamedQuery(name = "ServiceRequest.findAll", query = "SELECT s FROM ServiceRequest s"),
		@NamedQuery(name = "ServiceRequest.findByClientId", query = "SELECT s FROM ServiceRequest s where s.customer.id = :id"),
		@NamedQuery(name = "ServiceRequest.findByClientIdAndStatus", query = "SELECT s FROM ServiceRequest s where s.customer.id = :id and s.status = :status"),
		@NamedQuery(name = "ServiceRequest.findByStatus", query = "SELECT s FROM ServiceRequest s where s.status = :status"),
		@NamedQuery(name = "ServiceRequest.findByRequestDate", query = "SELECT s FROM ServiceRequest s where s.requestDate = :requestDate"),
		@NamedQuery(name = "ServiceRequest.findByRequestDateAndStatus", query = "SELECT s FROM ServiceRequest s where s.requestDate = :requestDate and s.status = :status"),
		@NamedQuery(name = "ServiceRequest.findByUnitStaffId", query = "SELECT s FROM ServiceRequest s where s.unitStaff.id = :unitStaffId"),
		@NamedQuery(name = "ServiceRequest.findByCarStaffIdAndRequestDateAndStatus", query = "SELECT s FROM ServiceRequest s where s.unitStaff.id = :carStaffId and s.requestDate = :requestDate and s.status = :status"),
		@NamedQuery(name = "ServiceRequest.findByTimeSlot", query = "SELECT s FROM ServiceRequest s where s.timeSlot.id = :id") })
public class ServiceRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	@Temporal(TemporalType.DATE)
	@Column(name = "request_date")
	private Date requestDate;

	private String notes;

	private double latitude;

	private double longitude;

	private double price;

	@Column(name = "unit_staff_token")
	private String unitStaffToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "unit_staff_assign_date")
	private Date unitStaffAssignDate;

	@Column(name = "unit_staff_assign_notes")
	private String unitStaffAssignNotes;

	@Column(name = "contact_no")
	private String contactNo;

	private String token;

	private int rating;
	@Column(name = "rating_comments")
	private String ratingComments;
	private int status;

	@Column(name = "status_notes")
	private String statusNotes;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "status_date")
	private Date statusDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	// bi-directional many-to-one association to Service
	@ManyToOne
	private Service service;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	// bi-directional many-to-one association to TimeSlot
	@ManyToOne
	@JoinColumn(name = "time_slot_id")
	private TimeSlot timeSlot;

	// bi-directional many-to-one association to UnitStaff
	@ManyToOne
	@JoinColumn(name = "unit_staff_id")
	private UnitStaff unitStaff;

	// bi-directional many-to-one association to ServiceRequestCustomerCar
	@OneToMany(mappedBy = "serviceRequest", cascade = CascadeType.ALL)
	private List<ServiceRequestCustomerCar> serviceRequestCustomerCars;

	// bi-directional many-to-one association to ServiceRequestExtraService
	@OneToMany(mappedBy = "serviceRequest", cascade = CascadeType.ALL)
	private List<ServiceRequestExtraService> serviceRequestExtraServices;

	public ServiceRequest() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getRatingComments() {
		return ratingComments;
	}

	public void setRatingComments(String ratingComments) {
		this.ratingComments = ratingComments;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStatusNotes() {
		return statusNotes;
	}

	public void setStatusNotes(String statusNotes) {
		this.statusNotes = statusNotes;
	}

	public void setUnitStaffToken(String unitStaffToken) {
		this.unitStaffToken = unitStaffToken;

	}

	public String getUnitStaffToken() {
		return unitStaffToken;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public UnitStaff getUnitStaff() {
		return unitStaff;
	}

	public void setUnitStaff(UnitStaff unitStaff) {
		this.unitStaff = unitStaff;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getUnitStaffAssignDate() {
		return unitStaffAssignDate;
	}

	public void setUnitStaffAssignDate(Date unitStaffAssignDate) {
		this.unitStaffAssignDate = unitStaffAssignDate;
	}

	public String getUnitStaffAssignNotes() {
		return unitStaffAssignNotes;
	}

	public void setUnitStaffAssignNotes(String unitStaffAssignNotes) {
		this.unitStaffAssignNotes = unitStaffAssignNotes;
	}

	public List<ServiceRequestCustomerCar> getServiceRequestCustomerCars() {
		return this.serviceRequestCustomerCars;
	}

	public void setServiceRequestCustomerCars(List<ServiceRequestCustomerCar> serviceRequestCustomerCars) {
		this.serviceRequestCustomerCars = serviceRequestCustomerCars;
	}

	public ServiceRequestCustomerCar addServiceRequestCustomerCar(ServiceRequestCustomerCar serviceRequestCustomerCar) {
		getServiceRequestCustomerCars().add(serviceRequestCustomerCar);
		serviceRequestCustomerCar.setServiceRequest(this);

		return serviceRequestCustomerCar;
	}

	public ServiceRequestCustomerCar removeServiceRequestCustomerCar(
			ServiceRequestCustomerCar serviceRequestCustomerCar) {
		getServiceRequestCustomerCars().remove(serviceRequestCustomerCar);
		serviceRequestCustomerCar.setServiceRequest(null);

		return serviceRequestCustomerCar;
	}

	public List<ServiceRequestExtraService> getServiceRequestExtraServices() {
		return this.serviceRequestExtraServices;
	}

	public void setServiceRequestExtraServices(List<ServiceRequestExtraService> serviceRequestExtraServices) {
		this.serviceRequestExtraServices = serviceRequestExtraServices;
	}

	public ServiceRequestExtraService addServiceRequestExtraService(
			ServiceRequestExtraService serviceRequestExtraService) {
		getServiceRequestExtraServices().add(serviceRequestExtraService);
		serviceRequestExtraService.setServiceRequest(this);

		return serviceRequestExtraService;
	}

	public ServiceRequestExtraService removeServiceRequestExtraService(
			ServiceRequestExtraService serviceRequestExtraService) {
		getServiceRequestExtraServices().remove(serviceRequestExtraService);
		serviceRequestExtraService.setServiceRequest(null);

		return serviceRequestExtraService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}