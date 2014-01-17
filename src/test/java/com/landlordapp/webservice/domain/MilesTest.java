package com.landlordapp.webservice.domain;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.landlordapp.webservice.domain.type.MilesType;

public class MilesTest {
	private static final Long ID = 1L;
	private static final String ID_ST = "1";
	@SuppressWarnings("deprecation")
	private static final Date DATE = new Date(110, 9, 10);
	private static final String DATE_ST = "10-10-2010";
	private static final Long PROPERTY_ID = 300L;
	private static final String PROPERTY_ID_ST = "{id: 300}";
	private static final Double NUMBER_OF_MILES = 100.001D;
	private static final String NUMBER_OF_MILES_ST = "100.001";
	private static final String MILES_TYPE_ST = "BANK";

	private JSONObject jsonMiles;

	@Before
	public void setUp() throws JSONException {
		jsonMiles = new JSONObject();
		jsonMiles.put(Miles.DATE, DATE_ST);
		jsonMiles.put(Miles.NUMBER_OF_MILES, NUMBER_OF_MILES_ST);
		jsonMiles.put(Miles.PROPERTY, new JSONObject(PROPERTY_ID_ST));
		jsonMiles.put(Miles.MILES_TYPE, MILES_TYPE_ST);
	}

	@Test
	public void settersShouldAllWorkAsExpected() {
		Miles miles = new Miles();
		miles.setId(ID);
		miles.setDate(DATE);
		miles.setNumberOfMiles(NUMBER_OF_MILES);
		miles.setMilesType(MilesType.BANK);
		Property property = new Property();
		property.setId(PROPERTY_ID);
		miles.setProperty(property);

		assertEquals(miles.getId(), ID);
		assertEquals(miles.getDate(), DATE);
		assertEquals(miles.getProperty(), property);
		assertEquals(miles.getNumberOfMiles(), NUMBER_OF_MILES);
		assertEquals(miles.getMilesType(), MilesType.BANK);
	}

	@Test
	public void contructorShouldPopulateFromJSONObjectWithID() throws JSONException {
		jsonMiles.put("id", ID);
		Miles miles = new Miles(jsonMiles);

		assertEquals(miles.getId(), ID);
		assertEquals(miles.getDate(), DATE);
		assertEquals(miles.getNumberOfMiles(), NUMBER_OF_MILES);
		assertEquals(miles.getProperty().getId(), PROPERTY_ID);
		assertEquals(miles.getMilesType(), MilesType.BANK);
	}

	@Test
	public void contructorShouldPopulateFromJSONObjectWithoutID() throws JSONException {
		Miles miles = new Miles(jsonMiles);

		assertEquals(miles.getDate(), DATE);
		assertEquals(miles.getNumberOfMiles(), NUMBER_OF_MILES);
		assertEquals(miles.getProperty().getId(), PROPERTY_ID);
		assertEquals(miles.getMilesType(), MilesType.BANK);
	}

	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObject() throws JSONException, IllegalArgumentException, IllegalAccessException {
		jsonMiles.put("id", ID);
		Miles miles = new Miles(jsonMiles);
		JSONObject actual = miles.toJSONObject();

		assertEquals(actual.getString(Miles.ID), ID_ST);
		assertEquals(actual.getString(Miles.DATE), DATE_ST);
		assertEquals(actual.getString(Miles.NUMBER_OF_MILES), NUMBER_OF_MILES_ST);
		assertEquals(actual.getString(Miles.MILES_TYPE), MILES_TYPE_ST);
	}

	@Test
	public void toJSONObjectShouldReturnAJSONRepresentationOfObjectWithPropertyIfPresent() throws JSONException, IllegalArgumentException, IllegalAccessException {
		jsonMiles.put("id", ID);
		Miles miles = new Miles(jsonMiles);
		miles.setProperty(new Property());
		JSONObject actual = miles.toJSONObject();

		assertEquals(actual.getJSONObject(Miles.PROPERTY).toString(), "{\"tenants\":[]}");
	}

}
