import com.luckercs.parser.CaseInsensitiveStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void testParser() {
        String sql = "select id,name from testdb.mytbl where id>5;";
        SqlBaseLexer sqlBaseLexer = new SqlBaseLexer(new CaseInsensitiveStream(CharStreams.fromString(sql)));

        CommonTokenStream commonTokenStream = new CommonTokenStream(sqlBaseLexer);
        SqlBaseParser sqlBaseParser = new SqlBaseParser(commonTokenStream);

        // sql --> ParseTree
        SqlBaseParser.CompoundOrSingleStatementContext astTree = sqlBaseParser.compoundOrSingleStatement();
        ParseTree astTree2 = sqlBaseParser.compoundOrSingleStatement();

        System.out.println(astTree.toStringTree());
        System.out.println("==========");
        System.out.println(astTree.toStringTree(sqlBaseParser));
    }
}