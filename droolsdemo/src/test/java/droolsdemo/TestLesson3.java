package droolsdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import droolscours.Account;
import droolscours.CashFlow;
import droolscours.Customer;
import droolscours.PrivateAccount;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;

public class TestLesson3 {
	StatelessKieSession sessionStateless = null;
	KieSession sessionStatefull = null;

	static KieContainer kieContainer;

	@BeforeClass
	public static void beforeClass() {
		kieContainer = KnowledgeSessionHelper.createRuleBase();
	}

	@Before
	public void setup() throws Exception {
		System.out.println("---------------Before--------------");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("---------------after--------------");
	}

	@Test
	public void testInConstrait() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson3");

		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);

		CashFlow cashFlow = new CashFlow();
		cashFlow.setType(CashFlow.CREDIT);
		sessionStatefull.insert(cashFlow);
		sessionStatefull.fireAllRules();
	}

	@Test
	public void testNestedAccessor() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson3");

		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);

		Customer customer = new Customer();
		customer.setName("Nico");
		customer.setSurname("Nicolas");

		PrivateAccount pAccount = new PrivateAccount();
		pAccount.setOwner(customer);

		sessionStatefull.insert(pAccount);
		sessionStatefull.fireAllRules();
	}

	@Test
	public void testInOrFact() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson3");

		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);

		Customer custmer = new Customer();
		custmer.setCountry("US");
		sessionStatefull.insert(custmer);

		PrivateAccount pAccount = new PrivateAccount();
		pAccount.setOwner(custmer);

		sessionStatefull.insert(pAccount);
		sessionStatefull.fireAllRules();
	}

	@Test
	public void testNotCondition() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson3");

		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);

		sessionStatefull.fireAllRules();
	}

	@Test
	public void testExisteCondition() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson3");

		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);

		Account testAccount = new Account();
		sessionStatefull.insert(testAccount);

		Customer customer = new Customer();
		sessionStatefull.insert(customer);

		sessionStatefull.fireAllRules();
	}
}
