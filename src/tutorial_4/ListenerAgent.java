package tutorial_4;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ListenerAgent extends Agent {
	private static final long serialVersionUID = 1493362282695853482L;

	/*
	 * This method is called when agent is initialized.
	 */
	@Override
	protected void setup() {
		/* Adding agent's behaviour */
		addBehaviour(new ListenForIncommingMessagesBehaviour());
	}

	/* CyclicBehaviour for receiving all incoming messages */
	class ListenForIncommingMessagesBehaviour extends CyclicBehaviour {
		private static final long serialVersionUID = 6130496380982287815L;

		@Override
		public void action() {
			/*
			 * receive() call gets first message from message-queue, if there is no message
			 * it returns null
			 */
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				/* when there is message agent sends message directly to logger-agent */
				sendMessageToLogger(msg);
			} else {
				/*
				 * block() call puts behaviour to blocked-state until there is message in
				 * message-queue. this method is used to remove Behaviour from scheduling queue
				 * to prevent idling.
				 */
				block();
			}
		}

		/*
		 * Message is prepared by filling necessary slots. send() call sends message to
		 * all agents mentioned in Receiver slot.
		 * 
		 * Check how message looks in Sniffer-agent trace.
		 */
		private void sendMessageToLogger(ACLMessage msgToLog) {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID(("logger"), AID.ISLOCALNAME));
			msg.setConversationId("message-for-logging");
			msg.setContent(msgToLog.getContent());
			send(msg);
		}
	}
}
