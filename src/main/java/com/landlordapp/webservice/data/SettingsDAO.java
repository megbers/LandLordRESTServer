package com.landlordapp.webservice.data;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.landlordapp.webservice.domain.Settings;

public class SettingsDAO extends HibernateDaoSupport {
	@Transactional(propagation=Propagation.REQUIRED)
	public Settings save(Settings transientInstance) {
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return transientInstance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Settings findById(Long id, String userId) {
		try {
			Object[] values = {id, userId};
			String queryString = "from com.landlordapp.webservice.domain.Settings as model where model.id = ? and model.userId = ?";
			List settingsList = getHibernateTemplate().find(queryString, values);
			if(settingsList.size() > 0) {
				return (Settings) settingsList.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
