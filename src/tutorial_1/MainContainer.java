package tutorial_1;

import jade.Boot;

public class MainContainer {
	/*
	 * In this example main container is launched.
	 * 
	 * It starts on localhost
	 * 
	 * If IP address that is shown in GUI doesn't match real machine IP the
	 * parameter "-host" should be used. Just specify there current machine IP.
	 * 
	 * It's better to use URIs not an IP addresses cause it allows more flexibility
	 */
	public static void main(String[] args) {
		String[] parameters = new String[] {
			"-gui",
			// "-host", "192.168.100.11",
			"a1:jade.core.Agent;" };
		Boot.main(parameters);
	}
}
