package com.landlordapp.webservice.domain;

public enum ExpenseType {
	 RENT(1), MORTGAGE(2), TAX(3), INSURANCE(3), REPAIR(4);
	 
	 private int code;
	 
	 private ExpenseType(int c) {
	   code = c;
	 }
	 
	 public int getCode() {
	   return code;
	 }
}
