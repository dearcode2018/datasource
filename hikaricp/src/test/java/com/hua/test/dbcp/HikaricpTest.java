/**
 * 描述: 
 * HikaricpTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.dbcp;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.zaxxer.hikari.HikariDataSource;


/**
 * 描述: 数据连接池 - 测试
 * 
 * @author qye.zheng
 * HikaricpTest
 */
public final class HikaricpTest extends BaseTest {

	
	/**
	 * 连接池-基本概念
	 * 
	 * 释放: 将连接归还数据库. 
	 *
	 * 创建: 从数据库获取连接
	 * 
	 * 租借: 指客户端从连接池获取连接
	 * 
	 * DBCP专有
	 * 驱逐线程: 驱逐符合条件的空闲连接的线程
	 * 
	 */
	/*
		connection-1: HikariProxyConnection@592688102 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-2: HikariProxyConnection@632249781 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-3: HikariProxyConnection@19717364 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-4: HikariProxyConnection@1540270363 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-5: HikariProxyConnection@1597655940 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-6: HikariProxyConnection@2619171 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-7: HikariProxyConnection@1728790703 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-8: HikariProxyConnection@1227074340 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-9: HikariProxyConnection@1154002927 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-10: HikariProxyConnection@2070529722 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-11: HikariProxyConnection@1188753216 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-12: HikariProxyConnection@317986356 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-13: HikariProxyConnection@331510866 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-14: HikariProxyConnection@640363654 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-15: HikariProxyConnection@924477420 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-16: HikariProxyConnection@99451533 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-17: HikariProxyConnection@84739718 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-18: HikariProxyConnection@2050835901 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-19: HikariProxyConnection@511473681 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-20: HikariProxyConnection@2011986105 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-21: HikariProxyConnection@439904756 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-22: HikariProxyConnection@171497379 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-23: HikariProxyConnection@2012846597 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-24: HikariProxyConnection@1665404403 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-25: HikariProxyConnection@988458918 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-26: HikariProxyConnection@1990451863 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-27: HikariProxyConnection@1295083508 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-28: HikariProxyConnection@249155636 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-29: HikariProxyConnection@1629604310 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-30: HikariProxyConnection@142555199 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-31: HikariProxyConnection@1320677379 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-32: HikariProxyConnection@246399377 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-33: HikariProxyConnection@1630521067 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-34: HikariProxyConnection@274773041 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-35: HikariProxyConnection@1629911510 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-36: HikariProxyConnection@292917034 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-37: HikariProxyConnection@242355057 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-38: HikariProxyConnection@455538610 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-39: HikariProxyConnection@1226622409 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-40: HikariProxyConnection@1957502751 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-41: HikariProxyConnection@1780132728 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-42: HikariProxyConnection@1177377518 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-43: HikariProxyConnection@1773206895 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-44: HikariProxyConnection@1970881185 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-45: HikariProxyConnection@1250391581 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-46: HikariProxyConnection@1725017993 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-47: HikariProxyConnection@140799417 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-48: HikariProxyConnection@926370398 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-49: HikariProxyConnection@1181869371 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c
		connection-50: HikariProxyConnection@767010715 wrapping com.mysql.cj.jdbc.ConnectionImpl@631330c

	 */
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDbcp() {
		try {
			HikariDataSource dataSource = new HikariDataSource();
			/** 固定参数 */
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai");
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			
			/** 可调参数 */
			/*
			 * 连接关闭自动提交(true)/回滚(false): false
			 * 需要在 Connection对象中单独设置
			 */
			dataSource.setAutoCommit(false);
			// 连接是否默认为只读
			dataSource.setReadOnly(false);
			// 连接的默认事务隔离级别
			dataSource.setTransactionIsolation("TRANSACTION_REPEATABLE_READ");
		
			/*
			 * 缓存状态: 开启则以后只读取缓存的 自动提交/只读 设置
			 * 若直接访问底层连接，则禁用该属性
			 */
			dataSource.setConnectionInitSql(null);
			// 超时时间
			dataSource.setConnectionTimeout(10 * 1000);
			/** 连接数设置，负数-不限制 */
			// 最大活动连接数(活动: 正在被客户端使用)
			dataSource.setMaximumPoolSize(5);
			
			// 空闲超时时间
			dataSource.setIdleTimeout(20 * 1000);

			/*
			 *  最小空闲连接数，低于该值，空闲连接将被创建
			 *  前提: 驱逐线程必须在运行，设置timeBetweenEvictionRunsMillis为正数
			 */
			dataSource.setMinimumIdle(4);
			// 连接的校验语句
			dataSource.setConnectionTestQuery("SELECT 1");
			

			// 校验语句的查询超时时间，创建Statement时设置. 秒
			dataSource.setValidationTimeout(10 * 1000);
		
			/*
			 *  连接的最大存活毫秒数，超时则在下次激活、钝化、校验时都将失败，
			 *  0/负数是无限的
			 */
			dataSource.setMaxLifetime(20 * 1000);
			
			Connection connection = null;
			PreparedStatement ps = null;
			String sql = "select * from person";			
			
			/*			connection = dataSource.getConnection();
			System.out.println("connection: " + connection.toString());
			ps = connection.prepareStatement(sql);
			
			connection = dataSource.getConnection();
			System.out.println("connection: " + connection.toString());
			ps = connection.prepareStatement(sql);*/
			
			//DelegatingConnection<Connection> a = null;
			/**
			 * 超过最大连接数，抛异常:
			 * java.sql.SQLException: An attempt by a client to checkout a 
			 * Connection has timed out.
			 */
			for (int i = 0; i < 50; i++)
			{
				connection = dataSource.getConnection();
				// 设置连接自动提交
				connection.setAutoCommit(false);
				// metaData对象
				//System.out.println("metaData: " +  connection.getMetaData().toString());
				System.out.println("connection-" + (i + 1) + ": " + connection);
				ps = connection.prepareStatement(sql);
				// 查询超时时间，秒
				ps.setQueryTimeout(5);
				connection.commit();
				connection.close();
			}
			
			dataSource.close();
		} catch (Exception e) {
			log.error("testDbcp =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
