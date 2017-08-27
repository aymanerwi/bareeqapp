package com.exdev.cc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class BaseDAO {

	protected EntityManager em;

	public BaseDAO() {
		openEntityManager();
	}

	private EntityManager openEntityManager() {
		if (em == null || !em.isOpen())
			em = Persistence.createEntityManagerFactory("ccpu").createEntityManager();
		return em;
	}

	public Query createQuery(String query) {
		Query q = em.createNamedQuery(query);
		return q;
	}

	public void closeEntityManager() throws Exception {
		if (em.isOpen())
			em.close();
	}

	public void startTransaction() throws Exception {
		if (!em.getTransaction().isActive())
			em.getTransaction().begin();
	}

	public void commitTransaction() throws Exception {
		if (em.getTransaction().isActive())
			em.getTransaction().commit();
	}

	public void rollbackTransaction() throws Exception {
		if (em.getTransaction().isActive())
			em.getTransaction().rollback();
	}

	public boolean save(Object entity) throws Exception {
		if (entity == null)
			return false;
		em.persist(entity);
		em.flush();
		return true;
	}

	public boolean delete(int id) throws Exception {
		return this.delete(find(id));
	}

	public boolean delete(Object entity) throws Exception {
		em.remove(entity);
		return true;
	}

	protected Object find(Class clazz, int id) throws Exception {
		return em.find(clazz, id);
	}

	public abstract Object find(int id) throws Exception;

	public abstract List<Object> list() throws Exception;

	public abstract List<Object> list(int start, int max) throws Exception;

	public abstract List<Object> list(String string) throws Exception;

	public abstract List<Object> list(String string, int start, int max) throws Exception;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
