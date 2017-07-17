package tutorial_1;

import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

/*
 * Every JADE-agent must be inherited from Agent class or its descendants
 */
public class SimpleAgent extends Agent {
	private static final long serialVersionUID = 4589033288690907928L;

	/*
	 * This method is called when Agent is created. Perform here agent's
	 * initialization
	 */
	@Override
	protected void setup() {
		System.out.println("setup method called");

		/* We add Behaviors that Agent is doing */
		addBehaviour(new SimpleAgentBehaviour());
		addBehaviour(new SimpleAgentOneShotBehaviour());
		addBehaviour(new SimpleAgentCyclicBehaviour());
		addBehaviour(new SimpleAgentTickerBehaviour(this, 1000));
		addBehaviour(new SimpleAgentWakerBehaviour(this, 2000));
		addBehaviour(new SimpleAgentSequentialBehaviour());
		addBehaviour(new SimpleAgentParallelBehaviour(this, ParallelBehaviour.WHEN_ANY));
		addBehaviour(new SimpleAgentFSMBehaviour());
	}

	/*
	 * This method is called when Agent is destroyed. Perform here agent's
	 * finalization
	 */

	@Override
	protected void takeDown() {
		System.out.println("takeDown method called");
	}

	/*
	 * Behaviour class is general class for all Behaviours. It allows the most
	 * flexibility, but requires manual programming of all methods
	 */
	class SimpleAgentBehaviour extends Behaviour {
		private static final long serialVersionUID = 250486242994804102L;

		/* Every time this behaviour is scheduled its "action" method is called */
		@Override
		public void action() {
			System.out.println("executing Behaviour");
			outputsRemaining--;
		}

		/*
		 * After every "action" call method "done" is called. If it returns "true" the
		 * behaviour goes to "stopped-state" which leads to freeing behaviour's object.
		 * If it return "false" behaviour continues to be scheduled
		 */
		@Override
		public boolean done() {
			if (outputsRemaining < 0) {
				System.out.println("stopping Behaviour");
				return true;
			}
			return false;
		}

		private int outputsRemaining = 5;
	}

	/*
	 * OneShotBehaviour is scheduled only once and after it it goes to
	 * "stopped-state".
	 * 
	 * Same functionality can be achieved by Behaviour class if its "done" method
	 * always returns "true".
	 * 
	 * This behaviour should be used for single-time activity
	 */
	class SimpleAgentOneShotBehaviour extends OneShotBehaviour {
		private static final long serialVersionUID = 6438337353069047542L;

		@Override
		public void action() {
			System.out.println("executing OneShotBehaviour");
		}
	}

	/*
	 * CyclicBehaviour is scheduled infinitely.
	 * 
	 * Same functionality can be achieved by Behaviour class if its "done" method
	 * always returns "false".
	 * 
	 * This behaviour should be used for constant processes.
	 */
	class SimpleAgentCyclicBehaviour extends CyclicBehaviour {
		private static final long serialVersionUID = -3413706803994369884L;

		@Override
		public void action() {
			if (outputsRemaining > 0) {
				System.out.println("executing CyclicBehaviour");
				outputsRemaining--;
			}
		}

		private int outputsRemaining = 5;
	}

	/*
	 * TickerBehaviour is scheduled in defined periods of time.
	 * 
	 * This behaviour should be used for activities that should be run periodically.
	 */
	class SimpleAgentTickerBehaviour extends TickerBehaviour {
		private static final long serialVersionUID = -8500033255375195994L;

		public SimpleAgentTickerBehaviour(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			if (outputsRemaining > 0) {
				System.out.println("executing TickerBehaviour");
				outputsRemaining--;
			} else {
				System.out.println("stopping TickerBehaviour");
				stop();
			}
		}

		private int outputsRemaining = 5;
	}

	/*
	 * WakerBehaviour is scheduled only once after timeout expires.
	 * 
	 * This behaviour should be used for time delayed acitivities.
	 */
	class SimpleAgentWakerBehaviour extends WakerBehaviour {
		private static final long serialVersionUID = 2508808170658574583L;

		public SimpleAgentWakerBehaviour(Agent a, long timeout) {
			super(a, timeout);
		}

		@Override
		public void onWake() {
			System.out.println("executing WakerBehaviour");
		}
	}

	/*
	 * SequentialBehaviour consist of several subbeharvours. When this behaviour is
	 * scheduled one of the subbehaviours executed. After current subbehaviour
	 * finishes this goes to next one. When the last subbehaviour finishes this
	 * behaviour also finishes.
	 * 
	 * This behaviour allows to unite behaviours in ordered-group for single time
	 * execution.
	 */
	class SimpleAgentSequentialBehaviour extends SequentialBehaviour {
		private static final long serialVersionUID = 2968241049478663245L;

		public SimpleAgentSequentialBehaviour() {
			addSubBehaviour(new OneShotBehaviour() {
				private static final long serialVersionUID = -2143755529969117686L;

				@Override
				public void action() {
					System.out.println("executing SequentialBehaviour.SubBehaviour 1");
				}
			});

			addSubBehaviour(new OneShotBehaviour() {
				private static final long serialVersionUID = -3100067072957151176L;

				@Override
				public void action() {
					System.out.println("executing SequentialBehaviour.SubBehaviour 2");
				}
			});
		}
	}

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
	class SimpleAgentParallelBehaviour extends ParallelBehaviour {
		private static final long serialVersionUID = 2237160835332572264L;

		public SimpleAgentParallelBehaviour(Agent a, int endCondition) {
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

	/*
	 * FSMBehaviour is used to create finite-state-machines. All subbehaviour
	 * represent finite-states. There are first state, last states and transitions.
	 * First state defines subbehaviour that is executed first. Last states defines
	 * subbehaviours after finishing them this behaviour is finished. Transitons
	 * defines sequence in which behaviours are executed.
	 */
	class SimpleAgentFSMBehaviour extends FSMBehaviour {
		private static final long serialVersionUID = -8811024012048652323L;

		public SimpleAgentFSMBehaviour() {
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
}
