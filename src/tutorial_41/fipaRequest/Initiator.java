package tutorial_41.fipaRequest;

import java.util.Date;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

public class Initiator extends Agent {

	@Override
	protected void setup() {
		addBehaviour(new StartRequestToDoSomething(this, 2000));
	}

	class StartRequestToDoSomething extends TickerBehaviour {
		public StartRequestToDoSomething(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			String requestedAction = "action1";

			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(new AID(("participant"), AID.ISLOCALNAME));
			msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
			msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
			msg.setContent(requestedAction);

			addBehaviour(new RequestToDoSomething(myAgent, msg));
		}

		private static final long serialVersionUID = 2151211108562731709L;
	}

	class RequestToDoSomething extends AchieveREInitiator {
		public RequestToDoSomething(Agent a, ACLMessage msg) {
			super(a, msg);
		}

		@Override
		protected void handleRefuse(ACLMessage refuse) {
			System.out.println("received refuse");
		}

		@Override
		protected void handleAgree(ACLMessage agree) {
			System.out.println("received agree");
		}

		@Override
		protected void handleInform(ACLMessage inform) {
			System.out.println("received inform");
		}

		@Override
		protected void handleFailure(ACLMessage failure) {
			System.out.println("received failure");
		}

		private static final long serialVersionUID = -8104498062148279796L;
	}

	private static final long serialVersionUID = -9153270530329904125L;
}
