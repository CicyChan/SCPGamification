package ems.interfaces;

import ems.object.Entitlement;

public interface IEntitlmentBase {
	public boolean isEntitlementExist(Entitlement entitlement);

	public void updateEntitlement(Entitlement entitlement);
}
