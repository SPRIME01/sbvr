package fiuba.tesis.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations;
import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations.RelationMentionsAnnotation;
import edu.stanford.nlp.ie.machinereading.structure.RelationMention;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.RelationExtractorAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class TermExtractor {

	private StanfordCoreNLP pipeline;
	
	public TermExtractor() {
		Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        pipeline = new StanfordCoreNLP(props);
	}
	
	protected boolean IsNoun(Tree tree) {
		return "NN".equals(tree.value()) || 
				"NNS".equals(tree.value()) || 
				"NNP".equals(tree.value()) || 
				"NNPS".equals(tree.value());
	}
	
	protected void findNouns(Tree tree, List<Tree> nouns) {
		if (IsNoun(tree)) {
			if (!nouns.contains(tree)){
				nouns.add(tree);
			}
		}
		
		for( Tree t : tree.children()) {
			findNouns(t, nouns);
		}
	}
	
	protected String findNounPhrase(Tree noun) {
		Tree parent = noun.parent(noun);
		if (parent != null)  {
			if ("NP".equals(parent.value())) {
				StringBuilder str = new StringBuilder();
				// build text
				parent.yieldWords().forEach( w -> str.append(w).append(" "));
				return str.toString();
			}
			return findNounPhrase(parent);
		}
		return "";
	}
	
	public List<String> getTerms(String stm)
	{
		List<Tree> nouns = new ArrayList<Tree>();
		List<String> terms = new ArrayList<String>();
		
		Annotation document = new Annotation(stm);
		
        pipeline.annotate(document);
        
		for(CoreMap sentence: document.get(SentencesAnnotation.class))
		{
			Tree tree = sentence.get(TreeAnnotation.class);
			findNouns(tree, nouns);
			
			for(Tree noun : nouns)
			{
				String nounPhrase = findNounPhrase(noun);
				if (!"".equals(nounPhrase) && !terms.contains(nounPhrase))
				{
					terms.add(nounPhrase);
				}
			}
		}
		
		
//		Map<Integer, CorefChain> graph = document.get(CorefChainAnnotation.class);
		
//		List<RelationMention> relations = document.get(MachineReadingAnnotations.RelationMentionsAnnotation.class);
//		if (relations != null) {
//			for(RelationMention r : relations) {
//				System.out.println(r.toString());	
//			}
//		}
		
		return terms;
	}
}
