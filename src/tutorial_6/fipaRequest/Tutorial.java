package tutorial_6.fipaRequest;

import jade.Boot;

public class Tutorial {
	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "initiator:tutorial_6.fipaRequest.Initiator;participant:tutorial_6.fipaRequest.Participant;";
		Boot.main(parameters);
	}
}
