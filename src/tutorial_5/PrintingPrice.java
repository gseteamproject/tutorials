package tutorial_5;

import jade.content.Predicate;

public class PrintingPrice implements Predicate {
	private static final long serialVersionUID = 7834185621062728351L;

	private Document document;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
