package tutorial_21;

import jade.Boot;

public class Tutorial {
	/*
	 * This examples shows how to receive and send messages.
	 * 
	 * At startup Logger, Listener and Sniffer agents are created. Listener is
	 * waiting for all incoming messages. When it receives message it sends it to
	 * Logger. Logger is waiting for incoming messages that only have specified
	 * "conversationId". When it receives message it outputs its "content" to
	 * console. Sniffer is special JADE platform agent that traces all messages that
	 * are transmitted by selected agents.
	 * 
	 * To run scenario properly Right-click on a Listener agent, select Send-Message
	 * from context menu, fill "content" slot and press Send.
	 * 
	 * All messages transmitted by Logger and Listener appears in Sniffer
	 * trace-window.
	 */
	public static void main(String[] args) {
		/*
		 * Preparing startup-parameters for JADE Platform.
		 * 
		 * There we start platform with three agents. First agent's name is listener,
		 * its class is tutorial_2.ListenerAgent. Second agent's name is logger, its
		 * class is tutorial_2.LoggerAgent. The last one is sniffer, its class is
		 * jade.tools.sniffer.Sniffer, its parameter is "l*", which tells sniffer to
		 * trace all agents whose name starts with "l".
		 */
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "listener:tutorial_21.ListenerAgent;logger:tutorial_21.LoggerAgent;sniffer:jade.tools.sniffer.Sniffer(l*);";
		Boot.main(parameters);
	}
}
