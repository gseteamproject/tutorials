package tutorial_2;

import jade.Boot;

public class Tutorial {

	// this examples shows how to:
	// - start agent
	// - perform initialization
	// - perform finalization
	// - explicitly delete agent
	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "agent:tutorial_2.SimpleAgent;";
		Boot.main(parameters);
	}
}
