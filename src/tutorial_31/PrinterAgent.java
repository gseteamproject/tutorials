package tutorial_31;

import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class PrinterAgent extends Agent {
	private static final long serialVersionUID = -7418692714860762106L;

	/*
	 * This method is called when Agent is created.
	 */
	@Override
	protected void setup() {
		/* forming service description */
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName("printing");
		serviceDescription.setType("office-service");
		/* forming agent description. one agent can provide several services */
		DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(getAID());
		agentDescription.addServices(serviceDescription);
		try {
			/* registering agent description in DF-Agent */
			DFService.register(this, agentDescription);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}

		/* registering Behaviours to react for different types of messages */
		addBehaviour(new HandleAcceptProposal());
		addBehaviour(new HandleCallForProposal());
	}

	/*
	 * This method is called when Agent is destroyed.
	 */
	@Override
	protected void takeDown() {
		try {
			/* deregistering agent description in DF-Agent */
			DFService.deregister(this);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}
	}

	/* This Behaviour handles "call-for-proposal" messages */
	class HandleCallForProposal extends CyclicBehaviour {
		private static final long serialVersionUID = 2429876704345890795L;

		@Override
		public void action() {
			/*
			 * only messages containing CALL-FOR-PROPOSAL in "performative" slot will be
			 * processed
			 */
			MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = receive(msgTemplate);
			if (msg != null) {
				/* create reply for incoming message with price at "content" slot */
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.PROPOSE);
				int price = new Random().nextInt(100);
				reply.setContent(String.valueOf(price));

				System.out.println(String.format("my price is %d", price));
				/* send reply for incoming message */
				send(reply);
			} else {
				/* wait till there is message matching template in message-queue */
				block();
			}
		}
	}

	/* This Behaviour handles "accept-proposal" messages */
	class HandleAcceptProposal extends CyclicBehaviour {
		private static final long serialVersionUID = 8759104857697556076L;

		@Override
		public void action() {
			/*
			 * only message containg ACCEPT-PROPOSAL in "performative" slot will be
			 * processed
			 */
			MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
			ACLMessage msg = receive(msgTemplate);
			if (msg != null) {
				/* create reply for incoming message */
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.INFORM);

				System.out.println("printing...");
				/* send reply for incoming message */
				send(reply);
			} else {
				/* wait till there is message matching template in message-queue */
				block();
			}

		}
	}
}
