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

	@SuppressWarnings("rawtypes")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Property findById(final Long id, final String userId) {
		try {
			Object[] values = {id, userId};
			String queryString = "from com.landlordapp.webservice.domain.Property as model where model.id = ? and model.userId = ?";
			List properties = getHibernateTemplate().find(queryString, values);
			if(properties.size() > 0) {
				return (Property) properties.get(0);
			}
			return null;
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
	public List<Property> findAll(String userId) {
		try {
			String queryString = "from com.landlordapp.webservice.domain.Property as model where model.userId = ?";
			Object[] values = {userId};
			return getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
