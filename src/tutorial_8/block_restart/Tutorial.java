package tutorial_8.block_restart;

import jade.Boot;

public class Tutorial {
	/* In this scenario there is no REAL blocking of behaviours */
	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "userAgent:tutorial_8.block_restart.ComplexAgent;";
		Boot.main(parameters);
	}
}
