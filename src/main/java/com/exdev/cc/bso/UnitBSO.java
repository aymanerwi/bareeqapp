package com.exdev.cc.bso;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.PartnerDAO;
import com.exdev.cc.dao.UnitDAO;
import com.exdev.cc.model.Partner;
import com.exdev.cc.model.Unit;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.UnitDTO;

public class UnitBSO extends BaseBSO {

	public static UnitDTO toUnitDTO(Unit pc) {
		UnitDTO pcDto = new UnitDTO(pc.getId(), pc.getName(), pc.getNotes(), pc.getMobileNo(), pc.getPlateNo(),
				pc.getModel());
		pcDto.setPartner(PartnerBSO.toPartnerDTO(pc.getPartner()));
		return pcDto;
	}

	public UnitBSO() throws BSOException {

	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		UnitDTO pcDto = (UnitDTO) dto;

		Unit pc = (Unit) o;

		pc.setName(dto.getName());
		pc.setMobileNo(pcDto.getMobileNo());
		pc.setModel(pcDto.getModel());
		pc.setNotes(dto.getNotes());
		pc.setPlateNo(pcDto.getPlateNo());
		PartnerDAO dao = new PartnerDAO();
		try {
			Partner p = (Partner) dao.find(pcDto.getPartner().getId());
			pc.setPartner(p);
		} catch (Exception e) {
			throw new BSOException(e);
		}

		return pc;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new UnitDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		Unit pc = (Unit) entity;
		return toUnitDTO(pc);
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		UnitDTO pcDto = (UnitDTO) dto;

		Unit pc = new Unit();

		pc.setId(dto.getId());
		pc.setName(dto.getName());
		pc.setMobileNo(pcDto.getMobileNo());
		pc.setModel(pcDto.getModel());
		pc.setNotes(dto.getNotes());
		pc.setPlateNo(pcDto.getPlateNo());
		PartnerDAO dao = new PartnerDAO();
		try {
			Partner p = (Partner) dao.find(pcDto.getPartner().getId());
			pc.setPartner(p);
		} catch (Exception e) {
			throw new BSOException(e);
		}

		return pc;
	}

}
