package tutorial_0;

import jade.Boot;

public class Tutorial {
	/*
	 * This example shows how to run JADE Platform with JADE Remote Management GUI
	 */
	public static void main(String[] args) {
		/*
		 * To run JADE Platform you need to call Boot.main() method.
		 * 
		 * JADE Platform can be configured by startup-parameters. These parameters go as
		 * an array of String objects. Some parameters are single and other are
		 * "key=value" parameters. "key" and "value" must be placed in different String
		 * objects.
		 * 
		 * For complete list of possible parameters see JADE documentation.
		 * 
		 * There we will use "-gui" parameter that orders to run JADE Remote Management
		 * Agent.
		 */
		String[] parameters = new String[1];
		parameters[0] = "-gui";

		/* Pass array with parameters as argument of Boot.main() method */
		Boot.main(parameters);
	}
}
