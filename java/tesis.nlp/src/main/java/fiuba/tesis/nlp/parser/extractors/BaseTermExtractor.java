package fiuba.tesis.nlp.parser.extractors;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class BaseTermExtractor {
	private XPathExpression expr;
	
	protected void getText(Node node, TreeMap<Integer, String> map) {
		
		if ("word".equals(node.getNodeName())) {
			NamedNodeMap attributes = node.getAttributes();
			Integer beginPosition = Integer.decode(attributes.getNamedItem("beginPosition").getNodeValue());
			String text = attributes.getNamedItem("text").getNodeValue();
			map.put(beginPosition, text);
		}
	
		if (node.hasChildNodes()) {
			NodeList children = node.getChildNodes();
			for(int i = 0; i < children.getLength(); i++){
				getText(children.item(i), map);
			}
		}
	}

	public BaseTermExtractor(String query) throws XPathExpressionException {
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		expr = xpath.compile(query);
	}
	
	public List<String> getTerms(Document document) throws XPathExpressionException
	{
		List<String> terms = new ArrayList<String>();
		NodeList nl = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		for(int i = 0; i < nl.getLength(); i++){
			Node n = nl.item(i);
			if (n.hasChildNodes()) {
				TreeMap<Integer, String> map = new TreeMap<Integer, String>();
				getText(n, map);
				StringBuilder str = new StringBuilder();
				map.values().forEach(s -> str.append(s).append(" "));
				
				String term = str.toString().trim();
				if (!terms.contains(term)) {
					terms.add(term);
				}
			}
			else {
				// single word term
				String text = n.getAttributes().getNamedItem("text").getNodeValue();
				if (!terms.contains(text)) {
					terms.add(text);
				}
			}
		}
			
		return terms;
	}
}
