package com.exdev.cc.dao;

import java.util.List;

import com.exdev.cc.model.CarType;

public class CarTypeDAO extends BaseDAO {

	@Override
	public Object find(int id) throws Exception {
		// TODO Auto-generated method stub
		return em.find(CarType.class, id);
	}

	@Override
	public List<Object> list() throws Exception {

		return em.createNamedQuery("CarType.findAll").getResultList();
	}

	@Override
	public List<Object> list(int start, int max) throws Exception {
		// TODO Auto-generated method stub
		return list();
	}

	@Override
	public List<Object> list(String string) throws Exception {
		// TODO Auto-generated method stub
		return list();
	}

	@Override
	public List<Object> list(String string, int start, int max) throws Exception {
		// TODO Auto-generated method stub
		return list();
	}

}
