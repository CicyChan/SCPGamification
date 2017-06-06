package ems.object.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import ems.enums.EntitlementType;
import ems.interfaces.IEntitlmentBase;
import ems.object.Customer;
import ems.object.Entitlement;
import ems.object.EntitlementItem;
import util.KnowledgeSessionHelper;

public class ServiceContract {
	public Customer customer;
	public Entitlement entitlement;

	private StatelessKieSession sessionStateless;
	private KieSession sessionStatefull;
	public static KieContainer kieContainer;

	public void setRuleEngineMeta() {
		kieContainer = KnowledgeSessionHelper.createRuleBase();
		sessionStatefull = KnowledgeSessionHelper.getStateFullKnowledgeSession(kieContainer, "ksession-rules");
	}

	public ServiceContract() {
		super();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addtEntitlementItems(String entitlement, String expireDate) throws Exception {
		this.setRuleEngineMeta();

		EntitlementItem entitlementForPrimumSupport = new EntitlementItem(entitlement, EntitlementType.Service,
				expireDate);
		this.getEntitlement().getEntitlementItems().add(entitlementForPrimumSupport);

	}

	public void fireRules() {

		IEntitlmentBase entitlementQuery = (IEntitlmentBase) this.customer;

		this.sessionStatefull.setGlobal("entitlementQuery", entitlementQuery);
		this.sessionStatefull.insert(this.customer);
		this.sessionStatefull.insert(this.getEntitlement());

		sessionStatefull.fireAllRules();

	}

	private Entitlement getEntitlement() {
		if (this.entitlement == null) {
			this.entitlement = new Entitlement();
			this.entitlement.setCustomer(this.getCustomer());
		}
		return this.entitlement;
	}

}
