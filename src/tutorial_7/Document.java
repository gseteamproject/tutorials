package tutorial_7;

import jade.content.Concept;

public class Document implements Concept {
	/*
	 * Concept term should be used for describing facts in problem area
	 * 
	 * member-fields must have corresponding setter and getter methods. They must be
	 * named get[field name with capital letter] and set[field name with capital
	 * letter]
	 */
	private static final long serialVersionUID = 8246999581538021598L;

	private String name;

	/* getter for name-field */
	public String getName() {
		return name;
	}

	/* setter for name-field */
	public void setName(String name) {
		this.name = name;
	}

	/* default constructor */
	public Document() {
	}

	/*
	 * constructor with parameters. if there is constructor with parameters there
	 * must be default constructor with no parameters
	 */
	public Document(String name) {
		this.name = name;
	}
}
