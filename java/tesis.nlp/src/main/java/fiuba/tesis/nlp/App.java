package fiuba.tesis.nlp;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import fiuba.tesis.nlp.parser.SbvrParser;

public class App 
{
    public static void main( String[] args ) throws ParserConfigurationException, UnsupportedEncodingException, TransformerException, XPathExpressionException
    {
    	List<String> sentences = new ArrayList<String>();
    	sentences.add("rental being open.");
    	
    	SbvrParser parser = new SbvrParser();
    	parser.parse(sentences);
    	
    	List<String> terms = parser.getTerms();
    	terms.forEach(t -> System.out.println(t));
    	parser.printDocument(System.out);
    }
    
}
