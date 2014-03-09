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
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Note findById(Long id, String userId) {
		try {
			Object[] values = {id, userId};
			String queryString = "from com.landlordapp.webservice.domain.Note as model where model.id = ? and model.userId = ?";
			List notes = getHibernateTemplate().find(queryString, values);
			if(notes.size() > 0) {
				return (Note) notes.get(0);
			}
			return null;
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
	public List<Note> findAll(String userId) {
		try {
			String queryString = "from com.landlordapp.webservice.domain.Note as model where model.userId = ?";
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
			String queryString = "from com.landlordapp.webservice.domain.Note as model where model.property.id= ? and model.userId = ?";
			Object[] values = {propertyId, userId};
			return getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
