package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.OrderManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultOrderManagementService;

import java.util.List;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		if(context.getLoggedInUser()==null){
			System.out.println("Please, log in or create new account to see list of your orders");
			return;
		}
		printMenuHeader();

		List<Order> orders=orderManagementService.getOrders();
		if(orders==null||orders.size()==0){
			System.out.println("Unfortunately, you don’t have any orders yet. Navigate back to main menu to place a new order");
		}
		for(Order order: orders){
			System.out.println(order);
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MY ORDERS *****");
	}

}
