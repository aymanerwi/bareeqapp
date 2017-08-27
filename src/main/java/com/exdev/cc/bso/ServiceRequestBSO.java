package com.exdev.cc.bso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.CustomerDAO;
import com.exdev.cc.dao.ServiceRequestDAO;
import com.exdev.cc.dao.UserDAO;
import com.exdev.cc.model.CarType;
import com.exdev.cc.model.Customer;
import com.exdev.cc.model.CustomerCar;
import com.exdev.cc.model.ExtraService;
import com.exdev.cc.model.ServiceRequest;
import com.exdev.cc.model.ServiceRequestCustomerCar;
import com.exdev.cc.model.ServiceRequestExtraService;
import com.exdev.cc.model.User;
import com.exdev.cc.utils.CleanCarUtils;
import com.exdev.cc.web.CCApp;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.CarTypeDTO;
import com.exdev.cc.web.dto.CustomerCarDTO;
import com.exdev.cc.web.dto.ServiceRequestAssignUnitSttafDTO;
import com.exdev.cc.web.dto.ServiceRequestCustomerCarDTO;
import com.exdev.cc.web.dto.ServiceRequestDTO;
import com.exdev.cc.web.dto.ServiceRequestExtraServiceDTO;
import com.exdev.cc.web.dto.ServiceRequestRatingDTO;
import com.exdev.cc.web.dto.ServiceRequestStatusDTO;

public class ServiceRequestBSO extends BaseBSO {

	private static final String DISPATCHER_EMAIL_ID = CCApp.CONFIG.getProperty("DISPATCHER_EMAIL_ID");
	private static final String DISPATCHER_ID = CCApp.CONFIG.getProperty("DISPATCHER_ID");;

	public ServiceRequestBSO() throws BSOException {
		super();

	}

	public void assignUnitStaffToServiceRequest(int id, ServiceRequestAssignUnitSttafDTO dto) throws BSOException {
		try {
			ServiceRequest sr = (ServiceRequest) dao.find(id);
			sr.setUnitStaff(UnitStaffBSO.toUnitStaff(dto.getUnitStaff()));
			sr.setUnitStaffToken(dto.getUnitStaffToken());
			sr.setUnitStaffAssignDate(new Date());
			sr.setUnitStaffAssignNotes(dto.getNotes());
			sr.setModifyDate(new Date());
			dao.startTransaction();
			dao.save(sr);
			dao.commitTransaction();
			sendRequestNotification("request_assined", sr.getId(), sr.getUnitStaff().getStaff().getUser().getToken(),
					"You have been assign a new Request- ID: " + sr.getId() + "");
		} catch (Exception e) {
			try {
				dao.rollbackTransaction();
			} catch (Exception ex) {
				throw new BSOException("Error BSO: cannot rollback transaction", ex);
			}
			throw new BSOException("Error: cannot save entity", e);
		}

	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		ServiceRequest sr = (ServiceRequest) o;
		ServiceRequestDTO crdto = (ServiceRequestDTO) dto;
		sr.setCustomer(CustomerBSO.toCustomer(crdto.getCustomer()));
		if (crdto.getCustomerCars() != null) {
			List<ServiceRequestCustomerCar> srccList = new ArrayList<>();
			sr.setServiceRequestCustomerCars(srccList);
			for (ServiceRequestCustomerCarDTO srccdto : crdto.getCustomerCars()) {
				ServiceRequestCustomerCar srcc = new ServiceRequestCustomerCar();
				srcc.setId(srccdto.getId());
				CustomerCarDTO ccdto = srccdto.getCustomerCar();
				if (ccdto != null) {
					CustomerCar cc = (CustomerCar) dao.getEm().find(CustomerCar.class, ccdto.getId());
					if (cc == null) {
						cc = new CustomerCar();
					}
					cc.setId(ccdto.getId());
					cc.setNotes(ccdto.getNotes());
					cc.setPlateNo(ccdto.getPlateNo());
					if (ccdto.getCarType() != null) {
						CarType ct = dao.getEm().find(CarType.class, ccdto.getCarType().getId());
						ct.setId(ct.getId());
						ct.setName(ct.getName());
						cc.setCarType(ct);
					}
					cc.setModelYear(ccdto.getModelYear());
					cc.setModel(ccdto.getModel());
					cc.setMake(ccdto.getMake());
					cc.setCustomer(sr.getCustomer());
					cc.setName(ccdto.getName() != null ? ccdto.getName()
							: "Car " + sr.getCustomer().getUser().getName() + ", " + cc.getCarType().getName());
					srcc.setCustomerCar(cc);
				}

				srcc.setNotes(srccdto.getNotes());
				sr.addServiceRequestCustomerCar(srcc);
			}

		}
		sr.setService(ServiceBSO.getService(crdto.getService()));
		sr.setRequestDate(crdto.getRequestDate());
		if (crdto.getExtraServices() != null) {
			List<ServiceRequestExtraService> sresList = new ArrayList<>();
			sr.setServiceRequestExtraServices(sresList);
			for (ServiceRequestExtraServiceDTO sres : crdto.getExtraServices()) {
				ServiceRequestExtraService es = new ServiceRequestExtraService();
				es.setId(sres.getId());
				es.setNotes(sres.getNotes());
				es.setPrice(sres.getPrice());
				es.setServiceRequest(sr);
				es.setExtraService(ExtraServiceBSO.toExtraService(sres.getExtraService()));
				sr.addServiceRequestExtraService(es);
			}
		}
		sr.setNotes(dto.getNotes());
		sr.setLongitude(crdto.getLongitude());
		sr.setLatitude(crdto.getLatitude());
		sr.setStatus(crdto.getStatus());
		sr.setStatusDate(crdto.getStatusDate());
		sr.setPrice(crdto.getPrice());
		sr.setRating(crdto.getRating());
		sr.setRatingComments(crdto.getRatingComments());
		sr.setToken(crdto.getToken());
		sr.setContactNo(crdto.getContactNo());
		sr.setUnitStaff(UnitStaffBSO.toUnitStaff(crdto.getUnitStaff()));
		sr.setUnitStaffAssignDate(crdto.getAssignedDate());
		sr.setUnitStaffAssignNotes(crdto.getAssignedNotes());
		sr.setStatusNotes(sr.getStatusNotes());
		sr.setTimeSlot(TimeSlotBSO.toTimeSlot(crdto.getTimeSlot()));
		sr.setCreateDate(crdto.getCreateDate());
		sr.setModifyDate(crdto.getModifyDate());
		sr.setName("(" + sr.getCustomer().getUser().getName() + "," + sr.getContactNo() + "),("
				+ sr.getService().getName() + "," + sr.getPrice() + ")");
		return sr;
	}

	@Override
	public void create(BaseDTO dto) throws BSOException {
		final ServiceRequestDTO crdto = (ServiceRequestDTO) dto;
		crdto.setStatus(ServiceRequestStatus.New);
		crdto.setStatusDate(new Date());
		crdto.setCreateDate(new Date());
		super.create(dto);
		// find first available unit_staff and assign it this sr
		final ServiceRequestDTO srdto = (ServiceRequestDTO) get(crdto.getId());

		Runnable send = new Runnable() {
			public void run() {
				sendRequestEmailAndNotification(srdto);
			}
		};
		Thread th = new Thread(send);
		th.setDaemon(true);
		th.start();
	}

	private void sendRequestEmailAndNotification(ServiceRequestDTO crdto) {
		try {
			
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			StringBuilder sb = new StringBuilder();
			
			sb.append("<strong>Service Request Details:</strong> ");
			sb.append("<br/>");
			sb.append("<strong>Request Date:</strong> "+ fmt.format(crdto.getRequestDate()));
			sb.append("<br/>");
			sb.append("<strong>Customer Name:</strong> "+ crdto.getCustomer().getName());
			sb.append("<br/>");
			sb.append("<strong>Contact No:</strong> "+ crdto.getContactNo());
			sb.append("<br/>");
			sb.append("<strong>Request ID:</strong> "+ crdto.getId());
			sb.append("<br/>");
			sb.append("<strong>Location:</strong> " + "http://maps.google.com/maps?q=loc:" + crdto.getLatitude()+ "," + crdto.getLongitude());
			sb.append("<br/>");
			sb.append("<strong>Car Type:</strong> "+ crdto.carTypes());
			sb.append("<br/>");
			sb.append("<strong>Service Name:</strong> "+crdto.getService().getName());
			sb.append("<br/>");
			sb.append("<strong>Extra Service:</strong> "+ crdto.extraServices());
			sb.append("<br/>");
			sb.append("<strong>Price:</strong> " + crdto.getPrice());
			sb.append("<br/>");
			CleanCarUtils.sendEMail(DISPATCHER_EMAIL_ID, "New Request has been submitted",sb.toString());
			
			UserDAO userDao = new UserDAO();
			List<User> admins = userDao.findByAdmin(true);
			userDao.closeEntityManager();
			for (User user : admins) {
				sendRequestNotification("new_request", crdto.getId(), user.getToken(), "New Request Created");
			}
		} catch (Exception e) {
			CCApp.LOGGER.severe("Error: cannot send email or notification to admin");
			e.printStackTrace();
		}
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new ServiceRequestDAO();
	}

	public List<ServiceRequestDTO> list(Date date) throws BSOException {
		try {
			ServiceRequestDAO cdao = (ServiceRequestDAO) dao;
			List<ServiceRequest> objs = cdao.list(date);

			List<ServiceRequestDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add((ServiceRequestDTO) toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List list(Date date, int status) throws BSOException {
		try {
			ServiceRequestDAO cdao = (ServiceRequestDAO) dao;
			List<ServiceRequest> objs = cdao.list(date, status);

			List<ServiceRequestDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add((ServiceRequestDTO) toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List<ServiceRequestDTO> list(Date date, int start, int max) throws BSOException {
		try {
			ServiceRequestDAO cdao = (ServiceRequestDAO) dao;
			List<ServiceRequest> objs = cdao.list(date, start, max);

			List<ServiceRequestDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add((ServiceRequestDTO) toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List<ServiceRequestDTO> listByCustomer(int customerId, int start, int max) throws BSOException {
		try {

			CustomerDAO custDao = new CustomerDAO();
			Customer customer = (Customer) custDao.find(customerId);
			if (customer == null)
				return null;
			List<ServiceRequest> srs = customer.getServiceRequests();

			List<ServiceRequestDTO> dtos = new ArrayList<>(srs.size());
			for (Object o : srs) {
				dtos.add((ServiceRequestDTO) toDTO(o));
			}
			custDao.closeEntityManager();
			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List<ServiceRequestDTO> listByCustomerAndStatus(int customerId, final int status, int start, int max)
			throws BSOException {

		try {
			CustomerDAO custDao = new CustomerDAO();
			Customer customer = (Customer) custDao.find(customerId);
			if (customer == null)
				return null;
			List<ServiceRequest> srs = customer.getServiceRequests();
			srs = (List) CollectionUtils.select(srs, new Predicate<ServiceRequest>() {

				@Override
				public boolean evaluate(ServiceRequest ts) {
					return status == ts.getStatus();

				}
			});
			List<ServiceRequestDTO> dtos = new ArrayList<>(srs.size());

			for (Object o : srs) {
				dtos.add((ServiceRequestDTO) toDTO(o));
			}
			custDao.closeEntityManager();
			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List<ServiceRequestDTO> listByStatus(int status, int start, int max) throws BSOException {
		ServiceRequestDAO srDao = (ServiceRequestDAO) dao;
		try {
			List<ServiceRequest> objs = srDao.list(status, start, max);

			List<ServiceRequestDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add((ServiceRequestDTO) toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List<ServiceRequestDTO> listByUnitStaff(int unitstaffid, Date requestDate, int status, int start, int max)
			throws BSOException {
		ServiceRequestDAO dao1 = (ServiceRequestDAO) dao;
		try {
			List<ServiceRequest> objs = dao1.listByCarStaffId(unitstaffid, requestDate, status, start, max);

			List<ServiceRequestDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add((ServiceRequestDTO) toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List<ServiceRequestDTO> listByUnitStaff(int unitstaffid, int start, int max) throws BSOException {
		ServiceRequestDAO dao1 = (ServiceRequestDAO) dao;
		try {
			List<ServiceRequest> objs = dao1.listByCarStaffId(unitstaffid, start, max);

			List<ServiceRequestDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add((ServiceRequestDTO) toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	private void sendRequestNotification(String path, int reqid, String token, String msg) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", msg);
			map.put("requestId", Integer.toString(reqid));
			CleanCarUtils.sendNotification(map, path, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseDTO toDTO(Object entity) throws BSOException {
		ServiceRequest sr = (ServiceRequest) entity;
		ServiceRequestDTO dto = new ServiceRequestDTO(sr.getId(), sr.getName(), sr.getNotes(), sr.getRequestDate(),
				sr.getLatitude(), sr.getLongitude(), sr.getPrice(), sr.getRating(), sr.getRatingComments(),
				ServiceBSO.toServiceDTO(sr.getService()), sr.getStatus(), sr.getStatusDate(), sr.getContactNo(),
				sr.getToken(), CustomerBSO.toCustomerDTO(sr.getCustomer()),
				TimeSlotBSO.toTimeSlotDTO(sr.getTimeSlot()));
		dto.setUnitStaff(UnitStaffBSO.toUnitStaffDTO(sr.getUnitStaff()));
		dto.setAssignedDate(sr.getUnitStaffAssignDate());
		dto.setAssignedNotes(sr.getUnitStaffAssignNotes());
		dto.setStatusNotes(sr.getStatusNotes());
		dto.setCreateDate(sr.getCreateDate());
		dto.setModifyDate(sr.getModifyDate());
		if (sr.getServiceRequestCustomerCars() != null) {
			List<ServiceRequestCustomerCarDTO> dtos = new ArrayList<>();
			for (ServiceRequestCustomerCar srcc : sr.getServiceRequestCustomerCars()) {
				dtos.add(new ServiceRequestCustomerCarDTO(srcc.getId(), sr.getName(), srcc.getNotes(),
						CustomerCarsBSO.toCustomerCarDTO(srcc.getCustomerCar())));
			}
			dto.setCustomerCars(dtos);
		}

		if (sr.getServiceRequestExtraServices() != null) {
			List<ServiceRequestExtraServiceDTO> dtos = new ArrayList<>();
			for (ServiceRequestExtraService es : sr.getServiceRequestExtraServices()) {
				dtos.add(new ServiceRequestExtraServiceDTO(es.getId(), es.getExtraService().getName(), es.getNotes(),
						es.getPrice(), ExtraServiceBSO.toExtraServiceDTO(es.getExtraService())));
			}
			dto.setExtraServices(dtos);
		}

		return dto;
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		ServiceRequest sr = new ServiceRequest();
		ServiceRequestDTO crdto = (ServiceRequestDTO) dto;
		buildEntity(sr, crdto);
		return sr;
	}

	@Override
	public void update(int id, BaseDTO dto) throws BSOException {
		ServiceRequestDTO crdto = (ServiceRequestDTO) dto;
		crdto.setModifyDate(new Date());
		super.update(id, dto);
	}

	public void updateRating(int id, ServiceRequestRatingDTO dto) throws BSOException {

		try {
			ServiceRequest sr = (ServiceRequest) dao.find(id);
			if(sr == null) throw new BSOException("Error: request not found");
			sr.setRating(dto.getRating());
			sr.setRatingComments(dto.getRatingComments());
			sr.setModifyDate(new Date());
			dao.startTransaction();
			dao.save(sr);
			dao.commitTransaction();
			UserDAO userDao = new UserDAO();
			List<User> admins = userDao.findByAdmin(true);
			userDao.closeEntityManager();
			for (User user : admins) {
				sendRequestNotification("rating", sr.getId(), user.getToken(),
						"Request Id: " + sr.getId() + "has been rated");
			}
		} catch (Exception e) {
			try {
				dao.rollbackTransaction();
			} catch (Exception e1) {
				throw new BSOException("Error BSO: cannot rollback transaction", e);
			}
			throw new BSOException("Error: cannot save entity", e);
		}
	}

	public void updateStatus(int id, ServiceRequestStatusDTO dto) throws BSOException {
		try {
			ServiceRequest sr = (ServiceRequest) dao.find(id);
			sr.setStatus(dto.getStatus());
			sr.setStatusDate(new Date());
			sr.setStatusNotes(dto.getNotes());
			sr.setModifyDate(new Date());
			dao.startTransaction();
			dao.save(sr);
			dao.commitTransaction();
			if (dto.getStatus() == ServiceRequestStatus.Cancelled) {
				sendRequestNotification("request_cancelled", sr.getId(), sr.getCustomer().getUser().getToken(),
						"The Request has been Cancelled");
				UserDAO userDao = new UserDAO();
				List<User> admins = userDao.findByAdmin(true);
				userDao.closeEntityManager();
				for (User user : admins) {
					sendRequestNotification("request_cancelled", sr.getId(), user.getToken(),
							"Request Id: " + sr.getId() + "has been Cancelled");
				}
			}
			if (dto.getStatus() == ServiceRequestStatus.Closed)
				sendRequestNotification("request_closed", sr.getId(), sr.getCustomer().getUser().getToken(),
						"The Request has been CLOSED");
		} catch (Exception e) {
			try {
				dao.rollbackTransaction();
			} catch (Exception e1) {
				throw new BSOException("Error BSO: cannot rollback transaction", e);
			}
			throw new BSOException("Error: cannot save entity", e);
		}

	}

}
