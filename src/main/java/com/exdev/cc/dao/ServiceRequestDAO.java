package com.exdev.cc.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import com.exdev.cc.model.ServiceRequest;

public class ServiceRequestDAO extends BaseDAO {

	@Override
	public Object find(int id) throws Exception {

		return find(ServiceRequest.class, id);
	}

	@Override
	public List<Object> list() throws Exception {
		return em.createNamedQuery("ServiceRequest.findAll").getResultList();
	}

	@Override
	public List<Object> list(int start, int max) throws Exception {
		return em.createNamedQuery("ServiceRequest.findAll").getResultList();
	}

	@Override
	public List<Object> list(String query) throws Exception {
		return em.createNamedQuery("ServiceRequest.findByName").setParameter("name", query)
				.getResultList();
	}

	@Override
	public List<Object> list(String clientId, int start, int max) throws Exception {
		return em.createNamedQuery("ServiceRequest.findByClientId").setParameter("clientId", clientId)
				.setFirstResult(start).setMaxResults(max).getResultList();

	}

	public List<ServiceRequest> list(String clientId, int status, int start, int max) throws Exception {
		return em.createNamedQuery("ServiceRequest.findByClientIdAndStatus")
				.setParameter("clientId", clientId).setParameter("status", status).setFirstResult(start)
				.setMaxResults(max).getResultList();
	}

	public List<ServiceRequest> list(int status, int start, int max) throws Exception {
		return em.createNamedQuery("ServiceRequest.findByStatus").setParameter("status", status)
				.setFirstResult(start).setMaxResults(max).getResultList();
	}

	public List<ServiceRequest> listByUnitStaffId(int unitStaffId) throws Exception {
		return em.createNamedQuery("ServiceRequest.findByUnitStaffId")
				.setParameter("unitStaffId", unitStaffId).getResultList();
	}

	public List<ServiceRequest> listByCarStaffId(int carStaffId, int start, int max) throws Exception {
		return em.createNamedQuery("ServiceRequest.findByCarStaffId")
				.setParameter("carStaffId", carStaffId).setFirstResult(start).setMaxResults(max).getResultList();
	}

	public List<ServiceRequest> listByCarStaffId(int carStaffId, Date requestDate, int status, int start, int max)
			throws Exception {
		return em.createNamedQuery("ServiceRequest.findByCarStaffIdAndRequestDateAndStatus")
				.setParameter("carStaffId", carStaffId).setParameter("requestDate", requestDate)
				.setParameter("status", status).setFirstResult(start).setMaxResults(max).getResultList();
	}

	public List<ServiceRequest> list(Date requestDate) {
		return em.createNamedQuery("ServiceRequest.findByRequestDate")
				.setParameter("requestDate", requestDate,TemporalType.DATE).getResultList();
	}

	public List<ServiceRequest> list(Date requestDate, int start, int max) {
		return em.createNamedQuery("ServiceRequest.findByRequestDate")
				.setParameter("requestDate", requestDate,TemporalType.DATE).setFirstResult(start).setMaxResults(max).getResultList();
	}

	public List list(Date requestDate, int status) {
		return em.createNamedQuery("ServiceRequest.findByRequestDateAndStatus")
				.setParameter("requestDate", requestDate,TemporalType.DATE).setParameter("status", status).getResultList();
	}

}
