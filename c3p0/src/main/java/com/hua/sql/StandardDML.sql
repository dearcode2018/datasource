/**
 * @filename StandardDML.sql
 * @description Sql - 数据操作语言
 * 插入语句
 * 删除语句
 * 更新语句
 * @author qye.zheng
 * @version 1.0
 */


/* ====================  ==================== */

/* 插入语句 */

INSERT INTO tb_name (column_list) VALUES (value_list);

/* SELECT INTO */
/*
从一个表中取出数据，然后将数据插入另一个张表，
这2张表的表结构一致 或者 返回的列表需要和新表保持一致.
*/
SELECT * | view_field_list INTO tb_name_b
FROM tb_name_a
WHERE filter_condition_list;

/* ====================  ==================== */

/* 删除语句 */

/* 删除所有的记录 - 无条件删除 */
DELETE FROM tb_name;
/* 或者 */
DELETE * FROM tb_name;

/* 截断表-移除所有记录，不记日志, 不可回滚 */
TRUNCATE TABLE tb_name;

/* 删除语句 - 条件删除 */
DELETE FROM tb_name WHERE filter_condtion_list;

/* ====================  ==================== */

/* 更新语句 */

/* 更新所有 - 无条件更新 */
UPDATE tb_name SET assignment_list;

/* 更新语句 - 条件更新 */
UPDATE tb_name SET assignment_list WHERE filter_condtion_list;

/* 更新多个字段 */
UPDATE tb_name a SET a.fieldA = value, a.fieldB = value WHERE filter_condition_list;
/* ====================  ==================== */




/* ====================  ==================== */





/* ====================  ==================== */



/* ====================  ==================== */	



