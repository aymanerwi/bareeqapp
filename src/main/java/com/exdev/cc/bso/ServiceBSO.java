package com.exdev.cc.bso;

import java.util.ArrayList;
import java.util.List;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.ServiceDAO;
import com.exdev.cc.model.CarType;
import com.exdev.cc.model.Service;
import com.exdev.cc.model.ServicePrice;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.CarTypeDTO;
import com.exdev.cc.web.dto.ServiceDTO;
import com.exdev.cc.web.dto.ServicePriceDTO;

public class ServiceBSO extends BaseBSO {

	public static Service getService(BaseDTO dto) throws BSOException {
		Service service;
		try {
			ServiceDAO dao = new ServiceDAO();
			service = (Service) dao.find(dto.getId());
			dao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException("Error: cannot fin service", e);
		}
		return service;
	}

	public static Service toService(BaseDTO dto) throws BSOException {
		Service service;
		try {
			ServiceDTO sdto = (ServiceDTO) dto;
			ServiceDAO dao = new ServiceDAO();

			service = (Service) dao.find(sdto.getId());
			if (service == null)
				throw new Exception("Error: service not found");
			service.setId(dto.getId());
			service.setName(dto.getName());
			service.setNotes(dto.getNotes());
			service.setPic(sdto.getPicture() != null ? sdto.getPicture().getBytes() : null);
			service.setDescription(sdto.getDescription());
			service.setDisabled(sdto.isDisabled());
			service.setServicePrices(new ArrayList<ServicePrice>());
			if (sdto.getPrices() != null) {
				for (ServicePriceDTO d : sdto.getPrices()) {
					ServicePrice sp = new ServicePrice();
					sp.setId(d.getId());
					sp.setName(d.getName());
					sp.setMinPrice(d.getMinPrice());
					sp.setPrice(d.getPrice());
					CarType ct = new CarType();
					ct.setId(d.getCarType().getId());
					ct.setName(d.getCarType().getName());
					sp.setCarType(ct);
					service.addServicePrice(sp);
				}
			}
			dao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException("Error: cannot find Service", e);
		}
		return service;
	}

	public static ServiceDTO toServiceDTO(Service s) {
		ServiceDTO dto = new ServiceDTO(s.getId(), s.getName(), s.getNotes(), null);
		dto.setDescription(s.getDescription());
		dto.setDisabled(s.getDisabled());
		List<ServicePriceDTO> psList = new ArrayList<>();
		for (ServicePrice ps : s.getServicePrices()) {
			psList.add(new ServicePriceDTO(ps.getId(), ps.getName(), ps.getNotes(), ps.getPrice(), ps.getMinPrice(),
					new CarTypeDTO(ps.getCarType().getId(), ps.getCarType().getName())));
		}
		dto.setPrices(psList);
		return dto;
	}

	public ServiceBSO() throws BSOException {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) {
		ServiceDTO sdto = (ServiceDTO) dto;
		Service service = (Service) o;
		service.setName(dto.getName());
		service.setNotes(dto.getNotes());
		service.setPic(sdto.getPicture() != null ? sdto.getPicture().getBytes() : null);
		service.setDescription(sdto.getDescription());
		service.setDisabled(sdto.isDisabled());
		service.setServicePrices(new ArrayList<ServicePrice>());
		for (ServicePriceDTO d : sdto.getPrices()) {
			ServicePrice sp = new ServicePrice();
			sp.setId(d.getId());
			sp.setName(d.getName());
			sp.setMinPrice(d.getMinPrice());
			sp.setPrice(d.getPrice());
			CarType ct = new CarType();
			ct.setId(d.getCarType().getId());
			ct.setName(d.getCarType().getName());
			sp.setCarType(ct);
			service.addServicePrice(sp);
		}
		return service;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new ServiceDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		Service s = (Service) entity;
		return toServiceDTO(s);
	}

	@Override
	protected Object toEntity(BaseDTO dto) {
		Service service = new Service();
		return buildEntity(service, dto);
	}
}
