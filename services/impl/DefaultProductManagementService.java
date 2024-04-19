package services.impl;

import enteties.Product;
import enteties.impl.DefaultProduct;
import services.ProductManagementService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultProductManagementService implements ProductManagementService {
	
	private static DefaultProductManagementService instance;
	
	private static List<Product> products;
	private static Path productsDb=Path.of("resources/products.txt");
	static {
		initProducts();
	}

	private static void initProducts() {
		try {
			products = Files.lines(productsDb)
					.map(s->s.split(System.lineSeparator()))
					.flatMap(Arrays::stream)
					.map(s->s.split(","))
					.map(p->new DefaultProduct(Integer.parseInt(p[0]),p[1],p[2],Double.parseDouble(p[3])))
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

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
//		for(Product product: products){
//			if(product!=null && product.getId()==productIdToAddToCart){
//				return product;
//			}
//		}
//		return null;
	}

}
