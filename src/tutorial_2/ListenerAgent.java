package tutorial_2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ListenerAgent extends Agent {
	private static final long serialVersionUID = 1493362282695853482L;

	@Override
	protected void setup() {
		addBehaviour(new ListenForIncommingMessagesBehaviour());
	}

	class ListenForIncommingMessagesBehaviour extends CyclicBehaviour {
		private static final long serialVersionUID = 6130496380982287815L;

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				sendMessageToLogger(msg);
			} else {
				block();
			}
		}

		private void sendMessageToLogger(ACLMessage msgToLog) {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID(("logger"), AID.ISLOCALNAME));
			msg.setConversationId("message-for-logging");
			msg.setContent(msgToLog.getContent());
			send(msg);
		}
	}
}
