package enteties.impl;

import enteties.Cart;
import enteties.Product;

import java.util.ArrayList;
import java.util.List;

public class DefaultCart implements Cart {

	private List<Product> products;

	{
		products=new ArrayList<>();
	}
	
	@Override
	public boolean isEmpty() {
		return products.isEmpty();
	}

	@Override
	public void addProduct(Product product) {
		if(product==null){
			return;
		}
		products.add(product);
	}

	@Override
	public List<Product> getProducts() {
		return new ArrayList<>(products);
	}

	@Override
	public void clear() {
		products=new ArrayList<>();

	}

}
