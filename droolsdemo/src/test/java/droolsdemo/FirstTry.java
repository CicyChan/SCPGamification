package droolsdemo;

import static org.junit.Assert.*;

import org.junit.Test;
import util.KnowledgeSessionHelper;

import org.junit.BeforeClass;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import droolscours.Account;

@SuppressWarnings("restriction")
public class FirstTry {
	
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
		sessionStatefull.fireAllRules();
	}

}
