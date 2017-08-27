package com.exdev.cc.dao;

import java.util.List;

import com.exdev.cc.model.Staff;

public class StaffDAO extends BaseDAO {

	public StaffDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object find(int id) throws Exception {
		return em.find(Staff.class, id);
	}

	@Override
	public List<Object> list() throws Exception {
		return em.createNamedQuery("Staff.findAll").getResultList();
	}

	@Override
	public List<Object> list(int start, int max) throws Exception {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Staff.findAll").setFirstResult(start).setMaxResults(max).getResultList();
	}

	@Override
	public List<Object> list(String string) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> list(String string, int start, int max) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
