package ems.object;

import java.util.ArrayList;
import java.util.List;

public class Entitlement {
	private List<EntitlementItem> entitlementItems = new ArrayList<EntitlementItem>();
	private Customer customer;

	public Entitlement() {
		super();
	}

	public void addEntitlementItem(EntitlementItem entitlementItem) {

		this.entitlementItems.add(entitlementItem);

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<EntitlementItem> getEntitlementItems() {

		return entitlementItems;
	}

	public void setEntitlementItems(List<EntitlementItem> entitlementItems) {
		this.entitlementItems = entitlementItems;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("-----Entitlement---\n");

		if (this.entitlementItems != null) {
			for (int i = 0; i < this.entitlementItems.size(); i++) {
				buff.append(
						"entitlement[" + i + "].Entitlement " + this.entitlementItems.get(i).getEntitlement() + "\n");
				buff.append("entitlement[" + i + "].EntitlementType "
						+ this.entitlementItems.get(i).getEntitlementType().toString() + "\n");
				buff.append("entitlement[" + i + "].StartDate " + this.entitlementItems.get(i).getStartDate() + "\n");
				buff.append("entitlement[" + i + "].ExpireDate " + this.entitlementItems.get(i).getExpireDate() + "\n");
			}
		}

		buff.append("-----Entitlement---\n");
		return buff.toString();
	}

}
