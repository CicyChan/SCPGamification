package droolsdemo;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.BeforeClass;
import org.kie.api.runtime.KieSession;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

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
	
	@Test
	public void testRuleOneFactWithFactAndUsageOfGalbalAndCallBack(){
		sessionStatefull = KnowledgeSessionHelper.getStateFullKnowledgeSession(kieContainer, "ksession-rules-lesson1");
		
		sessionStatefull.addEventListener(new RuleRuntimeEventListener(){

			public void objectInserted(ObjectInsertedEvent event) {
				
				System.out.println("Object inserted \n" +
						event.getObject().toString());
				
			}

			public void objectUpdated(ObjectUpdatedEvent event) {
				System.out.println("Object updated \n" +
						event.getObject().toString());
				
			}

			public void objectDeleted(ObjectDeletedEvent event) {
				System.out.println("Object deleted \n" +
						event.getOldObject().toString());
				
			}
			
		});
		
		Account TestAccount = new Account();
		TestAccount.setAccountno(10);
		
		FactHandle handelea = sessionStatefull.insert(TestAccount);
		TestAccount.setBalance(12.0);
		
		sessionStatefull.update(handelea, TestAccount);
		sessionStatefull.delete(handelea);
		
		sessionStatefull.fireAllRules();
		
		System.out.println("So you sawing somthing");
	}
	
	@Test
	public void testFirstOneTwoFireAllRulesWithUpdateInBetween(){
		
	}

}
