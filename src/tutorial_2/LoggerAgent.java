package tutorial_2;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class LoggerAgent extends Agent {
	private static final long serialVersionUID = 1828532491676359560L;

	@Override
	protected void setup() {
		addBehaviour(new ListenForIncommingMessagesBehaviour());
	}

	class ListenForIncommingMessagesBehaviour extends CyclicBehaviour {

		private static final long serialVersionUID = 2787586564664887456L;

		MessageTemplate messageTemplate = MessageTemplate.MatchConversationId("message-for-logging");

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive(messageTemplate);
			if (msg != null) {
				System.out.println(msg.getContent());
			} else {
				block();
			}
		}
	}
}
