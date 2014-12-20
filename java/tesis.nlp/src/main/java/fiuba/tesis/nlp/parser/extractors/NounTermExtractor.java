package fiuba.tesis.nlp.parser.extractors;

import javax.xml.xpath.XPathExpressionException;

public class NounTermExtractor extends BaseTermExtractor {
	private static String query = "//word["
			+ "not(ancestor::*[contains(@tag,'NN') or contains(@tag,'NNS') or contains(@tag,'NNP') or contains(@tag,'NNPS')]) and "
			+ "(@tag='NN' or @tag='NNS' or @tag='NNP' or @tag='NNPS')]";
	
	public NounTermExtractor() throws XPathExpressionException {
		super(query);
	}
}
