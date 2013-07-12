package com.landlordapp.webservice.data;

import org.junit.Test;

public class HibernateSessionFactoryTest {
	
	@Test
	public void testSessionFactory() {
		HibernateSessionFactory.getSession();
	}
	
}
