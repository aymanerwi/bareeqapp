package com.exdev.cc.bso;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.dao.ExtraServiceDAO;
import com.exdev.cc.model.ExtraService;
import com.exdev.cc.web.dto.BaseDTO;
import com.exdev.cc.web.dto.ExtraServiceDTO;

public class ExtraServiceBSO extends BaseBSO {

	public ExtraServiceBSO() throws BSOException {
		super();

	}

	@Override
	protected Object buildEntity(Object o, BaseDTO dto) throws BSOException {
		ExtraService es = (ExtraService) o;
		ExtraServiceDTO esdto = (ExtraServiceDTO) dto;
		es.setId(esdto.getId());
		es.setName(esdto.getName());
		es.setNotes(esdto.getNotes());
		es.setPrice(esdto.getPrice());

		return es;
	}

	@Override
	protected BaseDAO createDAO() throws BSOException {
		return new ExtraServiceDAO();
	}

	@Override
	protected BaseDTO toDTO(Object entity) throws BSOException {
		ExtraService es = (ExtraService) entity;
		return new ExtraServiceDTO(es.getId(), es.getName(), es.getNotes(), es.getPrice());
	}

	@Override
	protected Object toEntity(BaseDTO dto) throws BSOException {

		return buildEntity(new ExtraService(), dto);
	}

	public static ExtraService toExtraService(ExtraServiceDTO dto) throws BSOException {
		ExtraServiceBSO bso = new ExtraServiceBSO();
		ExtraService es = (ExtraService) bso.toEntity(dto);
		bso.closeService();
		return es;
	}

	public static ExtraServiceDTO toExtraServiceDTO(ExtraService es) {
		ExtraServiceDTO dto = new ExtraServiceDTO(es.getId(), es.getName(), es.getNotes(), es.getPrice());
		return dto;
	}

}
