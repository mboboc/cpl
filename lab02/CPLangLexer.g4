lexer grammar CPLangLexer;

/* Data type */
TYPE: UPPERCASE LETTER*;

fragment DIGIT : [0-9];
fragment DIGITS : DIGIT+;
fragment FRACTION : ('.' DIGITS);
fragment EXPONENT : ('e' ('+' | '-')? DIGITS)?;
FLOAT : DIGITS? FRACTION EXPONENT;

INT : DIGIT+;

/* Keywords */
IF : 'if';
ELSE: 'else';
THEN: 'then';
FI: 'fi';
TRUE: 'true';
FALSE: 'false';

/* Separators */
SEMICOL : ';';
COMMA : ',';

/* Operators */
ASSIGN : '=';
EQUAL : '==';
PLUS : '+';
MINUS : '-';
MUL: '*';
DIV: '/';

/* Brakets */
OPARAN : '(';
CPARAN : ')';
OBRACE : '{';
CBRACE : '}';

/* Identifier - function name/variable name */
fragment LETTER: [a-zA-Z];
fragment UPPERCASE: [A-Z];
fragment LOWERCASE: [a-z];
ID : (LOWERCASE | '_')(LETTER | '_' | DIGIT)*;

/* String */
STRING : '"' ('\\"' | .)*? '"'
    { System.out.println("there are no strings in CPLang, but shhh.."); };

/* Comments */
BLOCK_COMMENT : '/*' (BLOCK_COMMENT | .)*? ('*/' | EOF { System.err.println("ERROR: Unclosed block comment");}) -> skip;
COMMENT : '//' .*? '\r\n' -> skip;
UNCOMPLETE_COMMENT : '*/' {System.err.println("Invalid comment");} -> skip;

/* Whitespace */
WS : [ \n\r\t]+ -> skip;
