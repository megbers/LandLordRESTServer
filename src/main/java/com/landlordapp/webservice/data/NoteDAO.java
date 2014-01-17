package com.landlordapp.webservice.data;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.domain.Note;

public class NoteDAO extends HibernateDaoSupport {
	@Transactional(propagation=Propagation.REQUIRED)
	public Note save(Note transientInstance) {
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return transientInstance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Note findById(Long id) {
		try {
			Note instance = (Note) getHibernateTemplate().get("com.landlordapp.webservice.domain.Note", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Note transientInstance) {
		try {
			getHibernateTemplate().delete(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Note> findAll() {
		try {
			return getHibernateTemplate().find("from com.landlordapp.webservice.domain.Note");
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List findByProperty(Long propertyId){
		try {
			String queryString = "from Note as model where model.property.id= ?";
			Object[] values = {propertyId};
			return getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
