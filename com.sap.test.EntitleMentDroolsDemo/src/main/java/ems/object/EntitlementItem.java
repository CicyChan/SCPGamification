package ems.object;

import java.util.Date;

import ems.enums.EntitlementType;
import util.DateHelper;

public class EntitlementItem {
	private String entitlement;
	private EntitlementType entitlementType;
	private Date startDate;
	private Date expireDate;

	public EntitlementItem(String entitlement, EntitlementType entitlementType) throws Exception {
		super();

		this.entitlement = entitlement;
		this.entitlementType = entitlementType;

		this.startDate = new Date();
		this.expireDate = DateHelper.getDate("31.12.9999");

	}

	public EntitlementItem(String entitlement, EntitlementType entitlementType, String expireDate) throws Exception {
		super();
		this.entitlement = entitlement;
		this.entitlementType = entitlementType;
		this.startDate = new Date();
		this.expireDate = DateHelper.getDate(expireDate);
	}

	public String getEntitlement() {
		return entitlement;
	}

	public void setEntitlement(String entitlement) {
		this.entitlement = entitlement;
	}

	public EntitlementType getEntitlementType() {
		return entitlementType;
	}

	public void setEntitlementType(EntitlementType entitlementType) {
		this.entitlementType = entitlementType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
