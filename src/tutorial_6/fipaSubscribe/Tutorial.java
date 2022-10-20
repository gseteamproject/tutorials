package tutorial_6.fipaSubscribe;

import jade.Boot;

public class Tutorial {
	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "requester1:" + InitiatorAgent.class.getName() + ";";
		parameters[1] += "requester2:" + InitiatorAgent.class.getName() + ";";
		parameters[1] += "responder:" + ResponderAgent.class.getName() + ";";
		parameters[1] += "sniffer:jade.tools.sniffer.Sniffer(re*);";
		Boot.main(parameters);
	}
}
