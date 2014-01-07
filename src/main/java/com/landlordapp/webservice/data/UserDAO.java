package com.landlordapp.webservice.data;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.domain.User;

public class UserDAO extends HibernateDaoSupport {

	@Transactional(propagation = Propagation.REQUIRED)
	public User save(final User transientInstance) {
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return transientInstance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findById(final Long id) {
		try {
			User instance = (User) getHibernateTemplate().get("com.landlordapp.webservice.domain.User", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(final User transientInstance) {
		try {
			getHibernateTemplate().delete(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public User findByEmail(final String email) {
		try {
			String queryString = "from com.landlordapp.webservice.domain.User as model where model.email=?";
			Object[] values = { email };
			List<User> userList = getHibernateTemplate().find(queryString, values);
			if (userList.size() == 0) {
				return null;
			}
			return userList.get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findAll() {
		try {
			return getHibernateTemplate().find("from com.landlordapp.webservice.domain.User");
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
