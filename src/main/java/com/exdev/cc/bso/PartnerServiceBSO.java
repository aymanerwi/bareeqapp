package com.exdev.cc.bso;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.PartnerDAO;
import com.exdev.cc.dao.PartnerServiceDAO;
import com.exdev.cc.dao.ServiceDAO;
import com.exdev.cc.model.Partner;
import com.exdev.cc.model.PartnerService;
import com.exdev.cc.model.Service;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.PartnerServiceDTO;

public class PartnerServiceBSO extends BaseBSO {

	public static PartnerServiceDTO toProviderServiceDTO(PartnerService ps) {
		PartnerServiceDTO dto = new PartnerServiceDTO(ps.getId(), ps.getName(), ps.getNotes());
		dto.setProvider(PartnerBSO.toPartnerDTO(ps.getPartner()));
		dto.setService(ServiceBSO.toServiceDTO(ps.getService()));
		dto.setPrice(ps.getPrice());
		return dto;
	}

	public PartnerServiceBSO() throws BSOException {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		PartnerServiceDTO psdto = (PartnerServiceDTO) dto;
		PartnerService ps = (PartnerService) o;
		ps.setName(dto.getName());
		ps.setNotes(dto.getNotes());
		ps.setPrice(psdto.getPrice());
		PartnerDAO pdao = new PartnerDAO();
		try {
			Partner p = (Partner) pdao.find(psdto.getId());
			ps.setPartner(p);
			ServiceDAO sdao = new ServiceDAO();
			Service s = (Service) sdao.find(psdto.getService().getId());
			ps.setService(s);
		} catch (Exception e) {
			throw new BSOException(e);
		}
		return ps;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new PartnerServiceDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) {
		PartnerService ps = (PartnerService) entity;
		return toProviderServiceDTO(ps);
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {
		PartnerServiceDTO psdto = (PartnerServiceDTO) dto;
		PartnerService ps = new PartnerService();
		ps.setId(dto.getId());
		ps.setName(dto.getName());
		ps.setNotes(dto.getNotes());
		ps.setPrice(psdto.getPrice());
		PartnerDAO pdao = new PartnerDAO();
		try {
			Partner p = (Partner) pdao.find(psdto.getId());
			ps.setPartner(p);
			ServiceDAO sdao = new ServiceDAO();
			Service s = (Service) sdao.find(psdto.getService().getId());
			ps.setService(s);
		} catch (Exception e) {
			throw new BSOException(e);
		}
		return ps;
	}

}
