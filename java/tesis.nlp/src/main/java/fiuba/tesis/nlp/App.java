package fiuba.tesis.nlp;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.parser.ui.TreeJPanel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class App 
{
	public static void printTree(String indent, Tree tree)
	{
		if ("NP".equals(tree.value())) {
			StringBuilder str = new StringBuilder();
			for(Word w : tree.yieldWords()) {
				str.append(w).append(" ");
			}
			//System.out.println("TERM: " + str.toString());
		}
		System.out.print(indent); 
		tree.printLocalTree();
		for( Tree t : tree.children())
		{
			printTree(indent + "\t", t);
		}
	}
	
    public static void main( String[] args )
    {
    	//String text = "rental car has fuel level";
    	//String text = "If the renter who is responsible for a rental requests a price conversion for the rental then it is " +
    	//		"obligatory that the rental price of the rental is converted to the currency of the price conversion";
    	String text = "rental has drop-off branch";
//    	// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//        
//        // read some text in the text variable
//        String text = "rental car has fuel level";
//        
//        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);
//        
//        // run all Annotators on this text
        pipeline.annotate(document);
//        
//        // these are all the sentences in this document
//        // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//        
        for(CoreMap sentence: sentences) {
//          // traversing the words in the current sentence
//          // a CoreLabel is a CoreMap with additional token-specific methods
//          for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
//            // this is the text of the token
//            String word = token.get(TextAnnotation.class);
//            // this is the POS tag of the token
//            String pos = token.get(PartOfSpeechAnnotation.class);
//            // this is the NER label of the token
//            String ne = token.get(NamedEntityTagAnnotation.class);
//            System.out.println(word + ": " + pos + " - NER: " + ne);
//          }
//
          // this is the parse tree of the current sentence
          Tree tree = sentence.get(TreeAnnotation.class);
          printTree("", tree);
          
          TreeJPanel panel = new TreeJPanel();
          panel.setTree(tree);
          
//
//          // this is the Stanford dependency graph of the current sentence
//          // SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
        }

        // This is the coreference link graph
        // Each chain stores a set of mentions that link to each other,
        // along with a method for getting the most representative mention
        // Both sentence and token offsets start at 1!
        // Map<Integer, CorefChain> graph = document.get(CorefChainAnnotation.class);
    	
    	TermExtractor extractor = new TermExtractor();
    	List<String> terms = extractor.getTerms(text);
    	for(String term : terms) {
    		System.out.println("Term: " + term);
    	}
    }
}
