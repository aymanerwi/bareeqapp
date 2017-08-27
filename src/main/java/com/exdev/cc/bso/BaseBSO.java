package com.exdev.cc.bso;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.exdev.cc.dao.BaseDAO;
import com.exdev.cc.web.dto.BaseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseBSO {

	protected BaseDAO dao;

	public BaseBSO() throws BSOException {
		try {
			dao = createDAO();
		} catch (BSOException e) {
			throw new BSOException("Error: cannot create DAO", e);
		}
	}

	protected abstract Object buildEntity(Object o, BaseDTO dto) throws BSOException;

	public void closeService() throws BSOException {
		try {
			dao.closeEntityManager();
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot close entity manager", e);
		}
	}

	public void create(BaseDTO dto) throws BSOException {
		Object obj = toEntity(dto);
		try {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("<----CREATE OBJECT:----> " + mapper.writeValueAsString(dto));
			dao.startTransaction();
			dao.save(obj);
			dao.commitTransaction();

			dto.setId((int) PropertyUtils.getProperty(obj, "id"));
		} catch (Exception e) {
			try {
				dao.rollbackTransaction();
			} catch (Exception e1) {
				throw new BSOException("Error BSO: cannot rollback transaction", e);
			}
			throw new BSOException("Error BSO: cannot save entity", e);
		}
	}

	protected abstract BaseDAO createDAO() throws BSOException;

	public void delete(int id) throws BSOException {
		try {

			Object o = dao.find(id);
			if (o != null) {
				dao.startTransaction();
				dao.delete(o);
				dao.commitTransaction();
			}
		} catch (Exception e) {
			try {
				dao.rollbackTransaction();
			} catch (Exception e1) {
				throw new BSOException("Error BSO: cannot rollback transaction", e);
			}
			throw new BSOException("Error BSO: cannot delete object", e);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		closeService();
	}

	public BaseDTO get(int id) throws BSOException {
		try {
			Object entity = dao.find(id);
			if (entity == null)
				return null;
			return toDTO(entity);
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot get object", e);
		}

	}

	public List<BaseDTO> list() throws BSOException {
		try {
			List<Object> objs = dao.list();
			List<BaseDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add(toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List<BaseDTO> list(String query) throws BSOException {
		try {
			List<Object> objs = dao.list(query);
			List<BaseDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add(toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	public List<BaseDTO> list(int start, int max) throws BSOException {
		if (start == 0 || max == 0)
			return this.list();
		try {
			List<Object> objs = dao.list(start, max);
			List<BaseDTO> dtos = new ArrayList<>(objs.size());
			for (Object o : objs) {
				dtos.add(toDTO(o));
			}

			return dtos;
		} catch (Exception e) {
			throw new BSOException("Error BSO: cannot list objects", e);
		}
	}

	protected abstract BaseDTO toDTO(Object entity) throws BSOException;

	protected abstract Object toEntity(BaseDTO dto) throws BSOException;

	public void update(int id, BaseDTO dto) throws BSOException {

		try {
			Object o = dao.find(id);
			if (o == null)
				throw new BSOException("Entity not found!");
			
			dto.setId(id);
			Object obj = buildEntity(o, dto);
			dao.startTransaction();
			dao.save(obj);
			dao.commitTransaction();
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
