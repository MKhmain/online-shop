package services.impl;

import enteties.Product;
import enteties.impl.DefaultProduct;
import services.ProductManagementService;
import storage.impl.DefaultProductStoringService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DefaultProductManagementService implements ProductManagementService {
	
	private static DefaultProductManagementService instance;
	private static DefaultProductStoringService defaultProductStoringService;
	private static List<Product> products;
	
	static {
		defaultProductStoringService=DefaultProductStoringService.getInstance();
		initProducts();
	}

	private static void initProducts() {
		products = defaultProductStoringService.loadProducts();
	}
	
	private DefaultProductManagementService() {
		
	}

	public static ProductManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultProductManagementService();
		}
		return instance;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public Product getProductById(int productIdToAddToCart) {
		return products.stream().
				filter(Objects::nonNull).
				filter(p->p.getId()==productIdToAddToCart).findFirst().orElse(null);

	}

}
