package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Cart;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Product;

import java.util.Arrays;

public class DefaultCart implements Cart {

	private Product[] products;
	private int productIndex;
	private int DEFAULT_PRODUCT_SIZE=10;

	{
		products=new Product[DEFAULT_PRODUCT_SIZE];
	}
	
	@Override
	public boolean isEmpty() {
		if(products==null||products.length==0)
			return true;
		for(Product product: products){
			if(product!=null){
				return false;
			}
		}
		return true;
	}

	@Override
	public void addProduct(Product product) {
		if(product==null){
			return;
		}
		if(productIndex>=products.length){
			products= Arrays.copyOf(products, products.length<<1);
		}
		products[productIndex++]=product;
	}

	@Override
	public Product[] getProducts() {
		int notNullProductsAmount=0;
		for(Product product:products){
			if(product!=null)
				notNullProductsAmount++;
		}
		Product[] notNullProduct=new Product[notNullProductsAmount];
		int idx=0;
		for(Product product: products){
			if(product!=null){
				notNullProduct[idx++]=product;
			}
		}
		return notNullProduct;
	}

	@Override
	public void clear() {
		products=new Product[DEFAULT_PRODUCT_SIZE];
		productIndex=0;
	}

}
