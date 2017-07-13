package tutorial_2;

import jade.Boot;

public class Tutorial {

	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "listener:tutorial_2.ListenerAgent;logger:tutorial_2.LoggerAgent;sniffer:jade.tools.sniffer.Sniffer(l*);";
		Boot.main(parameters);
	}
}
