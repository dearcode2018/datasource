/**
 * C3p0Util.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3p0Util
 * 描述: C3P0 连接池 - 工具类
 * @author  qye.zheng
 */
public final class C3p0Util
{

	/* javax.sql.DataSource 数据源 */
	private static DataSource dataSource;

	/* java.sql.Connection 连接 */
	private static Connection conn;
	
	static
	{
		try
		{
			/*
			旧的写法 - 由于 cpds.setProperties 并没有将配置文件的属性
			置入各个属性，因此 c3p0无法正常启动
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			无法将map的value置入各个属性
			cpds.setProperties(C3p0Param.getProps());
			无法获取
			System.out.println("jdbcUrl: " + cpds.getJdbcUrl());
			将Map中的属性拷贝到cpds对象
			BeanUtil.copyProperties(cpds, C3p0Param.getProps());
			可以获取
			System.out.println("jdbcUrl: " + cpds.getJdbcUrl());
			dataSource = cpds;
			 */
			// 创建数据源对象
			
			ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
			pooledDataSource.setUser("root");
			pooledDataSource.setPassword("root");
			pooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/datasource?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai");
			pooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
			// 连接关闭自动提交: false
			pooledDataSource.setAutoCommitOnClose(false);
			pooledDataSource.setDataSourceName("mysqlDataSource");
			// 连接池最大连接数
			pooledDataSource.setMaxPoolSize(50);
			// 连接初始连接数，值在 min和max之间
			pooledDataSource.setInitialPoolSize(30);
			// 连接池最小连接数
			pooledDataSource.setMinPoolSize(20);
			// 最大空闲时间: 秒. 超过时间则连接被丢弃
			pooledDataSource.setMaxIdleTime(5000);
			// 最大语句数 PreparedStatement 数量 0-缓存关闭
			pooledDataSource.setMaxStatements(100);
			// 单个连接所拥有的最大缓存statement数量
			pooledDataSource.setMaxStatementsPerConnection(10);
			
			//pooledDataSource.setMaxConnectionAge(0);
			
			// 连接的测试语句
			pooledDataSource.setPreferredTestQuery("SELECT 1");
			
			// 取得连接的同时将检查连接的有效性
			pooledDataSource.setTestConnectionOnCheckin(true);
			// 连接提交的时候检查其有效性 | 性能消耗大，仅在需要的时候开启
			pooledDataSource.setTestConnectionOnCheckout(false);
			
			// 异步帮助线程的数量，可以提升执行效率
			pooledDataSource.setNumHelperThreads(3);
			
			// 连接池用完时，客户端从连接池获取连接的等待时间，毫秒数，0-表示无限期等待
			pooledDataSource.setCheckoutTimeout(5000);
			// 间隔多少秒检查连接池中的空闲连接
			pooledDataSource.setIdleConnectionTestPeriod(0);
			
			dataSource = pooledDataSource;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private C3p0Util()
	{
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * @return
	 */
	public static final Connection getConnection() {
		try
		{
			conn = dataSource.getConnection();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
}
