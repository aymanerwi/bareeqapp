package com.exdev.cc.web.dto;

import java.util.Date;
import java.util.List;

public class ServiceRequestDTO extends BaseDTO {

	private CustomerDTO customer;
	private List<ServiceRequestCustomerCarDTO> customerCars;
	private List<ServiceRequestExtraServiceDTO> extraServices;
	private Date requestDate;
	private double latitude;
	private double longitude;
	private double price;
	private int rating;
	private String ratingComments;
	private ServiceDTO service;
	private int status;
	private Date statusDate;
	private String statusNotes;
	private String contactNo;
	private String token;
	private UnitStaffDTO unitStaff;
	private String unitStaffToken;
	private Date assignedDate;
	private String assignedNotes;
	private TimeSlotDTO timeSlot;
	private Date createDate;
	private Date modifyDate;

	public ServiceRequestDTO() {
		super();
	}

	public ServiceRequestDTO(int id, String name, String notes, Date requestDate, double latitude, double longitude,
			double price, int rating, String ratingComments, ServiceDTO service, int status, Date statusDate,
			String contactNo, String token, CustomerDTO customer, TimeSlotDTO timeSlot) {
		super(id, name, notes);
		this.requestDate = requestDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.price = price;
		this.rating = rating;
		this.service = service;
		this.status = status;
		this.statusDate = statusDate;
		this.ratingComments = ratingComments;
		this.contactNo = contactNo;
		this.token = token;
		this.customer = customer;
		this.timeSlot = timeSlot;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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

	public ServiceDTO getService() {
		return service;
	}

	public void setService(ServiceDTO service) {
		this.service = service;
	}

	public String getRatingComments() {
		return ratingComments;
	}

	public void setRatingComments(String ratingComments) {
		this.ratingComments = ratingComments;
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

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getAssignedNotes() {
		return assignedNotes;
	}

	public void setAssignedNotes(String assignedNotes) {
		this.assignedNotes = assignedNotes;
	}

	public String getStatusNotes() {
		return statusNotes;
	}

	public void setStatusNotes(String statusNotes) {
		this.statusNotes = statusNotes;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public TimeSlotDTO getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlotDTO timeSlot) {
		this.timeSlot = timeSlot;
	}

	public UnitStaffDTO getUnitStaff() {
		return unitStaff;
	}

	public void setUnitStaff(UnitStaffDTO unitStaff) {
		this.unitStaff = unitStaff;
	}

	public String getUnitStaffToken() {
		return unitStaffToken;
	}

	public void setUnitStaffToken(String unitStaffToken) {
		this.unitStaffToken = unitStaffToken;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<ServiceRequestExtraServiceDTO> getExtraServices() {
		return extraServices;
	}

	public void setExtraServices(List<ServiceRequestExtraServiceDTO> extraServices) {
		this.extraServices = extraServices;
	}

	public List<ServiceRequestCustomerCarDTO> getCustomerCars() {
		return customerCars;
	}

	public void setCustomerCars(List<ServiceRequestCustomerCarDTO> customerCars) {
		this.customerCars = customerCars;
	}

	public String carTypes() {
		String carTypes ="";
		for (ServiceRequestCustomerCarDTO dto : customerCars) {
			if (dto.getCustomerCar() != null && dto.getCustomerCar().getCarType() != null)
				carTypes += dto.getCustomerCar().getCarType().getName() + ", ";
		}

		return carTypes;
	}

	public String extraServices() {
		String carServices = "";
		for (ServiceRequestExtraServiceDTO dto : extraServices) {
			carServices += dto.getName() + ", ";
		}
		return carServices;
	}

}
