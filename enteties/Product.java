package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties;

public interface Product {

	int getId();

	String getProductName();

	String getCategoryName();

	double getPrice();

	void setPrice(double price);
}