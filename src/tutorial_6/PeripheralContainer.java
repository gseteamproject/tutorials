package tutorial_6;

import jade.Boot;

public class PeripheralContainer {
	public static void main(String[] args) {
		String[] parameters = new String[] { 
			"-container",
			"-detect-main", "true",
			"-name", "*",
			"dummy2:tutorial_6.EmptyAgent;"
		};
		Boot.main(parameters);
	}
}
