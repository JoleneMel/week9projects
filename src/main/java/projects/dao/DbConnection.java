package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;
import java.math.BigDecimal;

public class DbConnection {

	private static final String SCHEMA = "projects";
	private static final String USER = "root";
	private static final String PASSWORD = "Death3the3kid3!";
	private static final String HOST = "localhost ";
	private static final int PORT = 3306;

	public static Connection getConnection() {
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", HOST, PORT, SCHEMA, USER, PASSWORD);
		
		
		
	try {
		Connection conn= DriverManager.getConnection(url);
		System.out.println("Sucessfully obtained connection!");
		return conn;
		
	} catch (SQLException e) {
		throw new DbException(e);
		
	
	}
	
	}
}
