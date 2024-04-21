package storage.impl;

import enteties.Order;
import services.impl.DefaultOrderManagementService;
import storage.OrderStoringService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderStoringService implements OrderStoringService {
    private static final Path path = Path.of("resources/orders.data");

    private static DefaultOrderStoringService instance;
    public static DefaultOrderStoringService getInstance(){
        if(instance==null) {
            instance=new DefaultOrderStoringService();
        }
        try {
            if(Files.notExists(path))
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
    @Override
    public void saveOrders(List<Order> orders) {
        try(var writer=new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            writer.writeObject(orders);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> loadOrders() {
        try (var reader = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            List<Order> orders = (List<Order>) reader.readObject();
            return orders;
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
