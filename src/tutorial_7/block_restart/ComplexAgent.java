package tutorial_7.block_restart;

import jade.core.Agent;

public class ComplexAgent extends Agent {

	@Override
	protected void setup() {
		addBehaviour(new BehaviourOne(this));
	}

	private static final long serialVersionUID = 1503576123596967437L;
}
