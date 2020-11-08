// Generated from D:/UPB/CPL/Idea/Parser-demo\CPLangParser.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CPLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, THEN=2, ELSE=3, FI=4, INT=5, ID=6, REAL=7, STRING=8, BLOCK_COMMENT=9, 
		WS=10;
	public static final int
		RULE_expr = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'then'", "'else'", "'fi'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "THEN", "ELSE", "FI", "INT", "ID", "REAL", "STRING", "BLOCK_COMMENT", 
			"WS"
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
	public String getGrammarFileName() { return "CPLangParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CPLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdContext extends ExprContext {
		public TerminalNode ID() { return getToken(CPLangParser.ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPLangParserListener ) ((CPLangParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPLangParserListener ) ((CPLangParserListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPLangParserVisitor ) return ((CPLangParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends ExprContext {
		public ExprContext cond;
		public ExprContext thenBranch;
		public ExprContext elseBranch;
		public TerminalNode IF() { return getToken(CPLangParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CPLangParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CPLangParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CPLangParser.FI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPLangParserListener ) ((CPLangParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPLangParserListener ) ((CPLangParserListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPLangParserVisitor ) return ((CPLangParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(CPLangParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPLangParserListener ) ((CPLangParserListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPLangParserListener ) ((CPLangParserListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPLangParserVisitor ) return ((CPLangParserVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expr);
		try {
			setState(12);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(2);
				match(IF);
				setState(3);
				((IfContext)_localctx).cond = expr();
				setState(4);
				match(THEN);
				setState(5);
				((IfContext)_localctx).thenBranch = expr();
				setState(6);
				match(ELSE);
				setState(7);
				((IfContext)_localctx).elseBranch = expr();
				setState(8);
				match(FI);
				}
				break;
			case ID:
				_localctx = new IdContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(10);
				match(ID);
				}
				break;
			case INT:
				_localctx = new IntContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(11);
				match(INT);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f\21\4\2\t\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\17\n\2\3\2\2\2\3\2\2\2\2\21\2"+
		"\16\3\2\2\2\4\5\7\3\2\2\5\6\5\2\2\2\6\7\7\4\2\2\7\b\5\2\2\2\b\t\7\5\2"+
		"\2\t\n\5\2\2\2\n\13\7\6\2\2\13\17\3\2\2\2\f\17\7\b\2\2\r\17\7\7\2\2\16"+
		"\4\3\2\2\2\16\f\3\2\2\2\16\r\3\2\2\2\17\3\3\2\2\2\3\16";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}