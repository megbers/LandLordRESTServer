package com.landlordapp.webservice.domain;

import static javax.persistence.FetchType.EAGER;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "property", catalog = "landlord")
public class Property extends BaseEntity implements java.io.Serializable {
	public static final String CURRENT_RENT = "currentRent";
	public static final String IMAGE_LOCATION = "imageLocation";
	public static final String LEASE_END = "leaseEnd";
	public static final String LEASE_START = "leaseStart";
	public static final String RENT_PERMIT = "rentPermit";
	public static final String PET_DEPOSITE = "petDeposite";
	public static final String SECURITY_DEPOSITE = "securityDeposite";
	public static final String ESCROW = "escrow";
	public static final String TAX = "tax";
	public static final String MORTGAGE = "mortgage";
	public static final String ADDRESS = "address";
	public static final String ID = "id";
	public static final String USER_ID = "userId";
	
	public static final long serialVersionUID = -3997672036235921630L;
	public Long id;
	public String address;
	public Double mortgage;
	public Double tax;
	public Set<Person> tenants = new HashSet<Person>();
	public Set<Expense> expense = new HashSet<Expense>();
	public Boolean escrow;
	public Double securityDeposite;
	public Double petDeposite;
	public Double currentRent;
	public Date rentPermit;
	public Date leaseStart;
	public Date leaseEnd;
	public String imageLocation;
	public String userId;
	
	public Property(){} 
	
	public Property(JSONObject json) throws JSONException {
		this.id = getLong(json, ID);
		this.address = getString(json, ADDRESS);
		this.mortgage = getDouble(json, MORTGAGE);
		this.tax = getDouble(json, TAX);
		this.escrow = getBoolean(json, ESCROW);
		this.securityDeposite = getDouble(json, SECURITY_DEPOSITE);
		this.petDeposite = getDouble(json, PET_DEPOSITE);
		this.rentPermit = getDate(json, RENT_PERMIT);
		this.leaseStart = getDate(json, LEASE_START);
		this.leaseEnd = getDate(json, LEASE_END);
		this.imageLocation = getString(json, IMAGE_LOCATION);
		this.currentRent = getDouble(json, CURRENT_RENT);
		this.userId = getString(json, USER_ID);

		//TODO Handle the tenants
		//this. = get(json, "");
		//private List<Person> tenants;
		//TODO Handle the expenses
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = ID, unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "user_id") 
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = ADDRESS, length = 50)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "current_mortage")
	public Double getMortgage() {
		return mortgage;
	}

	public void setMortgage(Double mortgage) {
		this.mortgage = mortgage;
	}

	@Column(name = "current_tax")
	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	//TODO Make this LAZY again
	@OneToMany(fetch = EAGER, mappedBy = "property")
	public Set<Person> getTenants() {
		return tenants;
	}

	public void setTenants(Set<Person> tenants) {
		this.tenants = tenants;
	}

	//TODO Make this LAZY again
	@OneToMany(fetch = EAGER, mappedBy = "property")
	public Set<Expense> getExpense() {
		return expense;
	}

	public void setExpense(Set<Expense> expense) {
		this.expense = expense;
	}

	@Column(name = ESCROW)
	public Boolean getEscrow() {
		return escrow;
	}

	public void setEscrow(Boolean escrow) {
		this.escrow = escrow;
	}

	@Column(name = "security_deposite")
	public Double getSecurityDeposite() {
		return securityDeposite;
	}

	public void setSecurityDeposite(Double securityDeposite) {
		this.securityDeposite = securityDeposite;
	}

	@Column(name = PET_DEPOSITE)
	public Double getPetDeposite() {
		return petDeposite;
	}

	public void setPetDeposite(Double petDeposite) {
		this.petDeposite = petDeposite;
	}
	
	@Column(name = "rent_permit")
	public Date getRentPermit() {
		return rentPermit;
	}

	public void setRentPermit(Date rentPermit) {
		this.rentPermit = rentPermit;
	}

	@Column(name = "lease_start")
	public Date getLeaseStart() {
		return leaseStart;
	}

	public void setLeaseStart(Date leaseStart) {
		this.leaseStart = leaseStart;
	}

	@Column(name = "lease_end")
	public Date getLeaseEnd() {
		return leaseEnd;
	}

	public void setLeaseEnd(Date leaseEnd) {
		this.leaseEnd = leaseEnd;
	}

	@Column(name = "email", length = 200)
	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	
	@Column(name = "current_rent", length = 200)
	public Double getCurrentRent() {
		return currentRent;
	}

	public void setCurrentRent(Double currentRent) {
		this.currentRent = currentRent;
	}

	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(ID, id);
		
		object.put(ADDRESS, address);
		object.put(MORTGAGE, mortgage);
		object.put(TAX, tax);
		object.put(ESCROW, escrow);
		object.put(SECURITY_DEPOSITE, securityDeposite);
		object.put(PET_DEPOSITE, petDeposite);
		object.put(RENT_PERMIT, formatDate(rentPermit));
		object.put(LEASE_START, formatDate(leaseStart));
		object.put(LEASE_END, formatDate(leaseEnd));
		object.put(IMAGE_LOCATION, imageLocation);
		object.put(CURRENT_RENT, currentRent);
		
		JSONArray array = new JSONArray();
		for(Person person:getTenants()) {
			array.put(person.toJSONObject());
		}
		object.put("tenants", array);
		
		return object;
	}
	
}
