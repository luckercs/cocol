import com.luckercs.parser.CaseInsensitiveStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

class ParserListenerTest {

    @Test
    void testParserListener() {
        String sql = "select id, name from testdb.mytbl where id>5;";
        SqlBaseLexer sqlBaseLexer = new SqlBaseLexer(new CaseInsensitiveStream(CharStreams.fromString(sql)));

        CommonTokenStream commonTokenStream = new CommonTokenStream(sqlBaseLexer);
        SqlBaseParser sqlBaseParser = new SqlBaseParser(commonTokenStream);

        ParseTree astTree = sqlBaseParser.singleStatement();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        MyListener myListener = new MyListener();
        parseTreeWalker.walk(myListener, astTree);
    }

    class MyListener extends SqlBaseParserBaseListener {
        @Override
        public void enterFromClause(SqlBaseParser.FromClauseContext ctx) {

            for (SqlBaseParser.RelationContext relationContext : ctx.relation()) {
                String string = relationContext.getText().toString();
                System.out.println("===> " + string);
            }
        }
    }
}