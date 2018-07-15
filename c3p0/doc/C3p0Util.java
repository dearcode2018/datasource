/**
 * C3p0Util.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.hua.bean.C3p0Param;
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
			
			dataSource = new ComboPooledDataSource();
			// 将Map中的属性拷贝到dataSource对象
			BeanUtil.copyProperties(dataSource, C3p0Param.getProps());
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
