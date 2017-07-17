package tutorial_6;

import jade.Boot;

public class PeripheralContainer {
	/*
	 * In this example peripheral container is launched.
	 * 
	 * "-container" parameter shows that it is peripheral container (container that
	 * doesn't have JADE Platform support agents)
	 * 
	 * It starts on localhost and tries to find any MainContainer in LAN
	 * 
	 * If it can't find any MainContainer in LAN but there is one you must fill
	 * "-host" and "-local-host" parameters. Place in "-host" address of
	 * MainContainer and in "-local-host" address of this PeripheralContainer.
	 * 
	 * It's better to use URIs not an IP addresses cause it allows more flexibility
	 */
	public static void main(String[] args) {
		String[] parameters = new String[] { 
				"-container", 
				"-detect-main", "true", 
				"-name", "*",
				// "-host", "192.168.100.11",
				// "-local-host", "192.168.100.14",
				"dummy2:tutorial_6.EmptyAgent;" };
		Boot.main(parameters);
	}
}
