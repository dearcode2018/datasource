/**
 * @filename StandardDDL.sql
 * @description Sql - 数据定义语言
 * 数据库操作
 * 数据表操作
 * 数据列操作
 * @author qye.zheng
 * @version 1.0
 */


/* ====================  ==================== */

/* 数据库操作 */

/* 创建数据库 */
CREATE DATABASE db_name;

/* 删除数据库 */
DROP DATABASE db_name;

/* 修改数据库 */
ALTER DATABASE db_name;

/* 查看数据库 */

/* ====================  ==================== */

/* 数据表操作 */

/* 创建表 */
CREATE TABLE tb_name (column_def_list);

/* 删除表 */
DROP TABLE tb_name;

/* 修改表 */
ALTER TABLE tb_name;

/* 查看表 */
DESCRIPTION tb_name;

/* ====================  ==================== */

/* 数据列操作 */

/* 添加列 */
ALTER TABLE tb_name ADD COLUMN column_defintion;

/* 删除列 */
ALTER TABLE tb_name DROP COLUMN column_name;

/* 修改列 */
ALTER TABLE tb_name ALTER COLUMN column_definition;

/* ====================  ==================== */

/*  */



/* ====================  ==================== */

/*  */

/* ====================  ==================== */

/*  */

/* ====================  ==================== */

/*  */

/* ====================  ==================== */

/*  */

/* ====================  ==================== */	

/* ====================  ==================== */

/*  */
