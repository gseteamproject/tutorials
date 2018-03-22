package tutorial_3;

import jade.core.behaviours.OneShotBehaviour;

/*
 * OneShotBehaviour is scheduled only once and after it it goes to
 * "stopped-state".
 * 
 * Same functionality can be achieved by Behaviour class if its "done" method
 * always returns "true".
 * 
 * This behaviour should be used for single-time activity
 */

public class MyOneShotBehaviour extends OneShotBehaviour {
	private static final long serialVersionUID = 6438337353069047542L;

	@Override
	public void action() {
		System.out.println("executing OneShotBehaviour");
	}
}
