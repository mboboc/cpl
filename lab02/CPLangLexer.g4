lexer grammar CPLangLexer;

tokens { ERROR }

@members{
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

fragment LETTER : [a-zA-Z];
fragment UPPERCASE: [A-Z];
fragment LOWERCASE: [a-z];
fragment NEW_LINE : '\r'? '\n';

/* If */
IF : 'if';
THEN : 'then';
ELSE : 'else';
FI : 'fi';

/* Not */
NOT : 'not';

/* While */
WHILE : 'while';
LOOP : 'loop';
POOL : 'pool';

/* Let */
LET : 'let';
IN : 'in';

/* Case */
CASE : 'case';
OF : 'of';
ESAC : 'esac';
ARROW : '=>';

fragment DIGIT : [0-9];

/* Data type */
SELF_TYPE : 'SELF_TYPE';
TYPE : UPPERCASE (LETTER | DIGIT)*;
ISVOID : 'isvoid';

/* Integer */
INT : DIGIT+;

/* Float */
fragment DIGITS : DIGIT+;
fragment EXPONENT : 'e' ('+' | '-')? DIGITS;
FLOAT : (DIGITS ('.' DIGITS?)? | '.' DIGITS) EXPONENT?;

/* Bool */
BOOL : 'true' | 'false';

/* Classes */
CLASS : 'class';
INHERITS : 'inherits';
NEW : 'new';
SELF : 'self';

/* Identifier */
ID : (LOWERCASE | '_')(LETTER | '_' | DIGIT)*;

/* String */
//STRING :  '"' ( '\\' ('\\'|'\t'|'\r\n'|'\r'|'\n'|'"') | ~('\\'|'\t'|'\r'|'\n'|'"') )* ('"' | NEW_LINE { raiseError("Lexical error: Unterminated string constant"); } | EOF { raiseError("Lexical error: EOF in string constant"); });

//INCOMPLETE_STRING
//: '"' ('\\"' | .)*? EOF //{ raiseError("Lexical error: EOF in string constant"); } | NEW_LINE { raiseError("Lexical error: Unterminated string constant"); })
//;

STRING
 : '"' ( '\\' ('\\'|'\t'|'\r\n'|'\r'|'\n'|'"') | ~('\\'|'\t'|'\r'|'\n'|'"') )* ('"' | EOF | '\r''\n' ) // { raiseError("EOF in string constant"); } | '\r''\n' { raiseError("Unterminated string constant"); })
 ;

SEMI : ';';
COMMA : ',';
COLON : ':';
DOT : '.';

LPAREN : '(';
RPAREN : ')';

LBRACE : '{';
RBRACE : '}';

PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';

ASSIGN : '<-';
EQUAL : '=';

LT : '<';
LE : '<=';

INV : '~';
AT : '@';

LINE_COMMENT
    : '--' .*? (NEW_LINE | EOF) -> skip
    ;

BLOCK_COMMENT
    : '(*'
      (BLOCK_COMMENT | .)*?
      ('*)' | EOF { System.err.println("EOF in comment"); }) -> skip
    ;

WS : [ \n\r\t]+ -> skip;