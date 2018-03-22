package tutorial_3;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

/*
 * ParallelBehaviour consist of several subbehaviours. When this behaviour is
 * scheduled one of the subbehaviours executed. After current subbehaviour
 * finishes this goes to next one. When the last subbehaviour finishes this goes
 * to first.
 * 
 * This behaviour allows to unite behaviours in ordered-group for infinite
 * execution or until some condition is met.
 * 
 * Basic usage of this behaviour its to interrupt some activity after some
 * period of time.
 */
public class MyParallelBehaviour extends ParallelBehaviour {
	private static final long serialVersionUID = 2237160835332572264L;

	public MyParallelBehaviour(Agent a, int endCondition) {
		super(a, endCondition);

		/* This behaviour will trigger in 2000 miliseconds. */
		addSubBehaviour(new WakerBehaviour(a, 2000) {
			private static final long serialVersionUID = 4986507737243540789L;

			@Override
			public void onWake() {
				System.out.println("executing ParallelBehaviour.SubBehaviour 1");
			}
		});

		/* This behaviour will trigger every 500 miliseconds. */
		addSubBehaviour(new TickerBehaviour(a, 500) {
			private static final long serialVersionUID = -5944819669246026250L;

			@Override
			protected void onTick() {
				System.out.println("executing ParallelBehaviour.SubBehaviour 2");
			}
		});

	}
}
