/**
 * Define a grammar called Hello
 */
grammar Hello;

/** The start rule; begin parsing here. */
prog: stat+ ;

stat: TERM_NAME 'has' ATTRIBUTE_NAME ;

// stat2: ATTRIBUTE_NAME 'is' ENUMERATION_VALUES ;

ID : [a-zA-Z]+ ;
TERM_NAME : [a-zA-Z]+ ; 
ATTRIBUTE_NAME : [a-zA-Z]+ ;
//ENUMERATION_VALUES : [ID, ]
NEWLINE:'\r'? '\n' ; // return newlines to parser (is end-statement signal)
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

