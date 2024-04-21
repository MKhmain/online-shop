package enteties.impl;

import enteties.Order;
import enteties.Product;

import java.util.List;

public class DefaultOrder implements Order {
	private static final long serialVersionId=1L;
	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;
	
	private String creditCardNumber;
	private List<Product> products;
	private int customerId;

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {

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
		return "Order: customer id - " + this.customerId + "\n" +
				"\tcredit card number - " + this.creditCardNumber + "\n" +
				"\tproducts - " + this.products;
	}

}
