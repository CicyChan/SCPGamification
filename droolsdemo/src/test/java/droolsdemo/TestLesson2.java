package droolsdemo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import droolscours.Account;
import droolscours.AccountingPeriod;
import droolscours.CashFlow;
import util.DateHelper;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;

public class TestLesson2 {
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
	public void testdeuxFait1() {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson2");

		OutputDisplay outputDisply = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", outputDisply);

		Account testAccount = new Account();
		sessionStatefull.insert(testAccount);

		AccountingPeriod period = new AccountingPeriod();
		sessionStatefull.insert(period);

		sessionStatefull.fireAllRules();
	}

	@Test
	public void testTwoFact() {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson2");

		OutputDisplay outputDisply = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", outputDisply);

		Account testAccount = new Account();
		testAccount.setAccountno(1);
		testAccount.setBalance(0);

		sessionStatefull.insert(testAccount);

		CashFlow cash1 = new CashFlow();
		cash1.setAccountNo(1);
		cash1.setAmount(1000);
		cash1.setType(CashFlow.CREDIT);

		sessionStatefull.insert(cash1);
		sessionStatefull.fireAllRules();
		Assert.assertEquals(testAccount.getBalance(), 1000.0, 0);

	}

	@Test
	public void testTwoFactTwoCashFlowMovement() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson2");

		OutputDisplay outputDisply = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", outputDisply);

		Account testAccount = new Account();
		testAccount.setAccountno(1);
		testAccount.setBalance(0);

		sessionStatefull.insert(testAccount);

		CashFlow cash1 = new CashFlow();
		cash1.setAccountNo(1);
		cash1.setAmount(1000);
		cash1.setMvDate(DateHelper.getDate("2010-01-15"));
		cash1.setType(CashFlow.CREDIT);
		sessionStatefull.insert(cash1);

		CashFlow cash2 = new CashFlow();
		cash2.setAccountNo(2);
		cash2.setAmount(1000);
		cash2.setMvDate(DateHelper.getDate("2010-01-15"));
		cash2.setType(CashFlow.CREDIT);
		sessionStatefull.insert(cash2);

		sessionStatefull.fireAllRules();
		Assert.assertEquals(testAccount.getBalance(), 1000.0, 0);

	}

}
