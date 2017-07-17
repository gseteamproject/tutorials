package tutorial_5;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

/*
 * This class defines ontology related to problem area. In order to support
 * basic types must be inherited from Ontology class or its descendants
 */
public class OfficeOntology extends Ontology {
	private static final long serialVersionUID = -1868417570607203382L;

	/*
	 * There goes Vocabulary of the ontology. This String-constants will be used in
	 * serialization/deserialization process. They must match corresponding fields.
	 */
	public static final String ONTOLOGY_NAME = "office-ontology";

	/* String-constants for "Document" concept */
	public static final String DOCUMENT = "Document";
	public static final String DOCUMENT_NAME = "name";

	/* String-constants for "Printing price" predicate */
	public static final String PRINTING_PRICE = "Printing price";
	public static final String PRINTING_PRICE_DOCUMENT = "document";
	public static final String PRINTING_PRICE_PRICE = "price";

	/* String-constants for "Print" action */
	public static final String PRINT = "Print";
	public static final String PRINT_DOCUMENT = "document";

	/* Ontology created as Singleton */
	private static Ontology theInstance = new OfficeOntology();

	private OfficeOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());

		/* there should be registered all terms that belong to ontology */
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
