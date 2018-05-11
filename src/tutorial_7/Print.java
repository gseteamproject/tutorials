package tutorial_7;

import jade.content.AgentAction;

public class Print implements AgentAction {
	/*
	 * AgentAction term should be used for actions that are performed by agents
	 * 
	 * member-fields must have corresponding setter and getter methods. They must be
	 * named get[field name with capital letter] and set[field name with capital
	 * letter]
	 */
	private static final long serialVersionUID = -5435075368709963467L;

	private Document document;

	/* getter for document-field */
	public Document getDocument() {
		return document;
	}

	/* setter for document-field */
	public void setDocument(Document document) {
		this.document = document;
	}
}
