package tutorial_7;

import jade.content.Predicate;

public class PrintingPrice implements Predicate {
	/*
	 * Predicate term should be used for facts that can be true or false
	 * 
	 * member-fields must have corresponding setter and getter methods. They must be
	 * named get[field name with capital letter] and set[field name with capital
	 * letter]
	 */
	private static final long serialVersionUID = 7834185621062728351L;

	private Document document;

	/* getter for document-field */
	public Document getDocument() {
		return document;
	}

	/* setter for document-field */
	public void setDocument(Document document) {
		this.document = document;
	}

	private int price;

	/* getter for price-field */
	public int getPrice() {
		return price;
	}

	/* setter for price-field */
	public void setPrice(int price) {
		this.price = price;
	}
}
