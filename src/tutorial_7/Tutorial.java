package tutorial_7;

import jade.Boot;

public class Tutorial {
	/*
	 * This example shows how to work with ontologies. It is based on example in
	 * tutorial_4.
	 * 
	 * The idea is that as useful information is transfered in "content" slot of
	 * message it is represented as String. So the information must be somehow
	 * serialised/deserialised to/from String representation. And as one group of
	 * developers can have an agreement of how to do this. Other group can have
	 * different opinion.
	 * 
	 * Example: Animal { name = "lucy", species = "cat" }
	 * 
	 * XML representation: <animal><name>lucy</name><species>cat</species></animal>
	 * 
	 * JSON representation: {"animal":{"name":"lucy","species":"cat"}}
	 * 
	 * 
	 * Ontology requires semantic analyze of problem area
	 * 
	 * Video for communication of different systems based on ontology
	 * [russian-language] https://www.youtube.com/watch?v=yO_KDo36ZEU
	 */
	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "printer1:tutorial_7.PrinterAgent;printer2:tutorial_7.PrinterAgent;worker:tutorial_7.WorkerAgent;";
		Boot.main(parameters);
	}
}
