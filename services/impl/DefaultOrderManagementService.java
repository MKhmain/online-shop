package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.OrderManagementService;

import java.util.Arrays;

public class DefaultOrderManagementService implements OrderManagementService {

	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;

	private Order[] orders;
	private int orderNumber;
	{
		orders=new Order[DEFAULT_ORDER_CAPACITY];
	}
	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if(order==null)
			return;
		if(orderNumber>=orders.length){
			orders= Arrays.copyOf(orders, orders.length<<1);
		}
		orders[orderNumber++]=order;
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {
		int numOfOrdersAmount=0;
		for(Order order: orders){
			if(order!=null&&order.getCustomerId()==userId){
				numOfOrdersAmount++;
			}
		}
		Order[] numOfOrders=new Order[numOfOrdersAmount];
		int idx=0;
		for(Order order:orders){
			if(order!=null&&order.getCustomerId()==userId){
				numOfOrders[idx++]=order;
			}
		}
		return numOfOrders;
	}

	@Override
	public Order[] getOrders() {
		int numOfOrdersAmount=0;
		for(Order order: orders){
			if(order!=null){
				numOfOrdersAmount++;
			}
		}
		Order[] numOfOrders=new Order[numOfOrdersAmount];
		int idx=0;
		for(Order order:orders){
			if(order!=null){
				numOfOrders[idx++]=order;
			}
		}
		return numOfOrders;
	}
	
	void clearServiceState() {
		orders=new Order[DEFAULT_ORDER_CAPACITY];
		orderNumber=0;
	}

}
