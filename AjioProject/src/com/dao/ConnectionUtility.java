package com.dao;

import java.sql.*;

public class ConnectionUtility {
	public static Connection getConnection() throws Exception {
		String url = "jdbc:mysql://localhost:3306/AjioProject";
		String uname = "root";
		String pass = "root";
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, uname, pass);
	}
}