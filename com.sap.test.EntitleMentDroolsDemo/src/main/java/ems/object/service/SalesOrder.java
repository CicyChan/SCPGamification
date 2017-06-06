package ems.object.service;

import java.util.ArrayList;
import java.util.List;

import ems.enums.EntitlementType;
import ems.object.Customer;
import ems.object.EntitlementItem;
import ems.object.Product;

public class SalesOrder {

	private Customer customer;
	private List<Product> products;

	public SalesOrder() {
		super();
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addProduct(Product product) {
		if (this.products == null) {
			this.products = new ArrayList<Product>();
		}

		this.products.add(product);
	};

	public List<Product> getProducts() {
		return products;
	}

	public void addEntitlmentToCustomer() throws Exception {

		for (int i = 0; i < this.products.size(); i++) {
			String productEntitlement = "Product" + this.products.get(i).getProductName();
			EntitlementItem entitlementForLicence = new EntitlementItem(productEntitlement, EntitlementType.License);

			String standardSupportEntitlement = "Standard Support " + this.products.get(i).getProductName();
			EntitlementItem entitlementForService = new EntitlementItem(standardSupportEntitlement,
					EntitlementType.Service);

			this.customer.getEntitlementItems().add(entitlementForLicence);
			this.customer.getEntitlementItems().add(entitlementForService);
		}

	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();

		buff.append("-----SalesOrder--- \n");
		buff.append("Customer name is " + this.customer.getName() + "\n");

		for (int i = 0; i < this.getProducts().size(); i++) {
			buff.append("Product is " + this.getProducts().get(i).getProductName() + "\n");
		}

		buff.append("-----SalesOrder---\n");

		return buff.toString();
	}

}
