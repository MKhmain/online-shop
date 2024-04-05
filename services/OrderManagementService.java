package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services;


import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;

import java.util.List;

public interface OrderManagementService {

	void addOrder(Order order);

	List<Order> getOrdersByUserId(int userId);
	
	List<Order> getOrders();

}
