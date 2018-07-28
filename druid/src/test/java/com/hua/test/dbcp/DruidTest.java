/**
 * 描述: 
 * DruidTest.java
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

import com.alibaba.druid.pool.DruidDataSource;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * DruidTest
 */
public final class DruidTest extends BaseTest {

	
	/*
		connection-1: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-2: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-3: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-4: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-5: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-6: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-7: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-8: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-9: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-10: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-11: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-12: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-13: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-14: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-15: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-16: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-17: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-18: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-19: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-20: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-21: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-22: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-23: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-24: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-25: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-26: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-27: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-28: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-29: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-30: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-31: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-32: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-33: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-34: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-35: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-36: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-37: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-38: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-39: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-40: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-41: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-42: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-43: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-44: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-45: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-46: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-47: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-48: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-49: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
		connection-50: com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737	
	 */
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDruid() {
		try {
			DruidDataSource dataSource = new DruidDataSource();
			/** 固定参数 */
			dataSource.setName("mysqlDataSource");
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai");
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			
			/** 可调参数 */
			/*
			 * 连接关闭自动提交(true)/回滚(false): false
			 * 需要在 Connection对象中单独设置
			 */
			// 连接关闭自动提交: false
			dataSource.setDefaultAutoCommit(false);
			dataSource.setDefaultReadOnly(false);
			
			// 连接池最大活动连接数
			dataSource.setMaxActive(5);
			// 连接初始连接数
			dataSource.setInitialSize(3);
			// 最小空闲数量
			dataSource.setMinIdle(3);
			/*
			 *  最大等待时间 毫秒，设置之后默认启用公平锁，并发效率有所降低
			 *  设置useUnfairLock为trure使用非公平锁
			 */
			dataSource.setMaxWait(5000);
			// 是否使用非公平锁
			dataSource.setUseUnfairLock(true);
			/*
			 *  是否缓存PrepareStatement
			 *  PSCache对支持游标的数据库性能提升巨大，例如oralce.
			 *  mysql下建议关闭
			 */
			dataSource.setPoolPreparedStatements(false);
			// 最大PSCache数量，大于0会自动开启PoolPreparedStatements 
			//dataSource.setMaxOpenPreparedStatements(100);
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
			// 默认查询超时时间，创建Statement时设置，秒
			dataSource.setQueryTimeout(60);
			// 查询超时时间 秒
			dataSource.setQueryTimeout(10);
			dataSource.setOracle(false);
			// 空闲时检查
			dataSource.setTestWhileIdle(true);
			// 驱逐线程休眠的毫秒数 (工作一次过后休息的时间)
			dataSource.setTimeBetweenEvictionRunsMillis(10 * 1000);
			// 空闲连接最少多少毫秒后才会被释放 (条件1)
			dataSource.setMinEvictableIdleTimeMillis(10 * 1000);
			// 初始化执行的sql集合
			//dataSource.setConnectionInitSqls(null);
			/*
			 *  当数据库抛出不可恢复的异常时，抛弃连接
			 *  实现 com.alibaba.druid.pool.ExceptionSorter 接口的类路径
			 */
			//dataSource.setExceptionSorter("");
			// 是否开启快速校验是失败
			dataSource.setFailFast(true);
			// 拦截器插件: filter:stat | filter:wall
			dataSource.setFilters("stat");
			// com.alibaba.druid.filter.Filter拦截器集合
			//dataSource.setProxyFilters(null);
			
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
				/*
				 * 输出的对象内容都相同，多次运行结果都相同
				 * 跟数据库服务器地址有关? 
				 * com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@2641e737
				 */
				System.out.println("connection-" + (i + 1) + ": " + connection.toString());
				ps = connection.prepareStatement(sql);
				// 查询超时时间，秒
				ps.setQueryTimeout(5);
				connection.commit();
				connection.close();
				//System.out.println("connection-" + (i + 1) + ": " + connection);
			}
			dataSource.close();
		} catch (Exception e) {
			log.error("testDruid =====> ", e);
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
