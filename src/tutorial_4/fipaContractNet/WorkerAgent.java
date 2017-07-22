package tutorial_4.fipaContractNet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

public class WorkerAgent extends Agent {
	private static final long serialVersionUID = -5061150948321398390L;

	@Override
	protected void setup() {
		addBehaviour(new PeriodicLookForActivePrinterRequest(this, 2000));
	}

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
				addBehaviour(new WorkerNetInitiator(myAgent, agents));
			} else {
				System.out.println("no agents providing service are found");
			}
		}

		private List<AID> findAgents(String serviceName) {
			ServiceDescription requiredService = new ServiceDescription();
			requiredService.setName(serviceName);
			DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
			agentDescriptionTemplate.addServices(requiredService);

			List<AID> foundAgents = new ArrayList<AID>();
			try {
				DFAgentDescription[] agentDescriptions = DFService.search(myAgent, agentDescriptionTemplate);
				for (DFAgentDescription agentDescription : agentDescriptions) {
					foundAgents.add(agentDescription.getName());
				}
			} catch (FIPAException exception) {
				exception.printStackTrace();
			}

			return foundAgents;
		}
	}

	class WorkerNetInitiator extends ContractNetInitiator {
		private static final long serialVersionUID = -5526179655342146860L;

		List<AID> printerAgents;

		private int bestPrice = -1;

		public WorkerNetInitiator(Agent a, List<AID> printerAgents) {
			super(a, null);
			this.printerAgents = printerAgents;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		protected Vector prepareCfps(ACLMessage cfp) {
			cfp = new ACLMessage(ACLMessage.CFP);
			cfp.setContent("document");
			cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
			cfp.setReplyByDate(new Date(System.currentTimeMillis() + 1000));
			for (AID printerAgent : printerAgents) {
				cfp.addReceiver(printerAgent);
			}
			Vector v = new Vector();
			v.add(cfp);
			return v;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected void handleAllResponses(Vector responses, Vector acceptances) {
			int bestOffer = -1;
			for (int i = 0; i < responses.size(); i++) {
				ACLMessage response = (ACLMessage) responses.get(i);
				if (response.getPerformative() == ACLMessage.PROPOSE) {
					int price = Integer.parseInt(response.getContent());
					if (bestOffer == -1 || price < bestPrice) {
						bestOffer = i;
						bestPrice = price;
					}
				}
			}
			for (int i = 0; i < responses.size(); i++) {
				ACLMessage accept = ((ACLMessage) responses.get(i)).createReply();
				accept.setContent("document");
				if (i == bestOffer) {
					accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
				} else {
					accept.setPerformative(ACLMessage.REJECT_PROPOSAL);
				}
				acceptances.add(accept);
			}
		}

		@Override
		protected void handleInform(ACLMessage inform) {
			System.out.println(String.format("document printed (price=%d)", bestPrice));
		}
	}
}
