package com.landlordapp.webservice.data;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.domain.Person;

public class PersonDAO extends HibernateDaoSupport {
	@Transactional(propagation=Propagation.REQUIRED)
	public Person save(Person transientInstance) {
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return transientInstance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Person findById(Long id) {
		try {
			Person instance = (Person) getHibernateTemplate().get("com.landlordapp.webservice.domain.Person", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Person transientInstance) {
		try {
			getHibernateTemplate().delete(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Person> findAll() {
		try {
			return getHibernateTemplate().find("from com.landlordapp.webservice.domain.Person");
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
