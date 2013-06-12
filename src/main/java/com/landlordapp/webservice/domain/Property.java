package com.landlordapp.webservice.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "property", catalog = "landlord")
public class Property extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -3997672036235921630L;
	private Long id;
	private String address;
	private Double mortgage;
	private Double tax;
	private List<Person> tenants;
	private Boolean escrow;
	private Double securityDeposite;
	private Double petDeposite;
	private Double currentRent;
	private Date rentPermit;
	private Date leaseStart;
	private Date leaseEnd;
	private String imageLocation;
	
	public Property(){} 
	
	public Property(JSONObject json) throws JSONException {
		this.id = getLong(json, "id");
		this.address = getString(json, "address");
		this.mortgage = getDouble(json, "mortgage");
		this.tax = getDouble(json, "tax");
		this.escrow = getBoolean(json, "escrow");
		this.securityDeposite = getDouble(json, "securityDeposite");
		this.petDeposite = getDouble(json, "petDeposite");
		this.rentPermit = getDate(json, "rentPermit");
		this.leaseStart = getDate(json, "leaseStart");
		this.leaseEnd = getDate(json, "leaseEnd");
		this.imageLocation = getString(json, "imageLocation");
		this.currentRent = getDouble(json, "currentRent");
		//TODO Handle the tenants
		//this. = get(json, "");
		//private List<Person> tenants;
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "address", length = 50)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
	public List<Person> getTenants() {
		return tenants;
	}

	public void setTenants(List<Person> tenants) {
		this.tenants = tenants;
	}

	@Column(name = "escrow")
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

	@Column(name = "petDeposite")
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
		object.put("id", id);
		
		object.put("address", address);
		object.put("mortgage", mortgage);
		object.put("tax", tax);
		//TODO Deal with Tenants
		//object.put("tenants", tenants);
		object.put("escrow", escrow);
		object.put("securityDeposite", securityDeposite);
		object.put("petDeposite", petDeposite);
		object.put("rentPermit", formatDate(rentPermit));
		object.put("leaseStart", formatDate(leaseStart));
		object.put("leaseEnd", formatDate(leaseEnd));
		object.put("imageLocation", imageLocation);
		object.put("currentRent", currentRent);
		return object;
	}
	
}
