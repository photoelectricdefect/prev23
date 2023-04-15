lexer grammar PrevLexer;

@header {
	package prev23.phase.lexan;
	import prev23.common.report.*;
	import prev23.data.sym.*;
}

@members {
    @Override
	public Token nextToken() {
		return (Token) super.nextToken();
	}
}

CONST_VOID : 'none';
CONST_BOOL : 'true' | 'false';
CONST_INT : ([1-9][0-9]*) 
| ([0][0-9]+ {
		if(true) {
			throw new Report.Error(new Location(_tokenStartLine,
				_tokenStartCharPositionInLine, getLine(), getCharPositionInLine()),
				"Leading 0 in integer constant"
			);
		}
	} )
| [0];
CONST_PTR : 'nil';
CONST_CHAR : ('\'' ([\u0020-\u0026\u0028-\u007E] | '\\\'') '\'') {
	if(getText().charAt(getText().length()-2)=='\\') {
		throw new Report.Error(new Location(_tokenStartLine,
			_tokenStartCharPositionInLine, getLine(), getCharPositionInLine()),
			"Invalid escapement"
		);
	}
};
CONST_STR : ('"' ([\u0020-\u0021\u0023-\u007E] | '\\\u0022')* '"') {
	if(getText().charAt(getText().length()-2)=='\\') {
		throw new Report.Error(new Location(_tokenStartLine,
			_tokenStartCharPositionInLine, getLine(), getCharPositionInLine()),
			"Invalid escapement"
		);
	}
};
KEY_BOOL : 'bool';
KEY_CHAR : 'char';
KEY_DEL : 'del';
KEY_DO : 'do';
KEY_ELSE : 'else';
KEY_FUN : 'fun';
KEY_IF : 'if';
KEY_IN : 'in';
KEY_INT : 'int';
KEY_LET : 'let';
KEY_NEW : 'new';
KEY_THEN : 'then';
KEY_TYP : 'typ';
KEY_VAR : 'var';
KEY_VOID : 'void';
KEY_WHILE : 'while';
SYM_PARENTH_L : '(';
SYM_PARENTH_R : ')';
SYM_CRBRACK_L : '{';
SYM_CRBRACK_R : '}';
SYM_SQBRACK_L : '[';
SYM_SQBRACK_R : ']';
SYM_COMMA : ',';
SYM_DOT : '.';
SYM_COLON : ':';
SYM_SEMICOLON : ';';
SYM_AMPERS : '&';
SYM_VPIPE : '|';
SYM_EXCLM : '!';
SYM_DBLEQ : '==';
SYM_NEQ : '!=';
SYM_LT : '<';
SYM_GT : '>';
SYM_LTE : '<=';
SYM_GTE : '>=';
SYM_AST : '*';
SYM_FSLASH : '/';
SYM_PRCNT : '%';
SYM_PLUS : '+';
SYM_MINUS : '-';
SYM_CRCMFLX : '^';
SYM_EQ : '=';
ID : [_a-zA-Z][_a-zA-Z0-9]*;
COMMENT : '#' ~[\r\n]* '\r'? '\n' -> skip;
TAB : '\t' {setCharPositionInLine(getCharPositionInLine()+8-getCharPositionInLine()%8);} -> skip;
WS : [ \r\n]+ -> skip;