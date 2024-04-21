package services.impl;


import enteties.Order;
import services.OrderManagementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultOrderManagementService implements OrderManagementService {


	private static DefaultOrderManagementService instance;

	private List<Order> orders;

	{
		orders=new ArrayList<>();
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
		orders.add(order);
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		return orders.stream().
				filter(Objects::nonNull).
				filter(o->o.getCustomerId()==userId).
				collect(Collectors.toList());

	}

	@Override
	public List<Order> getOrders() {
		return new ArrayList<>(orders);
	}
	
	void clearServiceState() {
		orders=new ArrayList<>();
	}

}
