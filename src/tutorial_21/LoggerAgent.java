package tutorial_21;

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
			/*
			 * receive() call gets first message from message-queue that matches given
			 * template, if there is no message it returns null
			 */
			ACLMessage msg = myAgent.receive(messageTemplate);
			if (msg != null) {
				/* when there is message agent outputs its content to Console */
				System.out.println(msg.getContent());
			} else {
				/*
				 * block() call puts behaviour to blocked-state until there is message in
				 * message-queue. this method is used to remove Behaviour from scheduling queue
				 * to prevent idling.
				 */
				block();
			}
		}
	}
}
