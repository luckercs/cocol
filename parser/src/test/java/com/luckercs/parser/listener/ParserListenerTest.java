package com.luckercs.parser.listener;

import com.luckercs.parser.CaseInsensitiveStream;
import com.luckercs.parser.CocolLexer;
import com.luckercs.parser.CocolParser;
import com.luckercs.parser.CocolParserBaseListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class ParserListenerTest {

    @Test
    void testParserListener() {
        String sql = "select id, name from testdb.mytbl where id>5;";
        CocolLexer cocolLexer = new CocolLexer(new CaseInsensitiveStream(CharStreams.fromString(sql)));

        CommonTokenStream commonTokenStream = new CommonTokenStream(cocolLexer);
        CocolParser cocolParser = new CocolParser(commonTokenStream);
        cocolParser.addParseListener(new MyListener());

        ParseTree astTree = cocolParser.singleStatement();
        System.out.println(astTree);
    }

    class MyListener extends CocolParserBaseListener {
        @Override
        public void exitFromClause(CocolParser.FromClauseContext ctx) {
            for (CocolParser.RelationContext relationContext : ctx.relation()) {
                String string = relationContext.getText().toString();
                System.out.println("===> " + string);
            }
        }

    }
}