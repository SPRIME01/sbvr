package fiuba.tesis.nlp.parser.extractors;

import javax.xml.xpath.XPathExpressionException;

public class AdjectiveTermExtractor extends BaseTermExtractor {
	private static String query = "//word[@tag='JJ' and parent::relationship[contains(@tag,'nsubj')]]";
	
	public AdjectiveTermExtractor() throws XPathExpressionException {
		super(query);
	}

}
