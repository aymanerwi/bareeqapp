package com.exdev.cc.dao;

import java.util.List;

import com.exdev.cc.model.CustomerCar;

public class CustomerCarDAO extends BaseDAO {

	public CustomerCarDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object find(int id) throws Exception {
		return find(CustomerCar.class, id);
	}

	@Override
	public List<Object> list() {
		return em.createNamedQuery("CustomerCar.findAll").getResultList();
	}

	@Override
	public List<Object> list(int start, int max) {
		return em.createNamedQuery("CustomerCar.findAll").setFirstResult(start).setMaxResults(max).getResultList();
	}

	@Override
	public List<Object> list(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> list(String string, int start, int max) {
		// TODO Auto-generated method stub
		return null;
	}

}
