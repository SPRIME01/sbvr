package fiuba.tesis.nlp;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;

public class XmlConverter {
	
	private void setAttributes(Document document, IndexedWord node, Element xmlNode) {
		Attr text = document.createAttribute("text");
		text.setValue(node.value());
		xmlNode.setAttributeNode(text);
		
		Attr tag = document.createAttribute("tag");
		tag.setValue(node.backingLabel().tag());
		xmlNode.setAttributeNode(tag);
		
		Attr beginPosition = document.createAttribute("beginPosition");
		beginPosition.setValue(Integer.toString(node.beginPosition()));
		xmlNode.setAttributeNode(beginPosition);
		
		Attr endPosition = document.createAttribute("endPosition");
		endPosition.setValue(Integer.toString(node.endPosition()));
		xmlNode.setAttributeNode(endPosition);
	}
	
	private void setEdgeAttributes(Document document, SemanticGraphEdge edge, Element xmlNode) {
		Attr tag = document.createAttribute("tag");
		tag.setValue(edge.getRelation().getShortName());
		xmlNode.setAttributeNode(tag);
	}
	
	private void parse(Document document, SemanticGraph graph, IndexedWord node, Element root)
	{
		Element word = document.createElement("word");
		setAttributes(document, node, word);
		
		for(IndexedWord u : graph.getChildren(node)) {
			SemanticGraphEdge edge = graph.getEdge(node, u);
			Element relationship = document.createElement("relationship");
			setEdgeAttributes(document, edge, relationship);
			parse(document, graph, u, relationship);
			word.appendChild(relationship);
		}
		
		root.appendChild(word);
	}
	
	public Document convert(List<SemanticGraph> graphs) throws ParserConfigurationException
	{
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		// root element
		Element root = document.createElement("sentences");
		for(SemanticGraph g : graphs) {
			Element s = document.createElement("sentence");
			parse(document, g, g.getFirstRoot(), s);
			root.appendChild(s);
		}
		
		document.appendChild(root);
		return document;
	}
	
	public void print(Document document, OutputStream out) throws UnsupportedEncodingException, TransformerException {		
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	    transformer.transform(new DOMSource(document), new StreamResult(new OutputStreamWriter(out, "UTF-8")));
	}
}
