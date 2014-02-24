package com.landlordapp.webservice.domain;

import static java.lang.Long.parseLong;
import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class PropertyTest {
	private static final String MORTGAGE_ST = "1234.56";
	private static final Double MORTGAGE = new Double(1234.56);
	private static final String RENT_ST = "3233.44";
	private static final Double RENT = new Double(3233.44);
	private static final Date LEASE_END = new Date(110, 9, 10);
	private static final String LEASE_END_ST = "10-10-2010";
	private static final Date LEASE_START = new Date(111, 10, 11);
	private static final String LEASE_START_ST = "11-11-2011";
	private static final Date PERMIT = new Date(112, 11, 12);
	private static final String PERMIT_ST = "12-12-2012";
	private static final Double PET = new Double(200.02);
	private static final String PET_ST = "200.02";
	private static final Double SECURITY = new Double(100.01);
	private static final String SECURITY_ST = "100.01";
	private static final Double TAX = new Double(20.01);
	private static final String TAX_ST = "20.01";
	private static final String IMAGE_LOCATION = "imageLocation";
	private static final String ADDRESS = "address";
	private static final String ESCROW_ST = "true";
	private static final Boolean ESCROW = new Boolean("true");
	private static final String USER_ID = "userId";
	private String id = "1001";
	private Long longId = new Long(parseLong(id));
	
	private JSONObject jsonProperty;
	
	@Before
	public void setUp() throws JSONException {
		jsonProperty = new JSONObject();
		jsonProperty.put(Property.ADDRESS, ADDRESS);
		jsonProperty.put(Property.MORTGAGE, MORTGAGE_ST);
		jsonProperty.put(Property.TAX, TAX_ST);
		//TODO Deal with Tenants
		//object.put("tenants", tenants);
		jsonProperty.put(Property.ESCROW, ESCROW_ST);
		jsonProperty.put(Property.SECURITY_DEPOSITE, SECURITY_ST);
		jsonProperty.put(Property.PET_DEPOSITE, PET_ST);
		jsonProperty.put(Property.RENT_PERMIT, PERMIT_ST);
		jsonProperty.put(Property.LEASE_START, LEASE_START_ST);
		jsonProperty.put(Property.LEASE_END, LEASE_END_ST);
		jsonProperty.put(Property.IMAGE_LOCATION, IMAGE_LOCATION);
		jsonProperty.put(Property.CURRENT_RENT, RENT_ST);
		jsonProperty.put(Property.USER_ID, USER_ID);
	}
	
	@Test 
	public void setterShouldAllWorkAsExpected() {
		Property property = new Property();
		
		property.setId(longId);
		property.setAddress(ADDRESS);
		property.setMortgage(MORTGAGE);
		property.setImageLocation(IMAGE_LOCATION);
		property.setPetDeposite(PET);
		property.setSecurityDeposite(SECURITY);
		property.setTax(TAX);
		property.setLeaseStart(LEASE_START);
		property.setLeaseEnd(LEASE_END);
		property.setRentPermit(PERMIT);
		property.setEscrow(ESCROW);
		property.setCurrentRent(RENT);
		property.setUserId(USER_ID);
		
		
		assertEquals(property.getId(), longId);
		assertEquals(property.getAddress(), ADDRESS);
		assertEquals(property.getMortgage(), MORTGAGE);
		assertEquals(property.getImageLocation(), IMAGE_LOCATION);
		assertEquals(property.getPetDeposite(), PET);
		assertEquals(property.getSecurityDeposite(), SECURITY);
		assertEquals(property.getTax(), TAX);
		assertEquals(property.getLeaseStart(), LEASE_START);
		assertEquals(property.getLeaseEnd(), LEASE_END);
		assertEquals(property.getRentPermit(), PERMIT);
		assertEquals(property.getEscrow(), ESCROW);
		assertEquals(property.getCurrentRent(), RENT);
		assertEquals(property.getUserId(), USER_ID);
	}
	
	@Test
	public void contructorShouldPopulateFromJSONObjectWithID() throws JSONException {
		jsonProperty.put("id", id);
		Property property = new Property(jsonProperty);
		
		assertEquals(property.getId(), longId);
		assertEquals(property.getAddress(), ADDRESS);
		assertEquals(property.getMortgage(), MORTGAGE);
		assertEquals(property.getImageLocation(), IMAGE_LOCATION);
		assertEquals(property.getPetDeposite(), PET);
		assertEquals(property.getSecurityDeposite(), SECURITY);
		assertEquals(property.getTax(), TAX);
		assertEquals(property.getLeaseStart(), LEASE_START);
		assertEquals(property.getLeaseEnd(), LEASE_END);
		assertEquals(property.getRentPermit(), PERMIT);
		assertEquals(property.getEscrow(), ESCROW);
		assertEquals(property.getCurrentRent(), RENT);
		assertEquals(property.getUserId(), USER_ID);
	}
	
	@Test
	public void contructorShouldPopulateFromJSONObjectWithoutID() throws JSONException {
		Property property = new Property(jsonProperty);
		assertEquals(property.getId(), null);
		assertEquals(property.getAddress(), ADDRESS);
		assertEquals(property.getMortgage(), MORTGAGE);
		assertEquals(property.getImageLocation(), IMAGE_LOCATION);
		assertEquals(property.getPetDeposite(), PET);
		assertEquals(property.getSecurityDeposite(), SECURITY);
		assertEquals(property.getTax(), TAX);
		assertEquals(property.getLeaseStart(), LEASE_START);
		assertEquals(property.getLeaseEnd(), LEASE_END);
		assertEquals(property.getRentPermit(), PERMIT);
		assertEquals(property.getEscrow(), ESCROW);
		assertEquals(property.getCurrentRent(), RENT);
		assertEquals(property.getUserId(), USER_ID);
	}
	
	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Property property = new Property(jsonProperty);
		JSONObject actual = property.toJSONObject();
		
		assertEquals(actual.getString(Property.ADDRESS), ADDRESS);
		assertEquals(actual.getString(Property.MORTGAGE), MORTGAGE_ST);
		assertEquals(actual.getString(Property.TAX), TAX_ST);
		assertEquals(actual.getString(Property.IMAGE_LOCATION), IMAGE_LOCATION);
		assertEquals(actual.getString(Property.PET_DEPOSITE), PET_ST);
		assertEquals(actual.getString(Property.SECURITY_DEPOSITE), SECURITY_ST);
		assertEquals(actual.getString(Property.LEASE_START), LEASE_START_ST);
		assertEquals(actual.getString(Property.LEASE_END), LEASE_END_ST);
		assertEquals(actual.getString(Property.RENT_PERMIT), PERMIT_ST);
		assertEquals(actual.getString(Property.ESCROW), ESCROW_ST);
		assertEquals(actual.getString(Property.CURRENT_RENT), RENT_ST);
	}
	
}
