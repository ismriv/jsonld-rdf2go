package ie.deri.smile.jsonld.rdf2go;

import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.model.Model;
import org.ontoware.rdf2go.model.ModelSet;
import org.ontoware.rdf2go.model.Statement;
import org.ontoware.rdf2go.model.node.DatatypeLiteral;
import org.ontoware.rdf2go.model.node.LanguageTagLiteral;
import org.ontoware.rdf2go.model.node.Literal;
import org.ontoware.rdf2go.model.node.Resource;
import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RDF2GoJSONLDSerializer extends de.dfki.km.json.jsonld.JSONLDSerializer {
	
	private static final Logger LOG = LoggerFactory.getLogger(RDF2GoJSONLDSerializer.class);

	public void importModel(Model model) {
		ClosableIterator<Statement> statements = model.iterator();
		while (statements.hasNext()) {
			handleStatement(statements.next());
		}
		statements.close();
	}
	
	public void importModelSet(ModelSet modelSet, URI... contexts) {
		ClosableIterator<Statement> statements = null;
		for (URI context : contexts) {
			statements = modelSet.findStatements(context, Variable.ANY, Variable.ANY, Variable.ANY);
			while (statements.hasNext()) {
				handleStatement(statements.next());
			}
			statements.close();
		}
	}

	public void handleStatement(Statement nextStatement) {
		Resource subject = nextStatement.getSubject();
		URI predicate = nextStatement.getPredicate();
		Object object = nextStatement.getObject();

		if (object instanceof DatatypeLiteral) {
			DatatypeLiteral literal = (DatatypeLiteral) object;
			String value = literal.getValue();
			String datatype = literal.getDatatype().toString();
			triple(subject.toString(), predicate.toString(), value, datatype, null);
		} else if (object instanceof LanguageTagLiteral) {
			LanguageTagLiteral literal = (LanguageTagLiteral) object;
			String value = literal.getValue();
			triple(subject.toString(), predicate.toString(), value, null, literal.getLanguageTag());
		} else if (object instanceof Literal) {
			Literal literal = (Literal) object;
			String value = literal.getValue();
			triple(subject.toString(), predicate.toString(), value, null, null);
		} else {
			triple(subject.toString(), predicate.toString(), object.toString());
		}
	}

}
