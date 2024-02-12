package databaseconfig;

import java.sql.*;

public class DbCon {
	private static final String URL = "jdbc:mysql://localhost:3307/cafe";
	private static final String USER = "root";
	private static final String PASSWORD = "0113";
	private static Connection con = null;
	
	public static Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
