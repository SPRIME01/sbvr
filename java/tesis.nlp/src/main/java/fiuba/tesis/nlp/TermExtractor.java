package fiuba.tesis.nlp;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations;
import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations.RelationMentionsAnnotation;
import edu.stanford.nlp.ie.machinereading.structure.RelationMention;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.RelationExtractorAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.BasicDependenciesAnnotation;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.ArraySet;
import edu.stanford.nlp.util.CoreMap;

public class TermExtractor {

	private StanfordCoreNLP pipeline;
	
	public TermExtractor() {
		Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        pipeline = new StanfordCoreNLP(props);
	}
	
	protected boolean isNoun(String tag) {
		return "NN".equals(tag) || 
				"NNS".equals(tag) || 
				"NNP".equals(tag) || 
				"NNPS".equals(tag);
	}
	
		
	protected String buildWord(Tree tree) {
		StringBuilder str = new StringBuilder();
		// build text
		tree.yieldWords().forEach( w -> str.append(w).append(" "));
		return str.toString();
	}
	
	protected void findTerms(SemanticGraph g, IndexedWord node, StringBuilder currentNoun, List<String> terms) {
		String tag = node.backingLabel().tag();
		boolean releaseNoun = false;		
		if (isNoun(tag) && currentNoun == null) {
			currentNoun = new StringBuilder();			
			releaseNoun = true;
		}
		if (currentNoun != null) {
			currentNoun.append(node.value()).append(" ");
		}
		for(IndexedWord u : g.getChildren(node)) {
			findTerms(g, u, currentNoun, terms);
		}
		if (releaseNoun){
			terms.add(currentNoun.toString());
			currentNoun = null;
		}
	}
	
	public List<String> getTerms(String stm)
	{
		List<Tree> nouns = new ArrayList<Tree>();
		List<String> terms = new ArrayList<String>();
		
		Annotation document = new Annotation(stm);
		
        pipeline.annotate(document);
        
		for(CoreMap sentence: document.get(SentencesAnnotation.class))
		{
			SemanticGraph dependencies = sentence.get(BasicDependenciesAnnotation.class);
			//bfs(dependencies, dependencies.getFirstRoot());
			findTerms(dependencies, dependencies.getFirstRoot(), null, terms);
		}
		
		
		return terms;
	}
}
