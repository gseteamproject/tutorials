package tutorial_3;

import jade.Boot;

public class Tutorial {

	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "printer1:tutorial_3.PrinterAgent;printer2:tutorial_3.PrinterAgent;worker:tutorial_3.WorkerAgent;";
		Boot.main(parameters);
	}
}
