grammar sbvr;

@header {
package fiuba.tesis.grammar.antlr4;
}

/** The start rule; begin parsing here. */
prog: stat+ ;

stat:  article | cardinality;

article: ('a' | 'an ' | 'the ');
cardinality: ('exactly ' | 'at least ' (POSITIVE_INTEGER ' and at most '| )) POSITIVE_INTEGER;

ID : [a-zA-Z]+ ;
POSITIVE_INTEGER : [0-9]+;
NEWLINE:'\r'? '\n' ; // return newlines to parser (is end-statement signal)
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
