package com.hutc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory { 
	private static String DRIVER;
	private static String URL;
	private static String UNAME;
	private static String UPASS;


	static {//静态的
		Properties props = new Properties();
		InputStream is = ConnectionFactory.class.getResourceAsStream("jdbcinfo.properties");
		try {
			props.load(is);
			DRIVER = props.getProperty("oracle.driver");
			URL = props.getProperty("oracle.url");
			UNAME = props.getProperty("oracle.uname");
			UPASS = props.getProperty("oracle.upass");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, UNAME, UPASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
