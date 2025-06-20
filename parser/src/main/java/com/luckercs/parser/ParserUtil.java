package com.luckercs.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.function.Function;

public class ParserUtil {

    public static CocolParser getCocolParser(CommonTokenStream tokenStream) {
        CocolParser parser = new CocolParser(tokenStream);
        parser.double_quoted_identifiers = false;
        // parser.addParseListener();
        // parser.removeErrorListeners();
        // parser.addErrorListener();
        return parser;
    }

    public static CommonTokenStream parseAllTokens(String sql) {
        CocolLexer lexer = new CocolLexer(new CaseInsensitiveStream(CharStreams.fromString(sql)));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill();
        return tokenStream;
    }

    public static ParserRuleContext toAst(CommonTokenStream tokenStream, Function<CocolParser, ParserRuleContext> parseFunction) {
        CocolParser parser = getCocolParser(tokenStream);
        ParserRuleContext tree;
        try {
            parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
            tree = parseFunction.apply(parser);
        } catch (ParseCancellationException ex) {
            tokenStream.seek(0);
            parser.reset();

            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
            tree = parseFunction.apply(parser);
        }
        return tree;
    }

    public static String ast2Str(CocolParser parser,  ParserRuleContext parserRuleContext) {
        return parserRuleContext.toStringTree(parser);
    }
}
