package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Cart;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Product;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.ProductManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultProductManagementService;

import java.util.List;
import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Menu menu=null;
		while(true) {
			printProducts();
			String input=readUserInput();
			if(context.getLoggedInUser()==null){
				menu=new MainMenu();
				System.out.println("You are not logged in. Please, sign in or create new account");
				break;
			}
			if(input.equalsIgnoreCase("menu")){
				menu=new MainMenu();
				break;
			}
			if(input.equalsIgnoreCase(CHECKOUT_COMMAND)){
				Cart cart=context.getSessionCart();
				if(cart==null|| cart.isEmpty()){
					System.out.println("Your cart is empty. Please, add product to cart first and then proceed with checkout");
				}else{
					menu=new CheckoutMenu();
					break;
				}
			}else{
				Product product=getProduct(input);
				if(product!=null){
					context.getSessionCart().addProduct(product);
					System.out.println("Product "+ product.getProductName()+"has been added to your cart. If you want to add a new product - enter the product id. If you want to proceed with checkout - enter word ‘checkout’ to console’b   ");
				}else{
					System.out.println("Please, enter product ID if you want to add product to cart. Or enter ‘checkout’ if you want to proceed with checkout. Or enter ‘menu’ if you want to navigate back to the main menu");
				}
			}

		}
		menu.start();
	}
	public void printProducts(){
		List<Product> products=productManagementService.getProducts();
		for(Product product: products ){
			System.out.println(product);
		}
	}
	private String readUserInput() {
		System.out.print("Product ID to add to cart or enter 'checkout' to proceed with checkout: ");
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		return userInput;
	}
	public Product getProduct(String input){
		int productId=Integer.parseInt(input);
		Product product=productManagementService.getProductById(productId);
		return product;
	}
	@Override
	public void printMenuHeader() {
		System.out.println("***** PRODUCT CATALOG *****");
		System.out.println("Enter product id to add it to the cart or 'menu' if you want to navigate back to the main menu");
	}

}
