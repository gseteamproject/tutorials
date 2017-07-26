package tutorial_7.block_restart;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;

public class BehaviourTwo extends SimpleBehaviour {

	int counter = 5;

	String thisName = getClass().getSimpleName();

	Behaviour suspended;

	public BehaviourTwo(Behaviour suspended) {
		super(suspended.getAgent());
		this.suspended = suspended;
	}

	@Override
	public void action() {
		System.out.println(String.format("%s: performing action method (%d executions left)", thisName, counter));
		counter--;
	}

	@Override
	public boolean done() {
		if (counter < 0) {
			System.out.println(String.format("%s: is completed", thisName));
			/*
			 * This call awakes blocked behaviour BUT it can be already awoken by receiving
			 * message
			 */
			suspended.restart();
			return true;
		}
		return false;
	}

	private static final long serialVersionUID = -117103075302229085L;
}
