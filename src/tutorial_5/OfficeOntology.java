package tutorial_5;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

public class OfficeOntology extends Ontology {
	private static final long serialVersionUID = -1868417570607203382L;

	public static final String ONTOLOGY_NAME = "office-ontology";

	public static final String DOCUMENT = "Document";
	public static final String DOCUMENT_NAME = "name";

	public static final String PRINTING_PRICE = "Printing price";
	public static final String PRINTING_PRICE_DOCUMENT = "document";
	public static final String PRINTING_PRICE_PRICE = "price";

	public static final String PRINT = "Print";
	public static final String PRINT_DOCUMENT = "document";

	private static Ontology theInstance = new OfficeOntology();

	private OfficeOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());

		try {
			add(new ConceptSchema(DOCUMENT), Document.class);
			ConceptSchema cs = (ConceptSchema) getSchema(DOCUMENT);
			cs.add(DOCUMENT_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));

			add(new PredicateSchema(PRINTING_PRICE), PrintingPrice.class);
			PredicateSchema ps = (PredicateSchema) getSchema(PRINTING_PRICE);
			ps.add(PRINTING_PRICE_DOCUMENT, (ConceptSchema) getSchema(DOCUMENT));
			ps.add(PRINTING_PRICE_PRICE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));

			add(new AgentActionSchema(PRINT), Print.class);
			AgentActionSchema as = (AgentActionSchema) getSchema(PRINT);
			as.add(PRINT_DOCUMENT, (ConceptSchema) getSchema(DOCUMENT));

		} catch (OntologyException e) {
			e.printStackTrace();
		}
	}

	public static Ontology getInstance() {
		return theInstance;
	}
}
