package tutorial_4;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WorkerAgent extends Agent {
	private static final long serialVersionUID = -5061150948321398390L;

	/*
	 * This method is called when Agent is created.
	 */
	@Override
	protected void setup() {
		/* add Behaviour that scheduled every 2000 miliseconds */
		addBehaviour(new PeriodicLookForActivePrinterRequest(this, 2000));
	}

	/*
	 * this Behaviour periodically asks DF-Agent for agents providing "printing"
	 * service. and if it finds some it starts LookForActivePrinterRequest Behaviour
	 */
	class PeriodicLookForActivePrinterRequest extends TickerBehaviour {
		private static final long serialVersionUID = 2543990340935580665L;

		public PeriodicLookForActivePrinterRequest(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			System.out.println("\nlooking for agents with printing service");
			List<AID> agents = findAgents("printing");
			if (!agents.isEmpty()) {
				System.out.println("agents providing service are found. trying to print...");
				addBehaviour(new LookForActivePrinterRequest(agents));
			} else {
				System.out.println("no agents providing service are found");
			}
		}

		private List<AID> findAgents(String serviceName) {
			/* prepare service-search template */
			ServiceDescription requiredService = new ServiceDescription();
			requiredService.setName(serviceName);
			/*
			 * prepare agent-search template. agent-search template can have several
			 * service-search templates
			 */
			DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
			agentDescriptionTemplate.addServices(requiredService);

			List<AID> foundAgents = new ArrayList<AID>();
			try {
				/* perform request to DF-Agent */
				DFAgentDescription[] agentDescriptions = DFService.search(myAgent, agentDescriptionTemplate);
				for (DFAgentDescription agentDescription : agentDescriptions) {
					/* store all found agents in an array for further processing */
					foundAgents.add(agentDescription.getName());
				}
			} catch (FIPAException exception) {
				exception.printStackTrace();
			}

			return foundAgents;
		}
	}

	/* possible states of request */
	private static enum RequestState {
		PREPARE_CALL_FOR_PROPOSAL, HANDLE_CALL_FOR_PROPOSAL_REPLY, PREPARE_ACCEPT_PROPOSAL, HANDLE_ACCEPT_PROPOSAL_REPLY, DONE
	};

	/*
	 * this behaviour handles process of sending call for proposal to printers,
	 * gathering their proposals and accepting one of them. in order to implement
	 * this all in single behaviour state-mechanics implemented
	 */
	class LookForActivePrinterRequest extends Behaviour {
		private static final long serialVersionUID = 55516044843165611L;

		List<AID> printerAgents;
		RequestState requestState;

		public LookForActivePrinterRequest(List<AID> printerAgents) {
			this.printerAgents = printerAgents;
			/* initial state for behaviour */
			this.requestState = RequestState.PREPARE_CALL_FOR_PROPOSAL;
		}

		MessageTemplate replyTemplate = null;
		int repliesLeft = 0;

		AID bestPrinterAgent = null;
		int bestPrice = 0;

		@Override
		public void action() {
			ACLMessage msg = null;

			/* perform actions accordingly to behaviour state */
			switch (requestState) {
			case PREPARE_CALL_FOR_PROPOSAL:
				msg = new ACLMessage(ACLMessage.CFP);
				for (AID agentProvidingService : printerAgents) {
					msg.addReceiver(agentProvidingService);
				}
				msg.setConversationId("printing");
				msg.setContent("document");
				msg.setReplyWith(String.valueOf(System.currentTimeMillis()));

				replyTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId("printing"),
						MessageTemplate.MatchInReplyTo(msg.getReplyWith()));
				repliesLeft = printerAgents.size();

				send(msg);

				requestState = RequestState.HANDLE_CALL_FOR_PROPOSAL_REPLY;
				break;

			case HANDLE_CALL_FOR_PROPOSAL_REPLY:
				msg = receive(replyTemplate);
				if (msg != null) {
					int price = Integer.parseInt(msg.getContent());
					if (bestPrinterAgent == null || price < bestPrice) {
						bestPrinterAgent = msg.getSender();
						bestPrice = price;
					}
					repliesLeft--;
					if (repliesLeft == 0) {
						requestState = RequestState.PREPARE_ACCEPT_PROPOSAL;
					}
				} else {
					block();
				}
				break;

			case PREPARE_ACCEPT_PROPOSAL:
				msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				msg.addReceiver(bestPrinterAgent);
				msg.setConversationId("printing");
				msg.setContent("document");
				msg.setReplyWith(String.valueOf(System.currentTimeMillis()));

				replyTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId("printing"),
						MessageTemplate.MatchInReplyTo(msg.getReplyWith()));
				repliesLeft = 1;

				send(msg);

				requestState = RequestState.HANDLE_ACCEPT_PROPOSAL_REPLY;
				break;

			case HANDLE_ACCEPT_PROPOSAL_REPLY:
				msg = receive(replyTemplate);
				if (msg != null) {
					System.out.println(String.format("document printed (price=%d)", bestPrice));
					repliesLeft = 0;
					requestState = RequestState.DONE;
				} else {
					block();
				}
				break;

			case DONE:
				break;

			default:
				break;
			}
		}

		@Override
		public boolean done() {
			/* behaviour is finished when it reaches DONE state */
			return requestState == RequestState.DONE;
		}
	}
}
