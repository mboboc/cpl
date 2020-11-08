// Generated from D:/UPB/CPL/Idea/Lexer-demo\CPLangLexer.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CPLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, INT=2, ID=3, REAL=4, STRING=5, BLOCK_COMMENT=6, WS=7;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "DIGIT", "INT", "LETTER", "ID", "DIGITS", "FRACTION", "EXPONENT", 
			"REAL", "STRING", "BLOCK_COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "INT", "ID", "REAL", "STRING", "BLOCK_COMMENT", "WS"
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


	public CPLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CPLangLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 9:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.out.println("there are no strings in CPLang, but shhh.."); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\tk\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\3\3\3\3\4\6\4\"\n\4\r\4\16\4#\3\5\3"+
		"\5\3\6\3\6\5\6*\n\6\3\6\3\6\3\6\7\6/\n\6\f\6\16\6\62\13\6\3\7\6\7\65\n"+
		"\7\r\7\16\7\66\3\b\3\b\5\b;\n\b\5\b=\n\b\3\t\3\t\5\tA\n\t\3\t\5\tD\n\t"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13N\n\13\f\13\16\13Q\13\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\7\f[\n\f\f\f\16\f^\13\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\6\rf\n\r\r\r\16\rg\3\r\3\r\4O\\\2\16\3\3\5\2\7\4\t\2\13\5\r\2"+
		"\17\2\21\2\23\6\25\7\27\b\31\t\3\2\6\3\2\62;\4\2C\\c|\4\2--//\5\2\13\f"+
		"\17\17\"\"\2t\2\3\3\2\2\2\2\7\3\2\2\2\2\13\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5\36\3\2\2\2\7!\3\2\2\2"+
		"\t%\3\2\2\2\13)\3\2\2\2\r\64\3\2\2\2\17<\3\2\2\2\21C\3\2\2\2\23E\3\2\2"+
		"\2\25I\3\2\2\2\27U\3\2\2\2\31e\3\2\2\2\33\34\7k\2\2\34\35\7h\2\2\35\4"+
		"\3\2\2\2\36\37\t\2\2\2\37\6\3\2\2\2 \"\5\5\3\2! \3\2\2\2\"#\3\2\2\2#!"+
		"\3\2\2\2#$\3\2\2\2$\b\3\2\2\2%&\t\3\2\2&\n\3\2\2\2\'*\5\t\5\2(*\7a\2\2"+
		")\'\3\2\2\2)(\3\2\2\2*\60\3\2\2\2+/\5\t\5\2,/\7a\2\2-/\5\5\3\2.+\3\2\2"+
		"\2.,\3\2\2\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\f\3\2"+
		"\2\2\62\60\3\2\2\2\63\65\5\5\3\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2"+
		"\2\2\66\67\3\2\2\2\67\16\3\2\2\28:\7\60\2\29;\5\r\7\2:9\3\2\2\2:;\3\2"+
		"\2\2;=\3\2\2\2<8\3\2\2\2<=\3\2\2\2=\20\3\2\2\2>@\7g\2\2?A\t\4\2\2@?\3"+
		"\2\2\2@A\3\2\2\2AB\3\2\2\2BD\5\r\7\2C>\3\2\2\2CD\3\2\2\2D\22\3\2\2\2E"+
		"F\5\r\7\2FG\5\17\b\2GH\5\21\t\2H\24\3\2\2\2IO\7$\2\2JK\7^\2\2KN\7$\2\2"+
		"LN\13\2\2\2MJ\3\2\2\2ML\3\2\2\2NQ\3\2\2\2OP\3\2\2\2OM\3\2\2\2PR\3\2\2"+
		"\2QO\3\2\2\2RS\7$\2\2ST\b\13\2\2T\26\3\2\2\2UV\7\61\2\2VW\7,\2\2W\\\3"+
		"\2\2\2X[\5\27\f\2Y[\13\2\2\2ZX\3\2\2\2ZY\3\2\2\2[^\3\2\2\2\\]\3\2\2\2"+
		"\\Z\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_`\7,\2\2`a\7\61\2\2ab\3\2\2\2bc\b\f\3"+
		"\2c\30\3\2\2\2df\t\5\2\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2hi\3\2"+
		"\2\2ij\b\r\3\2j\32\3\2\2\2\21\2#).\60\66:<@CMOZ\\g\4\3\13\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}