package com.exdev.cc.bso;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.PartnerDAO;
import com.exdev.cc.model.Partner;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.PartnerDTO;

public class PartnerBSO extends BaseBSO {

	public static PartnerDTO toPartnerDTO(Partner p) {
		if (p == null)
			return null;
		PartnerDTO dto = new PartnerDTO(p.getId(), p.getName(), p.getMobileNo());
		return dto;
	}

	public PartnerBSO() throws BSOException {

	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		PartnerDTO pdto = (PartnerDTO) dto;
		Partner provider = (Partner) o;
		provider.setName(dto.getName());
		provider.setMobileNo(pdto.getMobileNo());
		provider.setNotes(dto.getNotes());
		return provider;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new PartnerDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		Partner p = (Partner) entity;
		return toPartnerDTO(p);
	}

	@Override
	protected Object toEntity(BaseDTO dto) {
		PartnerDTO pdto = (PartnerDTO) dto;
		Partner provider = new Partner();
		provider.setName(dto.getName());
		provider.setId(dto.getId());
		provider.setMobileNo(pdto.getMobileNo());
		provider.setNotes(dto.getNotes());
		return provider;
	}

}
