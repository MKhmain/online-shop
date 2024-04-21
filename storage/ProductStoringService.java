package storage;

import enteties.Product;

import java.util.List;

public interface ProductStoringService {
    List<Product> loadProducts();
}
