package droolsdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

import droolscours.Account;
import droolscours.AccountingPeriod;
import droolscours.CashFlow;
import droolscours.Customer;
import droolscours.CustomerService;
import util.DateHelper;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;

public class TestLesson31 {

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
	public void testFromLHS() {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson31");

		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);
		sessionStatefull.setGlobal("customerService", new CustomerService());

		Customer TestCust = new Customer("Héron", "Nicolas", "A");
		sessionStatefull.insert(TestCust);
		sessionStatefull.fireAllRules();
	}

	@Test
	public void testCollectionLHS() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson31");

		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);

		Account testAccount = new Account();
		testAccount.setAccountno(1);
		testAccount.setBalance(0);
		sessionStatefull.insert(testAccount);
		sessionStatefull.fireAllRules();

		sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-01-15"), 1000, CashFlow.CREDIT, 1));
		sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-02-15"), 500, CashFlow.DEBIT, 1));
		sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-04-15"), 1000, CashFlow.CREDIT, 1));
		sessionStatefull
				.insert(new AccountingPeriod(DateHelper.getDate("2010-01-01"), DateHelper.getDate("2010-31-31")));

		sessionStatefull.fireAllRules();
	}

	@Test
	public void testAccumulate() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson31");
		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);
		sessionStatefull.insert(new Account(1, 0));

		FactHandle fa = sessionStatefull
				.insert(new CashFlow(DateHelper.getDate("2010-01-15"), 1000, CashFlow.CREDIT, 1));
		sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-02-15"), 500, CashFlow.DEBIT, 1));
		sessionStatefull.insert(new CashFlow(DateHelper.getDate("2010-04-15"), 1000, CashFlow.CREDIT, 1));
		sessionStatefull
				.insert(new AccountingPeriod(DateHelper.getDate("2010-01-01"), DateHelper.getDate("2010-12-31")));
		sessionStatefull.fireAllRules();
		sessionStatefull.delete(fa);
		sessionStatefull.fireAllRules();
	}
}
