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
	private String id = "1001";
	private Long longId = new Long(parseLong(id));
	
	private JSONObject jsonProperty;
	
	@Before
	public void setUp() throws JSONException {
		jsonProperty = new JSONObject();
		jsonProperty.put("address", ADDRESS);
		jsonProperty.put("mortgage", MORTGAGE_ST);
		jsonProperty.put("tax", TAX_ST);
		//TODO Deal with Tenants
		//object.put("tenants", tenants);
		jsonProperty.put("escrow", ESCROW_ST);
		jsonProperty.put("securityDeposite", SECURITY_ST);
		jsonProperty.put("petDeposite", PET_ST);
		jsonProperty.put("rentPermit", PERMIT_ST);
		jsonProperty.put("leaseStart", LEASE_START_ST);
		jsonProperty.put("leaseEnd", LEASE_END_ST);
		jsonProperty.put("imageLocation", IMAGE_LOCATION);
		jsonProperty.put("currentRent", RENT_ST);
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
	}
	
	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException {
		Property property = new Property(jsonProperty);
		JSONObject actual = property.toJSONObject();
		
		assertEquals(actual.getString("address"), ADDRESS);
		assertEquals(actual.getString("mortgage"), MORTGAGE_ST);
		assertEquals(actual.getString("tax"), TAX_ST);
		assertEquals(actual.getString("imageLocation"), IMAGE_LOCATION);
		assertEquals(actual.getString("petDeposite"), PET_ST);
		assertEquals(actual.getString("securityDeposite"), SECURITY_ST);
		assertEquals(actual.getString("leaseStart"), LEASE_START_ST);
		assertEquals(actual.getString("leaseEnd"), LEASE_END_ST);
		assertEquals(actual.getString("rentPermit"), PERMIT_ST);
		assertEquals(actual.getString("escrow"), ESCROW_ST);
		assertEquals(actual.getString("currentRent"), RENT_ST);
	}
	
}
