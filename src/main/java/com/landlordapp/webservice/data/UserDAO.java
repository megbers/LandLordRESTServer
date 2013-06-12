package com.landlordapp.webservice.data;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.domain.User;

public class UserDAO extends HibernateDaoSupport {
	
	@Transactional(propagation=Propagation.REQUIRED)
	public User save(User transientInstance) {
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return transientInstance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public User findById(Long id) {
		try {
			User instance = (User) getHibernateTemplate().get("com.landlordapp.webservice.domain.User", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(User transientInstance) {
		try {
			getHibernateTemplate().delete(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> findAll() {
		try {
			return getHibernateTemplate().find("from com.landlordapp.webservice.domain.User");
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
