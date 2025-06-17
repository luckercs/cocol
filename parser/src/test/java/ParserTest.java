import com.luckercs.parser.CaseInsensitiveStream;
import com.luckercs.parser.CocolLexer;
import com.luckercs.parser.CocolParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void testParser() {
        String sql = "select id,name from testdb.mytbl where id>5;";
        CocolLexer cocolLexer = new CocolLexer(new CaseInsensitiveStream(CharStreams.fromString(sql)));

        CommonTokenStream commonTokenStream = new CommonTokenStream(cocolLexer);
        CocolParser cocolParser = new CocolParser(commonTokenStream);

        // sql --> ParseTree
        CocolParser.CompoundOrSingleStatementContext astTree = cocolParser.compoundOrSingleStatement();
        ParseTree astTree2 = cocolParser.compoundOrSingleStatement();

        System.out.println(astTree.toStringTree());
        System.out.println("==========");
        System.out.println(astTree.toStringTree(cocolParser));
    }
}