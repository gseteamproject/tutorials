package tutorial_6;

import jade.Boot;

public class MainContainer {
	public static void main(String[] args) {
		String[] parameters = new String[] { 
			"-gui",
//			"-host", "192.168.100.11",
			"dummy1:tutorial_6.EmptyAgent;"
		};
		Boot.main(parameters);
	}
}
