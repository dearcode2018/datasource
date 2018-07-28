/**
 * 描述: 
 * TomcatDBCPTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.datasource;

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

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * TomcatDBCPTest
 */
public final class TomcatDBCPTest extends BaseTest {

	/*
		connection-1: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-2: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-3: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-4: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-5: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-6: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-7: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-8: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-9: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-10: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-11: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-12: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-13: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-14: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-15: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-16: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-17: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-18: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-19: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-20: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-21: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-22: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-23: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-24: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-25: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-26: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-27: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-28: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-29: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-30: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-31: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-32: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-33: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-34: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-35: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-36: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-37: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-38: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-39: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-40: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-41: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-42: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-43: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-44: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-45: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-46: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-47: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]
		connection-48: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@1f7030a6]]
		connection-49: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@57855c9a]]
		connection-50: ProxyConnection[PooledConnection[com.mysql.cj.jdbc.ConnectionImpl@184f6be2]]

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
			// org.apache.tomcat.jdbc.pool.DataSource
			DataSource dataSource = new DataSource();
			/** 固定参数 */
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai");
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			
			/** 可调参数 */
			/*
			 * 连接关闭自动提交(true)/回滚(false): false
			 * 需要在 Connection对象中单独设置
			 */
			dataSource.setDefaultAutoCommit(false);
			// 连接是否默认为只读
			dataSource.setDefaultReadOnly(false);
			// 连接的默认事务隔离级别
			dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			// mysq/oralce不支持，sqlserver值为数据库名
			//dataSource.setDefaultCatalog(null);


			/*
			 * 连接归还连接池时是否执行rollback()
			 * 前提是 autoCommit=true且非只读
			 */
			dataSource.setRollbackOnReturn(false);
			/** 连接数设置，负数-不限制 */
			// 连接池初始化连接数
			dataSource.setInitialSize(3);
			// 最大活动连接数(活动: 正在被客户端使用)
			dataSource.setMaxActive(5);
			/*
			 *  最大空闲连接数 (空闲: 没有被客户端使用的连接)
			 *  在访问负载较高时，最大空闲值应该调大，以避免
			 *  频繁释放和创建(释放: 将连接归还数据库. 创建: 从数据库获取连接)
			 */
			dataSource.setMaxIdle(10);
			/*
			 *  最小空闲连接数，低于该值，空闲连接将被创建
			 *  前提: 驱逐线程必须在运行，设置timeBetweenEvictionRunsMillis为正数
			 */
			dataSource.setMinIdle(4);
			/*
			 *  从连接池获取连接的最大等待时间: 毫秒数
			 *  负数表示一直等待
			 */
			dataSource.setMaxWait(3 * 1000);
			// 连接的校验语句
			dataSource.setValidationQuery("SELECT 1");
			// 校验语句的查询超时时间，创建Statement时设置. 秒
			dataSource.setValidationQueryTimeout(10);
			// 是否客户端向连接池租借连接时检查有效性
			dataSource.setTestOnBorrow(true);
			// 是否客户端向连接池归还连接时检查有效性
			dataSource.setTestOnReturn(true);
			// 是否连接空闲时检查有效性
			dataSource.setTestWhileIdle(true);
			// 驱逐线程休眠的毫秒数 (工作一次过后休息的时间)
			dataSource.setTimeBetweenEvictionRunsMillis(10 * 1000);
			// 每个驱逐线程检查的连接数
			dataSource.setNumTestsPerEvictionRun(3);
			// 空闲连接最少多少毫秒后才会被释放 (条件1)
			dataSource.setMinEvictableIdleTimeMillis(10 * 1000);
			// 可抛弃连接可以被移除的超时时间 秒
			dataSource.setRemoveAbandonedTimeout(300);
			// 是否为抛弃语句、连接开启日志
			dataSource.setLogAbandoned(true);
			
			// 初始化执行的sql集合
			//dataSource.setInitSQL("");
			// 是否开启公平机制
			dataSource.setFairQueue(false);
			// 是否允许jmx
			dataSource.setJmxEnabled(true);
			
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
