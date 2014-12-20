package fiuba.tesis.nlp.parser.extractors;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

public class ExtractorStrategy {
	private List<BaseTermExtractor> extractors;
	
	public ExtractorStrategy(List<BaseTermExtractor> extractors) {
		this.extractors = new ArrayList<BaseTermExtractor>();
		this.extractors.addAll(extractors);
	}
	
	public List<String> getTerms(Document xmlDocument) throws XPathExpressionException {
		List<String> terms = new ArrayList<String>();
		
		for(BaseTermExtractor ext : this.extractors){
			List<String> ts = ext.getTerms(xmlDocument);
			ts.forEach(s -> {
				if(!terms.contains(s))
					terms.add(s);
			});
		}
		
		return terms;
	}
}
