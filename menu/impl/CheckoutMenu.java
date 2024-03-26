package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl.DefaultOrder;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.OrderManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultOrderManagementService;

import java.util.Scanner;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;
	
	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}
	
	@Override
	public void start() {
		while(true) {
			printMenuHeader();
			var sc = new Scanner(System.in);
			String creditCard = sc.next();
			if(createOrder(creditCard)){
				continue;
			}
			context.getSessionCart().clear();
			break;
		}
		System.out.println("Thanks a lot for purchase. Details about order delivery are sent to your email.");
	}
	private boolean createOrder(String creditCard){
		Order order=new DefaultOrder();
		if(creditCard==null||order.isCreditCardNumberValid(creditCard)){
			return false;
		}
		order.setCreditCardNumber(creditCard);
		order.setCustomerId(context.getLoggedInUser().getId());
		order.setProducts(context.getSessionCart().getProducts());
		orderManagementService.addOrder(order);
		return true;
	}


	@Override
	public void printMenuHeader() {
		System.out.println("***** CHECKOUT *****");
		System.out.println("Enter your credit card number without spaces and press enter");
	}

}
