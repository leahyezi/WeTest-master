package com.weibo.userpool;

import java.sql.Connection;
import org.apache.commons.dbutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestDao {

	public static void main(String[] args) {
		Connection conn=JdbcUtil.getConnection();
		System.out.println(conn.getClass().getName());
		JdbcUtil.close(null, null, conn);
	}
	
	
	
}
