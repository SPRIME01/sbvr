package fiuba.tesis.nlp.parser.extractors;

import javax.xml.xpath.XPathExpressionException;

public class ClausalPassiveSubjectTermExtractor extends BaseTermExtractor {
	private static String query = "//word[parent::relationship[contains(@tag,'csubjpass')]]";
	
	public ClausalPassiveSubjectTermExtractor()
			throws XPathExpressionException {
		super(query);
	}

}
