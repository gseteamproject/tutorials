package tutorial_4;

import jade.Boot;

public class Tutorial {
	/*
	 * This examples shows how to work with interaction protocols. It is based on
	 * example in tutorial_3. All state handling mechanics replaced with
	 * Contract-Net interaction protocol. Printer agent acts as responder and worker
	 * agent acts as initiator.
	 * 
	 * The idea is that basic communication between agents can be described as
	 * interaction acts: request, query, contract-net, propose e.t.c
	 * 
	 * - Request - one agent asks another to perform some activity
	 * 
	 * - Propose - one agent tells another that it can perform some activity
	 * 
	 * - Query - one agents asks another about information
	 * 
	 * - Contract-net - one agents sends call for proposal for group agents, group
	 * answer with proposals, agent accept/declines proposal
	 *
	 * These communicative acts are easily described as state-machines for all
	 * participants.
	 * 
	 * Interaction Protocols implements these state machines in JADE-framework
	 * 
	 * FIPA specifications about interaction protocols can be found at
	 * http://www.fipa.org/repository/ips.php3
	 */
	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "printer1:tutorial_4.PrinterAgent;printer2:tutorial_4.PrinterAgent;worker:tutorial_4.WorkerAgent;";
		Boot.main(parameters);
	}
}
