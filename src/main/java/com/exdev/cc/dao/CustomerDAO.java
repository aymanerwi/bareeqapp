package com.exdev.cc.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.exdev.cc.model.Customer;
import com.exdev.cc.model.User;

public class CustomerDAO extends BaseDAO {

	public CustomerDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object find(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public List<Object> list() {
		return em.createNamedQuery("Customer.findAll").getResultList();
	}

	@Override
	public List<Object> list(int start, int max) {
		return em.createNamedQuery("Customer.findAll").setFirstResult(start).setMaxResults(max).getResultList();
	}

	@Override
	public List<Object> list(String query) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Customer.search").setParameter("query", "%".concat(query).concat("%"))
				.getResultList();
	}

	@Override
	public List<Object> list(String string, int start, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer findByMobileNo(String mobileNo) {
		try {
			return (Customer) em.createNamedQuery("Customer.findByMobileNo").setParameter("mobileNo", mobileNo)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
