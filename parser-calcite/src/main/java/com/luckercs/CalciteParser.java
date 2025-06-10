package com.luckercs;

import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

public class CalciteParser {

    public static void main(String[] args) throws SqlParseException {
        SqlParser sqlParser = SqlParser.create("SELECT * FROM myTable");

        SqlNode sqlNode = sqlParser.parseStmt();
        System.out.println(sqlNode);
    }
}
