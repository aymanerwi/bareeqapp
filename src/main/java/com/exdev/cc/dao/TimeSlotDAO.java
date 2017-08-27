package com.exdev.cc.dao;

import java.util.List;

import com.exdev.cc.model.TimeSlot;

public class TimeSlotDAO extends BaseDAO {

	public TimeSlotDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object find(int id) throws Exception {
		return em.find(TimeSlot.class, id);
	}

	@Override
	public List<Object> list() throws Exception {

		return createQuery("TimeSlot.findAll").getResultList();
	}

	@Override
	public List<Object> list(int start, int max) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
