/**
 * @filename StandardView.sql
 * @description Sql - 视图
 * 创建视图
 * 删除视图
 * 修改视图
 * 查询视图
 * @author qye.zheng
 * @version 1.0
 */

/* ====================  ==================== */
/* 创建视图 */

CREATE VIEW view_name AS 
SELECT DISTINCT view_field FROM tb_list WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) HAVING group_condition
ORDER BY field1 ASC, field2 DESC;

/* ====================  ==================== */

/* 删除视图 */
DROP VIEW view_name;

/* ====================  ==================== */

/* 修改视图 */
ALTER VIEW view_name;

CREATE OR REPLACE VIEW view_name AS
SELECT DISTINCT view_field FROM tb_list WHERE filter_condtion_list 
GROUP BY (case_clause or group_field) HAVING group_condition
ORDER BY field1 ASC, field2 DESC;

/* ====================  ==================== */

/* 查询视图 */

SELECT v.* FROM view_name v;

/* ====================  ==================== */

/*  */

/* ====================  ==================== */

/*  */

/* ====================  ==================== */

/*  */


/* ====================  ==================== */

/*  */


