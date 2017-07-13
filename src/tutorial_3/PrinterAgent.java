package tutorial_3;

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

	@Override
	protected void setup() {
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName("printing");
		serviceDescription.setType("office-service");
		DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(getAID());
		agentDescription.addServices(serviceDescription);
		try {
			DFService.register(this, agentDescription);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}

		addBehaviour(new HandleAcceptProposal());
		addBehaviour(new HandleCallForProposal());
	}

	@Override
	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}
	}

	class HandleCallForProposal extends CyclicBehaviour {
		private static final long serialVersionUID = 2429876704345890795L;

		@Override
		public void action() {
			MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = receive(msgTemplate);
			if (msg != null) {
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.PROPOSE);
				int price = new Random().nextInt(100);
				reply.setContent(String.valueOf(price));

				System.out.println(String.format("my price is %d", price));
				send(reply);
			} else {
				block();
			}
		}
	}

	class HandleAcceptProposal extends CyclicBehaviour {
		private static final long serialVersionUID = 8759104857697556076L;

		@Override
		public void action() {
			MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
			ACLMessage msg = receive(msgTemplate);
			if (msg != null) {
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.INFORM);

				System.out.println("printing...");
				send(reply);
			} else {
				block();
			}

		}
	}
}
