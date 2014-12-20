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
    	sentences.add("rental has drop-off branch.");
    	sentences.add("rental has rental price.");
    	sentences.add("rental has pick-up branch.");
    	sentences.add("rental has grace period.");
    	sentences.add("rental has actual return date-time.");
    	sentences.add("rental has actual pick-up date-time.");
    	sentences.add("rental has rented-car.");
    	sentences.add("rental has estimated-rental-price.");
    	sentences.add("rental incurs recovery charge");
    	
    	SbvrParser parser = new SbvrParser();
    	parser.parse(sentences);
    	
    	List<String> terms = parser.getTerms();
    	terms.forEach(t -> System.out.println(t));
    	// parser.printDocument(System.out);
    }
    
}
