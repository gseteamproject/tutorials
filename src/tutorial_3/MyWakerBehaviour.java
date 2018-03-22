package tutorial_3;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

/*
 * WakerBehaviour is scheduled only once after timeout expires.
 * 
 * This behaviour should be used for time delayed acitivities.
 */
public class MyWakerBehaviour extends WakerBehaviour {
	private static final long serialVersionUID = 2508808170658574583L;

	public MyWakerBehaviour(Agent a, long timeout) {
		super(a, timeout);
	}

	@Override
	public void onWake() {
		System.out.println("executing WakerBehaviour");
	}
}
