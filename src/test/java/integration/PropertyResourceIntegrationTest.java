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
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.landlordapp.webservice.rest.PropertyResource;
import com.landlordapp.webservice.service.PropertyService;

@Ignore
public class PropertyResourceIntegrationTest {
	private static final String ADDRESS = "33660 Test Ln.";
	private static final String ADDRESS_UPDATED = "1234 Updated Test Ln.";
	private static final String USER_ID = "userId";
	private PropertyResource resource;
	private PropertyService propertyService;
	private List<JSONObject> propertiesToDelete;
	private ApplicationContext appContext;
	
	@Before
	public void setUp() {
		//TODO Find a more spring like way to do this.
		appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		propertyService = (PropertyService) appContext.getBean("propertyService");
		resource = new PropertyResource();
		resource.setPropertyService(propertyService);
		
		propertiesToDelete = new ArrayList<JSONObject>();
	}
	
	@After
	public void cleanUp() throws JSONException, IllegalArgumentException, IllegalAccessException{
		for(JSONObject property:propertiesToDelete) {
			propertyService.delete(property);
		}
	}
	
	@Test
	public void createShouldCreateAProperty() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject property = resource.createProperty(createPropertyForTest(ADDRESS), USER_ID);
		
		assertNotNull(property.getLong("id"));
		assertEquals(property.getString("address"), ADDRESS);
		
		JSONObject propertyFromDatabase = propertyService.findOne(property.getLong("id"), USER_ID);
		assertNotNull(propertyFromDatabase);
		assertEquals(propertyFromDatabase.getString("address"), ADDRESS);
		
		propertiesToDelete.add(property);
	}
	
	@Test
	public void updateShoudUpdateAProperty() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject property = propertyService.create(createPropertyForTest(ADDRESS));
		
		property.put("address", ADDRESS_UPDATED);
		property = resource.updateProperty(property, USER_ID);
		assertEquals(property.getString("address"), ADDRESS_UPDATED);
		
		JSONObject propertyFromDatabase = propertyService.findOne(property.getLong("id"), USER_ID);
		assertNotNull(propertyFromDatabase);
		assertEquals(propertyFromDatabase.getString("address"), ADDRESS_UPDATED);
		
		propertiesToDelete.add(property);
	}
	
	@Test
	public void findOneShouldReturnAProperty() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject property = propertyService.create(createPropertyForTest(ADDRESS));
		
		JSONObject propertyFromDatabase = resource.findProperty(property.getLong("id"), USER_ID);
		assertEquals(propertyFromDatabase.get("address"), ADDRESS);
		
		propertiesToDelete.add(property);
	}
	
	@Test
	public void findOneShouldReturnNullWhenNoPropertyFound() throws JSONException, IllegalArgumentException, IllegalAccessException {
		assertNull(resource.findProperty(1001L, USER_ID));
	}
	
	@Test
	public void findAllShouldReturnAListProperty() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject property1 = propertyService.create(createPropertyForTest(ADDRESS));
		JSONObject property2 = propertyService.create(createPropertyForTest(ADDRESS_UPDATED));
		
		JSONArray propertiesFromDatabase = resource.findAllProperties(USER_ID);
		
		assertEquals(propertiesFromDatabase.length(), 2);
		assertEquals(propertiesFromDatabase.getJSONObject(0).getString("address"), ADDRESS);
		assertEquals(propertiesFromDatabase.getJSONObject(1).getString("address"), ADDRESS_UPDATED);
		
		propertiesToDelete.add(property1);
		propertiesToDelete.add(property2);
	}
	
	@Test
	public void deleteShouldRemoveAProperty() throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject property = propertyService.create(createPropertyForTest(ADDRESS));
		JSONObject deleteResponse = resource.deleteProperty(property.getLong("id"), USER_ID);
		
		assertEquals(deleteResponse.getInt("success"), 1);
		assertNull(resource.findProperty(property.getLong("id"), USER_ID));
	}
	
	
	private JSONObject createPropertyForTest(String address) throws JSONException {
		JSONObject newProperty = new JSONObject();
		newProperty.put("address", address);
		newProperty.put("userId", USER_ID);
		return newProperty;
	}
}
