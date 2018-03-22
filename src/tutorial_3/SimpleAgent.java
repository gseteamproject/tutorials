package tutorial_3;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

/*
 * Every JADE-agent must be inherited from Agent class or its descendants
 */
public class SimpleAgent extends Agent {
	private static final long serialVersionUID = 4589033288690907928L;

	/*
	 * This factory used to launch behaviours in separate execution threads
	 */
	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	/*
	 * This method is called when Agent is created. Perform here agent's
	 * initialization
	 */
	@Override
	protected void setup() {
		System.out.println("setup method called");

		/* We add Behaviors that Agent is doing */

		addBehaviour(new MySimpleBehaviour());
		addBehaviour(new MyOneShotBehaviour());
		addBehaviour(new MyCyclicBehaviour());
		addBehaviour(new MyTickerBehaviour(this, 1000));
		addBehaviour(new MyWakerBehaviour(this, 2000));
		addBehaviour(new MySequentialBehaviour());
		addBehaviour(new MyParallelBehaviour(this, ParallelBehaviour.WHEN_ANY));
		addBehaviour(new MyFSMBehaviour());
		addBehaviour(tbf.wrap(new MySimpleBehaviour()));
	}

	/*
	 * This method is called when Agent is destroyed. Perform here agent's
	 * finalization
	 */

	@Override
	protected void takeDown() {
		System.out.println("takeDown method called");
	}
}
