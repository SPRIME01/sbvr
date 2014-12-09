package fiuba.tesis.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class TermExtractor {

	private StanfordCoreNLP pipeline;
	
	public TermExtractor()
	{
		Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        pipeline = new StanfordCoreNLP(props);
	}
	
	protected void parseTree(Tree tree, List<String> terms) {
		if ("NP".equals(tree.value())) {
			StringBuilder str = new StringBuilder();
			// build text
			tree.yieldWords().forEach( w -> str.append(w).append(" "));
			if (!terms.contains(str)) {
				terms.add(str.toString());
			}
		}
		
		for( Tree t : tree.children()) {
			parseTree(t, terms);
		}
	}
	
	public List<String> getTerms(String stm)
	{
		List<String> terms = new ArrayList<String>();
		
		Annotation document = new Annotation(stm);
		
        pipeline.annotate(document);
        
		for(CoreMap sentence: document.get(SentencesAnnotation.class))
		{
			parseTree(sentence.get(TreeAnnotation.class), terms);
		}
		
		return terms;
	}
}
