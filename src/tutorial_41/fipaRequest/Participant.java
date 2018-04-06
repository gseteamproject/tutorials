package tutorial_41.fipaRequest;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

public class Participant extends Agent {
	@Override
	protected void setup() {
		MessageTemplate template = AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST);

		addBehaviour(new RespondToDoSomething(this, template));
	}

	class RespondToDoSomething extends AchieveREResponder {
		public RespondToDoSomething(Agent a, MessageTemplate mt) {
			super(a, mt);
		}

		@Override
		protected ACLMessage prepareResponse(ACLMessage request) throws NotUnderstoodException, RefuseException {
			// send AGREE
			ACLMessage agree = request.createReply();
			agree.setPerformative(ACLMessage.AGREE);
			return agree;
			// send REFUSE
			// throw new RefuseException("check-failed");
		}

		@Override
		protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response)
				throws FailureException {
			// if agent AGREEd to request
			// send INFORM
			ACLMessage inform = request.createReply();
			inform.setPerformative(ACLMessage.INFORM);
			return inform;
			// send FAILURE
			// throw new FailureException("unexpected-error");
		}

		private static final long serialVersionUID = -8009542545033008746L;
	}

	private static final long serialVersionUID = 4287675691537805694L;
}
