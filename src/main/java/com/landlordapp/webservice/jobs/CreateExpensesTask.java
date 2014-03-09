package com.landlordapp.webservice.jobs;

import static com.landlordapp.webservice.domain.type.ExpenseType.MORTGAGE;
import static com.landlordapp.webservice.domain.type.ExpenseType.RENT;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.landlordapp.webservice.data.ExpenseDAO;
import com.landlordapp.webservice.data.PropertyDAO;
import com.landlordapp.webservice.data.UserDAO;
import com.landlordapp.webservice.domain.Expense;
import com.landlordapp.webservice.domain.Property;
import com.landlordapp.webservice.domain.User;

public class CreateExpensesTask {
	@Autowired
	public PropertyDAO propertyDAO;
	@Autowired
	public UserDAO userDAO;
	@Autowired
	public ExpenseDAO expenseDAO;
	
	public void createExpenses() {
		System.out.println("--> CreateExpensesJob.createExpenses() called");
		List<User> users = userDAO.findAll();
		for(User user : users) {
			System.out.println(user.getId() + ":" + user.getEmail() + ":" + user.getPassword());
			List<Property> properties = propertyDAO.findAll(user.getEmail());
			for(Property property : properties) {
				System.out.println("\t" + property.getId() + ":" + property.getAddress());
				List<Expense> expenses = expenseDAO.findByProperty(property.getId(), user.getEmail());
				for(Expense expense : expenses) {
					System.out.println("\t\t" + expense.getId() + ":" + expense.getDescription() + ":" + expense.getProperty().getId());
				}
				
				//Auto-generated expenses
				//createMortgagePayment(property, user);
				//createRentExpense(property, user);
			}
		}
	}
	
	public void createRentExpense(Property property, User user) {
		Expense expense = new Expense();
		//expense.setId(id);
		expense.setAmountTotal(property.getCurrentRent());
		expense.setAmountPaid(0D);
		expense.setPaid(false);
		expense.setProperty(property);
		expense.setEnteredDate(new Date());
		expense.setDueDate(new Date());//property.getRentDate() or settings.getRentDate()
		expense.setPaidDate(null);
		expense.setDescription("Auto generated rent expense");
		expense.setExpenseType(RENT);
		if(user != null) {
			expense.setUserId(user.getEmail());
		}
		expenseDAO.save(expense);
	}
	
	public void createMortgagePayment(Property property, User user) {
		Expense expense = new Expense();
		//expense.setId(id);
		expense.setAmountTotal(property.getMortgage());
		expense.setAmountPaid(0D);
		expense.setPaid(false);
		expense.setProperty(property);
		expense.setEnteredDate(new Date());
		expense.setDueDate(new Date());//property.getMortgageDueDate()
		expense.setPaidDate(null);
		expense.setDescription("Auto generated mortgage payment");
		expense.setExpenseType(MORTGAGE);
		if(user != null) {
			expense.setUserId(user.getEmail());
		}
		expenseDAO.save(expense);
	}
	
}