package tutorial_6;

import jade.Boot;

public class MainContainer {
	public static void main(String[] args) {
		String[] parameters = new String[] { 
			"-gui",
			"dummy1:tutorial_6.EmptyAgent;"
		};
		Boot.main(parameters);
	}
}
