package com.exdev.cc.bso;

import java.util.ArrayList;
import java.util.List;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.CustomerCarDAO;
import com.exdev.cc.dao.CustomerDAO;
import com.exdev.cc.model.CarType;
import com.exdev.cc.model.Customer;
import com.exdev.cc.model.CustomerCar;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.CarTypeDTO;
import com.exdev.cc.web.dto.CustomerCarDTO;

public class CustomerCarsBSO extends BaseBSO {

	public static CustomerCar toCustomerCar(CustomerCarDTO cdto) throws BSOException {
		if (cdto == null)
			return null;
		CustomerCar cc = null;

		try {
			CustomerCarDAO ccDao = new CustomerCarDAO();
			cc = (CustomerCar) ccDao.getEm().find(CustomerCar.class, cdto.getId());
			if (cc == null) {
				throw new Exception("Error: null customer car");
			}
			cc.setId(cdto.getId());
			cc.setName(cdto.getName());
			cc.setNotes(cdto.getNotes());
			cc.setPlateNo(cdto.getPlateNo());
			if (cdto.getCarType() != null) {
				CarType ct = new CarType();
				ct.setId(cdto.getId());
				ct.setName(cdto.getCarType().getName());
				cc.setCarType(ct);
			}
			cc.setModelYear(cdto.getModelYear());
			cc.setModel(cdto.getModel());
			cc.setMake(cdto.getMake());
			ccDao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException("Error: cannot find customer car by id", e);
		}
		return cc;
	}

	public static CustomerCarDTO toCustomerCarDTO(CustomerCar cc) {
		if (cc == null)
			return null;
		CustomerCarDTO dto = new CustomerCarDTO();
		dto.setCustomer(CustomerBSO.toCustomerDTO(cc.getCustomer()));
		dto.setId(cc.getId());
		dto.setName(cc.getName());
		dto.setNotes(cc.getNotes());
		dto.setPlateNo(cc.getPlateNo());
		if (cc.getCarType() != null) {
			dto.setCarType(new CarTypeDTO(cc.getCarType().getId(), cc.getCarType().getName()));
		}
		dto.setModelYear(cc.getModelYear());
		dto.setModel(cc.getModel());
		dto.setMake(cc.getMake());
		return dto;
	}

	public CustomerCarsBSO() throws BSOException {

	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		CustomerCarDTO cdto = (CustomerCarDTO) dto;
		CustomerCar cc = (CustomerCar) o;
		Customer c = (Customer) dao.getEm().find(Customer.class, cdto.getCustomer().getId());
		cc.setCustomer(c);
		cc.setId(dto.getId());
		cc.setName(dto.getName());
		cc.setNotes(dto.getNotes());
		cc.setPlateNo(cdto.getPlateNo());
		if (cdto.getCarType() != null) {
			cc.setCarType(CarTypeBSO.getCarType(cdto.getCarType()));
		}
		cc.setModel(cdto.getModel());
		cc.setMake(cdto.getMake());
		cc.setModelYear(cdto.getModelYear());
		return cc;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new CustomerCarDAO();
	}

	public List list(int cutomerid) {
		Customer customer = (Customer) dao.getEm().find(Customer.class, cutomerid);
		if (customer == null)
			return null;
		List<CustomerCarDTO> cars = new ArrayList<>(customer.getCustomerCars().size());
		for (CustomerCar cc : customer.getCustomerCars()) {
			cars.add((CustomerCarDTO) toDTO(cc));
		}
		return cars;
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		return toCustomerCarDTO((CustomerCar) entity);
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		CustomerCarDTO cdto = (CustomerCarDTO) dto;
		CustomerCar cc = new CustomerCar();
		buildEntity(cc, cdto);
		return cc;
	}

}
