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

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDruid() {
		try {
			DruidDataSource druidDataSource = new DruidDataSource();
			
			druidDataSource.setUsername("root");
			druidDataSource.setPassword("root");
			druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai");
			druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			// 连接关闭自动提交: false
			druidDataSource.setDefaultAutoCommit(false);
			druidDataSource.setName("mysqlDataSource");
			
			// 连接池最大连接数
			druidDataSource.setMaxActive(50);
			// 连接初始连接数
			druidDataSource.setInitialSize(30);
			// 最大空闲时间: 毫秒. 超过时间则连接被丢弃
			druidDataSource.setMaxWait(5000);
			
			druidDataSource.setTestOnBorrow(true);
			// 查询超时时间 秒
			druidDataSource.setQueryTimeout(10);
			druidDataSource.setOracle(false);
			druidDataSource.setDefaultReadOnly(false);
			
			// 空闲时检查
			druidDataSource.setTestWhileIdle(true);
			
			
			//druidDataSource.getConnection();
			
			druidDataSource.close();
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
