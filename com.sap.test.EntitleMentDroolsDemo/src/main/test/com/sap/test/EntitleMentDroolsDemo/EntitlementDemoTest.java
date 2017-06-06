package com.sap.test.EntitleMentDroolsDemo;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import ems.object.Customer;
import ems.object.Product;
import ems.object.service.SalesOrder;
import ems.object.service.ServiceContract;
import util.KnowledgeSessionHelper;

public class EntitlementDemoTest {

	StatelessKieSession sessionStateless = null;
	KieSession sessionStatefull = null;

	static KieContainer kieContainer;

	@BeforeClass
	public static void beforeClass() {
		kieContainer = KnowledgeSessionHelper.createRuleBase();
	}

	@Before
	public void setup() throws Exception {
		System.out.println("----------setup------------");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("----------destroy------------");
	}

	@Test
	public void testEntiltement() throws Exception {
		/*
		 * try {
		 * 
		 * sessionStatefull =
		 * KnowledgeSessionHelper.getStateFullKnowledgeSession(kieContainer,
		 * "ksession-rules");
		 * 
		 * Message message = new Message(); message.setMessage("Hello World");
		 * message.setStatus(Message.HELLO); sessionStatefull.insert(message);
		 * sessionStatefull.fireAllRules(); } catch (Throwable t) {
		 * t.printStackTrace(); }
		 */

		/*
		 * Initial Customer C1
		 */
		Customer testCustomer = new Customer();
		testCustomer.setName("C1");

		System.out.println(testCustomer.toString());

		/*
		 * Initial Product P1, P2
		 */

		Product product1 = new Product("P1");
		Product product2 = new Product("P2");

		System.out.println(product1.toString());
		System.out.println(product2.toString());

		/*
		 * Action 1
		 * 1. create SalesOrder SO1 with product P1 for testCustomer
		 * 2. create standard entitlement for Product P1
		 */
		SalesOrder SO1 = new SalesOrder();
		SO1.setCustomer(testCustomer);
		SO1.addProduct(product1);
		SO1.addEntitlmentToCustomer();
		System.out.println(SO1.toString());
		System.out.println(testCustomer.toString());

		/*
		 * Action 2
		 * 1. create SalesOrder SO2 with product P1 for testCustomer
		 * 2. create standard entitlement for Product P1
		 */
		SalesOrder SO2 = new SalesOrder();
		SO2.setCustomer(testCustomer);
		SO2.addProduct(product2);
		SO2.addEntitlmentToCustomer();
		System.out.println(SO2.toString());
		System.out.println(testCustomer.toString());

		/*
		 * Action 3
		 * 1.create service contract with testCustomer
		 * 2.upgrade service to Premiums service
		 * 3.transaction date is 25.5.2017
		 */
		ServiceContract SC1 = new ServiceContract();
		String entitlement = "Premium Support";
		String ExpireDate = "24.05.2022";
		SC1.setCustomer(testCustomer);
		SC1.addtEntitlementItems(entitlement, ExpireDate);
		SC1.fireRules();

		System.out.println(testCustomer.toString());
	}

}
