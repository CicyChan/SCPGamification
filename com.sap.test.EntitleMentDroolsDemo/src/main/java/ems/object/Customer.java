package ems.object;

import java.util.ArrayList;
import java.util.List;

import ems.interfaces.IEntitlmentBase;
import ems.object.service.ServiceContract;

public class Customer implements IEntitlmentBase {

	private String name;

	private List<EntitlementItem> entitlementItems;
	private List<ServiceContract> servicecontracts;

	public Customer(String name) {
		super();
		this.name = name;
	}

	public Customer() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EntitlementItem> getEntitlementItems() {

		if (this.entitlementItems == null) {
			this.entitlementItems = new ArrayList<EntitlementItem>();
		}

		return entitlementItems;
	}

	public List<ServiceContract> getServicecontracts() {
		return servicecontracts;
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

		return "Customer [name=" + name + "] \n" + buff.toString();
	}

	public boolean isEntitlementExist(Entitlement entitlement) {

		if (entitlement.getCustomer().getName() == this.getName()) {
			return true;
		}
		return false;
	}

	public void updateEntitlement(Entitlement entitlement) {
		List<EntitlementItem> updateEntitlementItems = entitlement.getEntitlementItems();

		for (int i = 0; i < updateEntitlementItems.size(); i++) {
			this.getEntitlementItems().add(updateEntitlementItems.get(i));
		}
	}

}
