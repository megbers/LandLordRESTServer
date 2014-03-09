package com.landlordapp.webservice.data;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.domain.Miles;

public class MilesDAO extends HibernateDaoSupport {
	@Transactional(propagation=Propagation.REQUIRED)
	public Miles save(Miles transientInstance) {
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return transientInstance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//TODO Take into effect the userId
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Miles findById(Long id, String userId) {
		try {
			Miles instance = (Miles) getHibernateTemplate().get("com.landlordapp.webservice.domain.Miles", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Miles transientInstance) {
		try {
			getHibernateTemplate().delete(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Miles> findAll(String userId) {
		try {
			String queryString = "from com.landlordapp.webservice.domain.Miles as model where model.userId = ?";
			Object[] values = {userId};
			return getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List findByProperty(Long propertyId, String userId){
		try {
			String queryString = "from Miles as model where model.property.id= ? and model.userId = ?";
			Object[] values = {propertyId, userId};
			return getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
