package ems.object;

public class Product {

	private String productName;

	public Product(String productName) {
		super();
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("-----Product--- \n");

		buff.append("Product is " + this.getProductName() + "\n");

		buff.append("-----Product---\n");

		return buff.toString();
	}
}
