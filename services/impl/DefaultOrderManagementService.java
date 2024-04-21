package services.impl;


import enteties.Order;
import services.OrderManagementService;
import storage.impl.DefaultOrderStoringService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultOrderManagementService implements OrderManagementService {

	private static DefaultOrderStoringService defaultOrderStoringService;
	private static DefaultOrderManagementService instance;
	private List<Order> orders;

	{
		defaultOrderStoringService=DefaultOrderStoringService.getInstance();
		orders=defaultOrderStoringService.loadOrders();
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
		defaultOrderStoringService.saveOrders(orders);
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		return defaultOrderStoringService.loadOrders().stream().
				filter(Objects::nonNull).
				filter(o->o.getCustomerId()==userId).
				collect(Collectors.toList());

	}

	@Override
	public List<Order> getOrders() {
		if (orders == null || orders.size() == 0) {
			orders = defaultOrderStoringService.loadOrders();
		}
		return this.orders;
	}
	
	void clearServiceState() {
		orders.clear();
	}

}
