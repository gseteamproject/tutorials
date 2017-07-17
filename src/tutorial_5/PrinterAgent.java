package tutorial_5;

import java.util.Random;

import jade.content.ContentManager;
import jade.content.Predicate;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
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

	/* to form String representation SLCodec will be used */
	private Codec codec = new SLCodec();

	/* serialization/deserialization will be based on office ontology */
	private Ontology ontology = OfficeOntology.getInstance();

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

		/*
		 * codec and ontology must be registered as supported by agent. agent can
		 * support multiple codecs and ontologies
		 */
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
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

			/* content slot handling based on ontology mechanic */
			// begin
			ContentManager cm = myAgent.getContentManager();
			Predicate p = null;
			try {
				p = (Predicate) cm.extractContent(cfp);
			} catch (CodecException | OntologyException e) {
				reply.setPerformative(ACLMessage.REFUSE);
				reply.setContent(e.getMessage());
				return reply;
			}
			if (!(p instanceof PrintingPrice)) {
				reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
				return reply;
			}
			PrintingPrice printingPrice = (PrintingPrice) p;
			int price = new Random().nextInt(100);
			printingPrice.setPrice(price);
			try {
				cm.fillContent(reply, printingPrice);
			} catch (CodecException | OntologyException e) {
				e.printStackTrace();
			}
			// end

			System.out.println(String.format("my price is %d", price));
			return reply;
		}

		@Override
		protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept)
				throws FailureException {
			ACLMessage inform = accept.createReply();
			inform.setPerformative(ACLMessage.INFORM);
			inform.setContent(accept.getContent());

			// begin
			ContentManager cm = myAgent.getContentManager();
			Action a = null;
			try {
				a = (Action) cm.extractContent(accept);
			} catch (CodecException | OntologyException e) {
				e.printStackTrace();
			}
			Print print = (Print) a.getAction();
			// end

			System.out.println(String.format("printing %s ...", print.getDocument().getName()));
			return inform;
		}
	}
}
