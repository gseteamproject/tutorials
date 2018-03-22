package tutorial_3;

import java.util.Random;

import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;

/*
 * FSMBehaviour is used to create finite-state-machines. All subbehaviour
 * represent finite-states. There are first state, last states and transitions.
 * First state defines subbehaviour that is executed first. Last states defines
 * subbehaviours after finishing them this behaviour is finished. Transitons
 * defines sequence in which behaviours are executed.
 */
public class MyFSMBehaviour extends FSMBehaviour {
	private static final long serialVersionUID = -8811024012048652323L;

	public MyFSMBehaviour() {
		Random random = new Random();

		/* This behaviour will be executed first */
		registerFirstState(new OneShotBehaviour() {
			private static final long serialVersionUID = 8163784370399967348L;

			@Override
			public void action() {
				System.out.println("executing FSMBehaviour.SubBehaviour A");
			}

			/*
			 * After it is executed the control flow randomly goes to point 0, or point 1.
			 */
			@Override
			public int onEnd() {
				int next = random.nextInt(2);
				if (next == 0) {
					System.out.println("FSMBehaviour transit to B");
				}
				if (next == 1) {
					System.out.println("FSMBehaviour transit to C");
				}
				return next;
			}
		}, "A");

		/* This behaviour is intermediate state */
		registerState(new OneShotBehaviour() {
			private static final long serialVersionUID = -238061175221953362L;

			@Override
			public void action() {
				System.out.println("executing FSMBehaviour.SubBehaviour B");
			}

			/* After it is executed the control flow goes to point 0. */
			@Override
			public int onEnd() {
				System.out.println("FSMBehaviour transit to A");
				return 0;
			}
		}, "B");

		/* This behaviour is last state */
		registerLastState(new OneShotBehaviour() {
			private static final long serialVersionUID = -4142993565651741865L;

			@Override
			public void action() {
				System.out.println("executing FSMBehaviour.SubBehaviour C");
			}
		}, "C");

		/*
		 * Register Behaviour B as point-0 for Behaviour A. Register Behaviour C as
		 * point-1 for Behaviour A. Register Behaviour A as default point for Behaviour
		 * B.
		 * 
		 * After performing default transition all mentioned behaviours are reseted.
		 */
		registerTransition("A", "B", 0);
		registerTransition("A", "C", 1);
		registerDefaultTransition("B", "A", new String[] { "A", "B" });
	}
}
