package tutorial_3;

import jade.core.behaviours.CyclicBehaviour;

/*
 * CyclicBehaviour is scheduled infinitely.
 *
 * Same functionality can be achieved by Behaviour class if its "done" method
 * always returns "false".
 *
 * This behaviour should be used for constant processes.
 */
public class MyCyclicBehaviour extends CyclicBehaviour {
	private static final long serialVersionUID = -3413706803994369884L;

	private int counter = 3;

	@Override
	public void action() {
		if (counter > 0) {
			System.out.println("executing CyclicBehaviour");
			counter--;
		}
	}
}
