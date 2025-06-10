import com.luckercs.parser.CaseInsensitiveStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class ParserVisitorTest {

    @Test
    void testParserVisitor() {
        String sql = "select id,name from testdb.mytbl where id>5;";
        SqlBaseLexer sqlBaseLexer = new SqlBaseLexer(new CaseInsensitiveStream(CharStreams.fromString(sql)));

        CommonTokenStream commonTokenStream = new CommonTokenStream(sqlBaseLexer);
        SqlBaseParser sqlBaseParser = new SqlBaseParser(commonTokenStream);

        ParseTree astTree = sqlBaseParser.singleStatement();

        MyVisitor myVisitor = new MyVisitor();
        myVisitor.visit(astTree);
    }

    static class MyVisitor extends SqlBaseParserBaseVisitor {
        @Override
        public Object visitSelectClause(SqlBaseParser.SelectClauseContext ctx){
            System.out.println(ctx.namedExpressionSeq().getText().toString());
            return super.visitSelectClause(ctx);
        }
    }
}