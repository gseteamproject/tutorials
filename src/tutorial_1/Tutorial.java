package tutorial_1;

import jade.Boot;

public class Tutorial {

	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "agent:tutorial_1.SimpleAgent";
		Boot.main(parameters);
	}
}
