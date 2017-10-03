package tutorial_2;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class SimpleAgent extends Agent {

	// user defined initialization
	@Override
	protected void setup() {
		System.out.println("setup started");

		System.out.println("adding behaviours");
		addBehaviour(new OneShotBehaviour() {
			@Override
			public void action() {
				System.out.println("explicit transition to DELETED state");
				myAgent.doDelete();
			}

			private static final long serialVersionUID = -2425006811533183312L;
		});

		System.out.println("setup finished");
	}

	// user defined finalization
	@Override
	protected void takeDown() {
		System.out.println("takedown finished");
	}

	private static final long serialVersionUID = 7519831408872879563L;
}
