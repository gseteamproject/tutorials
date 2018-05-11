package tutorial_4;

import jade.Boot;

public class Tutorial {
	/*
	 * This examples shows how to work with catalog of services.
	 * 
	 * At startup two Printers and one Worker agents are created. Printers register
	 * themselves as "printing" service providers in DF-Agent. Worker periodically
	 * asks DF-Agent for agents providing "printing" service and receives all agents
	 * that providing this service as a response. Worker than sends offer for these
	 * agents and selects that one who provides best price.
	 * 
	 * In this example most of mechanics programmed manually. In next examples more
	 * framework features will be used.
	 */
	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "printer1:tutorial_4.PrinterAgent;printer2:tutorial_4.PrinterAgent;worker:tutorial_4.WorkerAgent;";
		Boot.main(parameters);
	}
}
