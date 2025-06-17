package com.luckercs.parser;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.jupiter.api.Test;

class ParserUtilTest {

    @Test
    void testParse() {
        String sql = "select id,name from testdb.mytbl where id>5;";
        CommonTokenStream commonTokenStream = ParserUtil.parseAllTokens(sql);

        ParserRuleContext ast = ParserUtil.toAst(commonTokenStream, CocolParser::compoundOrSingleStatement);

        String ast_str = ParserUtil.ast2Str(commonTokenStream, ast);
        System.out.println(ast_str);
    }
}