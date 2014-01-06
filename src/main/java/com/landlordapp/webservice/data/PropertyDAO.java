package com.landlordapp.webservice.data;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.domain.Property;

public class PropertyDAO extends HibernateDaoSupport {
	@Transactional(propagation = Propagation.REQUIRED)
	public Property save(final Property transientInstance) {
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return transientInstance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Property findById(final Long id) {
		try {
			Property instance = (Property) getHibernateTemplate().get("com.landlordapp.webservice.domain.Property", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(final Property transientInstance) {
		try {
			getHibernateTemplate().delete(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Property> findAll() {
		try {
			return getHibernateTemplate().find("from com.landlordapp.webservice.domain.Property");
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
