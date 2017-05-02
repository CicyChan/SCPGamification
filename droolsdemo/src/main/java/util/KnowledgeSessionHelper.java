package util;

import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

public class KnowledgeSessionHelper {
	public static KieContainer createRuleBase() {

		KieServices ks = KieServices.Factory.get();

		KieContainer kieContainer = ks.getKieClasspathContainer();

		return kieContainer;
	}

	public static StatelessKieSession getStatelessKnowledgeSession(KieContainer kieContainer, String SessionName) {

		StatelessKieSession kSession = kieContainer.newStatelessKieSession(SessionName);

		return kSession;
	}

	public static KieSession getStateFullKnowledgeSession(KieContainer kieContainer, String SessionName) {
		KieSession kSession = kieContainer.newKieSession(SessionName);

		return kSession;
	}

	public static KieSession getStateFulKnowledgeSessionWithCallBack(KieContainer kieContainer, String SessionName) {

		KieSession kSession = kieContainer.newKieSession(SessionName);

		kSession.addEventListener(new RuleRuntimeEventListener() {

			public void objectInserted(ObjectInsertedEvent event) {
				System.out.println("Object inserted \n" + event.getObject().toString());

			}

			public void objectUpdated(ObjectUpdatedEvent event) {
				System.out.println("Object updated \n" + event.getObject().toString());

			}

			public void objectDeleted(ObjectDeletedEvent event) {
				System.out.println("Object retracted \n" + event.getOldObject().toString());

			}

		});

		kSession.addEventListener(new AgendaEventListener() {

			public void matchCreated(MatchCreatedEvent event) {
				System.out.println("The Rule" + event.getMatch().getRule().getName() + " can be fired in agenda");

			}

			public void matchCancelled(MatchCancelledEvent event) {
				System.out.println("The Rule" + event.getMatch().getRule().getName() + " cannot be in agenda");

			}

			public void beforeMatchFired(BeforeMatchFiredEvent event) {
				System.out.println("The Rule" + event.getMatch().getRule().getName() + " will be fired");

			}

			public void afterMatchFired(AfterMatchFiredEvent event) {
				System.out.println("The Rule" + event.getMatch().getRule().getName() + " has be fired");
			}

			public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
				// TODO Auto-generated method stub

			}

			public void agendaGroupPushed(AgendaGroupPushedEvent event) {
				// TODO Auto-generated method stub

			}

			public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				// TODO Auto-generated method stub

			}

			public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				// TODO Auto-generated method stub

			}

			public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
				// TODO Auto-generated method stub

			}

			public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
				// TODO Auto-generated method stub

			}

		});

		return kSession;
	}
}
