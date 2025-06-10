
（1）sql层解析生成抽象语法树
hive：antlr 
spark： antlr
flink： antlr
trino： antlr
doris：  JavaCC + JavaCUP(Bison 的 Java 移植版，更易定制sql)+JFlex 
clickhouse:  c++  定制
mysql:  Bison、Flex 
PostgreSQL：Bison、Flex 


hive、spark、flink、trino、doris

 选择建议
优先使用 ANTLR4：
如果项目基于 JVM、需要处理复杂语法、且希望快速迭代（如数据处理框架、新 SQL 方言）。
考虑 Bison + Flex：
如果项目使用 C/C++、对性能要求极高、且语法相对稳定（如传统数据库内核）。
使用 JavaCC + JavaCUP：
如果项目基于 Java、语法复杂度中等、且需要与其他 Java 组件无缝集成（如 Doris）。

 选择建议
优先使用 ANTLR4：
如果项目基于 JVM、需要处理复杂语法、且希望快速迭代（如数据处理框架、新 SQL 方言）。
考虑 Bison + Flex：
如果项目使用 C/C++、对性能要求极高、且语法相对稳定（如传统数据库内核）。
使用 JavaCC + JavaCUP：
如果项目基于 Java、语法复杂度中等、且需要与其他 Java 组件无缝集成（如 Doris）。

使用antlr，支持java、c++、go语言，更为通用，贴合主流开源引擎

（2）sql优化器，关联元数据进行校验后，对抽象语法树进行优化，生成物理计划树	
hive：Calcite
Spark：Catalyst

（3）物理计划树对应的通用算子实现
hive：Calcite
Spark：Catalyst
Flink：Blink Planner（Calcite）	
Trino：纯 RBO（手写规则）
Doris： 两层优化器（RBO + CBO）

（4）物理计划树各个算子对应的任务调度和执行
hive：Calcite
Spark：Catalyst