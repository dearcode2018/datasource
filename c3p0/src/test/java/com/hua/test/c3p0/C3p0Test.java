/**
 * 描述: 
 * C3p0Test.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.c3p0;

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

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 描述: C3P0 连接池 - 测试
 * 
 * @author qye.zheng
 * C3p0Test
 */
public final class C3p0Test extends BaseTest {

	/* javax.sql.DataSource 数据源 */
	private static DataSource dataSource;
	
	
	/*
	 *
	 NewProxyConnection对象每次都是新的，而包装的真正连接对象会重复出现，
	 最多为maxPoolSize个，代理的Connection对象(NewProxyConnection)
	 调用close()方法把真实的连接对象即ConnectionImpl放回连接池中.
	 
	connection-1: com.mchange.v2.c3p0.impl.NewProxyConnection@2286778 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-2: com.mchange.v2.c3p0.impl.NewProxyConnection@4b4523f8 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-3: com.mchange.v2.c3p0.impl.NewProxyConnection@7791a895 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-4: com.mchange.v2.c3p0.impl.NewProxyConnection@2b9627bc [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-5: com.mchange.v2.c3p0.impl.NewProxyConnection@61f8bee4 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-6: com.mchange.v2.c3p0.impl.NewProxyConnection@7fac631b [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-7: com.mchange.v2.c3p0.impl.NewProxyConnection@5bc79255 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-8: com.mchange.v2.c3p0.impl.NewProxyConnection@3dd4520b [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-9: com.mchange.v2.c3p0.impl.NewProxyConnection@43814d18 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-10: com.mchange.v2.c3p0.impl.NewProxyConnection@2758fe70 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-11: com.mchange.v2.c3p0.impl.NewProxyConnection@551aa95a [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-12: com.mchange.v2.c3p0.impl.NewProxyConnection@6ebc05a6 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-13: com.mchange.v2.c3p0.impl.NewProxyConnection@3cef309d [wrapping: com.mysql.cj.jdbc.ConnectionImpl@7562f8b8]
	connection-14: com.mchange.v2.c3p0.impl.NewProxyConnection@3a883ce7 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-15: com.mchange.v2.c3p0.impl.NewProxyConnection@79be0360 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-16: com.mchange.v2.c3p0.impl.NewProxyConnection@3b084709 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-17: com.mchange.v2.c3p0.impl.NewProxyConnection@1efed156 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-18: com.mchange.v2.c3p0.impl.NewProxyConnection@a7e666 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-19: com.mchange.v2.c3p0.impl.NewProxyConnection@7494e528 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-20: com.mchange.v2.c3p0.impl.NewProxyConnection@9660f4e [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-21: com.mchange.v2.c3p0.impl.NewProxyConnection@5e853265 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-22: com.mchange.v2.c3p0.impl.NewProxyConnection@5d76b067 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@7562f8b8]
	connection-23: com.mchange.v2.c3p0.impl.NewProxyConnection@1e6d1014 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@7562f8b8]
	connection-24: com.mchange.v2.c3p0.impl.NewProxyConnection@1f554b06 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-25: com.mchange.v2.c3p0.impl.NewProxyConnection@131276c2 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-26: com.mchange.v2.c3p0.impl.NewProxyConnection@711f39f9 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-27: com.mchange.v2.c3p0.impl.NewProxyConnection@51cdd8a [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-28: com.mchange.v2.c3p0.impl.NewProxyConnection@2d6eabae [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-29: com.mchange.v2.c3p0.impl.NewProxyConnection@10bbd20a [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-30: com.mchange.v2.c3p0.impl.NewProxyConnection@184f6be2 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-31: com.mchange.v2.c3p0.impl.NewProxyConnection@5a1c0542 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-32: com.mchange.v2.c3p0.impl.NewProxyConnection@27a5f880 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@7562f8b8]
	connection-33: com.mchange.v2.c3p0.impl.NewProxyConnection@167fdd33 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-34: com.mchange.v2.c3p0.impl.NewProxyConnection@53f65459 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-35: com.mchange.v2.c3p0.impl.NewProxyConnection@74650e52 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-36: com.mchange.v2.c3p0.impl.NewProxyConnection@4b1c1ea0 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-37: com.mchange.v2.c3p0.impl.NewProxyConnection@3712b94 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@7562f8b8]
	connection-38: com.mchange.v2.c3p0.impl.NewProxyConnection@27a8c74e [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-39: com.mchange.v2.c3p0.impl.NewProxyConnection@646d64ab [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-40: com.mchange.v2.c3p0.impl.NewProxyConnection@e320068 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-41: com.mchange.v2.c3p0.impl.NewProxyConnection@704a52ec [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-42: com.mchange.v2.c3p0.impl.NewProxyConnection@2781e022 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@7562f8b8]
	connection-43: com.mchange.v2.c3p0.impl.NewProxyConnection@1877ab81 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-44: com.mchange.v2.c3p0.impl.NewProxyConnection@11438d26 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-45: com.mchange.v2.c3p0.impl.NewProxyConnection@5faeada1 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
	connection-46: com.mchange.v2.c3p0.impl.NewProxyConnection@1563da5 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@72f58e8b]
	connection-47: com.mchange.v2.c3p0.impl.NewProxyConnection@33c7e1bb [wrapping: com.mysql.cj.jdbc.ConnectionImpl@7562f8b8]
	connection-48: com.mchange.v2.c3p0.impl.NewProxyConnection@7a765367 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@29e87496]
	connection-49: com.mchange.v2.c3p0.impl.NewProxyConnection@3043fe0e [wrapping: com.mysql.cj.jdbc.ConnectionImpl@3d0d95d1]
	connection-50: com.mchange.v2.c3p0.impl.NewProxyConnection@2f943d71 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@58b81d03]
		 
	 */
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testC3p0() {
		try {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			/** 固定参数 */
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai");
			dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
			dataSource.setDataSourceName("mysqlDataSource");
			
			/** 可调参数 */
			/*
			 * 连接关闭自动提交(true)/回滚(false): false, c3p0该属性好像没起作用
			 * 需要在 Connection对象中单独设置
			 */
			dataSource.setAutoCommitOnClose(false);
			// 连接池中连接即将耗尽时而未到最大数量，c3p0获取的连接数
			dataSource.setAcquireIncrement(3);
			// 从数据库获取新连接失败后重复尝试的次数
			dataSource.setAcquireRetryAttempts(30);
			// 2次连接的间隔毫秒数
			dataSource.setAcquireRetryDelay(1000);
			/*
			 * 指定创建表名，c3p0将在该表进行测试
			 * 若指定了该属性，则preferredTestQuery属性将被忽略
			 * 用户不能再此表上进行任何操作.
			 */
			//dataSource.setAutomaticTestTable("t_test_c3p0");
			/*
			 * false: (默认)
			 * 获取连接失败将会引起所有等待连接池获取连接的线程失败，但
			 * 数据源仍有效保留，并在下次调用getConnection()时继续尝试获取连接
			 * 
			 * true:
			 * 在尝试连接失败后该数据源将申明已断开并永久关闭.
			 * 
			 * 该属性意义: 一次性判断数据源是否可用
			 */
			dataSource.setBreakAfterAcquireFailure(false);
			
			/*
			 * 指定实现了ConnectionTester或QueryConnectionTester的测试类
			 * 默认: com.mchange.v2.c3p0.impl.DefaultConnectionTester
			 */
			//dataSource.setConnectionTesterClassName("com.hua.connection.MyConnectionTester");
			
			// 间隔多少秒检查连接池中的空闲连接
			dataSource.setIdleConnectionTestPeriod(60);			
			// 连接池最大连接数
			dataSource.setMaxPoolSize(5);
			// 连接初始连接数，值在 min和max之间
			dataSource.setInitialPoolSize(3);
			// 连接池最小连接数
			dataSource.setMinPoolSize(2);
			/*
			 * 最大空闲时间: 秒. 超过时间则连接被丢弃，从连接池中移除
			 * 恢复到 minPoolSize数量的连接
			 * 为0则永不丢弃
			 */
			dataSource.setMaxIdleTime(5000);
			// 最大语句数 PreparedStatement 数量 0-缓存关闭
			dataSource.setMaxStatements(5);
			// 单个连接所拥有的最大缓存statement数量
			dataSource.setMaxStatementsPerConnection(2);
			
			//dataSource.setMaxConnectionAge(0);
			// 连接的测试语句
			dataSource.setPreferredTestQuery("SELECT 1");
			// 取得连接的同时将检查连接的有效性
			dataSource.setTestConnectionOnCheckin(true);
			/*
			 * 连接提交的时候检查其有效性 | 性能消耗大，仅在需要的时候开启
			 * 通常是通过idleConnectionTestPeriod或automaticTestTable等方法
			 * 来提升连接测试的性能
			 */
			dataSource.setTestConnectionOnCheckout(false);
			// 异步帮助线程的数量，可以提升执行效率
			dataSource.setNumHelperThreads(3);
			// 连接池用完时，客户端从连接池获取连接的等待时间，毫秒数，0-表示无限期等待
			dataSource.setCheckoutTimeout(5000);
			
			
			Connection connection = null;
			PreparedStatement ps = null;
			String sql = "select * from person";
			
/*			connection = dataSource.getConnection();
			System.out.println("connection: " + connection.toString());
			ps = connection.prepareStatement(sql);
			
			connection = dataSource.getConnection();
			System.out.println("connection: " + connection.toString());
			ps = connection.prepareStatement(sql);*/
			
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
				System.out.println("connection-" + (i + 1) + ": " + connection.toString());
				ps = connection.prepareStatement(sql);
				connection.commit();
				connection.close();
			}
			
		} catch (Exception e) {
			log.error("testC3p0 =====> ", e);
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
