package tutorial_3;

import jade.Boot;

public class Tutorial {
	/*
	 * This example shows different Behaviors to perform Agent's activities
	 */
	public static void main(String[] args) {
		/*
		 * Preparing startup-parameters for JADE Platform.
		 * 
		 * There we will use "-gui" parameter and specify command line to run agents at
		 * startup. Startup command line is a String that consist of each agent's
		 * command line separated by ";".
		 * 
		 * For example to run three agents at start up String will look like this:
		 * "agent1_command_line;agent2_command_line;agent3_command_line;"
		 * 
		 * Each agent's command line is a String that consist agent's instance name,
		 * agent's class full name and agent's parameters:
		 * agent_name:agent_class(agent_parameters)
		 * 
		 * There we create one agent with name "agent" from class located in
		 * "tutorial_1.SimpleAgent" with no parameters
		 */
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "agent:tutorial_3.SimpleAgent";
		Boot.main(parameters);
	}
}
