package droolsdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

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

	@Test
	public void testForAll() throws Exception {
		sessionStatefull = KnowledgeSessionHelper.getStateFulKnowledgeSessionWithCallBack(kieContainer,
				"ksession-rules-lesson3");

		OutputDisplay display = new OutputDisplay();
		sessionStatefull.setGlobal("showResult", display);

		Account testAccount = new Account();
		testAccount.setAccountno(1);
		testAccount.setBalance(0);
		sessionStatefull.insert(testAccount);

		CashFlow cash1 = new CashFlow();
		cash1.setAccountNo(1);
		sessionStatefull.insert(cash1);

		CashFlow cash2 = new CashFlow();
		cash2.setAccountNo(1);
		sessionStatefull.insert(cash2);

		Account testAccount2 = new Account();
		testAccount2.setAccountno(2);
		testAccount2.setBalance(0);
		sessionStatefull.insert(testAccount2);

		CashFlow cash3 = new CashFlow();
		cash3.setAccountNo(2);
		sessionStatefull.insert(cash3);

		sessionStatefull.fireAllRules();

	}

	@Test
	public void testEvalWithBigDecimal() throws Exception {
		String str = "package droolscours";
		str += "import java.math.BigDecimal; \n";
		str += "import java.util.List; \n";
		str += "global List list; \n";
		str += "rule \"rule1\" \n";
		str += "dialect \"java\" \n";
		str += "when \n";
		str += "$bd : BigDecimal( )\n";
		str += "eval ( $bd.compareTo( BigDecimal.ZERO ) > 0 ) \n";
		str += "then \n";
		str += "list.add( $bd ); \n";
		str += "end \n";

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL);

		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
		}

		// assertFalse(kbuilder.getErrors());
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

	}
}
