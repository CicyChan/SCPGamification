package droolsdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import droolscours.Account;
import droolscours.AccountingPeriod;
import util.KnowledgeSessionHelper;
import util.OutputDisplay;

@SuppressWarnings("restriction")
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

}
