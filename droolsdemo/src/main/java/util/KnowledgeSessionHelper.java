package util;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

public class KnowledgeSessionHelper {
	public static KieContainer createRuleBase(){
		
		KieServices ks = KieServices.Factory.get();
		
		KieContainer kieContainer = ks.getKieClasspathContainer();
		
		return kieContainer;
	}
	
	public static StatelessKieSession getStatelessKnowledgeSession(KieContainer kieContainer, String SessionName ){
		
		StatelessKieSession kSession = kieContainer.newStatelessKieSession(SessionName);
		
		return kSession;
	}
	
	public static KieSession getStateFullKnowledgeSession(KieContainer kieContainer, String SessionName){
		KieSession kSession = kieContainer.newKieSession(SessionName);
		
		return kSession;
	}
}
