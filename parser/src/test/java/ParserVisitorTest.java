import com.luckercs.parser.CaseInsensitiveStream;
import com.luckercs.parser.CocolLexer;
import com.luckercs.parser.CocolParser;
import com.luckercs.parser.CocolParserBaseVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class ParserVisitorTest {

    @Test
    void testParserVisitor() {
        String sql = "select id,name from testdb.mytbl where id>5;";
        CocolLexer cocolLexer = new CocolLexer(new CaseInsensitiveStream(CharStreams.fromString(sql)));

        CommonTokenStream commonTokenStream = new CommonTokenStream(cocolLexer);
        CocolParser cocolParser = new CocolParser(commonTokenStream);

        ParseTree astTree = cocolParser.singleStatement();

        MyVisitor myVisitor = new MyVisitor();
        myVisitor.visit(astTree);
    }

    static class MyVisitor extends CocolParserBaseVisitor {
        @Override
        public Object visitSelectClause(CocolParser.SelectClauseContext ctx){
            System.out.println(ctx.namedExpressionSeq().getText().toString());
            return super.visitSelectClause(ctx);
        }
    }
}