package tutorial_0;

import jade.Boot;

/*
 * This example illustrates how to run JADE Platform with JADE Remote Management GUI
 */
public class Application {

	public static void main(String[] args) {
		String[] parameters = new String[] { "-gui" };
		Boot.main(parameters);
	}
}
