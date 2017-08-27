package com.exdev.cc.bso;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.CarTypeDAO;
import com.exdev.cc.model.CarType;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.CarTypeDTO;

public class CarTypeBSO extends BaseBSO {

	public CarTypeBSO() throws BSOException {
		super();
		
	}
	
	public static CarType getCarType(CarTypeDTO dto) throws BSOException {
		CarType ct;
		try {
			CarTypeDAO dao = new CarTypeDAO();
			ct = (CarType) dao.find(dto.getId());
			dao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException("Error: cannot find CarType", e);
		}
		return ct;
	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		CarType ct = (CarType) o;
		ct.setId(dto.getId());
		ct.setName(dto.getName());
		return ct;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new CarTypeDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) throws BSOException {
		CarType ct = (CarType) entity;

		return new CarTypeDTO(ct.getId(), ct.getName());
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		return buildEntity(new CarType(), dto);
	}

}
