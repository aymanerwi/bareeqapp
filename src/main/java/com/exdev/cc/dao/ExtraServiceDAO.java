package com.exdev.cc.dao;

import java.util.List;

import com.exdev.cc.model.ExtraService;

public class ExtraServiceDAO extends BaseDAO {

	@Override
	public Object find(int id) throws Exception {
		return em.find(ExtraService.class, id);
	}

	@Override
	public List<Object> list() throws Exception {
		return em.createNamedQuery("ExtraService.findAll").getResultList();
	}

	@Override
	public List<Object> list(int start, int max) throws Exception {
		return list();
	}

	@Override
	public List<Object> list(String string) throws Exception {
		return list();
	}

	@Override
	public List<Object> list(String string, int start, int max) throws Exception {
		return list();
	}

}
