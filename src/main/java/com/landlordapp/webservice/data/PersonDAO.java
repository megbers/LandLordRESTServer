package com.landlordapp.webservice.data;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.domain.Person;

public class PersonDAO extends HibernateDaoSupport {
	@Transactional(propagation = Propagation.REQUIRED)
	public Person save(final Person transientInstance) {
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return transientInstance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Person findById(final Long id, String userId) {
		try {
			Person instance = (Person) getHibernateTemplate().get("com.landlordapp.webservice.domain.Person", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(final Person transientInstance) {
		try {
			getHibernateTemplate().delete(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Person> findAll(String userId) {
		try {
			String queryString = "from com.landlordapp.webservice.domain.Person as model where model.userId = ?";
			Object[] values = {userId};
			return getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List findByProperty(final Long propertyId, String userId) {
		try {
			String queryString = "from com.landlordapp.webservice.domain.Person as model where model.property.id= ? and model.userId = ?";
			Object[] values = { propertyId, userId };
			return getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
