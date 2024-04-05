package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Product;

import java.util.List;

public interface ProductManagementService {

	List<Product> getProducts();

	Product getProductById(int productIdToAddToCart);

}
