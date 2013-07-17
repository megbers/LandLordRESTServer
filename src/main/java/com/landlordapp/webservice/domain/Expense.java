package com.landlordapp.webservice.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "expense", catalog = "landlord")
public class Expense extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 8945516435574549712L;

	public static final String PROPERTY = "property";
	public static final String PAID_DATE = "paidDate";
	public static final String DUE_DATE = "dueDate";
	public static final String ENTERED_DATE = "enteredDate";
	public static final String PAID = "paid";
	public static final String AMOUNT_PAID = "amountPaid";
	public static final String AMOUNT_TOTAL = "amountTotal";
	public static final String ID = "id";
	public static final String DESCRIPTION = "description";
	
	public Long id;
	public Double amountTotal;
	public Double amountPaid;
	public Boolean paid;
	public Property property;
	public Date enteredDate;
	public Date dueDate;
	public Date paidDate;
	public String description;
	
	public Expense() {}
	
	public Expense(JSONObject json) {
		this.id = getLong(json, ID);
		this.amountTotal = getDouble(json, AMOUNT_TOTAL);
		this.amountPaid = getDouble(json, AMOUNT_PAID);
		this.paid = getBoolean(json, PAID);
		//TODO Handle Property
		this.enteredDate = getDate(json, ENTERED_DATE);
		this.dueDate = getDate(json, DUE_DATE);
		this.paidDate = getDate(json, PAID_DATE);
		this.description = getString(json, DESCRIPTION);
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = ID, unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "amount_total")
	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

	@Column(name = "amount_paid")
	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Column(name = PAID)
	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	@Column(name = "entered_date")
	public Date getEnteredDate() {
		return enteredDate;
	}
	
	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	@Column(name = "due_date")
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name = "paid_date")
	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "property_id", nullable = true)
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(ID, id);
		object.put(AMOUNT_TOTAL, amountTotal);
		object.put(AMOUNT_PAID, amountPaid);
		object.put(PAID, paid);
		object.put(ENTERED_DATE, formatDate(enteredDate));
		object.put(DUE_DATE, formatDate(dueDate));
		object.put(PAID_DATE, formatDate(paidDate));
		object.put(DESCRIPTION, description);
		
		if(property != null) {
			object.put(PROPERTY, property.toJSONObject());
		}
		
		return object;
	}

}
