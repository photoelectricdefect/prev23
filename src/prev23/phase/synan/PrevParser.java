// Generated from java-escape by ANTLR 4.11.1


	package prev23.phase.synan;
	
	import java.util.*;
	
	import prev23.common.report.*;
	import prev23.phase.lexan.*;
	
	import prev23.data.ast.tree.*;
	import prev23.data.ast.tree.decl.*;
	import prev23.data.ast.tree.expr.*;
	import prev23.data.ast.tree.stmt.*;
	import prev23.data.ast.tree.type.*;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PrevParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONST_VOID=1, CONST_BOOL=2, CONST_INT=3, CONST_PTR=4, CONST_CHAR=5, CONST_STR=6, 
		KEY_BOOL=7, KEY_CHAR=8, KEY_DEL=9, KEY_DO=10, KEY_ELSE=11, KEY_FUN=12, 
		KEY_IF=13, KEY_IN=14, KEY_INT=15, KEY_LET=16, KEY_NEW=17, KEY_THEN=18, 
		KEY_TYP=19, KEY_VAR=20, KEY_VOID=21, KEY_WHILE=22, SYM_PARENTH_L=23, SYM_PARENTH_R=24, 
		SYM_CRBRACK_L=25, SYM_CRBRACK_R=26, SYM_SQBRACK_L=27, SYM_SQBRACK_R=28, 
		SYM_COMMA=29, SYM_DOT=30, SYM_COLON=31, SYM_SEMICOLON=32, SYM_AMPERS=33, 
		SYM_VPIPE=34, SYM_EXCLM=35, SYM_DBLEQ=36, SYM_NEQ=37, SYM_LT=38, SYM_GT=39, 
		SYM_LTE=40, SYM_GTE=41, SYM_AST=42, SYM_FSLASH=43, SYM_PRCNT=44, SYM_PLUS=45, 
		SYM_MINUS=46, SYM_CRCMFLX=47, SYM_EQ=48, ID=49, COMMENT=50, TAB=51, WS=52;
	public static final int
		RULE_source = 0, RULE_declarations = 1, RULE_type_declarations = 2, RULE_function_declarations = 3, 
		RULE_variable_declarations = 4, RULE_type = 5, RULE_expression = 6, RULE_statement = 7, 
		RULE_grp_op_unary = 8, RULE_op_postfix = 9, RULE_op_prefix = 10, RULE_op_mult = 11, 
		RULE_op_add = 12, RULE_op_rel = 13, RULE_op_conj = 14, RULE_op_disj = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "declarations", "type_declarations", "function_declarations", 
			"variable_declarations", "type", "expression", "statement", "grp_op_unary", 
			"op_postfix", "op_prefix", "op_mult", "op_add", "op_rel", "op_conj", 
			"op_disj"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'none'", null, null, "'nil'", null, null, "'bool'", "'char'", 
			"'del'", "'do'", "'else'", "'fun'", "'if'", "'in'", "'int'", "'let'", 
			"'new'", "'then'", "'typ'", "'var'", "'void'", "'while'", "'('", "')'", 
			"'{'", "'}'", "'['", "']'", "','", "'.'", "':'", "';'", "'&'", "'|'", 
			"'!'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'*'", "'/'", "'%'", 
			"'+'", "'-'", "'^'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CONST_VOID", "CONST_BOOL", "CONST_INT", "CONST_PTR", "CONST_CHAR", 
			"CONST_STR", "KEY_BOOL", "KEY_CHAR", "KEY_DEL", "KEY_DO", "KEY_ELSE", 
			"KEY_FUN", "KEY_IF", "KEY_IN", "KEY_INT", "KEY_LET", "KEY_NEW", "KEY_THEN", 
			"KEY_TYP", "KEY_VAR", "KEY_VOID", "KEY_WHILE", "SYM_PARENTH_L", "SYM_PARENTH_R", 
			"SYM_CRBRACK_L", "SYM_CRBRACK_R", "SYM_SQBRACK_L", "SYM_SQBRACK_R", "SYM_COMMA", 
			"SYM_DOT", "SYM_COLON", "SYM_SEMICOLON", "SYM_AMPERS", "SYM_VPIPE", "SYM_EXCLM", 
			"SYM_DBLEQ", "SYM_NEQ", "SYM_LT", "SYM_GT", "SYM_LTE", "SYM_GTE", "SYM_AST", 
			"SYM_FSLASH", "SYM_PRCNT", "SYM_PLUS", "SYM_MINUS", "SYM_CRCMFLX", "SYM_EQ", 
			"ID", "COMMENT", "TAB", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    public Location location(Token token) { 
			if(token != null) {
				return new Location((prev23.data.sym.Token) token); 
			}
			else {
				return new Location(0,0,0,0);
			}
		}

	    public Location location(Locatable locat) { 
			if(locat != null) {
				return new Location(locat); 
			}
			else {
				return new Location(0,0,0,0);
			}
		}

	    public Location location(Token token1, Token token2) { 
			return new Location((prev23.data.sym.Token) token1, (prev23.data.sym.Token) token2); 
		}
	    
		public Location location(Token token1, Locatable locat2) { 
			return new Location((prev23.data.sym.Token) token1, locat2); 
		}
	    
		public Location location(Locatable locat1, Token token2) { 
			return new Location(locat1, (prev23.data.sym.Token) token2); 
		}
	    
		public Location location(Locatable locat1, Locatable locat2) { 
			return new Location(locat1, locat2); 
		}	

	public PrevParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceContext extends ParserRuleContext {
		public AstTree ast;
		public DeclarationsContext declarations;
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PrevParser.EOF, 0); }
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			((SourceContext)_localctx).declarations = declarations();
			setState(33);
			match(EOF);

					((SourceContext)_localctx).ast = ((SourceContext)_localctx).declarations.ast;
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationsContext extends ParserRuleContext {
		public AstTrees<AstTrees<AstDecl>> ast;
		public Vector<AstTrees<AstDecl>> nodes = new Vector<>();
		public Type_declarationsContext type_declarations;
		public Function_declarationsContext function_declarations;
		public Variable_declarationsContext variable_declarations;
		public List<Type_declarationsContext> type_declarations() {
			return getRuleContexts(Type_declarationsContext.class);
		}
		public Type_declarationsContext type_declarations(int i) {
			return getRuleContext(Type_declarationsContext.class,i);
		}
		public List<Function_declarationsContext> function_declarations() {
			return getRuleContexts(Function_declarationsContext.class);
		}
		public Function_declarationsContext function_declarations(int i) {
			return getRuleContext(Function_declarationsContext.class,i);
		}
		public List<Variable_declarationsContext> variable_declarations() {
			return getRuleContexts(Variable_declarationsContext.class);
		}
		public Variable_declarationsContext variable_declarations(int i) {
			return getRuleContext(Variable_declarationsContext.class,i);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(45);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case KEY_TYP:
					{
					setState(36);
					((DeclarationsContext)_localctx).type_declarations = type_declarations();

						_localctx.nodes.add(((DeclarationsContext)_localctx).type_declarations.ast);

					}
					break;
				case KEY_FUN:
					{
					setState(39);
					((DeclarationsContext)_localctx).function_declarations = function_declarations();

						_localctx.nodes.add(((DeclarationsContext)_localctx).function_declarations.ast);

					}
					break;
				case KEY_VAR:
					{
					setState(42);
					((DeclarationsContext)_localctx).variable_declarations = variable_declarations();

						_localctx.nodes.add(((DeclarationsContext)_localctx).variable_declarations.ast);

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 1576960L) != 0 );

				((DeclarationsContext)_localctx).ast = new AstTrees("Declarations",_localctx.nodes);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_declarationsContext extends ParserRuleContext {
		public AstTrees<AstDecl> ast;
		public Vector<AstDecl> nodes = new Vector<>();
		public Token id_0;
		public TypeContext tp_0;
		public Token id_1;
		public TypeContext tp_1;
		public TerminalNode KEY_TYP() { return getToken(PrevParser.KEY_TYP, 0); }
		public List<TerminalNode> SYM_EQ() { return getTokens(PrevParser.SYM_EQ); }
		public TerminalNode SYM_EQ(int i) {
			return getToken(PrevParser.SYM_EQ, i);
		}
		public TerminalNode SYM_SEMICOLON() { return getToken(PrevParser.SYM_SEMICOLON, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> SYM_COMMA() { return getTokens(PrevParser.SYM_COMMA); }
		public TerminalNode SYM_COMMA(int i) {
			return getToken(PrevParser.SYM_COMMA, i);
		}
		public Type_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_declarations; }
	}

	public final Type_declarationsContext type_declarations() throws RecognitionException {
		Type_declarationsContext _localctx = new Type_declarationsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(KEY_TYP);
			setState(52);
			((Type_declarationsContext)_localctx).id_0 = match(ID);
			setState(53);
			match(SYM_EQ);
			setState(54);
			((Type_declarationsContext)_localctx).tp_0 = type();

				_localctx.nodes.add(new AstTypDecl(location(((Type_declarationsContext)_localctx).id_0,((Type_declarationsContext)_localctx).tp_0.ast), ((Type_declarationsContext)_localctx).id_0.getText(), ((Type_declarationsContext)_localctx).tp_0.ast));

			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SYM_COMMA) {
				{
				{
				setState(56);
				match(SYM_COMMA);
				setState(57);
				((Type_declarationsContext)_localctx).id_1 = match(ID);
				setState(58);
				match(SYM_EQ);
				setState(59);
				((Type_declarationsContext)_localctx).tp_1 = type();

					_localctx.nodes.add(new AstTypDecl(location(((Type_declarationsContext)_localctx).id_1,((Type_declarationsContext)_localctx).tp_1.ast), ((Type_declarationsContext)_localctx).id_1.getText(), ((Type_declarationsContext)_localctx).tp_1.ast));

				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(SYM_SEMICOLON);

				((Type_declarationsContext)_localctx).ast = new AstTrees("TypeDeclarations",_localctx.nodes);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_declarationsContext extends ParserRuleContext {
		public AstTrees<AstDecl> ast;
		public Vector<AstParDecl> pr_decls = new Vector<>();
		public Vector<AstDecl> nodes = new Vector<>();
		public AstStmt nullableStmt = null;
		public Token id_0;
		public Token id_1;
		public TypeContext tp_0;
		public Token id_2;
		public TypeContext tp_1;
		public TypeContext tp_2;
		public StatementContext st_0;
		public Token id_3;
		public Token id_4;
		public TypeContext tp_3;
		public Token id_5;
		public TypeContext tp_4;
		public TypeContext tp_5;
		public StatementContext st_1;
		public TerminalNode KEY_FUN() { return getToken(PrevParser.KEY_FUN, 0); }
		public List<TerminalNode> SYM_PARENTH_L() { return getTokens(PrevParser.SYM_PARENTH_L); }
		public TerminalNode SYM_PARENTH_L(int i) {
			return getToken(PrevParser.SYM_PARENTH_L, i);
		}
		public List<TerminalNode> SYM_PARENTH_R() { return getTokens(PrevParser.SYM_PARENTH_R); }
		public TerminalNode SYM_PARENTH_R(int i) {
			return getToken(PrevParser.SYM_PARENTH_R, i);
		}
		public List<TerminalNode> SYM_COLON() { return getTokens(PrevParser.SYM_COLON); }
		public TerminalNode SYM_COLON(int i) {
			return getToken(PrevParser.SYM_COLON, i);
		}
		public TerminalNode SYM_SEMICOLON() { return getToken(PrevParser.SYM_SEMICOLON, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> SYM_EQ() { return getTokens(PrevParser.SYM_EQ); }
		public TerminalNode SYM_EQ(int i) {
			return getToken(PrevParser.SYM_EQ, i);
		}
		public List<TerminalNode> SYM_COMMA() { return getTokens(PrevParser.SYM_COMMA); }
		public TerminalNode SYM_COMMA(int i) {
			return getToken(PrevParser.SYM_COMMA, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Function_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declarations; }
	}

	public final Function_declarationsContext function_declarations() throws RecognitionException {
		Function_declarationsContext _localctx = new Function_declarationsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(KEY_FUN);
			setState(71);
			((Function_declarationsContext)_localctx).id_0 = match(ID);
			setState(72);
			match(SYM_PARENTH_L);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(73);
				((Function_declarationsContext)_localctx).id_1 = match(ID);
				setState(74);
				match(SYM_COLON);
				setState(75);
				((Function_declarationsContext)_localctx).tp_0 = type();

					_localctx.pr_decls.add(new AstParDecl(location(((Function_declarationsContext)_localctx).id_1,((Function_declarationsContext)_localctx).tp_0.ast),((Function_declarationsContext)_localctx).id_1.getText(),((Function_declarationsContext)_localctx).tp_0.ast));

				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SYM_COMMA) {
					{
					{
					setState(77);
					match(SYM_COMMA);
					setState(78);
					((Function_declarationsContext)_localctx).id_2 = match(ID);
					setState(79);
					match(SYM_COLON);
					setState(80);
					((Function_declarationsContext)_localctx).tp_1 = type();

						_localctx.pr_decls.add(new AstParDecl(location(((Function_declarationsContext)_localctx).id_2,((Function_declarationsContext)_localctx).tp_1.ast),((Function_declarationsContext)_localctx).id_2.getText(),((Function_declarationsContext)_localctx).tp_1.ast));	

					}
					}
					setState(87);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(90);
			match(SYM_PARENTH_R);
			setState(91);
			match(SYM_COLON);
			setState(92);
			((Function_declarationsContext)_localctx).tp_2 = type();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SYM_EQ) {
				{
				setState(93);
				match(SYM_EQ);
				setState(94);
				((Function_declarationsContext)_localctx).st_0 = statement();

					((Function_declarationsContext)_localctx).nullableStmt = ((Function_declarationsContext)_localctx).st_0.ast;

				}
			}


				Location lc=_localctx.nullableStmt!=null?location(((Function_declarationsContext)_localctx).id_0,_localctx.nullableStmt):location(((Function_declarationsContext)_localctx).id_0,((Function_declarationsContext)_localctx).tp_2.ast);
				_localctx.nodes.add(new AstFunDecl(lc, ((Function_declarationsContext)_localctx).id_0.getText(), new AstTrees("ParamDeclarations",_localctx.pr_decls), ((Function_declarationsContext)_localctx).tp_2.ast,_localctx.nullableStmt));	
				_localctx.pr_decls.clear();
				((Function_declarationsContext)_localctx).nullableStmt = null;

			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SYM_COMMA) {
				{
				{
				setState(100);
				match(SYM_COMMA);
				setState(101);
				((Function_declarationsContext)_localctx).id_3 = match(ID);
				setState(102);
				match(SYM_PARENTH_L);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(103);
					((Function_declarationsContext)_localctx).id_4 = match(ID);
					setState(104);
					match(SYM_COLON);
					setState(105);
					((Function_declarationsContext)_localctx).tp_3 = type();

						_localctx.pr_decls.add(new AstParDecl(location(((Function_declarationsContext)_localctx).id_4,((Function_declarationsContext)_localctx).tp_3.ast),((Function_declarationsContext)_localctx).id_4.getText(),((Function_declarationsContext)_localctx).tp_3.ast));	

					setState(115);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SYM_COMMA) {
						{
						{
						setState(107);
						match(SYM_COMMA);
						setState(108);
						((Function_declarationsContext)_localctx).id_5 = match(ID);
						setState(109);
						match(SYM_COLON);
						setState(110);
						((Function_declarationsContext)_localctx).tp_4 = type();

							_localctx.pr_decls.add(new AstParDecl(location(((Function_declarationsContext)_localctx).id_5,((Function_declarationsContext)_localctx).tp_4.ast),((Function_declarationsContext)_localctx).id_5.getText(),((Function_declarationsContext)_localctx).tp_4.ast));

						}
						}
						setState(117);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(120);
				match(SYM_PARENTH_R);
				setState(121);
				match(SYM_COLON);
				setState(122);
				((Function_declarationsContext)_localctx).tp_5 = type();
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SYM_EQ) {
					{
					setState(123);
					match(SYM_EQ);
					setState(124);
					((Function_declarationsContext)_localctx).st_1 = statement();

						((Function_declarationsContext)_localctx).nullableStmt = ((Function_declarationsContext)_localctx).st_1.ast;

					}
				}


					Location lc0=_localctx.nullableStmt!=null?location(((Function_declarationsContext)_localctx).id_3,_localctx.nullableStmt):location(((Function_declarationsContext)_localctx).id_3,((Function_declarationsContext)_localctx).tp_5.ast);
					_localctx.nodes.add(new AstFunDecl(lc0, ((Function_declarationsContext)_localctx).id_3.getText(), new AstTrees("ParamDeclarations", _localctx.pr_decls), ((Function_declarationsContext)_localctx).tp_5.ast,_localctx.nullableStmt));	
					_localctx.pr_decls.clear();
					((Function_declarationsContext)_localctx).nullableStmt = null;

				}
				}
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(136);
			match(SYM_SEMICOLON);

				((Function_declarationsContext)_localctx).ast = new AstTrees("FunDeclarations",_localctx.nodes);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Variable_declarationsContext extends ParserRuleContext {
		public AstTrees<AstDecl> ast;
		public Vector<AstDecl> nodes = new Vector<>();
		public Token id_0;
		public TypeContext tp_0;
		public Token id_1;
		public TypeContext tp_1;
		public TerminalNode KEY_VAR() { return getToken(PrevParser.KEY_VAR, 0); }
		public List<TerminalNode> SYM_COLON() { return getTokens(PrevParser.SYM_COLON); }
		public TerminalNode SYM_COLON(int i) {
			return getToken(PrevParser.SYM_COLON, i);
		}
		public TerminalNode SYM_SEMICOLON() { return getToken(PrevParser.SYM_SEMICOLON, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> SYM_COMMA() { return getTokens(PrevParser.SYM_COMMA); }
		public TerminalNode SYM_COMMA(int i) {
			return getToken(PrevParser.SYM_COMMA, i);
		}
		public Variable_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declarations; }
	}

	public final Variable_declarationsContext variable_declarations() throws RecognitionException {
		Variable_declarationsContext _localctx = new Variable_declarationsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(KEY_VAR);
			setState(140);
			((Variable_declarationsContext)_localctx).id_0 = match(ID);
			setState(141);
			match(SYM_COLON);
			setState(142);
			((Variable_declarationsContext)_localctx).tp_0 = type();

				_localctx.nodes.add(new AstVarDecl(location(((Variable_declarationsContext)_localctx).id_0,((Variable_declarationsContext)_localctx).tp_0.ast),((Variable_declarationsContext)_localctx).id_0.getText(),((Variable_declarationsContext)_localctx).tp_0.ast));

			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SYM_COMMA) {
				{
				{
				setState(144);
				match(SYM_COMMA);
				setState(145);
				((Variable_declarationsContext)_localctx).id_1 = match(ID);
				setState(146);
				match(SYM_COLON);
				setState(147);
				((Variable_declarationsContext)_localctx).tp_1 = type();

					_localctx.nodes.add(new AstVarDecl(location(((Variable_declarationsContext)_localctx).id_1,((Variable_declarationsContext)_localctx).tp_1.ast),((Variable_declarationsContext)_localctx).id_1.getText(),((Variable_declarationsContext)_localctx).tp_1.ast));

				}
				}
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(155);
			match(SYM_SEMICOLON);

				((Variable_declarationsContext)_localctx).ast = new AstTrees("VarDeclarations",_localctx.nodes);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public AstType ast;
		public Vector<AstCmpDecl> nodes = new Vector<>();
		public Token KEY_VOID;
		public Token KEY_CHAR;
		public Token KEY_INT;
		public Token KEY_BOOL;
		public Token ID;
		public Token SYM_SQBRACK_L;
		public ExpressionContext expression;
		public TypeContext type;
		public Token SYM_CRCMFLX;
		public Token SYM_CRBRACK_L;
		public Token id_0;
		public TypeContext tp_0;
		public Token id_1;
		public TypeContext tp_1;
		public Token SYM_CRBRACK_R;
		public Token SYM_PARENTH_L;
		public Token SYM_PARENTH_R;
		public TerminalNode KEY_VOID() { return getToken(PrevParser.KEY_VOID, 0); }
		public TerminalNode KEY_CHAR() { return getToken(PrevParser.KEY_CHAR, 0); }
		public TerminalNode KEY_INT() { return getToken(PrevParser.KEY_INT, 0); }
		public TerminalNode KEY_BOOL() { return getToken(PrevParser.KEY_BOOL, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public TerminalNode SYM_SQBRACK_L() { return getToken(PrevParser.SYM_SQBRACK_L, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SYM_SQBRACK_R() { return getToken(PrevParser.SYM_SQBRACK_R, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode SYM_CRCMFLX() { return getToken(PrevParser.SYM_CRCMFLX, 0); }
		public TerminalNode SYM_CRBRACK_L() { return getToken(PrevParser.SYM_CRBRACK_L, 0); }
		public List<TerminalNode> SYM_COLON() { return getTokens(PrevParser.SYM_COLON); }
		public TerminalNode SYM_COLON(int i) {
			return getToken(PrevParser.SYM_COLON, i);
		}
		public TerminalNode SYM_CRBRACK_R() { return getToken(PrevParser.SYM_CRBRACK_R, 0); }
		public List<TerminalNode> SYM_COMMA() { return getTokens(PrevParser.SYM_COMMA); }
		public TerminalNode SYM_COMMA(int i) {
			return getToken(PrevParser.SYM_COMMA, i);
		}
		public TerminalNode SYM_PARENTH_L() { return getToken(PrevParser.SYM_PARENTH_L, 0); }
		public TerminalNode SYM_PARENTH_R() { return getToken(PrevParser.SYM_PARENTH_R, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		int _la;
		try {
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEY_VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				((TypeContext)_localctx).KEY_VOID = match(KEY_VOID);

						((TypeContext)_localctx).ast = new AstAtomType(location(((TypeContext)_localctx).KEY_VOID),AstAtomType.Type.VOID);
					
				}
				break;
			case KEY_CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				((TypeContext)_localctx).KEY_CHAR = match(KEY_CHAR);

						((TypeContext)_localctx).ast = new AstAtomType(location(((TypeContext)_localctx).KEY_CHAR),AstAtomType.Type.CHAR);
					
				}
				break;
			case KEY_INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				((TypeContext)_localctx).KEY_INT = match(KEY_INT);

						((TypeContext)_localctx).ast = new AstAtomType(location(((TypeContext)_localctx).KEY_INT),AstAtomType.Type.INT);
					
				}
				break;
			case KEY_BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(164);
				((TypeContext)_localctx).KEY_BOOL = match(KEY_BOOL);

						((TypeContext)_localctx).ast = new AstAtomType(location(((TypeContext)_localctx).KEY_BOOL),AstAtomType.Type.BOOL);
					
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(166);
				((TypeContext)_localctx).ID = match(ID);

						((TypeContext)_localctx).ast = new AstNameType(location(((TypeContext)_localctx).ID), ((TypeContext)_localctx).ID.getText());
					
				}
				break;
			case SYM_SQBRACK_L:
				enterOuterAlt(_localctx, 6);
				{
				setState(168);
				((TypeContext)_localctx).SYM_SQBRACK_L = match(SYM_SQBRACK_L);
				setState(169);
				((TypeContext)_localctx).expression = expression(0);
				setState(170);
				match(SYM_SQBRACK_R);
				setState(171);
				((TypeContext)_localctx).type = type();

						((TypeContext)_localctx).ast = new AstArrType(location(((TypeContext)_localctx).SYM_SQBRACK_L,((TypeContext)_localctx).type.ast),((TypeContext)_localctx).type.ast,((TypeContext)_localctx).expression.ast);
					
				}
				break;
			case SYM_CRCMFLX:
				enterOuterAlt(_localctx, 7);
				{
				setState(174);
				((TypeContext)_localctx).SYM_CRCMFLX = match(SYM_CRCMFLX);
				setState(175);
				((TypeContext)_localctx).type = type();

						((TypeContext)_localctx).ast = new AstPtrType(location(((TypeContext)_localctx).SYM_CRCMFLX,((TypeContext)_localctx).type.ast),((TypeContext)_localctx).type.ast);
					
				}
				break;
			case SYM_CRBRACK_L:
				enterOuterAlt(_localctx, 8);
				{
				setState(178);
				((TypeContext)_localctx).SYM_CRBRACK_L = match(SYM_CRBRACK_L);
				setState(179);
				((TypeContext)_localctx).id_0 = match(ID);
				setState(180);
				match(SYM_COLON);
				setState(181);
				((TypeContext)_localctx).tp_0 = type();

						_localctx.nodes.add(new AstCmpDecl(location(((TypeContext)_localctx).id_0,((TypeContext)_localctx).tp_0.ast),((TypeContext)_localctx).id_0.getText(),((TypeContext)_localctx).tp_0.ast));
					
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SYM_COMMA) {
					{
					{
					setState(183);
					match(SYM_COMMA);
					setState(184);
					((TypeContext)_localctx).id_1 = match(ID);
					setState(185);
					match(SYM_COLON);
					setState(186);
					((TypeContext)_localctx).tp_1 = type();

							_localctx.nodes.add(new AstCmpDecl(location(((TypeContext)_localctx).id_1,((TypeContext)_localctx).tp_1.ast),((TypeContext)_localctx).id_1.getText(),((TypeContext)_localctx).tp_1.ast));
						
					}
					}
					setState(193);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(194);
				((TypeContext)_localctx).SYM_CRBRACK_R = match(SYM_CRBRACK_R);

						((TypeContext)_localctx).ast = new AstRecType(location(((TypeContext)_localctx).SYM_CRBRACK_L,((TypeContext)_localctx).SYM_CRBRACK_R),new AstTrees("ComponentDeclarations",_localctx.nodes));
					
				}
				break;
			case SYM_PARENTH_L:
				enterOuterAlt(_localctx, 9);
				{
				setState(197);
				((TypeContext)_localctx).SYM_PARENTH_L = match(SYM_PARENTH_L);
				setState(198);
				((TypeContext)_localctx).type = type();
				setState(199);
				((TypeContext)_localctx).SYM_PARENTH_R = match(SYM_PARENTH_R);

						((TypeContext)_localctx).ast = ((TypeContext)_localctx).type.ast;
						_localctx.ast.relocate(location(((TypeContext)_localctx).SYM_PARENTH_L,((TypeContext)_localctx).SYM_PARENTH_R));
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public Vector<AstExpr> nodes = new Vector<>();
		public boolean isCast = false;
		public boolean isCall = false;
		public ExpressionContext ex_0;
		public Token CONST_VOID;
		public Token CONST_BOOL;
		public Token CONST_INT;
		public Token CONST_PTR;
		public Token CONST_CHAR;
		public Token CONST_STR;
		public Token ID;
		public Token SYM_PARENTH_L;
		public ExpressionContext expression;
		public ExpressionContext ex_1;
		public Token SYM_PARENTH_R;
		public Op_prefixContext op_prefix;
		public TypeContext type;
		public Token KEY_NEW;
		public Token KEY_DEL;
		public Op_multContext op_mult;
		public Op_addContext op_add;
		public Op_relContext op_rel;
		public Op_conjContext op_conj;
		public Op_disjContext op_disj;
		public Token SYM_SQBRACK_R;
		public Token SYM_CRCMFLX;
		public TerminalNode CONST_VOID() { return getToken(PrevParser.CONST_VOID, 0); }
		public TerminalNode CONST_BOOL() { return getToken(PrevParser.CONST_BOOL, 0); }
		public TerminalNode CONST_INT() { return getToken(PrevParser.CONST_INT, 0); }
		public TerminalNode CONST_PTR() { return getToken(PrevParser.CONST_PTR, 0); }
		public TerminalNode CONST_CHAR() { return getToken(PrevParser.CONST_CHAR, 0); }
		public TerminalNode CONST_STR() { return getToken(PrevParser.CONST_STR, 0); }
		public TerminalNode ID() { return getToken(PrevParser.ID, 0); }
		public TerminalNode SYM_PARENTH_L() { return getToken(PrevParser.SYM_PARENTH_L, 0); }
		public TerminalNode SYM_PARENTH_R() { return getToken(PrevParser.SYM_PARENTH_R, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> SYM_COMMA() { return getTokens(PrevParser.SYM_COMMA); }
		public TerminalNode SYM_COMMA(int i) {
			return getToken(PrevParser.SYM_COMMA, i);
		}
		public Op_prefixContext op_prefix() {
			return getRuleContext(Op_prefixContext.class,0);
		}
		public TerminalNode SYM_COLON() { return getToken(PrevParser.SYM_COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode KEY_NEW() { return getToken(PrevParser.KEY_NEW, 0); }
		public TerminalNode KEY_DEL() { return getToken(PrevParser.KEY_DEL, 0); }
		public Op_multContext op_mult() {
			return getRuleContext(Op_multContext.class,0);
		}
		public Op_addContext op_add() {
			return getRuleContext(Op_addContext.class,0);
		}
		public Op_relContext op_rel() {
			return getRuleContext(Op_relContext.class,0);
		}
		public Op_conjContext op_conj() {
			return getRuleContext(Op_conjContext.class,0);
		}
		public Op_disjContext op_disj() {
			return getRuleContext(Op_disjContext.class,0);
		}
		public TerminalNode SYM_SQBRACK_L() { return getToken(PrevParser.SYM_SQBRACK_L, 0); }
		public TerminalNode SYM_SQBRACK_R() { return getToken(PrevParser.SYM_SQBRACK_R, 0); }
		public TerminalNode SYM_CRCMFLX() { return getToken(PrevParser.SYM_CRCMFLX, 0); }
		public TerminalNode SYM_DOT() { return getToken(PrevParser.SYM_DOT, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONST_VOID:
				{
				setState(205);
				((ExpressionContext)_localctx).CONST_VOID = match(CONST_VOID);

								((ExpressionContext)_localctx).ast = new AstAtomExpr(location(((ExpressionContext)_localctx).CONST_VOID),AstAtomExpr.Type.VOID,((ExpressionContext)_localctx).CONST_VOID.getText());
							
				}
				break;
			case CONST_BOOL:
				{
				setState(207);
				((ExpressionContext)_localctx).CONST_BOOL = match(CONST_BOOL);

								((ExpressionContext)_localctx).ast = new AstAtomExpr(location(((ExpressionContext)_localctx).CONST_BOOL),AstAtomExpr.Type.BOOL,((ExpressionContext)_localctx).CONST_BOOL.getText());
							
				}
				break;
			case CONST_INT:
				{
				setState(209);
				((ExpressionContext)_localctx).CONST_INT = match(CONST_INT);

								((ExpressionContext)_localctx).ast = new AstAtomExpr(location(((ExpressionContext)_localctx).CONST_INT),AstAtomExpr.Type.INT,((ExpressionContext)_localctx).CONST_INT.getText());
							
				}
				break;
			case CONST_PTR:
				{
				setState(211);
				((ExpressionContext)_localctx).CONST_PTR = match(CONST_PTR);

								((ExpressionContext)_localctx).ast = new AstAtomExpr(location(((ExpressionContext)_localctx).CONST_PTR),AstAtomExpr.Type.PTR,((ExpressionContext)_localctx).CONST_PTR.getText());
							
				}
				break;
			case CONST_CHAR:
				{
				setState(213);
				((ExpressionContext)_localctx).CONST_CHAR = match(CONST_CHAR);

								((ExpressionContext)_localctx).ast = new AstAtomExpr(location(((ExpressionContext)_localctx).CONST_CHAR),AstAtomExpr.Type.CHAR,((ExpressionContext)_localctx).CONST_CHAR.getText());
							
				}
				break;
			case CONST_STR:
				{
				setState(215);
				((ExpressionContext)_localctx).CONST_STR = match(CONST_STR);

								((ExpressionContext)_localctx).ast = new AstAtomExpr(location(((ExpressionContext)_localctx).CONST_STR),AstAtomExpr.Type.STR,((ExpressionContext)_localctx).CONST_STR.getText());
							
				}
				break;
			case ID:
				{
				setState(217);
				((ExpressionContext)_localctx).ID = match(ID);
				setState(234);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(218);
					((ExpressionContext)_localctx).SYM_PARENTH_L = match(SYM_PARENTH_L);
					setState(230);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((_la) & ~0x3f) == 0 && ((1L << _la) & 809274926301822L) != 0) {
						{
						setState(219);
						((ExpressionContext)_localctx).ex_0 = ((ExpressionContext)_localctx).expression = expression(0);

										_localctx.nodes.add(((ExpressionContext)_localctx).ex_0.ast);
									
						setState(227);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SYM_COMMA) {
							{
							{
							setState(221);
							match(SYM_COMMA);
							setState(222);
							((ExpressionContext)_localctx).ex_1 = ((ExpressionContext)_localctx).expression = expression(0);

											_localctx.nodes.add(((ExpressionContext)_localctx).ex_1.ast);
										
							}
							}
							setState(229);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(232);
					((ExpressionContext)_localctx).SYM_PARENTH_R = match(SYM_PARENTH_R);

									((ExpressionContext)_localctx).isCall = true;
								
					}
					break;
				}

								if (_localctx.isCall) {
									((ExpressionContext)_localctx).ast = new AstCallExpr(location(((ExpressionContext)_localctx).ID,((ExpressionContext)_localctx).SYM_PARENTH_R),((ExpressionContext)_localctx).ID.getText(),new AstTrees("Arguments",_localctx.nodes));
								}
								else {
									((ExpressionContext)_localctx).ast = new AstNameExpr(location(((ExpressionContext)_localctx).ID),((ExpressionContext)_localctx).ID.getText());
								}
							
				}
				break;
			case SYM_EXCLM:
			case SYM_PLUS:
			case SYM_MINUS:
			case SYM_CRCMFLX:
				{
				setState(237);
				((ExpressionContext)_localctx).op_prefix = op_prefix();
				setState(238);
				((ExpressionContext)_localctx).expression = expression(9);

								((ExpressionContext)_localctx).ast = new AstPfxExpr(location(((ExpressionContext)_localctx).op_prefix.tk,((ExpressionContext)_localctx).expression.ast), ((ExpressionContext)_localctx).op_prefix.oper, ((ExpressionContext)_localctx).expression.ast);
							
				}
				break;
			case SYM_PARENTH_L:
				{
				setState(241);
				((ExpressionContext)_localctx).SYM_PARENTH_L = match(SYM_PARENTH_L);
				setState(242);
				((ExpressionContext)_localctx).expression = expression(0);
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SYM_COLON) {
					{
					setState(243);
					match(SYM_COLON);
					setState(244);
					((ExpressionContext)_localctx).type = type();

									((ExpressionContext)_localctx).isCast = true;
								
					}
				}

				setState(249);
				((ExpressionContext)_localctx).SYM_PARENTH_R = match(SYM_PARENTH_R);

								if (_localctx.isCast) {
									((ExpressionContext)_localctx).ast = new AstCastExpr(location(((ExpressionContext)_localctx).SYM_PARENTH_L,((ExpressionContext)_localctx).SYM_PARENTH_R),((ExpressionContext)_localctx).expression.ast,((ExpressionContext)_localctx).type.ast);
								}
								else {
									((ExpressionContext)_localctx).ast = ((ExpressionContext)_localctx).expression.ast;
									_localctx.ast.relocate(location(((ExpressionContext)_localctx).SYM_PARENTH_L,((ExpressionContext)_localctx).SYM_PARENTH_R));
								}
							
				}
				break;
			case KEY_NEW:
				{
				setState(252);
				((ExpressionContext)_localctx).KEY_NEW = match(KEY_NEW);
				setState(253);
				((ExpressionContext)_localctx).SYM_PARENTH_L = match(SYM_PARENTH_L);
				setState(254);
				((ExpressionContext)_localctx).type = type();
				setState(255);
				((ExpressionContext)_localctx).SYM_PARENTH_R = match(SYM_PARENTH_R);

								((ExpressionContext)_localctx).ast = new AstNewExpr(location(((ExpressionContext)_localctx).KEY_NEW,((ExpressionContext)_localctx).SYM_PARENTH_R),((ExpressionContext)_localctx).type.ast);
							
				}
				break;
			case KEY_DEL:
				{
				setState(258);
				((ExpressionContext)_localctx).KEY_DEL = match(KEY_DEL);
				setState(259);
				((ExpressionContext)_localctx).SYM_PARENTH_L = match(SYM_PARENTH_L);
				setState(260);
				((ExpressionContext)_localctx).expression = expression(0);
				setState(261);
				((ExpressionContext)_localctx).SYM_PARENTH_R = match(SYM_PARENTH_R);

								((ExpressionContext)_localctx).ast = new AstDelExpr(location(((ExpressionContext)_localctx).KEY_DEL,((ExpressionContext)_localctx).SYM_PARENTH_R),((ExpressionContext)_localctx).expression.ast);
							
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(306);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(304);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.ex_0 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(266);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(267);
						((ExpressionContext)_localctx).op_mult = op_mult();
						setState(268);
						((ExpressionContext)_localctx).ex_1 = ((ExpressionContext)_localctx).expression = expression(9);

						          				((ExpressionContext)_localctx).ast = new AstBinExpr(location(((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast),((ExpressionContext)_localctx).op_mult.oper,((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast);
						          			
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.ex_0 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(271);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(272);
						((ExpressionContext)_localctx).op_add = op_add();
						setState(273);
						((ExpressionContext)_localctx).ex_1 = ((ExpressionContext)_localctx).expression = expression(8);

						          				((ExpressionContext)_localctx).ast = new AstBinExpr(location(((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast),((ExpressionContext)_localctx).op_add.oper,((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast);			
						          			
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.ex_0 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(276);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(277);
						((ExpressionContext)_localctx).op_rel = op_rel();
						setState(278);
						((ExpressionContext)_localctx).ex_1 = ((ExpressionContext)_localctx).expression = expression(7);

						          				((ExpressionContext)_localctx).ast = new AstBinExpr(location(((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast),((ExpressionContext)_localctx).op_rel.oper,((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast);
						          			
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.ex_0 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(281);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(282);
						((ExpressionContext)_localctx).op_conj = op_conj();
						setState(283);
						((ExpressionContext)_localctx).ex_1 = ((ExpressionContext)_localctx).expression = expression(6);

						          				((ExpressionContext)_localctx).ast = new AstBinExpr(location(((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast),((ExpressionContext)_localctx).op_conj.oper,((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast);			
						          			
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.ex_0 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(286);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(287);
						((ExpressionContext)_localctx).op_disj = op_disj();
						setState(288);
						((ExpressionContext)_localctx).ex_1 = ((ExpressionContext)_localctx).expression = expression(5);

						          				((ExpressionContext)_localctx).ast = new AstBinExpr(location(((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast),((ExpressionContext)_localctx).op_disj.oper,((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ex_1.ast);
						          			
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.ex_0 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(291);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(302);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case SYM_SQBRACK_L:
							{
							setState(292);
							match(SYM_SQBRACK_L);
							setState(293);
							((ExpressionContext)_localctx).ex_1 = ((ExpressionContext)_localctx).expression = expression(0);
							setState(294);
							((ExpressionContext)_localctx).SYM_SQBRACK_R = match(SYM_SQBRACK_R);

							          				((ExpressionContext)_localctx).ast = new AstArrExpr(location(((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).SYM_SQBRACK_R), ((ExpressionContext)_localctx).ex_0.ast, ((ExpressionContext)_localctx).ex_1.ast);
							          			
							}
							break;
						case SYM_CRCMFLX:
							{
							setState(297);
							((ExpressionContext)_localctx).SYM_CRCMFLX = match(SYM_CRCMFLX);

							          				((ExpressionContext)_localctx).ast = new AstSfxExpr(location(((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).SYM_CRCMFLX), AstSfxExpr.Oper.PTR, ((ExpressionContext)_localctx).ex_0.ast);
							          			
							}
							break;
						case SYM_DOT:
							{
							setState(299);
							match(SYM_DOT);
							setState(300);
							((ExpressionContext)_localctx).ID = match(ID);

							          				((ExpressionContext)_localctx).ast = new AstRecExpr(location(((ExpressionContext)_localctx).ex_0.ast,((ExpressionContext)_localctx).ID), ((ExpressionContext)_localctx).ex_0.ast, new AstNameExpr(location(((ExpressionContext)_localctx).ID), ((ExpressionContext)_localctx).ID.getText()));
							          			
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					}
					} 
				}
				setState(308);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public AstStmt ast;
		public Vector<AstStmt> nodes = new Vector<>();
		public boolean isAssignment = false;
		public AstStmt nullableStmt = null;
		public ExpressionContext ex_0;
		public ExpressionContext ex_1;
		public Token KEY_IF;
		public ExpressionContext expression;
		public StatementContext st_0;
		public StatementContext st_1;
		public Token KEY_WHILE;
		public StatementContext statement;
		public Token KEY_LET;
		public DeclarationsContext declarations;
		public Token SYM_CRBRACK_L;
		public Token SYM_CRBRACK_R;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode SYM_EQ() { return getToken(PrevParser.SYM_EQ, 0); }
		public TerminalNode KEY_IF() { return getToken(PrevParser.KEY_IF, 0); }
		public TerminalNode KEY_THEN() { return getToken(PrevParser.KEY_THEN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode KEY_ELSE() { return getToken(PrevParser.KEY_ELSE, 0); }
		public TerminalNode KEY_WHILE() { return getToken(PrevParser.KEY_WHILE, 0); }
		public TerminalNode KEY_DO() { return getToken(PrevParser.KEY_DO, 0); }
		public TerminalNode KEY_LET() { return getToken(PrevParser.KEY_LET, 0); }
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode KEY_IN() { return getToken(PrevParser.KEY_IN, 0); }
		public TerminalNode SYM_CRBRACK_L() { return getToken(PrevParser.SYM_CRBRACK_L, 0); }
		public TerminalNode SYM_CRBRACK_R() { return getToken(PrevParser.SYM_CRBRACK_R, 0); }
		public List<TerminalNode> SYM_SEMICOLON() { return getTokens(PrevParser.SYM_SEMICOLON); }
		public TerminalNode SYM_SEMICOLON(int i) {
			return getToken(PrevParser.SYM_SEMICOLON, i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_statement);
		int _la;
		try {
			setState(357);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONST_VOID:
			case CONST_BOOL:
			case CONST_INT:
			case CONST_PTR:
			case CONST_CHAR:
			case CONST_STR:
			case KEY_DEL:
			case KEY_NEW:
			case SYM_PARENTH_L:
			case SYM_EXCLM:
			case SYM_PLUS:
			case SYM_MINUS:
			case SYM_CRCMFLX:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				((StatementContext)_localctx).ex_0 = expression(0);
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SYM_EQ) {
					{
					setState(310);
					match(SYM_EQ);
					setState(311);
					((StatementContext)_localctx).ex_1 = expression(0);

									((StatementContext)_localctx).isAssignment = true;
								
					}
				}


								if(_localctx.isAssignment) {
									((StatementContext)_localctx).ast = new AstAssignStmt(location(((StatementContext)_localctx).ex_0.ast,((StatementContext)_localctx).ex_1.ast),((StatementContext)_localctx).ex_0.ast,((StatementContext)_localctx).ex_1.ast);
								}
								else {
									((StatementContext)_localctx).ast = new AstExprStmt(location(((StatementContext)_localctx).ex_0.ast),((StatementContext)_localctx).ex_0.ast);
								}
							
				}
				break;
			case KEY_IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(318);
				((StatementContext)_localctx).KEY_IF = match(KEY_IF);
				setState(319);
				((StatementContext)_localctx).expression = expression(0);
				setState(320);
				match(KEY_THEN);
				setState(321);
				((StatementContext)_localctx).st_0 = statement();
				setState(326);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(322);
					match(KEY_ELSE);
					setState(323);
					((StatementContext)_localctx).st_1 = statement();

									((StatementContext)_localctx).nullableStmt = ((StatementContext)_localctx).st_1.ast;
								
					}
					break;
				}

								Location lc=_localctx.nullableStmt!=null?location(((StatementContext)_localctx).KEY_IF,_localctx.nullableStmt):location(((StatementContext)_localctx).KEY_IF,((StatementContext)_localctx).st_0.ast);
								((StatementContext)_localctx).ast = new AstIfStmt(lc, ((StatementContext)_localctx).expression.ast, ((StatementContext)_localctx).st_0.ast, _localctx.nullableStmt);
							
				}
				break;
			case KEY_WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(330);
				((StatementContext)_localctx).KEY_WHILE = match(KEY_WHILE);
				setState(331);
				((StatementContext)_localctx).expression = expression(0);
				setState(332);
				match(KEY_DO);
				setState(333);
				((StatementContext)_localctx).statement = statement();

								((StatementContext)_localctx).ast = new AstWhileStmt(location(((StatementContext)_localctx).KEY_WHILE,((StatementContext)_localctx).statement.ast),((StatementContext)_localctx).expression.ast,((StatementContext)_localctx).statement.ast);
							
				}
				break;
			case KEY_LET:
				enterOuterAlt(_localctx, 4);
				{
				setState(336);
				((StatementContext)_localctx).KEY_LET = match(KEY_LET);
				setState(337);
				((StatementContext)_localctx).declarations = declarations();
				setState(338);
				match(KEY_IN);
				setState(339);
				((StatementContext)_localctx).statement = statement();

								((StatementContext)_localctx).ast = new AstDeclStmt(location(((StatementContext)_localctx).KEY_LET,((StatementContext)_localctx).statement.ast),((StatementContext)_localctx).declarations.ast,((StatementContext)_localctx).statement.ast);
							
				}
				break;
			case SYM_CRBRACK_L:
				enterOuterAlt(_localctx, 5);
				{
				setState(342);
				((StatementContext)_localctx).SYM_CRBRACK_L = match(SYM_CRBRACK_L);
				setState(343);
				((StatementContext)_localctx).st_0 = statement();

								_localctx.nodes.add(((StatementContext)_localctx).st_0.ast);
							
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SYM_SEMICOLON) {
					{
					{
					setState(345);
					match(SYM_SEMICOLON);
					setState(346);
					((StatementContext)_localctx).st_1 = statement();

									_localctx.nodes.add(((StatementContext)_localctx).st_1.ast);
								
					}
					}
					setState(353);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(354);
				((StatementContext)_localctx).SYM_CRBRACK_R = match(SYM_CRBRACK_R);

								((StatementContext)_localctx).ast = new AstStmts(location(((StatementContext)_localctx).SYM_CRBRACK_L,((StatementContext)_localctx).SYM_CRBRACK_R),new AstTrees("Statements",_localctx.nodes));
							
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Grp_op_unaryContext extends ParserRuleContext {
		public TerminalNode SYM_EXCLM() { return getToken(PrevParser.SYM_EXCLM, 0); }
		public TerminalNode SYM_PLUS() { return getToken(PrevParser.SYM_PLUS, 0); }
		public TerminalNode SYM_MINUS() { return getToken(PrevParser.SYM_MINUS, 0); }
		public TerminalNode SYM_CRCMFLX() { return getToken(PrevParser.SYM_CRCMFLX, 0); }
		public Grp_op_unaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grp_op_unary; }
	}

	public final Grp_op_unaryContext grp_op_unary() throws RecognitionException {
		Grp_op_unaryContext _localctx = new Grp_op_unaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_grp_op_unary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 246324964360192L) != 0) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_postfixContext extends ParserRuleContext {
		public TerminalNode SYM_SQBRACK_L() { return getToken(PrevParser.SYM_SQBRACK_L, 0); }
		public TerminalNode SYM_SQBRACK_R() { return getToken(PrevParser.SYM_SQBRACK_R, 0); }
		public TerminalNode SYM_CRCMFLX() { return getToken(PrevParser.SYM_CRCMFLX, 0); }
		public TerminalNode SYM_DOT() { return getToken(PrevParser.SYM_DOT, 0); }
		public Op_postfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_postfix; }
	}

	public final Op_postfixContext op_postfix() throws RecognitionException {
		Op_postfixContext _localctx = new Op_postfixContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_op_postfix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 140738964750336L) != 0) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_prefixContext extends ParserRuleContext {
		public AstPfxExpr.Oper oper;
		public Token tk;
		public Token SYM_EXCLM;
		public Token SYM_PLUS;
		public Token SYM_MINUS;
		public Token SYM_CRCMFLX;
		public TerminalNode SYM_EXCLM() { return getToken(PrevParser.SYM_EXCLM, 0); }
		public TerminalNode SYM_PLUS() { return getToken(PrevParser.SYM_PLUS, 0); }
		public TerminalNode SYM_MINUS() { return getToken(PrevParser.SYM_MINUS, 0); }
		public TerminalNode SYM_CRCMFLX() { return getToken(PrevParser.SYM_CRCMFLX, 0); }
		public Op_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_prefix; }
	}

	public final Op_prefixContext op_prefix() throws RecognitionException {
		Op_prefixContext _localctx = new Op_prefixContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_op_prefix);
		try {
			setState(371);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYM_EXCLM:
				enterOuterAlt(_localctx, 1);
				{
				setState(363);
				((Op_prefixContext)_localctx).SYM_EXCLM = match(SYM_EXCLM);

						((Op_prefixContext)_localctx).oper = AstPfxExpr.Oper.NOT;
						((Op_prefixContext)_localctx).tk = ((Op_prefixContext)_localctx).SYM_EXCLM;
					
				}
				break;
			case SYM_PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(365);
				((Op_prefixContext)_localctx).SYM_PLUS = match(SYM_PLUS);

						((Op_prefixContext)_localctx).oper = AstPfxExpr.Oper.ADD;
						((Op_prefixContext)_localctx).tk = ((Op_prefixContext)_localctx).SYM_PLUS;
					
				}
				break;
			case SYM_MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(367);
				((Op_prefixContext)_localctx).SYM_MINUS = match(SYM_MINUS);

						((Op_prefixContext)_localctx).oper = AstPfxExpr.Oper.SUB;
						((Op_prefixContext)_localctx).tk = ((Op_prefixContext)_localctx).SYM_MINUS;	
					
				}
				break;
			case SYM_CRCMFLX:
				enterOuterAlt(_localctx, 4);
				{
				setState(369);
				((Op_prefixContext)_localctx).SYM_CRCMFLX = match(SYM_CRCMFLX);

						((Op_prefixContext)_localctx).oper = AstPfxExpr.Oper.PTR;
						((Op_prefixContext)_localctx).tk = ((Op_prefixContext)_localctx).SYM_CRCMFLX;	
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_multContext extends ParserRuleContext {
		public AstBinExpr.Oper oper;
		public Token tk;
		public Token SYM_AST;
		public Token SYM_FSLASH;
		public Token SYM_PRCNT;
		public TerminalNode SYM_AST() { return getToken(PrevParser.SYM_AST, 0); }
		public TerminalNode SYM_FSLASH() { return getToken(PrevParser.SYM_FSLASH, 0); }
		public TerminalNode SYM_PRCNT() { return getToken(PrevParser.SYM_PRCNT, 0); }
		public Op_multContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_mult; }
	}

	public final Op_multContext op_mult() throws RecognitionException {
		Op_multContext _localctx = new Op_multContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_op_mult);
		try {
			setState(379);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYM_AST:
				enterOuterAlt(_localctx, 1);
				{
				setState(373);
				((Op_multContext)_localctx).SYM_AST = match(SYM_AST);

					((Op_multContext)_localctx).oper = AstBinExpr.Oper.MUL;
					((Op_multContext)_localctx).tk = ((Op_multContext)_localctx).SYM_AST;	

				}
				break;
			case SYM_FSLASH:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				((Op_multContext)_localctx).SYM_FSLASH = match(SYM_FSLASH);

					((Op_multContext)_localctx).oper = AstBinExpr.Oper.DIV;
					((Op_multContext)_localctx).tk = ((Op_multContext)_localctx).SYM_FSLASH;

				}
				break;
			case SYM_PRCNT:
				enterOuterAlt(_localctx, 3);
				{
				setState(377);
				((Op_multContext)_localctx).SYM_PRCNT = match(SYM_PRCNT);

					((Op_multContext)_localctx).oper = AstBinExpr.Oper.MOD;
					((Op_multContext)_localctx).tk = ((Op_multContext)_localctx).SYM_PRCNT;

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_addContext extends ParserRuleContext {
		public AstBinExpr.Oper oper;
		public Token tk;
		public Token SYM_PLUS;
		public Token SYM_MINUS;
		public TerminalNode SYM_PLUS() { return getToken(PrevParser.SYM_PLUS, 0); }
		public TerminalNode SYM_MINUS() { return getToken(PrevParser.SYM_MINUS, 0); }
		public Op_addContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_add; }
	}

	public final Op_addContext op_add() throws RecognitionException {
		Op_addContext _localctx = new Op_addContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_op_add);
		try {
			setState(385);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYM_PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(381);
				((Op_addContext)_localctx).SYM_PLUS = match(SYM_PLUS);

					((Op_addContext)_localctx).oper = AstBinExpr.Oper.ADD;
					((Op_addContext)_localctx).tk = ((Op_addContext)_localctx).SYM_PLUS;

				}
				break;
			case SYM_MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(383);
				((Op_addContext)_localctx).SYM_MINUS = match(SYM_MINUS);

					((Op_addContext)_localctx).oper = AstBinExpr.Oper.SUB;
					((Op_addContext)_localctx).tk = ((Op_addContext)_localctx).SYM_MINUS;

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_relContext extends ParserRuleContext {
		public AstBinExpr.Oper oper;
		public Token tk;
		public Token SYM_DBLEQ;
		public Token SYM_NEQ;
		public Token SYM_LT;
		public Token SYM_GT;
		public Token SYM_LTE;
		public Token SYM_GTE;
		public TerminalNode SYM_DBLEQ() { return getToken(PrevParser.SYM_DBLEQ, 0); }
		public TerminalNode SYM_NEQ() { return getToken(PrevParser.SYM_NEQ, 0); }
		public TerminalNode SYM_LT() { return getToken(PrevParser.SYM_LT, 0); }
		public TerminalNode SYM_GT() { return getToken(PrevParser.SYM_GT, 0); }
		public TerminalNode SYM_LTE() { return getToken(PrevParser.SYM_LTE, 0); }
		public TerminalNode SYM_GTE() { return getToken(PrevParser.SYM_GTE, 0); }
		public Op_relContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_rel; }
	}

	public final Op_relContext op_rel() throws RecognitionException {
		Op_relContext _localctx = new Op_relContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_op_rel);
		try {
			setState(399);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYM_DBLEQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				((Op_relContext)_localctx).SYM_DBLEQ = match(SYM_DBLEQ);

					((Op_relContext)_localctx).oper = AstBinExpr.Oper.EQU;
					((Op_relContext)_localctx).tk = ((Op_relContext)_localctx).SYM_DBLEQ;

				}
				break;
			case SYM_NEQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(389);
				((Op_relContext)_localctx).SYM_NEQ = match(SYM_NEQ);

					((Op_relContext)_localctx).oper = AstBinExpr.Oper.NEQ;
					((Op_relContext)_localctx).tk = ((Op_relContext)_localctx).SYM_NEQ;

				}
				break;
			case SYM_LT:
				enterOuterAlt(_localctx, 3);
				{
				setState(391);
				((Op_relContext)_localctx).SYM_LT = match(SYM_LT);

					((Op_relContext)_localctx).oper = AstBinExpr.Oper.LTH;
					((Op_relContext)_localctx).tk = ((Op_relContext)_localctx).SYM_LT;

				}
				break;
			case SYM_GT:
				enterOuterAlt(_localctx, 4);
				{
				setState(393);
				((Op_relContext)_localctx).SYM_GT = match(SYM_GT);

					((Op_relContext)_localctx).oper = AstBinExpr.Oper.GTH;
					((Op_relContext)_localctx).tk = ((Op_relContext)_localctx).SYM_GT;

				}
				break;
			case SYM_LTE:
				enterOuterAlt(_localctx, 5);
				{
				setState(395);
				((Op_relContext)_localctx).SYM_LTE = match(SYM_LTE);

					((Op_relContext)_localctx).oper = AstBinExpr.Oper.LEQ;
					((Op_relContext)_localctx).tk = ((Op_relContext)_localctx).SYM_LTE;

				}
				break;
			case SYM_GTE:
				enterOuterAlt(_localctx, 6);
				{
				setState(397);
				((Op_relContext)_localctx).SYM_GTE = match(SYM_GTE);

					((Op_relContext)_localctx).oper = AstBinExpr.Oper.GEQ;
					((Op_relContext)_localctx).tk = ((Op_relContext)_localctx).SYM_GTE;

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_conjContext extends ParserRuleContext {
		public AstBinExpr.Oper oper;
		public Token tk;
		public Token SYM_AMPERS;
		public TerminalNode SYM_AMPERS() { return getToken(PrevParser.SYM_AMPERS, 0); }
		public Op_conjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_conj; }
	}

	public final Op_conjContext op_conj() throws RecognitionException {
		Op_conjContext _localctx = new Op_conjContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_op_conj);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			((Op_conjContext)_localctx).SYM_AMPERS = match(SYM_AMPERS);

				((Op_conjContext)_localctx).oper = AstBinExpr.Oper.AND;
				((Op_conjContext)_localctx).tk = ((Op_conjContext)_localctx).SYM_AMPERS;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op_disjContext extends ParserRuleContext {
		public AstBinExpr.Oper oper;
		public Token tk;
		public Token SYM_VPIPE;
		public TerminalNode SYM_VPIPE() { return getToken(PrevParser.SYM_VPIPE, 0); }
		public Op_disjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_disj; }
	}

	public final Op_disjContext op_disj() throws RecognitionException {
		Op_disjContext _localctx = new Op_disjContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_op_disj);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			((Op_disjContext)_localctx).SYM_VPIPE = match(SYM_VPIPE);

				((Op_disjContext)_localctx).oper = AstBinExpr.Oper.OR;
				((Op_disjContext)_localctx).tk = ((Op_disjContext)_localctx).SYM_VPIPE;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u0198\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0004\u0001.\b\u0001\u000b\u0001\f\u0001/\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u0002?\b\u0002\n\u0002\f\u0002B\t\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0005\u0003T\b\u0003\n\u0003\f\u0003W\t\u0003\u0003"+
		"\u0003Y\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003b\b\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003r\b\u0003\n\u0003\f\u0003u\t\u0003\u0003\u0003w\b\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003\u0080\b\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"\u0084\b\u0003\n\u0003\f\u0003\u0087\t\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u0004\u0097\b\u0004\n\u0004\f\u0004\u009a\t\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005\u00be\b\u0005\n\u0005\f\u0005\u00c1"+
		"\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00cb\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006\u00e2\b\u0006\n\u0006\f\u0006\u00e5\t\u0006"+
		"\u0003\u0006\u00e7\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00eb\b"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u00f8\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0109"+
		"\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u012f\b\u0006\u0005\u0006\u0131\b\u0006\n\u0006\f\u0006"+
		"\u0134\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u013b\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u0147\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007"+
		"\u015e\b\u0007\n\u0007\f\u0007\u0161\t\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u0166\b\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0174"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u017c\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u0182\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0190\b\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0000"+
		"\u0001\f\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e\u0000\u0002\u0002\u0000##-/\u0003\u0000\u001b"+
		"\u001c\u001e\u001e//\u01c4\u0000 \u0001\u0000\u0000\u0000\u0002-\u0001"+
		"\u0000\u0000\u0000\u00043\u0001\u0000\u0000\u0000\u0006F\u0001\u0000\u0000"+
		"\u0000\b\u008b\u0001\u0000\u0000\u0000\n\u00ca\u0001\u0000\u0000\u0000"+
		"\f\u0108\u0001\u0000\u0000\u0000\u000e\u0165\u0001\u0000\u0000\u0000\u0010"+
		"\u0167\u0001\u0000\u0000\u0000\u0012\u0169\u0001\u0000\u0000\u0000\u0014"+
		"\u0173\u0001\u0000\u0000\u0000\u0016\u017b\u0001\u0000\u0000\u0000\u0018"+
		"\u0181\u0001\u0000\u0000\u0000\u001a\u018f\u0001\u0000\u0000\u0000\u001c"+
		"\u0191\u0001\u0000\u0000\u0000\u001e\u0194\u0001\u0000\u0000\u0000 !\u0003"+
		"\u0002\u0001\u0000!\"\u0005\u0000\u0000\u0001\"#\u0006\u0000\uffff\uffff"+
		"\u0000#\u0001\u0001\u0000\u0000\u0000$%\u0003\u0004\u0002\u0000%&\u0006"+
		"\u0001\uffff\uffff\u0000&.\u0001\u0000\u0000\u0000\'(\u0003\u0006\u0003"+
		"\u0000()\u0006\u0001\uffff\uffff\u0000).\u0001\u0000\u0000\u0000*+\u0003"+
		"\b\u0004\u0000+,\u0006\u0001\uffff\uffff\u0000,.\u0001\u0000\u0000\u0000"+
		"-$\u0001\u0000\u0000\u0000-\'\u0001\u0000\u0000\u0000-*\u0001\u0000\u0000"+
		"\u0000./\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000"+
		"\u0000\u000001\u0001\u0000\u0000\u000012\u0006\u0001\uffff\uffff\u0000"+
		"2\u0003\u0001\u0000\u0000\u000034\u0005\u0013\u0000\u000045\u00051\u0000"+
		"\u000056\u00050\u0000\u000067\u0003\n\u0005\u00007@\u0006\u0002\uffff"+
		"\uffff\u000089\u0005\u001d\u0000\u00009:\u00051\u0000\u0000:;\u00050\u0000"+
		"\u0000;<\u0003\n\u0005\u0000<=\u0006\u0002\uffff\uffff\u0000=?\u0001\u0000"+
		"\u0000\u0000>8\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000@>\u0001"+
		"\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AC\u0001\u0000\u0000\u0000"+
		"B@\u0001\u0000\u0000\u0000CD\u0005 \u0000\u0000DE\u0006\u0002\uffff\uffff"+
		"\u0000E\u0005\u0001\u0000\u0000\u0000FG\u0005\f\u0000\u0000GH\u00051\u0000"+
		"\u0000HX\u0005\u0017\u0000\u0000IJ\u00051\u0000\u0000JK\u0005\u001f\u0000"+
		"\u0000KL\u0003\n\u0005\u0000LU\u0006\u0003\uffff\uffff\u0000MN\u0005\u001d"+
		"\u0000\u0000NO\u00051\u0000\u0000OP\u0005\u001f\u0000\u0000PQ\u0003\n"+
		"\u0005\u0000QR\u0006\u0003\uffff\uffff\u0000RT\u0001\u0000\u0000\u0000"+
		"SM\u0001\u0000\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001\u0000\u0000"+
		"\u0000UV\u0001\u0000\u0000\u0000VY\u0001\u0000\u0000\u0000WU\u0001\u0000"+
		"\u0000\u0000XI\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001"+
		"\u0000\u0000\u0000Z[\u0005\u0018\u0000\u0000[\\\u0005\u001f\u0000\u0000"+
		"\\a\u0003\n\u0005\u0000]^\u00050\u0000\u0000^_\u0003\u000e\u0007\u0000"+
		"_`\u0006\u0003\uffff\uffff\u0000`b\u0001\u0000\u0000\u0000a]\u0001\u0000"+
		"\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000c\u0085"+
		"\u0006\u0003\uffff\uffff\u0000de\u0005\u001d\u0000\u0000ef\u00051\u0000"+
		"\u0000fv\u0005\u0017\u0000\u0000gh\u00051\u0000\u0000hi\u0005\u001f\u0000"+
		"\u0000ij\u0003\n\u0005\u0000js\u0006\u0003\uffff\uffff\u0000kl\u0005\u001d"+
		"\u0000\u0000lm\u00051\u0000\u0000mn\u0005\u001f\u0000\u0000no\u0003\n"+
		"\u0005\u0000op\u0006\u0003\uffff\uffff\u0000pr\u0001\u0000\u0000\u0000"+
		"qk\u0001\u0000\u0000\u0000ru\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000"+
		"\u0000st\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000us\u0001\u0000"+
		"\u0000\u0000vg\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000xy\u0005\u0018\u0000\u0000yz\u0005\u001f\u0000\u0000"+
		"z\u007f\u0003\n\u0005\u0000{|\u00050\u0000\u0000|}\u0003\u000e\u0007\u0000"+
		"}~\u0006\u0003\uffff\uffff\u0000~\u0080\u0001\u0000\u0000\u0000\u007f"+
		"{\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081"+
		"\u0001\u0000\u0000\u0000\u0081\u0082\u0006\u0003\uffff\uffff\u0000\u0082"+
		"\u0084\u0001\u0000\u0000\u0000\u0083d\u0001\u0000\u0000\u0000\u0084\u0087"+
		"\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086"+
		"\u0001\u0000\u0000\u0000\u0086\u0088\u0001\u0000\u0000\u0000\u0087\u0085"+
		"\u0001\u0000\u0000\u0000\u0088\u0089\u0005 \u0000\u0000\u0089\u008a\u0006"+
		"\u0003\uffff\uffff\u0000\u008a\u0007\u0001\u0000\u0000\u0000\u008b\u008c"+
		"\u0005\u0014\u0000\u0000\u008c\u008d\u00051\u0000\u0000\u008d\u008e\u0005"+
		"\u001f\u0000\u0000\u008e\u008f\u0003\n\u0005\u0000\u008f\u0098\u0006\u0004"+
		"\uffff\uffff\u0000\u0090\u0091\u0005\u001d\u0000\u0000\u0091\u0092\u0005"+
		"1\u0000\u0000\u0092\u0093\u0005\u001f\u0000\u0000\u0093\u0094\u0003\n"+
		"\u0005\u0000\u0094\u0095\u0006\u0004\uffff\uffff\u0000\u0095\u0097\u0001"+
		"\u0000\u0000\u0000\u0096\u0090\u0001\u0000\u0000\u0000\u0097\u009a\u0001"+
		"\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001"+
		"\u0000\u0000\u0000\u0099\u009b\u0001\u0000\u0000\u0000\u009a\u0098\u0001"+
		"\u0000\u0000\u0000\u009b\u009c\u0005 \u0000\u0000\u009c\u009d\u0006\u0004"+
		"\uffff\uffff\u0000\u009d\t\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u0015"+
		"\u0000\u0000\u009f\u00cb\u0006\u0005\uffff\uffff\u0000\u00a0\u00a1\u0005"+
		"\b\u0000\u0000\u00a1\u00cb\u0006\u0005\uffff\uffff\u0000\u00a2\u00a3\u0005"+
		"\u000f\u0000\u0000\u00a3\u00cb\u0006\u0005\uffff\uffff\u0000\u00a4\u00a5"+
		"\u0005\u0007\u0000\u0000\u00a5\u00cb\u0006\u0005\uffff\uffff\u0000\u00a6"+
		"\u00a7\u00051\u0000\u0000\u00a7\u00cb\u0006\u0005\uffff\uffff\u0000\u00a8"+
		"\u00a9\u0005\u001b\u0000\u0000\u00a9\u00aa\u0003\f\u0006\u0000\u00aa\u00ab"+
		"\u0005\u001c\u0000\u0000\u00ab\u00ac\u0003\n\u0005\u0000\u00ac\u00ad\u0006"+
		"\u0005\uffff\uffff\u0000\u00ad\u00cb\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0005/\u0000\u0000\u00af\u00b0\u0003\n\u0005\u0000\u00b0\u00b1\u0006"+
		"\u0005\uffff\uffff\u0000\u00b1\u00cb\u0001\u0000\u0000\u0000\u00b2\u00b3"+
		"\u0005\u0019\u0000\u0000\u00b3\u00b4\u00051\u0000\u0000\u00b4\u00b5\u0005"+
		"\u001f\u0000\u0000\u00b5\u00b6\u0003\n\u0005\u0000\u00b6\u00bf\u0006\u0005"+
		"\uffff\uffff\u0000\u00b7\u00b8\u0005\u001d\u0000\u0000\u00b8\u00b9\u0005"+
		"1\u0000\u0000\u00b9\u00ba\u0005\u001f\u0000\u0000\u00ba\u00bb\u0003\n"+
		"\u0005\u0000\u00bb\u00bc\u0006\u0005\uffff\uffff\u0000\u00bc\u00be\u0001"+
		"\u0000\u0000\u0000\u00bd\u00b7\u0001\u0000\u0000\u0000\u00be\u00c1\u0001"+
		"\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c2\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c3\u0005\u001a\u0000\u0000\u00c3\u00c4\u0006"+
		"\u0005\uffff\uffff\u0000\u00c4\u00cb\u0001\u0000\u0000\u0000\u00c5\u00c6"+
		"\u0005\u0017\u0000\u0000\u00c6\u00c7\u0003\n\u0005\u0000\u00c7\u00c8\u0005"+
		"\u0018\u0000\u0000\u00c8\u00c9\u0006\u0005\uffff\uffff\u0000\u00c9\u00cb"+
		"\u0001\u0000\u0000\u0000\u00ca\u009e\u0001\u0000\u0000\u0000\u00ca\u00a0"+
		"\u0001\u0000\u0000\u0000\u00ca\u00a2\u0001\u0000\u0000\u0000\u00ca\u00a4"+
		"\u0001\u0000\u0000\u0000\u00ca\u00a6\u0001\u0000\u0000\u0000\u00ca\u00a8"+
		"\u0001\u0000\u0000\u0000\u00ca\u00ae\u0001\u0000\u0000\u0000\u00ca\u00b2"+
		"\u0001\u0000\u0000\u0000\u00ca\u00c5\u0001\u0000\u0000\u0000\u00cb\u000b"+
		"\u0001\u0000\u0000\u0000\u00cc\u00cd\u0006\u0006\uffff\uffff\u0000\u00cd"+
		"\u00ce\u0005\u0001\u0000\u0000\u00ce\u0109\u0006\u0006\uffff\uffff\u0000"+
		"\u00cf\u00d0\u0005\u0002\u0000\u0000\u00d0\u0109\u0006\u0006\uffff\uffff"+
		"\u0000\u00d1\u00d2\u0005\u0003\u0000\u0000\u00d2\u0109\u0006\u0006\uffff"+
		"\uffff\u0000\u00d3\u00d4\u0005\u0004\u0000\u0000\u00d4\u0109\u0006\u0006"+
		"\uffff\uffff\u0000\u00d5\u00d6\u0005\u0005\u0000\u0000\u00d6\u0109\u0006"+
		"\u0006\uffff\uffff\u0000\u00d7\u00d8\u0005\u0006\u0000\u0000\u00d8\u0109"+
		"\u0006\u0006\uffff\uffff\u0000\u00d9\u00ea\u00051\u0000\u0000\u00da\u00e6"+
		"\u0005\u0017\u0000\u0000\u00db\u00dc\u0003\f\u0006\u0000\u00dc\u00e3\u0006"+
		"\u0006\uffff\uffff\u0000\u00dd\u00de\u0005\u001d\u0000\u0000\u00de\u00df"+
		"\u0003\f\u0006\u0000\u00df\u00e0\u0006\u0006\uffff\uffff\u0000\u00e0\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e1\u00dd\u0001\u0000\u0000\u0000\u00e2\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e7\u0001\u0000\u0000\u0000\u00e5\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e6\u00db\u0001\u0000\u0000\u0000\u00e6\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00e9"+
		"\u0005\u0018\u0000\u0000\u00e9\u00eb\u0006\u0006\uffff\uffff\u0000\u00ea"+
		"\u00da\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb"+
		"\u00ec\u0001\u0000\u0000\u0000\u00ec\u0109\u0006\u0006\uffff\uffff\u0000"+
		"\u00ed\u00ee\u0003\u0014\n\u0000\u00ee\u00ef\u0003\f\u0006\t\u00ef\u00f0"+
		"\u0006\u0006\uffff\uffff\u0000\u00f0\u0109\u0001\u0000\u0000\u0000\u00f1"+
		"\u00f2\u0005\u0017\u0000\u0000\u00f2\u00f7\u0003\f\u0006\u0000\u00f3\u00f4"+
		"\u0005\u001f\u0000\u0000\u00f4\u00f5\u0003\n\u0005\u0000\u00f5\u00f6\u0006"+
		"\u0006\uffff\uffff\u0000\u00f6\u00f8\u0001\u0000\u0000\u0000\u00f7\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u00f9"+
		"\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\u0018\u0000\u0000\u00fa\u00fb"+
		"\u0006\u0006\uffff\uffff\u0000\u00fb\u0109\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fd\u0005\u0011\u0000\u0000\u00fd\u00fe\u0005\u0017\u0000\u0000\u00fe"+
		"\u00ff\u0003\n\u0005\u0000\u00ff\u0100\u0005\u0018\u0000\u0000\u0100\u0101"+
		"\u0006\u0006\uffff\uffff\u0000\u0101\u0109\u0001\u0000\u0000\u0000\u0102"+
		"\u0103\u0005\t\u0000\u0000\u0103\u0104\u0005\u0017\u0000\u0000\u0104\u0105"+
		"\u0003\f\u0006\u0000\u0105\u0106\u0005\u0018\u0000\u0000\u0106\u0107\u0006"+
		"\u0006\uffff\uffff\u0000\u0107\u0109\u0001\u0000\u0000\u0000\u0108\u00cc"+
		"\u0001\u0000\u0000\u0000\u0108\u00cf\u0001\u0000\u0000\u0000\u0108\u00d1"+
		"\u0001\u0000\u0000\u0000\u0108\u00d3\u0001\u0000\u0000\u0000\u0108\u00d5"+
		"\u0001\u0000\u0000\u0000\u0108\u00d7\u0001\u0000\u0000\u0000\u0108\u00d9"+
		"\u0001\u0000\u0000\u0000\u0108\u00ed\u0001\u0000\u0000\u0000\u0108\u00f1"+
		"\u0001\u0000\u0000\u0000\u0108\u00fc\u0001\u0000\u0000\u0000\u0108\u0102"+
		"\u0001\u0000\u0000\u0000\u0109\u0132\u0001\u0000\u0000\u0000\u010a\u010b"+
		"\n\b\u0000\u0000\u010b\u010c\u0003\u0016\u000b\u0000\u010c\u010d\u0003"+
		"\f\u0006\t\u010d\u010e\u0006\u0006\uffff\uffff\u0000\u010e\u0131\u0001"+
		"\u0000\u0000\u0000\u010f\u0110\n\u0007\u0000\u0000\u0110\u0111\u0003\u0018"+
		"\f\u0000\u0111\u0112\u0003\f\u0006\b\u0112\u0113\u0006\u0006\uffff\uffff"+
		"\u0000\u0113\u0131\u0001\u0000\u0000\u0000\u0114\u0115\n\u0006\u0000\u0000"+
		"\u0115\u0116\u0003\u001a\r\u0000\u0116\u0117\u0003\f\u0006\u0007\u0117"+
		"\u0118\u0006\u0006\uffff\uffff\u0000\u0118\u0131\u0001\u0000\u0000\u0000"+
		"\u0119\u011a\n\u0005\u0000\u0000\u011a\u011b\u0003\u001c\u000e\u0000\u011b"+
		"\u011c\u0003\f\u0006\u0006\u011c\u011d\u0006\u0006\uffff\uffff\u0000\u011d"+
		"\u0131\u0001\u0000\u0000\u0000\u011e\u011f\n\u0004\u0000\u0000\u011f\u0120"+
		"\u0003\u001e\u000f\u0000\u0120\u0121\u0003\f\u0006\u0005\u0121\u0122\u0006"+
		"\u0006\uffff\uffff\u0000\u0122\u0131\u0001\u0000\u0000\u0000\u0123\u012e"+
		"\n\n\u0000\u0000\u0124\u0125\u0005\u001b\u0000\u0000\u0125\u0126\u0003"+
		"\f\u0006\u0000\u0126\u0127\u0005\u001c\u0000\u0000\u0127\u0128\u0006\u0006"+
		"\uffff\uffff\u0000\u0128\u012f\u0001\u0000\u0000\u0000\u0129\u012a\u0005"+
		"/\u0000\u0000\u012a\u012f\u0006\u0006\uffff\uffff\u0000\u012b\u012c\u0005"+
		"\u001e\u0000\u0000\u012c\u012d\u00051\u0000\u0000\u012d\u012f\u0006\u0006"+
		"\uffff\uffff\u0000\u012e\u0124\u0001\u0000\u0000\u0000\u012e\u0129\u0001"+
		"\u0000\u0000\u0000\u012e\u012b\u0001\u0000\u0000\u0000\u012f\u0131\u0001"+
		"\u0000\u0000\u0000\u0130\u010a\u0001\u0000\u0000\u0000\u0130\u010f\u0001"+
		"\u0000\u0000\u0000\u0130\u0114\u0001\u0000\u0000\u0000\u0130\u0119\u0001"+
		"\u0000\u0000\u0000\u0130\u011e\u0001\u0000\u0000\u0000\u0130\u0123\u0001"+
		"\u0000\u0000\u0000\u0131\u0134\u0001\u0000\u0000\u0000\u0132\u0130\u0001"+
		"\u0000\u0000\u0000\u0132\u0133\u0001\u0000\u0000\u0000\u0133\r\u0001\u0000"+
		"\u0000\u0000\u0134\u0132\u0001\u0000\u0000\u0000\u0135\u013a\u0003\f\u0006"+
		"\u0000\u0136\u0137\u00050\u0000\u0000\u0137\u0138\u0003\f\u0006\u0000"+
		"\u0138\u0139\u0006\u0007\uffff\uffff\u0000\u0139\u013b\u0001\u0000\u0000"+
		"\u0000\u013a\u0136\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000"+
		"\u0000\u013b\u013c\u0001\u0000\u0000\u0000\u013c\u013d\u0006\u0007\uffff"+
		"\uffff\u0000\u013d\u0166\u0001\u0000\u0000\u0000\u013e\u013f\u0005\r\u0000"+
		"\u0000\u013f\u0140\u0003\f\u0006\u0000\u0140\u0141\u0005\u0012\u0000\u0000"+
		"\u0141\u0146\u0003\u000e\u0007\u0000\u0142\u0143\u0005\u000b\u0000\u0000"+
		"\u0143\u0144\u0003\u000e\u0007\u0000\u0144\u0145\u0006\u0007\uffff\uffff"+
		"\u0000\u0145\u0147\u0001\u0000\u0000\u0000\u0146\u0142\u0001\u0000\u0000"+
		"\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000\u0000"+
		"\u0000\u0148\u0149\u0006\u0007\uffff\uffff\u0000\u0149\u0166\u0001\u0000"+
		"\u0000\u0000\u014a\u014b\u0005\u0016\u0000\u0000\u014b\u014c\u0003\f\u0006"+
		"\u0000\u014c\u014d\u0005\n\u0000\u0000\u014d\u014e\u0003\u000e\u0007\u0000"+
		"\u014e\u014f\u0006\u0007\uffff\uffff\u0000\u014f\u0166\u0001\u0000\u0000"+
		"\u0000\u0150\u0151\u0005\u0010\u0000\u0000\u0151\u0152\u0003\u0002\u0001"+
		"\u0000\u0152\u0153\u0005\u000e\u0000\u0000\u0153\u0154\u0003\u000e\u0007"+
		"\u0000\u0154\u0155\u0006\u0007\uffff\uffff\u0000\u0155\u0166\u0001\u0000"+
		"\u0000\u0000\u0156\u0157\u0005\u0019\u0000\u0000\u0157\u0158\u0003\u000e"+
		"\u0007\u0000\u0158\u015f\u0006\u0007\uffff\uffff\u0000\u0159\u015a\u0005"+
		" \u0000\u0000\u015a\u015b\u0003\u000e\u0007\u0000\u015b\u015c\u0006\u0007"+
		"\uffff\uffff\u0000\u015c\u015e\u0001\u0000\u0000\u0000\u015d\u0159\u0001"+
		"\u0000\u0000\u0000\u015e\u0161\u0001\u0000\u0000\u0000\u015f\u015d\u0001"+
		"\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u0160\u0162\u0001"+
		"\u0000\u0000\u0000\u0161\u015f\u0001\u0000\u0000\u0000\u0162\u0163\u0005"+
		"\u001a\u0000\u0000\u0163\u0164\u0006\u0007\uffff\uffff\u0000\u0164\u0166"+
		"\u0001\u0000\u0000\u0000\u0165\u0135\u0001\u0000\u0000\u0000\u0165\u013e"+
		"\u0001\u0000\u0000\u0000\u0165\u014a\u0001\u0000\u0000\u0000\u0165\u0150"+
		"\u0001\u0000\u0000\u0000\u0165\u0156\u0001\u0000\u0000\u0000\u0166\u000f"+
		"\u0001\u0000\u0000\u0000\u0167\u0168\u0007\u0000\u0000\u0000\u0168\u0011"+
		"\u0001\u0000\u0000\u0000\u0169\u016a\u0007\u0001\u0000\u0000\u016a\u0013"+
		"\u0001\u0000\u0000\u0000\u016b\u016c\u0005#\u0000\u0000\u016c\u0174\u0006"+
		"\n\uffff\uffff\u0000\u016d\u016e\u0005-\u0000\u0000\u016e\u0174\u0006"+
		"\n\uffff\uffff\u0000\u016f\u0170\u0005.\u0000\u0000\u0170\u0174\u0006"+
		"\n\uffff\uffff\u0000\u0171\u0172\u0005/\u0000\u0000\u0172\u0174\u0006"+
		"\n\uffff\uffff\u0000\u0173\u016b\u0001\u0000\u0000\u0000\u0173\u016d\u0001"+
		"\u0000\u0000\u0000\u0173\u016f\u0001\u0000\u0000\u0000\u0173\u0171\u0001"+
		"\u0000\u0000\u0000\u0174\u0015\u0001\u0000\u0000\u0000\u0175\u0176\u0005"+
		"*\u0000\u0000\u0176\u017c\u0006\u000b\uffff\uffff\u0000\u0177\u0178\u0005"+
		"+\u0000\u0000\u0178\u017c\u0006\u000b\uffff\uffff\u0000\u0179\u017a\u0005"+
		",\u0000\u0000\u017a\u017c\u0006\u000b\uffff\uffff\u0000\u017b\u0175\u0001"+
		"\u0000\u0000\u0000\u017b\u0177\u0001\u0000\u0000\u0000\u017b\u0179\u0001"+
		"\u0000\u0000\u0000\u017c\u0017\u0001\u0000\u0000\u0000\u017d\u017e\u0005"+
		"-\u0000\u0000\u017e\u0182\u0006\f\uffff\uffff\u0000\u017f\u0180\u0005"+
		".\u0000\u0000\u0180\u0182\u0006\f\uffff\uffff\u0000\u0181\u017d\u0001"+
		"\u0000\u0000\u0000\u0181\u017f\u0001\u0000\u0000\u0000\u0182\u0019\u0001"+
		"\u0000\u0000\u0000\u0183\u0184\u0005$\u0000\u0000\u0184\u0190\u0006\r"+
		"\uffff\uffff\u0000\u0185\u0186\u0005%\u0000\u0000\u0186\u0190\u0006\r"+
		"\uffff\uffff\u0000\u0187\u0188\u0005&\u0000\u0000\u0188\u0190\u0006\r"+
		"\uffff\uffff\u0000\u0189\u018a\u0005\'\u0000\u0000\u018a\u0190\u0006\r"+
		"\uffff\uffff\u0000\u018b\u018c\u0005(\u0000\u0000\u018c\u0190\u0006\r"+
		"\uffff\uffff\u0000\u018d\u018e\u0005)\u0000\u0000\u018e\u0190\u0006\r"+
		"\uffff\uffff\u0000\u018f\u0183\u0001\u0000\u0000\u0000\u018f\u0185\u0001"+
		"\u0000\u0000\u0000\u018f\u0187\u0001\u0000\u0000\u0000\u018f\u0189\u0001"+
		"\u0000\u0000\u0000\u018f\u018b\u0001\u0000\u0000\u0000\u018f\u018d\u0001"+
		"\u0000\u0000\u0000\u0190\u001b\u0001\u0000\u0000\u0000\u0191\u0192\u0005"+
		"!\u0000\u0000\u0192\u0193\u0006\u000e\uffff\uffff\u0000\u0193\u001d\u0001"+
		"\u0000\u0000\u0000\u0194\u0195\u0005\"\u0000\u0000\u0195\u0196\u0006\u000f"+
		"\uffff\uffff\u0000\u0196\u001f\u0001\u0000\u0000\u0000\u001d-/@UXasv\u007f"+
		"\u0085\u0098\u00bf\u00ca\u00e3\u00e6\u00ea\u00f7\u0108\u012e\u0130\u0132"+
		"\u013a\u0146\u015f\u0165\u0173\u017b\u0181\u018f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}