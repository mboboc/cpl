// Generated from D:/UPB/CPL/Idea/Parser-demo\CPLangLexer.g4 by ANTLR 4.8
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
		IF=1, THEN=2, ELSE=3, FI=4, INT=5, ID=6, REAL=7, STRING=8, BLOCK_COMMENT=9, 
		WS=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "FI", "DIGIT", "INT", "LETTER", "ID", "DIGITS", 
			"FRACTION", "EXPONENT", "REAL", "STRING", "BLOCK_COMMENT", "WS"
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
		case 12:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\f~\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\6\7\65\n\7\r"+
		"\7\16\7\66\3\b\3\b\3\t\3\t\5\t=\n\t\3\t\3\t\3\t\7\tB\n\t\f\t\16\tE\13"+
		"\t\3\n\6\nH\n\n\r\n\16\nI\3\13\3\13\5\13N\n\13\5\13P\n\13\3\f\3\f\5\f"+
		"T\n\f\3\f\5\fW\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16a\n\16\f\16"+
		"\16\16d\13\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\7\17n\n\17\f\17"+
		"\16\17q\13\17\3\17\3\17\3\17\3\17\3\17\3\20\6\20y\n\20\r\20\16\20z\3\20"+
		"\3\20\4bo\2\21\3\3\5\4\7\5\t\6\13\2\r\7\17\2\21\b\23\2\25\2\27\2\31\t"+
		"\33\n\35\13\37\f\3\2\6\3\2\62;\4\2C\\c|\4\2--//\5\2\13\f\17\17\"\"\2\u0087"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\r\3\2\2\2\2\21\3\2"+
		"\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5"+
		"$\3\2\2\2\7)\3\2\2\2\t.\3\2\2\2\13\61\3\2\2\2\r\64\3\2\2\2\178\3\2\2\2"+
		"\21<\3\2\2\2\23G\3\2\2\2\25O\3\2\2\2\27V\3\2\2\2\31X\3\2\2\2\33\\\3\2"+
		"\2\2\35h\3\2\2\2\37x\3\2\2\2!\"\7k\2\2\"#\7h\2\2#\4\3\2\2\2$%\7v\2\2%"+
		"&\7j\2\2&\'\7g\2\2\'(\7p\2\2(\6\3\2\2\2)*\7g\2\2*+\7n\2\2+,\7u\2\2,-\7"+
		"g\2\2-\b\3\2\2\2./\7h\2\2/\60\7k\2\2\60\n\3\2\2\2\61\62\t\2\2\2\62\f\3"+
		"\2\2\2\63\65\5\13\6\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67"+
		"\3\2\2\2\67\16\3\2\2\289\t\3\2\29\20\3\2\2\2:=\5\17\b\2;=\7a\2\2<:\3\2"+
		"\2\2<;\3\2\2\2=C\3\2\2\2>B\5\17\b\2?B\7a\2\2@B\5\13\6\2A>\3\2\2\2A?\3"+
		"\2\2\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\22\3\2\2\2EC\3\2\2\2F"+
		"H\5\13\6\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\24\3\2\2\2KM\7\60"+
		"\2\2LN\5\23\n\2ML\3\2\2\2MN\3\2\2\2NP\3\2\2\2OK\3\2\2\2OP\3\2\2\2P\26"+
		"\3\2\2\2QS\7g\2\2RT\t\4\2\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UW\5\23\n\2V"+
		"Q\3\2\2\2VW\3\2\2\2W\30\3\2\2\2XY\5\23\n\2YZ\5\25\13\2Z[\5\27\f\2[\32"+
		"\3\2\2\2\\b\7$\2\2]^\7^\2\2^a\7$\2\2_a\13\2\2\2`]\3\2\2\2`_\3\2\2\2ad"+
		"\3\2\2\2bc\3\2\2\2b`\3\2\2\2ce\3\2\2\2db\3\2\2\2ef\7$\2\2fg\b\16\2\2g"+
		"\34\3\2\2\2hi\7\61\2\2ij\7,\2\2jo\3\2\2\2kn\5\35\17\2ln\13\2\2\2mk\3\2"+
		"\2\2ml\3\2\2\2nq\3\2\2\2op\3\2\2\2om\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7,"+
		"\2\2st\7\61\2\2tu\3\2\2\2uv\b\17\3\2v\36\3\2\2\2wy\t\5\2\2xw\3\2\2\2y"+
		"z\3\2\2\2zx\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\b\20\3\2} \3\2\2\2\21\2\66<"+
		"ACIMOSV`bmoz\4\3\16\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}