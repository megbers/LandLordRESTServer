package integration;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.landlordapp.webservice.rest.PropertyResource;
import com.landlordapp.webservice.service.PropertyService;

public class PropertyResourceIntegrationTest {
	private static final String ADDRESS = "33660 Test Ln.";
	private static final String ADDRESS_UPDATED = "1234 Updated Test Ln.";
	private PropertyResource resource;
	private PropertyService propertyService;
	private List<JSONObject> propertiesToDelete;
	
	@Before
	public void setUp() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		propertyService = (PropertyService) appContext.getBean("propertyService");
		resource = new PropertyResource();
		resource.setPropertyService(propertyService);
		
		propertiesToDelete = new ArrayList<JSONObject>();
	}
	
	@After
	public void cleanUp() throws JSONException {
		for(JSONObject property:propertiesToDelete) {
			propertyService.delete(property);
		}
	}
	
	@Test
	public void createShouldCreateAProperty() throws JSONException {
		JSONObject property = resource.createProperty(createPropertyForTest(ADDRESS));
		
		assertNotNull(property.getLong("id"));
		assertEquals(property.getString("address"), ADDRESS);
		
		JSONObject propertyFromDatabase = propertyService.findOne(property.getString("id"));
		assertNotNull(propertyFromDatabase);
		assertEquals(propertyFromDatabase.getString("address"), ADDRESS);
		
		propertiesToDelete.add(property);
	}
	
	@Test
	public void updateShoudUpdateAProperty() throws JSONException {
		JSONObject property = propertyService.create(createPropertyForTest(ADDRESS));
		
		property.put("address", ADDRESS_UPDATED);
		property = resource.updateProperty(property);
		assertEquals(property.getString("address"), ADDRESS_UPDATED);
		
		JSONObject propertyFromDatabase = propertyService.findOne(property.getString("id"));
		assertNotNull(propertyFromDatabase);
		assertEquals(propertyFromDatabase.getString("address"), ADDRESS_UPDATED);
		
		propertiesToDelete.add(property);
	}
	
	@Test
	public void findOneShouldReturnAProperty() throws JSONException {
		JSONObject property = propertyService.create(createPropertyForTest(ADDRESS));
		
		JSONObject propertyFromDatabase = resource.findProperty(property.getString("id"));
		assertEquals(propertyFromDatabase.get("address"), ADDRESS);
		
		propertiesToDelete.add(property);
	}
	
	@Test
	public void findOneShouldReturnNullWhenNoPropertyFound() throws JSONException {
		assertNull(resource.findProperty("1001"));
	}
	
	@Test
	public void findAllShouldReturnAListProperty() throws JSONException {
		JSONObject property1 = propertyService.create(createPropertyForTest(ADDRESS));
		JSONObject property2 = propertyService.create(createPropertyForTest(ADDRESS_UPDATED));
		
		JSONArray propertiesFromDatabase = resource.findAllProperties();
		
		assertEquals(propertiesFromDatabase.length(), 2);
		assertEquals(propertiesFromDatabase.getJSONObject(0).getString("address"), ADDRESS);
		assertEquals(propertiesFromDatabase.getJSONObject(1).getString("address"), ADDRESS_UPDATED);
		
		propertiesToDelete.add(property1);
		propertiesToDelete.add(property2);
	}
	
	@Test
	public void deleteShouldRemoveAProperty() throws JSONException {
		JSONObject property = propertyService.create(createPropertyForTest(ADDRESS));
		JSONObject deleteResponse = resource.deleteProperty(property.getString("id"));
		
		assertEquals(deleteResponse.getInt("success"), 1);
		assertNull(resource.findProperty(property.getString("id")));
	}
	
	
	private JSONObject createPropertyForTest(String address) throws JSONException {
		JSONObject newProperty = new JSONObject();
		newProperty.put("address", address);
		return newProperty;
	}
}
