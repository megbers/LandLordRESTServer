package com.landlordapp.webservice.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import com.landlordapp.webservice.domain.type.ExpenseType;

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
	public static final String EXPENSE_TYPE = "expenseType";

	public Long id;
	public Double amountTotal;
	public Double amountPaid;
	public Boolean paid;
	public Property property;
	public Date enteredDate;
	public Date dueDate;
	public Date paidDate;
	public String description;
	public ExpenseType expenseType;

	public Expense() {
	}

	public Expense(final JSONObject json) {
		this.id = getLong(json, ID);
		this.amountTotal = getDouble(json, AMOUNT_TOTAL);
		this.amountPaid = getDouble(json, AMOUNT_PAID);
		this.paid = getBoolean(json, PAID);
		this.enteredDate = getDate(json, ENTERED_DATE, "yyyy-MM-dd");
		this.dueDate = getDate(json, DUE_DATE, "yyyy-MM-dd");
		this.paidDate = getDate(json, PAID_DATE, "yyyy-MM-dd");
		this.description = getString(json, DESCRIPTION);
		this.expenseType = ExpenseType.valueOf(getString(json, EXPENSE_TYPE));

		// TODO Handle Property
		// Should I have the front end submit the entire property, too?
		try {
			this.property = new Property(json.getJSONObject(PROPERTY));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.property.setId(getLong(json, PROPERTY));
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = ID, unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Column(name = "amount_total")
	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(final Double amountTotal) {
		this.amountTotal = amountTotal;
	}

	@Column(name = "amount_paid")
	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(final Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Column(name = PAID)
	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(final Boolean paid) {
		this.paid = paid;
	}

	@Column(name = "entered_date")
	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(final Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	@Column(name = "due_date")
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(final Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "paid_date")
	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(final Date paidDate) {
		this.paidDate = paidDate;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "property_id", nullable = true)
	public Property getProperty() {
		return property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(final ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put(ID, id);
		object.put(AMOUNT_TOTAL, amountTotal);
		object.put(AMOUNT_PAID, amountPaid);
		object.put(PAID, paid);
		object.put(ENTERED_DATE, formatDate(enteredDate, "yyyy-MM-dd"));
		object.put(DUE_DATE, formatDate(dueDate, "yyyy-MM-dd"));
		object.put(PAID_DATE, formatDate(paidDate, "yyyy-MM-dd"));
		object.put(DESCRIPTION, description);
		object.put(EXPENSE_TYPE, expenseType);

		if (property != null) {
			object.put(PROPERTY, property.toJSONObject());
		}

		return object;
	}

}
