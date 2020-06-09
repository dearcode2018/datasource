/**
 * 描述: 
 * DbcpTest.java
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

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.DelegatingConnection;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 数据连接池 - 测试
 * 
 * @author qye.zheng
 * DbcpTest
 */
public final class DbcpTest extends BaseTest {

	
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
		connection-1: 415186196, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-2: 897074030, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-3: 1739876329, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-4: 156545103, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-5: 1209669119, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-6: 1291113768, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-7: 641853239, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-8: 614685048, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-9: 265119009, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-10: 1434041222, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-11: 802581203, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-12: 188576144, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-13: 1873859565, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-14: 999609945, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-15: 900008524, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-16: 1159114532, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-17: 497359413, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-18: 1902260856, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-19: 1640639994, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-20: 2074185499, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-21: 183284570, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-22: 194706439, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-23: 1411892748, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-24: 1024429571, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-25: 1138193439, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-26: 1601292138, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-27: 1330754528, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-28: 1370651081, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-29: 1586845078, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-30: 336484883, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-31: 392781299, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-32: 736778932, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-33: 708890004, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-34: 1948863195, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-35: 793315160, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-36: 660879561, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-37: 1904324159, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-38: 1313532469, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-39: 124407148, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-40: 1738236591, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-41: 2040352617, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-42: 24606376, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-43: 297927961, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-44: 868964689, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-45: 673186785, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-46: 1579526446, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-47: 300031246, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-48: 1073533248, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-49: 1268959798, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
		connection-50: 928466577, URL=jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai, UserName=root@localhost, MySQL Connector/J
	
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
			BasicDataSource dataSource = new BasicDataSource();
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
			 * 缓存状态: 开启则以后只读取缓存的 自动提交/只读 设置
			 * 若直接访问底层连接，则禁用该属性
			 */
			dataSource.setCacheState(true);
			// 默认查询超时时间，创建Statement时设置，秒
			dataSource.setDefaultQueryTimeout(60);
			// 连接归还连接池时是否设置允许自动提交
			dataSource.setEnableAutoCommitOnReturn(true);
			/*
			 * 连接归还连接池时是否执行rollback()
			 * 前提是 autoCommit=true且非只读
			 */
			dataSource.setRollbackOnReturn(false);
			/** 连接数设置，负数-不限制 */
			// 连接池初始化连接数
			dataSource.setInitialSize(3);
			// 最大活动连接数(活动: 正在被客户端使用)
			dataSource.setMaxTotal(5);
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
			dataSource.setMaxWaitMillis(3 * 1000);
			// 连接的校验语句
			dataSource.setValidationQuery("SELECT 1");
			// 校验语句的查询超时时间，创建Statement时设置. 秒
			dataSource.setValidationQueryTimeout(10);
			// 是否连接创建的时候校验连接有效性，校验失败则归还给数据库?
			dataSource.setTestOnCreate(true);
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
			// 空闲连接最少多少毫秒后才会被释放 (条件2，包含了条件1和 minIdle)
			dataSource.setSoftMinEvictableIdleTimeMillis(5 * 1000);
			/*
			 *  连接的最大存活毫秒数，超时则在下次激活、钝化、校验时都将失败，
			 *  0/负数是无限的
			 */
			dataSource.setMaxConnLifetimeMillis(0);
			// 初始化执行的sql集合
			//dataSource.setConnectionInitSqls(null);
			// 后进先出: 优先租借最后一次租借的对象
			dataSource.setLifo(true);
			// 连接存活时间超过 maxConnLifetimeMillis时被释放，是否输出日志
			dataSource.setLogExpiredConnections(true);
			// 是否开启预处理语句池
			dataSource.setPoolPreparedStatements(true);
			// 预处理语句池的大小
			dataSource.setMaxOpenPreparedStatements(500);
			// 是否允许PoolGuard访问底层连接
			dataSource.setAccessToUnderlyingConnectionAllowed(false);
			// 租借时是否移除可抛弃的连接
			dataSource.setRemoveAbandonedOnBorrow(true);
			dataSource.setRemoveAbandonedOnMaintenance(true);
			// 可抛弃连接可以被移除的超时时间 秒
			dataSource.setRemoveAbandonedTimeout(300);
			// 是否为抛弃语句、连接开启日志
			dataSource.setLogAbandoned(true);
			/*
			 * 是否开启快速校验是失败
			 * 
			 * 发生致命异常，快速失败，不再去做过多校验，例如再去执行isValid()
			 异常码（SQL_STATE）指以下：
		    57P01 (ADMIN SHUTDOWN)
		    57P02 (CRASH SHUTDOWN)
		    57P03 (CANNOT CONNECT NOW)
		    01002 (SQL92 disconnect error)
		    JZ0C0 (Sybase disconnect error)
		    JZ0C1 (Sybase disconnect error)
		    Any SQL_STATE code that starts with "08"

			 */
			dataSource.setFastFailValidation(true);
			// 断开连接的SQL_STATE码集合 57P01...
			//dataSource.setDisconnectionSqlCodes();
			
			
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
