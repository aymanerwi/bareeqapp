package com.exdev.cc.dao;

import java.util.List;

import com.exdev.cc.model.Partner;

public class PartnerDAO extends BaseDAO {

	private static final String PROVIDER_FIND_ALL = "Partner.findAll";

	public PartnerDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Object> list() {
		return em.createNamedQuery(PROVIDER_FIND_ALL).getResultList();
	}

	@Override
	public List<Object> list(int start, int max) {

		return em.createNamedQuery(PROVIDER_FIND_ALL).setFirstResult(start).setMaxResults(max).getResultList();
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

	@Override
	public Object find(int id) throws Exception {
		return find(Partner.class, id);
	}

}
