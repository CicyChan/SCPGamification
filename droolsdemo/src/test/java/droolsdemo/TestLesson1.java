package droolsdemo;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.BeforeClass;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import droolscours.Account;
import util.KnowledgeSessionHelper;
import util.OutputHistory;

@SuppressWarnings("restriction")
public class TestLesson1 {
	
	StatelessKieSession sessionStateless = null;
	KieSession sessionStatefull = null;
	
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass(){
		kieContainer = KnowledgeSessionHelper.createRuleBase();
	}
	
	@Test
	public void testFirstOne() {
		sessionStatefull = KnowledgeSessionHelper.getStateFullKnowledgeSession(kieContainer, "ksession-rules-lesson1");
		
		OutputHistory outputHistory = new OutputHistory();
		
		Account TestAccount = new Account();
		sessionStatefull.setGlobal("showResult", outputHistory);
		
		sessionStatefull.insert(TestAccount);
		sessionStatefull.fireAllRules();
	}

}
