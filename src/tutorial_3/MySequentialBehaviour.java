package tutorial_3;

import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;

/*
 * SequentialBehaviour consist of several subbeharvours. When this behaviour is
 * scheduled one of the subbehaviours executed. After current subbehaviour
 * finishes this goes to next one. When the last subbehaviour finishes this
 * behaviour also finishes.
 * 
 * This behaviour allows to unite behaviours in ordered-group for single time
 * execution.
 */
public class MySequentialBehaviour extends SequentialBehaviour {
	private static final long serialVersionUID = 2968241049478663245L;

	public MySequentialBehaviour() {
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
