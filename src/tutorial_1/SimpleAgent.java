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

public class SimpleAgent extends Agent {
	private static final long serialVersionUID = 4589033288690907928L;

	@Override
	protected void setup() {
		System.out.println("setup method called");

		addBehaviour(new SimpleAgentBehaviour());
		addBehaviour(new SimpleAgentOneShotBehaviour());
		addBehaviour(new SimpleAgentCyclicBehaviour());
		addBehaviour(new SimpleAgentTickerBehaviour(this, 1000));
		addBehaviour(new SimpleAgentWakerBehaviour(this, 2000));
		addBehaviour(new SimpleAgentSequentialBehaviour());
		addBehaviour(new SimpleAgentParallelBehaviour(this, ParallelBehaviour.WHEN_ANY));
		addBehaviour(new SimpleAgentFSMBehaviour());
	}

	@Override
	protected void takeDown() {
		System.out.println("takeDown method called");
	}

	class SimpleAgentBehaviour extends Behaviour {
		private static final long serialVersionUID = 250486242994804102L;

		@Override
		public void action() {
			System.out.println("executing Behaviour");
			outputsRemaining--;
		}

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

	class SimpleAgentOneShotBehaviour extends OneShotBehaviour {
		private static final long serialVersionUID = 6438337353069047542L;

		@Override
		public void action() {
			System.out.println("executing OneShotBehaviour");
		}
	}

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

	class SimpleAgentParallelBehaviour extends ParallelBehaviour {
		private static final long serialVersionUID = 2237160835332572264L;

		public SimpleAgentParallelBehaviour(Agent a, int endCondition) {
			super(a, endCondition);

			addSubBehaviour(new WakerBehaviour(a, 2000) {
				private static final long serialVersionUID = 4986507737243540789L;

				@Override
				public void onWake() {
					System.out.println("executing ParallelBehaviour.SubBehaviour 1");
				}
			});

			addSubBehaviour(new TickerBehaviour(a, 500) {
				private static final long serialVersionUID = -5944819669246026250L;

				@Override
				protected void onTick() {
					System.out.println("executing ParallelBehaviour.SubBehaviour 2");
				}
			});

		}
	}

	class SimpleAgentFSMBehaviour extends FSMBehaviour {
		private static final long serialVersionUID = -8811024012048652323L;

		public SimpleAgentFSMBehaviour() {
			Random random = new Random();

			registerFirstState(new OneShotBehaviour() {
				private static final long serialVersionUID = 8163784370399967348L;

				@Override
				public void action() {
					System.out.println("executing FSMBehaviour.SubBehaviour A");
				}

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

			registerState(new OneShotBehaviour() {
				private static final long serialVersionUID = -238061175221953362L;

				@Override
				public void action() {
					System.out.println("executing FSMBehaviour.SubBehaviour B");
				}

				@Override
				public int onEnd() {
					System.out.println("FSMBehaviour transit to A");
					return 0;
				}
			}, "B");

			registerLastState(new OneShotBehaviour() {
				private static final long serialVersionUID = -4142993565651741865L;

				@Override
				public void action() {
					System.out.println("executing FSMBehaviour.SubBehaviour C");
				}
			}, "C");

			registerTransition("A", "B", 0);
			registerTransition("A", "C", 1);
			registerDefaultTransition("B", "A", new String[] { "A", "B" });
		}
	}
}
