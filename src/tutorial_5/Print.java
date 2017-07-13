package tutorial_5;

import jade.content.AgentAction;

public class Print implements AgentAction {
	private static final long serialVersionUID = -5435075368709963467L;

	private Document document;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
}
