package com.exdev.cc.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.exdev.cc.model.User;

public class UserDAO extends BaseDAO {

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	public User findByUsername(String username) {
		try {
			return (User) em.createNamedQuery("User.findByUsername").setParameter("username", username)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<User> findByMobileNo(String mobileNo) {
		try {
			return (List) em.createNamedQuery("User.findByMobileNo").setParameter("mobileNo", mobileNo).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Object find(int id) throws Exception {
		return find(User.class, id);
	}

	@Override
	public List<Object> list() {
		return em.createNamedQuery("User.findAll").getResultList();
	}

	@Override
	public List<Object> list(int start, int max) {
		return em.createNamedQuery("User.findAll").setFirstResult(start).setMaxResults(max).getResultList();
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

	public User findByToken(String token) {
		return (User) em.createNamedQuery("User.findByToken").setParameter("token", token).getSingleResult();

	}
	
	public User findByUUID(String uuid) {
		return (User) em.createNamedQuery("User.findByUUID").setParameter("uuid", uuid).getSingleResult();

	}

	public List<User> findByAdmin(boolean admin) {
		return em.createNamedQuery("User.findByAdmin").setParameter("admin", admin).getResultList();

	}

	public List<User> findByStaff(boolean staff) {
		return em.createNamedQuery("User.findByStaff").setParameter("staff", staff).getResultList();

	}

	public List<User> findByCustomer(boolean customer) {
		return em.createNamedQuery("User.findByCustomer").setParameter("customer", customer).getResultList();

	}

}
