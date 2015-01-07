package fiuba.tesis.grammar.test;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import fiuba.tesis.grammar.antlr4.sbvrLexer;
import fiuba.tesis.grammar.antlr4.sbvrParser;
import fiuba.tesis.grammar.antlr4.sbvrParser.ProgContext;

public class AllTests {
	
	@Test
	public void test1() {
		String expressionString = "a";
		ANTLRInputStream input = new ANTLRInputStream(expressionString);
		sbvrLexer lexer = new sbvrLexer(input);
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// create a parser that feeds off the tokens buffer
		sbvrParser parser = new sbvrParser(tokens);
		ProgContext tree = parser.prog(); // begin parsing at init rule
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
	}

}
