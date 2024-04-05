package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Product;

import java.util.Arrays;
import java.util.List;

public class DefaultOrder implements Order {

	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;
	
	private String creditCardNumber;
	private List<Product> products;
	private int customerId;

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		System.out.println(creditCardNumber.toCharArray().length == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER);
		System.out.println(!creditCardNumber.contains(" "));
		System.out.println(Long.parseLong(creditCardNumber) > 0);
		return creditCardNumber.toCharArray().length == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER &&
				!creditCardNumber.contains(" ") && Long.parseLong(creditCardNumber) > 0;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		if (creditCardNumber == null) {
			return;
		}
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public void setProducts(List<Product> products) {
		if(products==null){
			return;
		}
		this.products=products;
	}

	@Override
	public void setCustomerId(int customerId) {
		if(customerId<=0){
			return;
		}
		this.customerId=customerId;
	}


	@Override
	public int getCustomerId() {
		return this.customerId;
	}
	
	@Override
	public String toString() {
		return "Order: customer id - " + this.customerId + "\t" +
				"credit card number - " + this.creditCardNumber + "\t" +
				"products - " + this.products;
	}

}
