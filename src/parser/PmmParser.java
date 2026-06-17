// Generated from C:/Users/david/Desktop/Uniovi/3º/2º Cuatrimestre/DLP/Project/src/parser/Pmm.g4 by ANTLR 4.13.2
package parser;

    import ast.*;
    import ast.definitions.*;
    import ast.expressions.*;
    import ast.statements.*;
    import ast.types.*;
    import ast.expressions.literals.*;
    import java.util.Set;
    import java.util.HashSet;
    import errorhandler.ErrorHandler;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PmmParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, ID=41, INT_CONSTANT=42, REAL_CONSTANT=43, CHAR_CONSTANT=44, 
		SINGLE_LINE_COMMENT=45, MULTI_LINE_COMMENT=46, WS=47;
	public static final int
		RULE_program = 0, RULE_variables = 1, RULE_varDefinition = 2, RULE_params = 3, 
		RULE_variableParam = 4, RULE_functionDefinition = 5, RULE_mainDefinition = 6, 
		RULE_returnType = 7, RULE_expression = 8, RULE_statement = 9, RULE_block = 10, 
		RULE_type = 11, RULE_recordField = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "variables", "varDefinition", "params", "variableParam", "functionDefinition", 
			"mainDefinition", "returnType", "expression", "statement", "block", "type", 
			"recordField"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "':'", "';'", "'&'", "'def'", "'('", "')'", "'->'", "'{'", 
			"'}'", "'main'", "'None'", "'['", "']'", "'.'", "'-'", "'!'", "'*'", 
			"'/'", "'%'", "'+'", "'>'", "'>='", "'<'", "'<='", "'!='", "'=='", "'&&'", 
			"'||'", "'print'", "'input'", "'='", "'if'", "'else'", "'while'", "'return'", 
			"'char'", "'int'", "'double'", "'struct'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "ID", "INT_CONSTANT", "REAL_CONSTANT", 
			"CHAR_CONSTANT", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT", "WS"
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
	public String getGrammarFileName() { return "Pmm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PmmParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public Program ast;
		public List<Definition> definitions = new ArrayList<>();
		public FunctionDefinitionContext func;
		public VarDefinitionContext var;
		public MainDefinitionContext main;
		public TerminalNode EOF() { return getToken(PmmParser.EOF, 0); }
		public MainDefinitionContext mainDefinition() {
			return getRuleContext(MainDefinitionContext.class,0);
		}
		public List<FunctionDefinitionContext> functionDefinition() {
			return getRuleContexts(FunctionDefinitionContext.class);
		}
		public FunctionDefinitionContext functionDefinition(int i) {
			return getRuleContext(FunctionDefinitionContext.class,i);
		}
		public List<VarDefinitionContext> varDefinition() {
			return getRuleContexts(VarDefinitionContext.class);
		}
		public VarDefinitionContext varDefinition(int i) {
			return getRuleContext(VarDefinitionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(32);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__4:
						{
						setState(26);
						((ProgramContext)_localctx).func = functionDefinition();
						_localctx.definitions.add(((ProgramContext)_localctx).func.ast); 
						}
						break;
					case ID:
						{
						setState(29);
						((ProgramContext)_localctx).var = varDefinition();
						_localctx.definitions.addAll(((ProgramContext)_localctx).var.ast); 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(36);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(37);
			((ProgramContext)_localctx).main = mainDefinition();
			setState(38);
			match(EOF);

			            _localctx.definitions.add(((ProgramContext)_localctx).main.ast);
			            ((ProgramContext)_localctx).ast =  new Program(_localctx.definitions);

			          
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
	public static class VariablesContext extends ParserRuleContext {
		public List<VarDefinition> ast = new ArrayList<>();
		public List<String> vars = new ArrayList<>();
		public Token ID1;
		public Token ID2;
		public TypeContext type;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(PmmParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PmmParser.ID, i);
		}
		public VariablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variables; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterVariables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitVariables(this);
		}
	}

	public final VariablesContext variables() throws RecognitionException {
		VariablesContext _localctx = new VariablesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_variables);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			((VariablesContext)_localctx).ID1 = match(ID);
			_localctx.vars.add((((VariablesContext)_localctx).ID1!=null?((VariablesContext)_localctx).ID1.getText():null)); 
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(43);
				match(T__0);
				setState(44);
				((VariablesContext)_localctx).ID2 = match(ID);
				_localctx.vars.add((((VariablesContext)_localctx).ID2!=null?((VariablesContext)_localctx).ID2.getText():null)); 
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			match(T__1);
			setState(52);
			((VariablesContext)_localctx).type = type();

			            for (String str: _localctx.vars) {
			                _localctx.ast.add(new VarDefinition((((VariablesContext)_localctx).ID1!=null?((VariablesContext)_localctx).ID1.getLine():0), ((VariablesContext)_localctx).ID1.getCharPositionInLine() + 1, str, ((VariablesContext)_localctx).type.ast, false));
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
	public static class VarDefinitionContext extends ParserRuleContext {
		public List<VarDefinition> ast = new ArrayList<>();
		public List<Token> tokens = new ArrayList<>();
		public Token ID1;
		public Token ID2;
		public TypeContext type;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(PmmParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PmmParser.ID, i);
		}
		public VarDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterVarDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitVarDefinition(this);
		}
	}

	public final VarDefinitionContext varDefinition() throws RecognitionException {
		VarDefinitionContext _localctx = new VarDefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			((VarDefinitionContext)_localctx).ID1 = match(ID);
			 _localctx.tokens.add(((VarDefinitionContext)_localctx).ID1); 
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(57);
				match(T__0);
				setState(58);
				((VarDefinitionContext)_localctx).ID2 = match(ID);
				 _localctx.tokens.add(((VarDefinitionContext)_localctx).ID2); 
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			match(T__1);
			setState(66);
			((VarDefinitionContext)_localctx).type = type();
			setState(67);
			match(T__2);

			            Set<String> seenNames = new HashSet<>();

			            for (Token t : _localctx.tokens) {
			                String name = t.getText();

			                VarDefinition var = new VarDefinition(
			                    t.getLine(),
			                    t.getCharPositionInLine() + 1,
			                    name,
			                    ((VarDefinitionContext)_localctx).type.ast,
			                    false
			                );

			                if (!seenNames.add(name)) {
			                    new ErrorType("Variable '" + name + "' already defined", var);
			                } else {
			                    _localctx.ast.add(var);
			                }

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
	public static class ParamsContext extends ParserRuleContext {
		public List<VarDefinition> ast = new ArrayList<>();
		public VariableParamContext var1;
		public VariableParamContext var2;
		public List<VariableParamContext> variableParam() {
			return getRuleContexts(VariableParamContext.class);
		}
		public VariableParamContext variableParam(int i) {
			return getRuleContext(VariableParamContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(70);
				((ParamsContext)_localctx).var1 = variableParam();
				_localctx.ast.addAll(((ParamsContext)_localctx).var1.ast);
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(72);
					match(T__0);
					setState(73);
					((ParamsContext)_localctx).var2 = variableParam();
					_localctx.ast.addAll(((ParamsContext)_localctx).var2.ast); 
					}
					}
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
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
	public static class VariableParamContext extends ParserRuleContext {
		public List<VarDefinition> ast = new ArrayList<>();
		public Token ID1;
		public TypeContext type;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(PmmParser.ID, 0); }
		public VariableParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterVariableParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitVariableParam(this);
		}
	}

	public final VariableParamContext variableParam() throws RecognitionException {
		VariableParamContext _localctx = new VariableParamContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variableParam);
		try {
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				((VariableParamContext)_localctx).ID1 = match(ID);
				setState(84);
				match(T__1);
				setState(85);
				((VariableParamContext)_localctx).type = type();
				   _localctx.ast.add(new VarDefinition((((VariableParamContext)_localctx).ID1!=null?((VariableParamContext)_localctx).ID1.getLine():0), ((VariableParamContext)_localctx).ID1.getCharPositionInLine() + 1, (((VariableParamContext)_localctx).ID1!=null?((VariableParamContext)_localctx).ID1.getText():null), ((VariableParamContext)_localctx).type.ast, false)); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				((VariableParamContext)_localctx).ID1 = match(ID);
				setState(89);
				match(T__3);
				setState(90);
				match(T__1);
				setState(91);
				((VariableParamContext)_localctx).type = type();
				   _localctx.ast.add(new VarDefinition((((VariableParamContext)_localctx).ID1!=null?((VariableParamContext)_localctx).ID1.getLine():0), ((VariableParamContext)_localctx).ID1.getCharPositionInLine() + 1, (((VariableParamContext)_localctx).ID1!=null?((VariableParamContext)_localctx).ID1.getText():null), ((VariableParamContext)_localctx).type.ast, true)); 
				}
				break;
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
	public static class FunctionDefinitionContext extends ParserRuleContext {
		public Definition ast;
		public List<VarDefinition> definitions = new ArrayList<>();
		public List<Statement> statements = new ArrayList<>();
		public Token ID;
		public ParamsContext par;
		public ReturnTypeContext tp;
		public VarDefinitionContext varDef;
		public StatementContext st;
		public TerminalNode ID() { return getToken(PmmParser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public List<VarDefinitionContext> varDefinition() {
			return getRuleContexts(VarDefinitionContext.class);
		}
		public VarDefinitionContext varDefinition(int i) {
			return getRuleContext(VarDefinitionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitFunctionDefinition(this);
		}
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionDefinition);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__4);
			setState(97);
			((FunctionDefinitionContext)_localctx).ID = match(ID);
			setState(98);
			match(T__5);
			setState(99);
			((FunctionDefinitionContext)_localctx).par = params();
			setState(100);
			match(T__6);
			setState(101);
			match(T__7);
			setState(102);
			((FunctionDefinitionContext)_localctx).tp = returnType();
			setState(103);
			match(T__1);
			setState(104);
			match(T__8);
			setState(110);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(105);
					((FunctionDefinitionContext)_localctx).varDef = varDefinition();
					 _localctx.definitions.addAll(((FunctionDefinitionContext)_localctx).varDef.ast); 
					}
					} 
				}
				setState(112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33100239405120L) != 0)) {
				{
				{
				setState(113);
				((FunctionDefinitionContext)_localctx).st = statement();
				 _localctx.statements.addAll(((FunctionDefinitionContext)_localctx).st.ast); 
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			match(T__9);

			        Type retType = (((FunctionDefinitionContext)_localctx).tp.ast != null) ? ((FunctionDefinitionContext)_localctx).tp.ast : VoidType.getInstance();
			        ((FunctionDefinitionContext)_localctx).ast =  new FuncDefinition((((FunctionDefinitionContext)_localctx).ID!=null?((FunctionDefinitionContext)_localctx).ID.getLine():0), ((FunctionDefinitionContext)_localctx).ID.getCharPositionInLine() + 1,
			               (((FunctionDefinitionContext)_localctx).ID!=null?((FunctionDefinitionContext)_localctx).ID.getText():null), new FunctionType(retType, ((FunctionDefinitionContext)_localctx).par.ast),
			               _localctx.definitions, _localctx.statements);
			      
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
	public static class MainDefinitionContext extends ParserRuleContext {
		public Definition ast;
		public List<VarDefinition> definitions = new ArrayList<>();
		public List<Statement> statements = new ArrayList<>();
		public Token id;
		public VarDefinitionContext varDef;
		public StatementContext st;
		public List<VarDefinitionContext> varDefinition() {
			return getRuleContexts(VarDefinitionContext.class);
		}
		public VarDefinitionContext varDefinition(int i) {
			return getRuleContext(VarDefinitionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MainDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterMainDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitMainDefinition(this);
		}
	}

	public final MainDefinitionContext mainDefinition() throws RecognitionException {
		MainDefinitionContext _localctx = new MainDefinitionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_mainDefinition);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__4);
			setState(125);
			((MainDefinitionContext)_localctx).id = match(T__10);
			setState(126);
			match(T__5);
			setState(127);
			match(T__6);
			setState(128);
			match(T__7);
			setState(129);
			match(T__11);
			setState(130);
			match(T__1);
			setState(131);
			match(T__8);
			setState(137);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(132);
					((MainDefinitionContext)_localctx).varDef = varDefinition();
					 _localctx.definitions.addAll(((MainDefinitionContext)_localctx).varDef.ast); 
					}
					} 
				}
				setState(139);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33100239405120L) != 0)) {
				{
				{
				setState(140);
				((MainDefinitionContext)_localctx).st = statement();
				 _localctx.statements.addAll(((MainDefinitionContext)_localctx).st.ast); 
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148);
			match(T__9);

			        ((MainDefinitionContext)_localctx).ast =  new FuncDefinition((((MainDefinitionContext)_localctx).id!=null?((MainDefinitionContext)_localctx).id.getLine():0), ((MainDefinitionContext)_localctx).id.getCharPositionInLine() + 1,
			               (((MainDefinitionContext)_localctx).id!=null?((MainDefinitionContext)_localctx).id.getText():null),
			               new FunctionType(),
			               _localctx.definitions, _localctx.statements);
			      
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
	public static class ReturnTypeContext extends ParserRuleContext {
		public Type ast;
		public TypeContext tp;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitReturnType(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_returnType);
		try {
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				((ReturnTypeContext)_localctx).tp = type();
				((ReturnTypeContext)_localctx).ast =  ((ReturnTypeContext)_localctx).tp.ast; 
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(T__11);
				((ReturnTypeContext)_localctx).ast =  null; 
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
		public Expression ast;
		public List<Expression> expressions = new ArrayList<>();
		public ExpressionContext exp;
		public ExpressionContext left;
		public TypeContext tp;
		public Token ID;
		public ExpressionContext exp1;
		public ExpressionContext exp2;
		public Token INT;
		public Token REAL;
		public Token CHAR;
		public Token VAR;
		public Token OP;
		public ExpressionContext right;
		public ExpressionContext index;
		public Token FIELD;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(PmmParser.ID, 0); }
		public TerminalNode INT_CONSTANT() { return getToken(PmmParser.INT_CONSTANT, 0); }
		public TerminalNode REAL_CONSTANT() { return getToken(PmmParser.REAL_CONSTANT, 0); }
		public TerminalNode CHAR_CONSTANT() { return getToken(PmmParser.CHAR_CONSTANT, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(159);
				match(T__5);
				setState(160);
				((ExpressionContext)_localctx).exp = expression(0);
				setState(161);
				match(T__6);
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).exp.ast; 
				}
				break;
			case 2:
				{
				setState(164);
				match(T__5);
				setState(165);
				((ExpressionContext)_localctx).tp = type();
				setState(166);
				match(T__6);
				setState(167);
				((ExpressionContext)_localctx).exp = expression(12);
				((ExpressionContext)_localctx).ast =  new Cast(((ExpressionContext)_localctx).exp.ast.getLine(), ((ExpressionContext)_localctx).exp.ast.getColumn(), ((ExpressionContext)_localctx).exp.ast, ((ExpressionContext)_localctx).tp.ast); 
				}
				break;
			case 3:
				{
				setState(170);
				match(T__15);
				setState(171);
				((ExpressionContext)_localctx).exp = expression(11);
				((ExpressionContext)_localctx).ast =  new UnaryMinus(((ExpressionContext)_localctx).exp.ast.getLine(), ((ExpressionContext)_localctx).exp.ast.getColumn(), ((ExpressionContext)_localctx).exp.ast); 
				}
				break;
			case 4:
				{
				setState(174);
				match(T__16);
				setState(175);
				((ExpressionContext)_localctx).exp = expression(10);
				((ExpressionContext)_localctx).ast =  new Negation(((ExpressionContext)_localctx).exp.ast.getLine(), ((ExpressionContext)_localctx).exp.ast.getColumn(), ((ExpressionContext)_localctx).exp.ast); 
				}
				break;
			case 5:
				{
				setState(178);
				((ExpressionContext)_localctx).ID = match(ID);
				setState(179);
				match(T__5);
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 32985349029952L) != 0)) {
					{
					setState(180);
					((ExpressionContext)_localctx).exp1 = expression(0);
					_localctx.expressions.add(((ExpressionContext)_localctx).exp1.ast); 
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__0) {
						{
						{
						setState(182);
						match(T__0);
						setState(183);
						((ExpressionContext)_localctx).exp2 = expression(0);
						_localctx.expressions.add(((ExpressionContext)_localctx).exp2.ast); 
						}
						}
						setState(190);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(193);
				match(T__6);
				((ExpressionContext)_localctx).ast =  new FunctionInvocation((((ExpressionContext)_localctx).ID!=null?((ExpressionContext)_localctx).ID.getLine():0), ((ExpressionContext)_localctx).ID.getCharPositionInLine()+1,
				                new Variable((((ExpressionContext)_localctx).ID!=null?((ExpressionContext)_localctx).ID.getLine():0), ((ExpressionContext)_localctx).ID.getCharPositionInLine()+1,(((ExpressionContext)_localctx).ID!=null?((ExpressionContext)_localctx).ID.getText():null)),
				                _localctx.expressions); 
				}
				break;
			case 6:
				{
				setState(195);
				((ExpressionContext)_localctx).INT = match(INT_CONSTANT);
				((ExpressionContext)_localctx).ast =  new IntLiteral((((ExpressionContext)_localctx).INT!=null?((ExpressionContext)_localctx).INT.getLine():0), ((ExpressionContext)_localctx).INT.getCharPositionInLine()+1, LexerHelper.lexemeToInt((((ExpressionContext)_localctx).INT!=null?((ExpressionContext)_localctx).INT.getText():null))); 
				}
				break;
			case 7:
				{
				setState(197);
				((ExpressionContext)_localctx).REAL = match(REAL_CONSTANT);
				((ExpressionContext)_localctx).ast =  new RealLiteral((((ExpressionContext)_localctx).REAL!=null?((ExpressionContext)_localctx).REAL.getLine():0), ((ExpressionContext)_localctx).REAL.getCharPositionInLine()+1, LexerHelper.lexemeToReal((((ExpressionContext)_localctx).REAL!=null?((ExpressionContext)_localctx).REAL.getText():null))); 
				}
				break;
			case 8:
				{
				setState(199);
				((ExpressionContext)_localctx).CHAR = match(CHAR_CONSTANT);
				((ExpressionContext)_localctx).ast =  new CharLiteral((((ExpressionContext)_localctx).CHAR!=null?((ExpressionContext)_localctx).CHAR.getLine():0), ((ExpressionContext)_localctx).CHAR.getCharPositionInLine()+1, LexerHelper.lexemeToChar((((ExpressionContext)_localctx).CHAR!=null?((ExpressionContext)_localctx).CHAR.getText():null))); 
				}
				break;
			case 9:
				{
				setState(201);
				((ExpressionContext)_localctx).VAR = match(ID);
				 ((ExpressionContext)_localctx).ast =  new Variable((((ExpressionContext)_localctx).VAR!=null?((ExpressionContext)_localctx).VAR.getLine():0), ((ExpressionContext)_localctx).VAR.getCharPositionInLine()+1, (((ExpressionContext)_localctx).VAR!=null?((ExpressionContext)_localctx).VAR.getText():null)); 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(237);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(235);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(206);
						((ExpressionContext)_localctx).OP = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1835008L) != 0)) ) {
							((ExpressionContext)_localctx).OP = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(207);
						((ExpressionContext)_localctx).right = expression(10);
						((ExpressionContext)_localctx).ast =  ExpressionFactory.createArithmeticOrModulus(((ExpressionContext)_localctx).left.ast.getLine(), ((ExpressionContext)_localctx).left.ast.getColumn(), ((ExpressionContext)_localctx).left.ast, ((ExpressionContext)_localctx).right.ast, (((ExpressionContext)_localctx).OP!=null?((ExpressionContext)_localctx).OP.getText():null)); 
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(210);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(211);
						((ExpressionContext)_localctx).OP = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__15 || _la==T__20) ) {
							((ExpressionContext)_localctx).OP = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(212);
						((ExpressionContext)_localctx).right = expression(9);
						((ExpressionContext)_localctx).ast =  new Arithmetic(((ExpressionContext)_localctx).left.ast.getLine(), ((ExpressionContext)_localctx).left.ast.getColumn(), ((ExpressionContext)_localctx).left.ast, ((ExpressionContext)_localctx).right.ast, (((ExpressionContext)_localctx).OP!=null?((ExpressionContext)_localctx).OP.getText():null)); 
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(215);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(216);
						((ExpressionContext)_localctx).OP = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 264241152L) != 0)) ) {
							((ExpressionContext)_localctx).OP = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(217);
						((ExpressionContext)_localctx).right = expression(8);
						((ExpressionContext)_localctx).ast =  new Comparison(((ExpressionContext)_localctx).left.ast.getLine(), ((ExpressionContext)_localctx).left.ast.getColumn(), ((ExpressionContext)_localctx).left.ast, ((ExpressionContext)_localctx).right.ast, (((ExpressionContext)_localctx).OP!=null?((ExpressionContext)_localctx).OP.getText():null)); 
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(221);
						((ExpressionContext)_localctx).OP = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__27 || _la==T__28) ) {
							((ExpressionContext)_localctx).OP = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(222);
						((ExpressionContext)_localctx).right = expression(7);
						((ExpressionContext)_localctx).ast =  new Logical(((ExpressionContext)_localctx).left.ast.getLine(), ((ExpressionContext)_localctx).left.ast.getColumn(), ((ExpressionContext)_localctx).left.ast, ((ExpressionContext)_localctx).right.ast, (((ExpressionContext)_localctx).OP!=null?((ExpressionContext)_localctx).OP.getText():null));
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.exp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(225);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(226);
						match(T__12);
						setState(227);
						((ExpressionContext)_localctx).index = expression(0);
						setState(228);
						match(T__13);
						((ExpressionContext)_localctx).ast =  new ArrayAccess(((ExpressionContext)_localctx).exp.ast.getLine(), ((ExpressionContext)_localctx).exp.ast.getColumn(), ((ExpressionContext)_localctx).exp.ast, ((ExpressionContext)_localctx).index.ast); 
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.exp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(231);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(232);
						match(T__14);
						setState(233);
						((ExpressionContext)_localctx).FIELD = match(ID);
						((ExpressionContext)_localctx).ast =  new FieldAccess(((ExpressionContext)_localctx).exp.ast.getLine(), ((ExpressionContext)_localctx).exp.ast.getColumn(), (((ExpressionContext)_localctx).FIELD!=null?((ExpressionContext)_localctx).FIELD.getText():null), ((ExpressionContext)_localctx).exp.ast); 
						}
						break;
					}
					} 
				}
				setState(239);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		public List<Statement> ast = new ArrayList<>();
		public List<Statement> statements = new ArrayList<>();
		public List<Expression> expressions = new ArrayList<>();
		public ExpressionContext exp;
		public ExpressionContext exp2;
		public ExpressionContext left;
		public ExpressionContext right;
		public BlockContext b1;
		public BlockContext b2;
		public Token ID;
		public ExpressionContext exp1;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ID() { return getToken(PmmParser.ID, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		int _la;
		try {
			setState(318);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				match(T__29);
				setState(241);
				((StatementContext)_localctx).exp = expression(0);
				_localctx.ast.add(new Print(((StatementContext)_localctx).exp.ast.getLine(), ((StatementContext)_localctx).exp.ast.getColumn(), ((StatementContext)_localctx).exp.ast));
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(243);
					match(T__0);
					setState(244);
					((StatementContext)_localctx).exp2 = expression(0);
					_localctx.ast.add(new Print(((StatementContext)_localctx).exp2.ast.getLine(), ((StatementContext)_localctx).exp2.ast.getColumn(), ((StatementContext)_localctx).exp2.ast)); 
					}
					}
					setState(251);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(252);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(T__30);
				setState(255);
				((StatementContext)_localctx).exp = expression(0);
				_localctx.ast.add(new Read(((StatementContext)_localctx).exp.ast.getLine(), ((StatementContext)_localctx).exp.ast.getColumn(), ((StatementContext)_localctx).exp.ast)); 
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(257);
					match(T__0);
					setState(258);
					((StatementContext)_localctx).exp2 = expression(0);
					_localctx.ast.add(new Read(((StatementContext)_localctx).exp2.ast.getLine(), ((StatementContext)_localctx).exp2.ast.getColumn(), ((StatementContext)_localctx).exp2.ast)); 
					}
					}
					setState(265);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(266);
				match(T__2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(268);
				((StatementContext)_localctx).left = expression(0);
				setState(269);
				match(T__31);
				setState(270);
				((StatementContext)_localctx).right = expression(0);
				setState(271);
				match(T__2);
				_localctx.ast.add(new Assignment(((StatementContext)_localctx).left.ast.getLine(), ((StatementContext)_localctx).left.ast.getColumn(), ((StatementContext)_localctx).left.ast, ((StatementContext)_localctx).right.ast)); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(274);
				match(T__32);
				setState(275);
				((StatementContext)_localctx).exp = expression(0);
				setState(276);
				match(T__1);
				setState(277);
				((StatementContext)_localctx).b1 = block();
				setState(278);
				match(T__33);
				setState(279);
				match(T__1);
				setState(280);
				((StatementContext)_localctx).b2 = block();
				_localctx.ast.add(new IfElse(((StatementContext)_localctx).exp.ast.getLine(), ((StatementContext)_localctx).exp.ast.getColumn(), ((StatementContext)_localctx).b1.ast, ((StatementContext)_localctx).exp.ast, ((StatementContext)_localctx).b2.ast)); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(283);
				match(T__32);
				setState(284);
				((StatementContext)_localctx).exp = expression(0);
				setState(285);
				match(T__1);
				setState(286);
				((StatementContext)_localctx).b1 = block();
				_localctx.ast.add(new IfElse(((StatementContext)_localctx).exp.ast.getLine(), ((StatementContext)_localctx).exp.ast.getColumn(), ((StatementContext)_localctx).b1.ast, ((StatementContext)_localctx).exp.ast)); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(289);
				match(T__34);
				setState(290);
				((StatementContext)_localctx).exp = expression(0);
				setState(291);
				match(T__1);
				setState(292);
				((StatementContext)_localctx).b2 = block();
				_localctx.ast.add(new While(((StatementContext)_localctx).exp.ast.getLine(), ((StatementContext)_localctx).exp.ast.getColumn(), ((StatementContext)_localctx).exp.ast, ((StatementContext)_localctx).b2.ast)); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(295);
				match(T__35);
				setState(296);
				((StatementContext)_localctx).exp = expression(0);
				setState(297);
				match(T__2);
				_localctx.ast.add(new Return(((StatementContext)_localctx).exp.ast.getLine(), ((StatementContext)_localctx).exp.ast.getColumn(), ((StatementContext)_localctx).exp.ast)); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(300);
				((StatementContext)_localctx).ID = match(ID);
				setState(301);
				match(T__5);
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 32985349029952L) != 0)) {
					{
					setState(302);
					((StatementContext)_localctx).exp1 = expression(0);
					_localctx.expressions.add(((StatementContext)_localctx).exp1.ast); 
					setState(310);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__0) {
						{
						{
						setState(304);
						match(T__0);
						setState(305);
						((StatementContext)_localctx).exp2 = expression(0);
						_localctx.expressions.add(((StatementContext)_localctx).exp2.ast); 
						}
						}
						setState(312);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(315);
				match(T__6);
				setState(316);
				match(T__2);
				_localctx.ast.add(new FunctionInvocation((((StatementContext)_localctx).ID!=null?((StatementContext)_localctx).ID.getLine():0), ((StatementContext)_localctx).ID.getCharPositionInLine()+1,
				                 new Variable((((StatementContext)_localctx).ID!=null?((StatementContext)_localctx).ID.getLine():0), ((StatementContext)_localctx).ID.getCharPositionInLine()+1,(((StatementContext)_localctx).ID!=null?((StatementContext)_localctx).ID.getText():null)),
				                 _localctx.expressions)); 
				}
				break;
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
	public static class BlockContext extends ParserRuleContext {
		public List<Statement> ast = new ArrayList<>();
		public StatementContext st;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_block);
		int _la;
		try {
			setState(333);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case T__15:
			case T__16:
			case T__29:
			case T__30:
			case T__32:
			case T__34:
			case T__35:
			case ID:
			case INT_CONSTANT:
			case REAL_CONSTANT:
			case CHAR_CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				((BlockContext)_localctx).st = statement();
				_localctx.ast.addAll(((BlockContext)_localctx).st.ast); 
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				match(T__8);
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33100239405120L) != 0)) {
					{
					{
					setState(324);
					((BlockContext)_localctx).st = statement();
					_localctx.ast.addAll(((BlockContext)_localctx).st.ast);
					}
					}
					setState(331);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(332);
				match(T__9);
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
	public static class TypeContext extends ParserRuleContext {
		public Type ast;
		public List<RecordField> records = new ArrayList<>();
		public Token SIZE;
		public TypeContext tp;
		public TypeContext type;
		public RecordFieldContext sf;
		public TerminalNode INT_CONSTANT() { return getToken(PmmParser.INT_CONSTANT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<RecordFieldContext> recordField() {
			return getRuleContexts(RecordFieldContext.class);
		}
		public RecordFieldContext recordField(int i) {
			return getRuleContext(RecordFieldContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_type);
		int _la;
		try {
			setState(360);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__36:
				enterOuterAlt(_localctx, 1);
				{
				setState(335);
				match(T__36);
				((TypeContext)_localctx).ast =  CharType.getInstance(); 
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 2);
				{
				setState(337);
				match(T__37);
				((TypeContext)_localctx).ast =  IntType.getInstance(); 
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 3);
				{
				setState(339);
				match(T__38);
				((TypeContext)_localctx).ast =  RealType.getInstance(); 
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 4);
				{
				setState(341);
				match(T__12);
				setState(342);
				((TypeContext)_localctx).SIZE = match(INT_CONSTANT);
				setState(343);
				match(T__13);
				setState(344);
				((TypeContext)_localctx).tp = ((TypeContext)_localctx).type = type();
				((TypeContext)_localctx).ast =  new ArrayType(LexerHelper.lexemeToInt((((TypeContext)_localctx).SIZE!=null?((TypeContext)_localctx).SIZE.getText():null)), ((TypeContext)_localctx).type.ast); 
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 5);
				{
				setState(347);
				match(T__39);
				setState(348);
				match(T__8);
				 Set<String> structNames = new HashSet<>(); 
				setState(353); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(350);
					((TypeContext)_localctx).sf = recordField();

					        for(RecordField rf : ((TypeContext)_localctx).sf.ast) {
					            if (!structNames.add(rf.getName())) {
					                new ErrorType("Field '" + rf.getName() + "' already defined in struct", rf);
					            } else {
					                _localctx.records.add(rf);
					            }
					        }
					      
					}
					}
					setState(355); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(357);
				match(T__9);
				 ((TypeContext)_localctx).ast =  new StructType(_localctx.records); 
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
	public static class RecordFieldContext extends ParserRuleContext {
		public List<RecordField> ast = new ArrayList<>();
		public List<Token> tokens = new ArrayList<>();
		public Token ID1;
		public Token ID2;
		public TypeContext type;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(PmmParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PmmParser.ID, i);
		}
		public RecordFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).enterRecordField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PmmListener ) ((PmmListener)listener).exitRecordField(this);
		}
	}

	public final RecordFieldContext recordField() throws RecognitionException {
		RecordFieldContext _localctx = new RecordFieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_recordField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			((RecordFieldContext)_localctx).ID1 = match(ID);
			 _localctx.tokens.add(((RecordFieldContext)_localctx).ID1); 
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(364);
				match(T__0);
				setState(365);
				((RecordFieldContext)_localctx).ID2 = match(ID);
				 _localctx.tokens.add(((RecordFieldContext)_localctx).ID2); 
				}
				}
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(372);
			match(T__1);
			setState(373);
			((RecordFieldContext)_localctx).type = type();
			setState(374);
			match(T__2);

			            Set<String> lineNames = new HashSet<>();
			            for (Token t : _localctx.tokens) {
			                String name = t.getText();
			                RecordField rec = new RecordField(
			                    t.getLine(),
			                    t.getCharPositionInLine() + 1,
			                    name,
			                    ((RecordFieldContext)_localctx).type.ast
			                );
			                if (!lineNames.add(name)) {
			                    new ErrorType("Field '" + name + "' duplicated in same line", rec);
			                } else {
			                    _localctx.ast.add(rec);
			                }
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 14);
		case 5:
			return precpred(_ctx, 13);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001/\u017a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000!\b\u0000\n\u0000\f\u0000$\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001/\b\u0001\n\u0001\f\u00012\t\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002=\b\u0002\n\u0002\f\u0002"+
		"@\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0005\u0003M\b\u0003\n\u0003\f\u0003P\t\u0003\u0003\u0003R\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004_\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005m\b\u0005\n\u0005\f\u0005p\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0005\u0005u\b\u0005\n\u0005\f\u0005x\t\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006\u0088\b\u0006\n\u0006\f\u0006\u008b\t\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u0090\b\u0006\n\u0006"+
		"\f\u0006\u0093\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u009d\b\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0005\b\u00bb\b\b\n\b\f\b\u00be\t\b\u0003\b\u00c0\b\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u00cc\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u00ec\b\b\n\b\f\b\u00ef"+
		"\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u00f8"+
		"\b\t\n\t\f\t\u00fb\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0005\t\u0106\b\t\n\t\f\t\u0109\t\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u0135\b\t\n\t\f\t\u0138\t\t"+
		"\u0003\t\u013a\b\t\u0001\t\u0001\t\u0001\t\u0003\t\u013f\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u0148\b\n\n\n\f\n\u014b"+
		"\t\n\u0001\n\u0003\n\u014e\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0004\u000b\u0162\b\u000b\u000b\u000b\f"+
		"\u000b\u0163\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0169\b\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u0170\b\f\n\f\f\f\u0173"+
		"\t\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0000\u0001\u0010"+
		"\r\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000"+
		"\u0004\u0001\u0000\u0012\u0014\u0002\u0000\u0010\u0010\u0015\u0015\u0001"+
		"\u0000\u0016\u001b\u0001\u0000\u001c\u001d\u019b\u0000\"\u0001\u0000\u0000"+
		"\u0000\u0002)\u0001\u0000\u0000\u0000\u00047\u0001\u0000\u0000\u0000\u0006"+
		"Q\u0001\u0000\u0000\u0000\b^\u0001\u0000\u0000\u0000\n`\u0001\u0000\u0000"+
		"\u0000\f|\u0001\u0000\u0000\u0000\u000e\u009c\u0001\u0000\u0000\u0000"+
		"\u0010\u00cb\u0001\u0000\u0000\u0000\u0012\u013e\u0001\u0000\u0000\u0000"+
		"\u0014\u014d\u0001\u0000\u0000\u0000\u0016\u0168\u0001\u0000\u0000\u0000"+
		"\u0018\u016a\u0001\u0000\u0000\u0000\u001a\u001b\u0003\n\u0005\u0000\u001b"+
		"\u001c\u0006\u0000\uffff\uffff\u0000\u001c!\u0001\u0000\u0000\u0000\u001d"+
		"\u001e\u0003\u0004\u0002\u0000\u001e\u001f\u0006\u0000\uffff\uffff\u0000"+
		"\u001f!\u0001\u0000\u0000\u0000 \u001a\u0001\u0000\u0000\u0000 \u001d"+
		"\u0001\u0000\u0000\u0000!$\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000"+
		"\u0000\"#\u0001\u0000\u0000\u0000#%\u0001\u0000\u0000\u0000$\"\u0001\u0000"+
		"\u0000\u0000%&\u0003\f\u0006\u0000&\'\u0005\u0000\u0000\u0001\'(\u0006"+
		"\u0000\uffff\uffff\u0000(\u0001\u0001\u0000\u0000\u0000)*\u0005)\u0000"+
		"\u0000*0\u0006\u0001\uffff\uffff\u0000+,\u0005\u0001\u0000\u0000,-\u0005"+
		")\u0000\u0000-/\u0006\u0001\uffff\uffff\u0000.+\u0001\u0000\u0000\u0000"+
		"/2\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u000001\u0001\u0000\u0000"+
		"\u000013\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000034\u0005\u0002"+
		"\u0000\u000045\u0003\u0016\u000b\u000056\u0006\u0001\uffff\uffff\u0000"+
		"6\u0003\u0001\u0000\u0000\u000078\u0005)\u0000\u00008>\u0006\u0002\uffff"+
		"\uffff\u00009:\u0005\u0001\u0000\u0000:;\u0005)\u0000\u0000;=\u0006\u0002"+
		"\uffff\uffff\u0000<9\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000\u0000"+
		"><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?A\u0001\u0000\u0000"+
		"\u0000@>\u0001\u0000\u0000\u0000AB\u0005\u0002\u0000\u0000BC\u0003\u0016"+
		"\u000b\u0000CD\u0005\u0003\u0000\u0000DE\u0006\u0002\uffff\uffff\u0000"+
		"E\u0005\u0001\u0000\u0000\u0000FG\u0003\b\u0004\u0000GN\u0006\u0003\uffff"+
		"\uffff\u0000HI\u0005\u0001\u0000\u0000IJ\u0003\b\u0004\u0000JK\u0006\u0003"+
		"\uffff\uffff\u0000KM\u0001\u0000\u0000\u0000LH\u0001\u0000\u0000\u0000"+
		"MP\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000"+
		"\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000QF\u0001\u0000"+
		"\u0000\u0000QR\u0001\u0000\u0000\u0000R\u0007\u0001\u0000\u0000\u0000"+
		"ST\u0005)\u0000\u0000TU\u0005\u0002\u0000\u0000UV\u0003\u0016\u000b\u0000"+
		"VW\u0006\u0004\uffff\uffff\u0000W_\u0001\u0000\u0000\u0000XY\u0005)\u0000"+
		"\u0000YZ\u0005\u0004\u0000\u0000Z[\u0005\u0002\u0000\u0000[\\\u0003\u0016"+
		"\u000b\u0000\\]\u0006\u0004\uffff\uffff\u0000]_\u0001\u0000\u0000\u0000"+
		"^S\u0001\u0000\u0000\u0000^X\u0001\u0000\u0000\u0000_\t\u0001\u0000\u0000"+
		"\u0000`a\u0005\u0005\u0000\u0000ab\u0005)\u0000\u0000bc\u0005\u0006\u0000"+
		"\u0000cd\u0003\u0006\u0003\u0000de\u0005\u0007\u0000\u0000ef\u0005\b\u0000"+
		"\u0000fg\u0003\u000e\u0007\u0000gh\u0005\u0002\u0000\u0000hn\u0005\t\u0000"+
		"\u0000ij\u0003\u0004\u0002\u0000jk\u0006\u0005\uffff\uffff\u0000km\u0001"+
		"\u0000\u0000\u0000li\u0001\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000"+
		"nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000ov\u0001\u0000\u0000"+
		"\u0000pn\u0001\u0000\u0000\u0000qr\u0003\u0012\t\u0000rs\u0006\u0005\uffff"+
		"\uffff\u0000su\u0001\u0000\u0000\u0000tq\u0001\u0000\u0000\u0000ux\u0001"+
		"\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000"+
		"wy\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000yz\u0005\n\u0000\u0000"+
		"z{\u0006\u0005\uffff\uffff\u0000{\u000b\u0001\u0000\u0000\u0000|}\u0005"+
		"\u0005\u0000\u0000}~\u0005\u000b\u0000\u0000~\u007f\u0005\u0006\u0000"+
		"\u0000\u007f\u0080\u0005\u0007\u0000\u0000\u0080\u0081\u0005\b\u0000\u0000"+
		"\u0081\u0082\u0005\f\u0000\u0000\u0082\u0083\u0005\u0002\u0000\u0000\u0083"+
		"\u0089\u0005\t\u0000\u0000\u0084\u0085\u0003\u0004\u0002\u0000\u0085\u0086"+
		"\u0006\u0006\uffff\uffff\u0000\u0086\u0088\u0001\u0000\u0000\u0000\u0087"+
		"\u0084\u0001\u0000\u0000\u0000\u0088\u008b\u0001\u0000\u0000\u0000\u0089"+
		"\u0087\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a"+
		"\u0091\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0003\u0012\t\u0000\u008d\u008e\u0006\u0006\uffff\uffff\u0000\u008e"+
		"\u0090\u0001\u0000\u0000\u0000\u008f\u008c\u0001\u0000\u0000\u0000\u0090"+
		"\u0093\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091"+
		"\u0092\u0001\u0000\u0000\u0000\u0092\u0094\u0001\u0000\u0000\u0000\u0093"+
		"\u0091\u0001\u0000\u0000\u0000\u0094\u0095\u0005\n\u0000\u0000\u0095\u0096"+
		"\u0006\u0006\uffff\uffff\u0000\u0096\r\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0003\u0016\u000b\u0000\u0098\u0099\u0006\u0007\uffff\uffff\u0000\u0099"+
		"\u009d\u0001\u0000\u0000\u0000\u009a\u009b\u0005\f\u0000\u0000\u009b\u009d"+
		"\u0006\u0007\uffff\uffff\u0000\u009c\u0097\u0001\u0000\u0000\u0000\u009c"+
		"\u009a\u0001\u0000\u0000\u0000\u009d\u000f\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0006\b\uffff\uffff\u0000\u009f\u00a0\u0005\u0006\u0000\u0000\u00a0"+
		"\u00a1\u0003\u0010\b\u0000\u00a1\u00a2\u0005\u0007\u0000\u0000\u00a2\u00a3"+
		"\u0006\b\uffff\uffff\u0000\u00a3\u00cc\u0001\u0000\u0000\u0000\u00a4\u00a5"+
		"\u0005\u0006\u0000\u0000\u00a5\u00a6\u0003\u0016\u000b\u0000\u00a6\u00a7"+
		"\u0005\u0007\u0000\u0000\u00a7\u00a8\u0003\u0010\b\f\u00a8\u00a9\u0006"+
		"\b\uffff\uffff\u0000\u00a9\u00cc\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005"+
		"\u0010\u0000\u0000\u00ab\u00ac\u0003\u0010\b\u000b\u00ac\u00ad\u0006\b"+
		"\uffff\uffff\u0000\u00ad\u00cc\u0001\u0000\u0000\u0000\u00ae\u00af\u0005"+
		"\u0011\u0000\u0000\u00af\u00b0\u0003\u0010\b\n\u00b0\u00b1\u0006\b\uffff"+
		"\uffff\u0000\u00b1\u00cc\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005)\u0000"+
		"\u0000\u00b3\u00bf\u0005\u0006\u0000\u0000\u00b4\u00b5\u0003\u0010\b\u0000"+
		"\u00b5\u00bc\u0006\b\uffff\uffff\u0000\u00b6\u00b7\u0005\u0001\u0000\u0000"+
		"\u00b7\u00b8\u0003\u0010\b\u0000\u00b8\u00b9\u0006\b\uffff\uffff\u0000"+
		"\u00b9\u00bb\u0001\u0000\u0000\u0000\u00ba\u00b6\u0001\u0000\u0000\u0000"+
		"\u00bb\u00be\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000"+
		"\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000\u0000"+
		"\u00be\u00bc\u0001\u0000\u0000\u0000\u00bf\u00b4\u0001\u0000\u0000\u0000"+
		"\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c2\u0005\u0007\u0000\u0000\u00c2\u00cc\u0006\b\uffff\uffff\u0000"+
		"\u00c3\u00c4\u0005*\u0000\u0000\u00c4\u00cc\u0006\b\uffff\uffff\u0000"+
		"\u00c5\u00c6\u0005+\u0000\u0000\u00c6\u00cc\u0006\b\uffff\uffff\u0000"+
		"\u00c7\u00c8\u0005,\u0000\u0000\u00c8\u00cc\u0006\b\uffff\uffff\u0000"+
		"\u00c9\u00ca\u0005)\u0000\u0000\u00ca\u00cc\u0006\b\uffff\uffff\u0000"+
		"\u00cb\u009e\u0001\u0000\u0000\u0000\u00cb\u00a4\u0001\u0000\u0000\u0000"+
		"\u00cb\u00aa\u0001\u0000\u0000\u0000\u00cb\u00ae\u0001\u0000\u0000\u0000"+
		"\u00cb\u00b2\u0001\u0000\u0000\u0000\u00cb\u00c3\u0001\u0000\u0000\u0000"+
		"\u00cb\u00c5\u0001\u0000\u0000\u0000\u00cb\u00c7\u0001\u0000\u0000\u0000"+
		"\u00cb\u00c9\u0001\u0000\u0000\u0000\u00cc\u00ed\u0001\u0000\u0000\u0000"+
		"\u00cd\u00ce\n\t\u0000\u0000\u00ce\u00cf\u0007\u0000\u0000\u0000\u00cf"+
		"\u00d0\u0003\u0010\b\n\u00d0\u00d1\u0006\b\uffff\uffff\u0000\u00d1\u00ec"+
		"\u0001\u0000\u0000\u0000\u00d2\u00d3\n\b\u0000\u0000\u00d3\u00d4\u0007"+
		"\u0001\u0000\u0000\u00d4\u00d5\u0003\u0010\b\t\u00d5\u00d6\u0006\b\uffff"+
		"\uffff\u0000\u00d6\u00ec\u0001\u0000\u0000\u0000\u00d7\u00d8\n\u0007\u0000"+
		"\u0000\u00d8\u00d9\u0007\u0002\u0000\u0000\u00d9\u00da\u0003\u0010\b\b"+
		"\u00da\u00db\u0006\b\uffff\uffff\u0000\u00db\u00ec\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\n\u0006\u0000\u0000\u00dd\u00de\u0007\u0003\u0000\u0000\u00de"+
		"\u00df\u0003\u0010\b\u0007\u00df\u00e0\u0006\b\uffff\uffff\u0000\u00e0"+
		"\u00ec\u0001\u0000\u0000\u0000\u00e1\u00e2\n\u000e\u0000\u0000\u00e2\u00e3"+
		"\u0005\r\u0000\u0000\u00e3\u00e4\u0003\u0010\b\u0000\u00e4\u00e5\u0005"+
		"\u000e\u0000\u0000\u00e5\u00e6\u0006\b\uffff\uffff\u0000\u00e6\u00ec\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e8\n\r\u0000\u0000\u00e8\u00e9\u0005\u000f"+
		"\u0000\u0000\u00e9\u00ea\u0005)\u0000\u0000\u00ea\u00ec\u0006\b\uffff"+
		"\uffff\u0000\u00eb\u00cd\u0001\u0000\u0000\u0000\u00eb\u00d2\u0001\u0000"+
		"\u0000\u0000\u00eb\u00d7\u0001\u0000\u0000\u0000\u00eb\u00dc\u0001\u0000"+
		"\u0000\u0000\u00eb\u00e1\u0001\u0000\u0000\u0000\u00eb\u00e7\u0001\u0000"+
		"\u0000\u0000\u00ec\u00ef\u0001\u0000\u0000\u0000\u00ed\u00eb\u0001\u0000"+
		"\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u0011\u0001\u0000"+
		"\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005\u001e"+
		"\u0000\u0000\u00f1\u00f2\u0003\u0010\b\u0000\u00f2\u00f9\u0006\t\uffff"+
		"\uffff\u0000\u00f3\u00f4\u0005\u0001\u0000\u0000\u00f4\u00f5\u0003\u0010"+
		"\b\u0000\u00f5\u00f6\u0006\t\uffff\uffff\u0000\u00f6\u00f8\u0001\u0000"+
		"\u0000\u0000\u00f7\u00f3\u0001\u0000\u0000\u0000\u00f8\u00fb\u0001\u0000"+
		"\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000"+
		"\u0000\u0000\u00fa\u00fc\u0001\u0000\u0000\u0000\u00fb\u00f9\u0001\u0000"+
		"\u0000\u0000\u00fc\u00fd\u0005\u0003\u0000\u0000\u00fd\u013f\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u0005\u001f\u0000\u0000\u00ff\u0100\u0003\u0010"+
		"\b\u0000\u0100\u0107\u0006\t\uffff\uffff\u0000\u0101\u0102\u0005\u0001"+
		"\u0000\u0000\u0102\u0103\u0003\u0010\b\u0000\u0103\u0104\u0006\t\uffff"+
		"\uffff\u0000\u0104\u0106\u0001\u0000\u0000\u0000\u0105\u0101\u0001\u0000"+
		"\u0000\u0000\u0106\u0109\u0001\u0000\u0000\u0000\u0107\u0105\u0001\u0000"+
		"\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108\u010a\u0001\u0000"+
		"\u0000\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u010a\u010b\u0005\u0003"+
		"\u0000\u0000\u010b\u013f\u0001\u0000\u0000\u0000\u010c\u010d\u0003\u0010"+
		"\b\u0000\u010d\u010e\u0005 \u0000\u0000\u010e\u010f\u0003\u0010\b\u0000"+
		"\u010f\u0110\u0005\u0003\u0000\u0000\u0110\u0111\u0006\t\uffff\uffff\u0000"+
		"\u0111\u013f\u0001\u0000\u0000\u0000\u0112\u0113\u0005!\u0000\u0000\u0113"+
		"\u0114\u0003\u0010\b\u0000\u0114\u0115\u0005\u0002\u0000\u0000\u0115\u0116"+
		"\u0003\u0014\n\u0000\u0116\u0117\u0005\"\u0000\u0000\u0117\u0118\u0005"+
		"\u0002\u0000\u0000\u0118\u0119\u0003\u0014\n\u0000\u0119\u011a\u0006\t"+
		"\uffff\uffff\u0000\u011a\u013f\u0001\u0000\u0000\u0000\u011b\u011c\u0005"+
		"!\u0000\u0000\u011c\u011d\u0003\u0010\b\u0000\u011d\u011e\u0005\u0002"+
		"\u0000\u0000\u011e\u011f\u0003\u0014\n\u0000\u011f\u0120\u0006\t\uffff"+
		"\uffff\u0000\u0120\u013f\u0001\u0000\u0000\u0000\u0121\u0122\u0005#\u0000"+
		"\u0000\u0122\u0123\u0003\u0010\b\u0000\u0123\u0124\u0005\u0002\u0000\u0000"+
		"\u0124\u0125\u0003\u0014\n\u0000\u0125\u0126\u0006\t\uffff\uffff\u0000"+
		"\u0126\u013f\u0001\u0000\u0000\u0000\u0127\u0128\u0005$\u0000\u0000\u0128"+
		"\u0129\u0003\u0010\b\u0000\u0129\u012a\u0005\u0003\u0000\u0000\u012a\u012b"+
		"\u0006\t\uffff\uffff\u0000\u012b\u013f\u0001\u0000\u0000\u0000\u012c\u012d"+
		"\u0005)\u0000\u0000\u012d\u0139\u0005\u0006\u0000\u0000\u012e\u012f\u0003"+
		"\u0010\b\u0000\u012f\u0136\u0006\t\uffff\uffff\u0000\u0130\u0131\u0005"+
		"\u0001\u0000\u0000\u0131\u0132\u0003\u0010\b\u0000\u0132\u0133\u0006\t"+
		"\uffff\uffff\u0000\u0133\u0135\u0001\u0000\u0000\u0000\u0134\u0130\u0001"+
		"\u0000\u0000\u0000\u0135\u0138\u0001\u0000\u0000\u0000\u0136\u0134\u0001"+
		"\u0000\u0000\u0000\u0136\u0137\u0001\u0000\u0000\u0000\u0137\u013a\u0001"+
		"\u0000\u0000\u0000\u0138\u0136\u0001\u0000\u0000\u0000\u0139\u012e\u0001"+
		"\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a\u013b\u0001"+
		"\u0000\u0000\u0000\u013b\u013c\u0005\u0007\u0000\u0000\u013c\u013d\u0005"+
		"\u0003\u0000\u0000\u013d\u013f\u0006\t\uffff\uffff\u0000\u013e\u00f0\u0001"+
		"\u0000\u0000\u0000\u013e\u00fe\u0001\u0000\u0000\u0000\u013e\u010c\u0001"+
		"\u0000\u0000\u0000\u013e\u0112\u0001\u0000\u0000\u0000\u013e\u011b\u0001"+
		"\u0000\u0000\u0000\u013e\u0121\u0001\u0000\u0000\u0000\u013e\u0127\u0001"+
		"\u0000\u0000\u0000\u013e\u012c\u0001\u0000\u0000\u0000\u013f\u0013\u0001"+
		"\u0000\u0000\u0000\u0140\u0141\u0003\u0012\t\u0000\u0141\u0142\u0006\n"+
		"\uffff\uffff\u0000\u0142\u014e\u0001\u0000\u0000\u0000\u0143\u0149\u0005"+
		"\t\u0000\u0000\u0144\u0145\u0003\u0012\t\u0000\u0145\u0146\u0006\n\uffff"+
		"\uffff\u0000\u0146\u0148\u0001\u0000\u0000\u0000\u0147\u0144\u0001\u0000"+
		"\u0000\u0000\u0148\u014b\u0001\u0000\u0000\u0000\u0149\u0147\u0001\u0000"+
		"\u0000\u0000\u0149\u014a\u0001\u0000\u0000\u0000\u014a\u014c\u0001\u0000"+
		"\u0000\u0000\u014b\u0149\u0001\u0000\u0000\u0000\u014c\u014e\u0005\n\u0000"+
		"\u0000\u014d\u0140\u0001\u0000\u0000\u0000\u014d\u0143\u0001\u0000\u0000"+
		"\u0000\u014e\u0015\u0001\u0000\u0000\u0000\u014f\u0150\u0005%\u0000\u0000"+
		"\u0150\u0169\u0006\u000b\uffff\uffff\u0000\u0151\u0152\u0005&\u0000\u0000"+
		"\u0152\u0169\u0006\u000b\uffff\uffff\u0000\u0153\u0154\u0005\'\u0000\u0000"+
		"\u0154\u0169\u0006\u000b\uffff\uffff\u0000\u0155\u0156\u0005\r\u0000\u0000"+
		"\u0156\u0157\u0005*\u0000\u0000\u0157\u0158\u0005\u000e\u0000\u0000\u0158"+
		"\u0159\u0003\u0016\u000b\u0000\u0159\u015a\u0006\u000b\uffff\uffff\u0000"+
		"\u015a\u0169\u0001\u0000\u0000\u0000\u015b\u015c\u0005(\u0000\u0000\u015c"+
		"\u015d\u0005\t\u0000\u0000\u015d\u0161\u0006\u000b\uffff\uffff\u0000\u015e"+
		"\u015f\u0003\u0018\f\u0000\u015f\u0160\u0006\u000b\uffff\uffff\u0000\u0160"+
		"\u0162\u0001\u0000\u0000\u0000\u0161\u015e\u0001\u0000\u0000\u0000\u0162"+
		"\u0163\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0163"+
		"\u0164\u0001\u0000\u0000\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165"+
		"\u0166\u0005\n\u0000\u0000\u0166\u0167\u0006\u000b\uffff\uffff\u0000\u0167"+
		"\u0169\u0001\u0000\u0000\u0000\u0168\u014f\u0001\u0000\u0000\u0000\u0168"+
		"\u0151\u0001\u0000\u0000\u0000\u0168\u0153\u0001\u0000\u0000\u0000\u0168"+
		"\u0155\u0001\u0000\u0000\u0000\u0168\u015b\u0001\u0000\u0000\u0000\u0169"+
		"\u0017\u0001\u0000\u0000\u0000\u016a\u016b\u0005)\u0000\u0000\u016b\u0171"+
		"\u0006\f\uffff\uffff\u0000\u016c\u016d\u0005\u0001\u0000\u0000\u016d\u016e"+
		"\u0005)\u0000\u0000\u016e\u0170\u0006\f\uffff\uffff\u0000\u016f\u016c"+
		"\u0001\u0000\u0000\u0000\u0170\u0173\u0001\u0000\u0000\u0000\u0171\u016f"+
		"\u0001\u0000\u0000\u0000\u0171\u0172\u0001\u0000\u0000\u0000\u0172\u0174"+
		"\u0001\u0000\u0000\u0000\u0173\u0171\u0001\u0000\u0000\u0000\u0174\u0175"+
		"\u0005\u0002\u0000\u0000\u0175\u0176\u0003\u0016\u000b\u0000\u0176\u0177"+
		"\u0005\u0003\u0000\u0000\u0177\u0178\u0006\f\uffff\uffff\u0000\u0178\u0019"+
		"\u0001\u0000\u0000\u0000\u001b \"0>NQ^nv\u0089\u0091\u009c\u00bc\u00bf"+
		"\u00cb\u00eb\u00ed\u00f9\u0107\u0136\u0139\u013e\u0149\u014d\u0163\u0168"+
		"\u0171";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}