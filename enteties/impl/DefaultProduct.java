package enteties.impl;

import enteties.Product;

public class DefaultProduct implements Product {

	private int id;
	private String productName;
	private String categoryName;
	private double price;

	public DefaultProduct() {
	}

	public DefaultProduct(int id, String productName, String categoryName, double price) {
		this.id = id;
		this.productName = productName;
		this.categoryName = categoryName;
		this.price = price;
	}

	@Override
	public String toString() {
		return "\nProduct id=" + id + ",\n" +
				"\tproduct name=" + productName+",\n"+
				"\tcategory name=" + categoryName + ",\n" +
				"\tprice=" + price;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getProductName() {
		return this.productName;
	}

	@Override
	public String getCategoryName() {
		return this.categoryName;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

}