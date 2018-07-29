package cn.dw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class JdbcUtils {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?characterEncoding=UTF-8", "root", "123456");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) {
		JdbcUtils utils = new JdbcUtils();
		Connection connection = utils.getConnection();
		System.out.println(connection);
		new Date();
	}

}
