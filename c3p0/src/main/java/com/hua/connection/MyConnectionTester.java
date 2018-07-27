/**
  * @filename MyConnectionTester.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.connection;

import java.sql.Connection;

import com.mchange.v2.c3p0.ConnectionTester;

 /**
 * @type MyConnectionTester
 * @description 
 * @author qianye.zheng
 */
public class MyConnectionTester implements ConnectionTester
{

	private static final long serialVersionUID = -8942158392153648634L;

	/**
	 * @description 连接的活跃检查
	 * @param c
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public int activeCheckConnection(Connection c)
	{
		System.out.println("MyConnectionTester.activeCheckConnection()");
		
		return 0;
	}

	/**
	 * @description 
	 * @param c
	 * @param t
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public int statusOnException(Connection c, Throwable t)
	{
		System.out.println("MyConnectionTester.statusOnException()");
		
		return 0;
	}

}
