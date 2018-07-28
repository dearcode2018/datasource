/**
 * 描述: 
 * ViburDbcpTest.java
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

import org.junit.Ignore;
import org.junit.Test;
import org.vibur.dbcp.ViburDBCPDataSource;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ViburDbcpTest
 */
public final class ViburDbcpTest extends BaseTest {

	

	/*
		connection-1: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-2: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-3: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-4: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-5: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-6: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-7: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-8: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-9: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-10: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-11: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-12: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-13: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-14: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-15: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-16: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-17: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-18: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-19: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-20: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-21: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-22: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-23: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-24: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-25: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-26: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-27: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-28: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-29: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-30: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-31: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-32: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-33: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-34: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-35: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-36: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-37: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-38: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-39: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-40: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-41: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-42: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-43: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-44: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-45: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-46: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-47: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-48: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-49: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f
		connection-50: Vibur proxy for: com.mysql.cj.jdbc.ConnectionImpl@5c18298f	 
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
			ViburDBCPDataSource dataSource = new ViburDBCPDataSource();
			
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
			dataSource.setDefaultAutoCommit(false);
			// 连接是否默认为只读
			dataSource.setDefaultReadOnly(false);
			// 连接的默认事务隔离级别
			dataSource.setDefaultTransactionIsolationIntValue(Connection.TRANSACTION_REPEATABLE_READ);
			// 从数据库获取连接重试次数
			dataSource.setAcquireRetryAttempts(3);
			// 重试的延迟时间
			dataSource.setAcquireRetryDelayInMs(1 * 1000);
			/** 连接数设置，负数-不限制 */
			// 连接池初始化连接数
			dataSource.setPoolInitialSize(3);
			// 最大活动连接数(活动: 正在被客户端使用)
			dataSource.setPoolMaxSize(5);
			/*
			 *  连接的最大存活毫秒数，超时则在下次激活、钝化、校验时都将失败，
			 *  0/负数是无限的
			 */
			// 是否关闭sql警告
			dataSource.setClearSQLWarnings(false);
			// 连接超时时间
			dataSource.setConnectionTimeoutInMs(5 * 1000);
			// 连接空闲最大时间 秒
			dataSource.setConnectionIdleLimitInSeconds(30);
			// 是否允许jmx
			dataSource.setEnableJMX(true);
			// 是否超时输出日志
			dataSource.setLogAllStackTracesOnTimeout(true);
			// 是否开启公平机制
			dataSource.setPoolFair(false);
			// 是否使用网络超时
			//dataSource.setUseNetworkTimeout(true);
			// 是否网络超时功能 必须指定超时执行器
			//dataSource.setNetworkTimeoutExecutor(null);
			// 校验超时时间，秒
			dataSource.setValidateTimeoutInSeconds(5);
			// 预处理语句池的大小
			dataSource.setStatementCacheMaxSize(100);
			
			// 启动 Vibur DBCP
			dataSource.start();
			
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
