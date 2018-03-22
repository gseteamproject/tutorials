package tutorial_3;

import jade.core.behaviours.SimpleBehaviour;

/*
 * SimpleBehaviour class is general class for all Behaviours. It allows the most
 * flexibility, but requires manual programming of all methods
 */

public class MySimpleBehaviour extends SimpleBehaviour {
	private static final long serialVersionUID = 250486242994804102L;

	private int counter = 3;

	/* Every time this behaviour is scheduled its "action" method is called */
	@Override
	public void action() {
		System.out.println("executing SimpleBehaviour");
		counter--;
	}

	/*
	 * After every "action" call method "done" is called. If it returns "true" the
	 * behaviour goes to "stopped-state" which leads to freeing behaviour's object.
	 * If it return "false" behaviour continues to be scheduled
	 */
	@Override
	public boolean done() {
		if (counter > 0) {
			return false;
		}
		System.out.println("stopping SimpleBehaviour");
		return true;
	}
}
