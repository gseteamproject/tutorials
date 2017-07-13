package tutorial_0;

import jade.Boot;

/*
 * This tutorial illustrates how to run JADE Platform with JADE Remote Management GUI
 */
public class Tutorial {

	public static void main(String[] args) {
		String[] parameters = new String[1];
		parameters[0] = "-gui";
		Boot.main(parameters);
	}
}
