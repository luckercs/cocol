
> sql 词法语法解析 转为 SqlNode 结构来进行承载
sql(string) --> SqlNode(Calcite SqlNode)

> 将 antlr ParserRuleContext（ast） 解析为 substrait 承载的逻辑计划
