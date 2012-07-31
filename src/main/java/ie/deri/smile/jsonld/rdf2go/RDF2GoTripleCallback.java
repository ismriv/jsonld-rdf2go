package ie.deri.smile.jsonld.rdf2go;

import org.ontoware.rdf2go.RDF2Go;
import org.ontoware.rdf2go.model.Model;
import org.ontoware.rdf2go.model.node.Node;
import org.ontoware.rdf2go.model.node.URI;

import de.dfki.km.json.jsonld.JSONLDTripleCallback;

public class RDF2GoTripleCallback implements JSONLDTripleCallback {

	private final Model model;
	
	public RDF2GoTripleCallback() {
		model = RDF2Go.getModelFactory().createModel();
		model.open();
	}
	
    public Model getModel() {
        return model;
    }

	@Override
	public void triple(String s, String p, String o) {
		if (s == null || p == null || o == null) {
			// TODO: I don't know what to do here!!!
			return;
		}

		// subject, predicate, object will always be URIs
		model.addStatement(model.createURI(s), model.createURI(p), model.createURI(o));
	}

	@Override
	public void triple(String s, String p, String value, String datatype, String language) {
		if (s == null || p == null || value == null) {
			// TODO: I don't know what to do here!!!
			return;
		}

		URI subject = model.createURI(s);
		URI predicate = model.createURI(p);

		Node object = null;
		if (language != null) {
			object = model.createLanguageTagLiteral(value, language);
		} else if (datatype != null) {
			object = model.createDatatypeLiteral(value, model.createURI(datatype));
		} else {
			object = model.createPlainLiteral(value);
		}

		model.addStatement(subject, predicate, object);
	}

}
