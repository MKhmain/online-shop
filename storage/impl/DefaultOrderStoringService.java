package storage.impl;

import enteties.Order;
import services.impl.DefaultOrderManagementService;
import storage.OrderStoringService;

import java.nio.file.Path;
import java.util.List;

public class DefaultOrderStoringService implements OrderStoringService {
    private static final Path path = Path.of("resources/ordersDb.csv");

    private static DefaultOrderStoringService instance;
    public static DefaultOrderStoringService getInstance(){
        if(instance==null) {
            instance=new DefaultOrderStoringService();
        }
        return instance;
    }
    @Override
    public void saveOrders(List<Order> orders) {
        StringBuilder stb=new StringBuilder();
        for(Order order: orders){
            stb.append(order.getCustomerId());
        }
    }

    @Override
    public List<Order> loadOrders() {
        return null;
    }
}
