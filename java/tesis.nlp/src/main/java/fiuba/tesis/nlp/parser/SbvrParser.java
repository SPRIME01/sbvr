package fiuba.tesis.nlp.parser;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.BasicDependenciesAnnotation;
import edu.stanford.nlp.util.CoreMap;
import fiuba.tesis.nlp.XmlConverter;
import fiuba.tesis.nlp.parser.extractors.AdjectiveTermExtractor;
import fiuba.tesis.nlp.parser.extractors.BaseTermExtractor;
import fiuba.tesis.nlp.parser.extractors.ExtractorStrategy;
import fiuba.tesis.nlp.parser.extractors.NounTermExtractor;

public class SbvrParser {
	
	private ExtractorStrategy extractorStrategy;
	private XmlConverter xmlConverter;
	private List<String> terms;
	private Document xmlDocument;
	private StanfordCoreNLP pipeline;
	
	public SbvrParser() throws XPathExpressionException {
		terms = new ArrayList<String>();
		
		List<BaseTermExtractor> extractors = Arrays.asList(new NounTermExtractor(), new AdjectiveTermExtractor());
		extractorStrategy = new ExtractorStrategy(extractors);
		
		xmlConverter = new XmlConverter();
		
		Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        pipeline = new StanfordCoreNLP(props);
	}
	
	public void parse(List<String> sentences) throws XPathExpressionException, ParserConfigurationException {
		
		// convert sentences into a single string
        StringBuilder str = new StringBuilder();
        sentences.forEach(s -> str.append(s).append("\n"));
        
		// create an empty Annotation just with the given text
        Annotation annotation = new Annotation(str.toString());      
        // run all Annotators on this text
        pipeline.annotate(annotation);
        
        List<SemanticGraph> semanticGraphs = new ArrayList<SemanticGraph>();
        List<CoreMap> nlpSentences = annotation.get(SentencesAnnotation.class);
        nlpSentences.forEach(s -> {
        	SemanticGraph dependencies = s.get(BasicDependenciesAnnotation.class);
        	semanticGraphs.add(dependencies);
        });
        
        xmlDocument = xmlConverter.convert(semanticGraphs);
		terms = extractorStrategy.getTerms(xmlDocument);
	}
	
	public List<String> getTerms() {
		return terms;
	}
	
	public void printDocument(OutputStream out) 
				throws UnsupportedEncodingException, TransformerException {
		xmlConverter.print(xmlDocument, out);
	}
	
}
