/**
 * @filename StandardDQL.sql
 * @description Sql - 数据查询语言
 * 标准查询
 * 查询列表
 * 查询过滤
 * 子查询
 * 模糊查询
 * 集合运算
 * 表连接
 * 连接过滤
 * 分组聚合
 * 分组过滤
 * 排序查询
 * @author qye.zheng
 * @version 1.0
 */


/*  */

/* ====================  ==================== */

/* 标准查询 */
SELECT DISTINCT view_field FROM tb_list WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) HAVING group_condition
ORDER BY field1 ASC, field2 DESC;

/* 取出查询结果所有列 */
SELECT * FROM tb_list;

/* ====================  ==================== */

/* 查询列表 */

/*
SELECT 所有的字段来源于表连接之后的结果集，是其子集.

*/

/* 列表去重 */
SELECT DISTINCT view_field FROM tb_list WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) HAVING group_condition
ORDER BY field1 ASC, field2 DESC;

/* CASE 语句 */
/* CASE WHEN -用法1 (不推荐)  */
(CASE var_name
	WHEN value_1 THEN result_1
	WHEN value_2 THEN result_2
	WHEN value_3 THEN result_3
	ELSE result_other
END)

/* CASE WHEN -用法2 (推荐) 可构建复杂条件 */
(CASE 
	WHEN boolean_exp_1 THEN result_1
	WHEN boolean_exp_2 THEN result_2
	WHEN boolean_exp_3 THEN result_3   
	ELSE result_other
END)

/* CASE语句可以用于 SELECT/WHERE/GROUP BY 等字句中 */

/* CASE WHEN-示例1 (构成单列) */
(CASE t.name
	WHEN  'a' THEN 'A_name'
	WHEN 'b' THEN 'B_name'
	ELSE 'C_name'
END) AS new_col_name

/* CASE WHEN-示例2 (构成单列) */
(CASE
	WHEN t.name = 'a' AND t.age > 20 THEN 'A_name'
	WHEN t.name = 'b' OR t.age < 30 THEN 'B_name'
	ELSE 'C_name'
END) AS new_col_name

/* 多列构成 新的一列 */

/* CASE WHEN-示例 生产商+供应商+零售商
t.a : 0-不是生产商，1-是生产商
t.b : 0-不是供应商，1-是供应商
t.c : 0-不是零售商，1-是零售商
 */
((CASE
	WHEN t.a = '1' THEN '生产商' 
	ELSE '') 
||
 (CASE 
	WHEN t.b = '1' and t.a = '1' THEN '+供应商'
	WHEN t.b = '1' and (t.a = '0' OR t.a IS NULL) THEN '供应商'
	ELSE '' ) 
 ||
 (CASE
	WHEN t.c = '1' and (t.a = '1' OR t.b = '1') THEN '+零售商'
	WHEN t.c = '1' THEN '零售商'
	ELSE '')) AS entType


/*
CASE WHEN-示例 地区人口统计
CREATE TABLE t_man (country varchar2(30), population number(5));
INSERT INTO t_man (country, population) VALUES ('中国', 125);
INSERT INTO t_man (country, population) VALUES ('美国', 60);
INSERT INTO t_man (country, population) VALUES ('印度', 110);
INSERT INTO t_man (country, population) VALUES ('加拿大', 10);
INSERT INTO t_man (country, population) VALUES ('日本', 40);
INSERT INTO t_man (country, population) VALUES ('墨西哥', 70);
通过该例子，可以这样理解group by的用法：group by 某个列，根据值是否相等来进行归类
*/
SELECT  
/* 统计符合条件的洲的人口 (统计结果依赖于 分组细节 group by) */
SUM(population) AS "人口",   
		/* 显示用 （非必须 与group by 中的case字句必须一致） */
        (CASE country      
                WHEN '中国'     THEN '亚洲'      
                WHEN '印度'     THEN '亚洲'      
                WHEN '日本'     THEN '亚洲'      
                WHEN '美国'     THEN '北美洲'      
                WHEN '加拿大'  THEN '北美洲'    
                WHEN '墨西哥'  THEN '北美洲'      
        ELSE '其他' END) AS "洲" FROM t_man
		/* 分组细节，决定分组如何组织 */
        GROUP BY
           (CASE country      
				/* 若满足该条件，则归到此类别-自定义的类别 */
                WHEN '中国'     THEN '亚洲'
                WHEN '印度'     THEN '亚洲'      
                WHEN '日本'     THEN '亚洲'      
                WHEN '美国'     THEN '北美洲'      
                WHEN '加拿大'  THEN '北美洲'      
                WHEN '墨西哥'  THEN '北美洲'      
        ELSE '其他' END); 




/* ====================  ==================== */

/* 查询过滤 */

SELECT DISTINCT view_field FROM tb_list WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) HAVING group_condition
ORDER BY field1 ASC, field2 DESC;

/* 
表连接完成之后，根据结果集进行过滤

*/

/*
where 子句 - 运算符
> >= 
< <= 
<> =
BETWEEN 
LIKE


*/

/* AND | OR | NOT */
SELECT DISTINCT view_filed FROM tb_list
WHERE filter_condtion AND | OR | NOT filter_condtion ...;

/* 注意: 不同的数据库对 BETWEEN 区间的开闭是不同的 */
/* NOT BETWEEN AND | BETWEEN AND */
SELECT DISTINCT view_filed FROM tb_list
WHERE field NOT BETWEEN xx AND yy;

/* NOT IN | IN */
SELECT DISTINCT view_filed FROM tb_list
WHERE field NOT IN (value_list);

/* NOT EXISTS | EXISTS */
SELECT DISTINCT view_filed FROM tb_list
WHERE NOT EXISTS (SELECT 1 FROM tb_list WHERE filter_condition_list);

/* IS NOT NULL | IS NULL */



/* ====================  ==================== */

/* 子查询 */
/* 
子查询类型：
单行子查询：返回0-1行结果
多行子查询：返回0-n行结果
*/

/* 多张表查询结果构成一张新表 */

SELECT DISTINCT view_field FROM tb_list, (SELECT * FROM tb_list) T WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) HAVING group_condition
ORDER BY field1 ASC, field2 DESC;


/* 关系分析 */
/* ANY-任意一个 */
/* 大于任意一个,即大于子查询的最小值即可 */
> ANY(多行子查询)

/* 小于任意一个,即小于子查询最大值即可 */
< ANY(多行子查询) 比子查询最小值还要小
= ANY(多行子查询)等价于 IN (多行子查询)

/* ALL-所有 */
/* 大于所有,大于子查询最大值 */
> ALL(多行子查询)

/* 小于所有,小于子查询最小值 */
< ALL(多行子查询)
= ALL(多行子查询) 等价于 IN (多行子查询)

/* SOME-某些
SOME与ANY的功能相同。 */

/* ====================  ==================== */

/* 模糊查询 */

/*
 _ 任意单个字符
 % 任意多个字符
[charList] 在字符列表中任意单一字符
[^charList] 或 [!charList] 不在字符列表中任意单一字符

 转义 : ESCAPT '/'
 /_
 /%
//
*/

SELECT DISTINCT view_field FROM tb_list WHERE field LIKE '' ESCAPE '/';

/* 转义: _ */
SELECT DISTINCT view_field FROM tb_list WHERE field LIKE '/_' ESCAPE '/';

/* 转义: % */
SELECT DISTINCT view_field FROM tb_list WHERE field LIKE '/%' ESCAPE '/';

/* 转义: / */
SELECT DISTINCT view_field FROM tb_list WHERE field LIKE '//' ESCAPE '/';

/* 前缀匹配 */
SELECT DISTINCT view_field FROM tb_list WHERE field LIKE 'value%' ESCAPE '/';

/* 后缀匹配 */
SELECT DISTINCT view_field FROM tb_list WHERE field LIKE '%value' ESCAPE '/';

/* 两端匹配 */
SELECT DISTINCT view_field FROM tb_list WHERE field LIKE '%value%' ESCAPE '/';

/* ====================  ==================== */

/* 集合运算 */

/* 集合运算前提条件 ： 集合之间保持列表一致 */
/* 集合运算-并集(重复) */
SELECT DISTINCT view_field FROM tb_list
UNION ALL 
SELECT DISTINCT view_field FROM tb_list

/* 集合运算-并集(去重) */
SELECT DISTINCT view_field FROM tb_list
UNION 
SELECT DISTINCT view_field FROM tb_list

/* 集合运算-交集 */
SELECT DISTINCT view_field FROM tb_list
INTERSECT 
SELECT DISTINCT view_field FROM tb_list

/* 集合运算-补集 */
SELECT DISTINCT view_field FROM tb_list
MINUS 
SELECT DISTINCT view_field FROM tb_list

/* ====================  ==================== */

/* 表连接(table join) */

/*

内连接: 2张表同时满足条件的记录才出现在结果集

外连接: 仅将左/右表所有记录查出，或将两边记录都查出;
外连接就是不仅仅返回2张表匹配的行，而且返回1或2张
表不匹配的行.

左连接: 左表没有找到和右表匹配的，则返回左表的行，
而该行对应的右表字段则用null填充.

 */

/* 内连接，INNER JOIN */
SELECT DISTINCT view_field FROM tb_1 INNER JOIN tb_2 ON (join_condidtion) 
WHERE filter_condtion_list;

/* 内连接，INNER JOIN - 简写 */
SELECT DISTINCT view_field FROM tb_1 JOIN tb_2 ON (join_condidtion) 
WHERE filter_condtion_list;

/* 自然连接:  */

/* 外连接 OUTER JOIN */
SELECT DISTINCT view_field FROM tb_1 LEFT | RIGHT OUTER JOIN tb_2 ON (join_condidtion) 
WHERE filter_condtion_list;

/* 外连接 OUTER JOIN - 简写 */
SELECT DISTINCT view_field FROM tb_1 LEFT | RIGHT JOIN tb_2 ON (join_condidtion) 
WHERE filter_condtion_list;

SELECT DISTINCT view_field FROM tb_1 FULL OUTER JOIN tb_2 ON (join_condidtion) 
WHERE filter_condtion_list;

SELECT DISTINCT view_field FROM tb_1 FULL JOIN tb_2 ON (join_condidtion) 
WHERE filter_condtion_list;

/* 交叉连接-笛卡尔积 */
SELECT DISTINCT view_field FROM tb_1 CROSS JOIN tb_2 ON (join_condidtion) 
WHERE filter_condtion_list;


/* ====================  ==================== */

/* 连接过滤 */


/* 

连接条件: 
除了自然连接，2张表需要各自用一个/多个字段进行一一对应的等值连接之外，
同时，在连接之前，2张表可以先进行内部的过滤，因此，建议将内部过滤写在
连接条件的最前面.

ON (内部连接条件... and|or 自然连接条件)

 */

SELECT DISTINCT view_field 
FROM tb_1 JOIN tb_2 ON (join_condidtion AND | OR condition...) 
WHERE filter_condtion_list;

/* ====================  ==================== */

/* 分组聚合 */

/*
单个字段
将多表连接、过滤之后的列表，参照A字段，
进行第1轮分组，列表中所有A字段值相同的，
都分配为一组，然后在组内可以对字段A进行
统计(COUNT)/求和(SUM)/均值(AVG)/最值(MIN/MAX)

多个字段
将多表连接、过滤之后的列表，参照多个字段，
其余原理和单个字段相同，规则就是只有在GROUP BY
后面出现的字段才能出现在查询结果列表中(直接以字段形式出现)
其他字段只能以聚合函数的参数形式出现

 */

/* 单个字段 */
SELECT column_a, COUNT(column_a) FROM tb_list
WHERE filter_condition_list
GROUP BY column_a;

/* 多个字段 */
SELECT column_a, column_b, COUNT(column_a, column_b) FROM tb_list
WHERE filter_condition_list
GROUP BY column_a, column_b;

/* ====================  ==================== */	

/* 分组过滤 */

/*
分组过滤条件:
不是根据单个/多个分组参照字段来过滤，而应该是以各个组的数据总特征
来进行过滤，可以使用聚合函数来参加过滤

*/

/* 简单分组条件过滤 */
SELECT column_a, COUNT(column_a) FROM tb_list
WHERE filter_condition_list
GROUP BY column_a
HAVING COUNT(column_a) > xx;

/* ====================  ==================== */	

/* 排序查询 */
SELECT DISTINCT view_field FROM tb_list WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) 
HAVING group_condition
ORDER BY field1 ASC, field2 DESC;

/* 单字段排序 */
SELECT DISTINCT view_field FROM tb_list WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) 
HAVING group_condition
ORDER BY field ASC | DESC;

/* 多字段排序 (顺序 决定 优先级) */
SELECT DISTINCT view_field FROM tb_list WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) 
HAVING group_condition
ORDER BY field1 ASC, field2 DESC;



/* ====================  ==================== */	

/*  */

/* ====================  ==================== */	

/*  */

/* ====================  ==================== */	

/*  */

