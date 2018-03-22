package tutorial_3;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/*
 * TickerBehaviour is scheduled in defined periods of time.
 * 
 * This behaviour should be used for activities that should be run periodically.
 */

public class MyTickerBehaviour extends TickerBehaviour {
	private static final long serialVersionUID = -8500033255375195994L;

	private int counter = 3;

	public MyTickerBehaviour(Agent a, long period) {
		super(a, period);
	}

	@Override
	protected void onTick() {
		if (counter > 0) {
			System.out.println("executing TickerBehaviour");
			counter--;
		} else {
			System.out.println("stopping TickerBehaviour");
			stop();
		}
	}
}
