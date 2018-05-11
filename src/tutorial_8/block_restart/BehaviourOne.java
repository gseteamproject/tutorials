package tutorial_8.block_restart;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

public class BehaviourOne extends SimpleBehaviour {

	private int state;

	String thisName = getClass().getSimpleName();

	final static int USER_DEFINED_STATE_ONE = 1;
	final static int USER_DEFINED_STATE_TWO = 2;
	final static int USER_DEFINED_STATE_THREE = 3;
	final static int USER_DEFINED_STATE_FOUR = 4;

	public BehaviourOne(Agent a) {
		super(a);
		state = USER_DEFINED_STATE_ONE;
	}

	@Override
	public void action() {

		switch (state) {
		case USER_DEFINED_STATE_ONE:
			System.out.println(String.format("%s: transition to state - %d", thisName, USER_DEFINED_STATE_TWO));
			state = USER_DEFINED_STATE_TWO;
			break;
		case USER_DEFINED_STATE_TWO:
			System.out.println(String.format("%s: transition to state - %d", thisName, USER_DEFINED_STATE_THREE));
			state = USER_DEFINED_STATE_THREE;

			System.out.println(String.format("%s: starting - %s", thisName, BehaviourTwo.class.getSimpleName()));
			myAgent.addBehaviour(new BehaviourTwo(this));

			System.out.println(String.format("%s: moving to execution state - %s", thisName, STATE_BLOCKED));
			/*
			 * After executing this method behaviour is stopped from execution BUT will be
			 * started again when agent receives ANY message. So this approach does not allow
			 * to block one behaviour wait of execution of another and start it again
			 */
			this.block();
			break;
		case USER_DEFINED_STATE_THREE:
			System.out.println(String.format("%s: moving to execution state - %s", thisName, getExecutionState()));

			System.out.println(String.format("%s: transition to state - %d", thisName, USER_DEFINED_STATE_FOUR));
			state = USER_DEFINED_STATE_FOUR;
			break;
		default:
			break;
		}
	}

	@Override
	public boolean done() {
		if (state == USER_DEFINED_STATE_FOUR) {
			System.out.println(String.format("%s: is completed", thisName));
			return true;
		}
		return false;
	}

	private static final long serialVersionUID = -8868005217577638031L;
}
