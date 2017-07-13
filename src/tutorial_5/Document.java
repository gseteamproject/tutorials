package tutorial_5;

import jade.content.Concept;

public class Document implements Concept {
	private static final long serialVersionUID = 8246999581538021598L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Document() {
	}

	public Document(String name) {
		this.name = name;
	}
}
