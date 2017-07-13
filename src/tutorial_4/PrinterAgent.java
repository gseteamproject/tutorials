package tutorial_4;

import java.util.Random;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetResponder;

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

		addBehaviour(new PrinterNetResponder(this));
	}

	@Override
	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}
	}

	class PrinterNetResponder extends ContractNetResponder {
		private static final long serialVersionUID = -6602178386832898743L;

		public PrinterNetResponder(Agent a) {
			super(a, createMessageTemplate(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET));
		}

		@Override
		protected ACLMessage handleCfp(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
			ACLMessage reply = cfp.createReply();
			reply.setPerformative(ACLMessage.PROPOSE);
			int price = new Random().nextInt(100);
			reply.setContent(String.valueOf(price));
			System.out.println(String.format("my price is %d", price));
			return reply;
		}

		@Override
		protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept)
				throws FailureException {
			ACLMessage inform = accept.createReply();
			inform.setPerformative(ACLMessage.INFORM);

			System.out.println("printing...");
			return inform;
		}
	}
}
