package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Users;

public class UsersDao {
	public static boolean createNewUser(Users u) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO users(name,email,password) VALUES(?,?,aes_encrypt(?,'k1'))");
		ps.setString(1, u.getName());
		ps.setString(2, u.getEmail());
		ps.setString(3, u.getPassword());
		
		try {
			int r = ps.executeUpdate();
			if (r != 0)
				return true;
		} catch (Exception e1) {
			return false;
		}
		return false;
	}
	
	public static String getUserId(String email) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT id FROM users WHERE email = ?");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getString(1);
	}
	public static boolean isValidLogin(Users u) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT aes_decrypt(password,'k1') FROM users WHERE email = ?");		
		ps.setString(1, u.getEmail());
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		if (rs.getString(1).equals(u.getPassword()))
			return true;

		return false;
	}

	public static String loggedInUser(Users u) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT name FROM users WHERE email = ?");
		ps.setString(1, u.getEmail());
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getString(1);
	}
	
	public static String updateUserSessionId(Users u) throws Exception {
		
		return null;
	}

	public static boolean isUserNotExists(Users u) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email = ?");
		ps.setString(1, u.getEmail());
		ResultSet rs = ps.executeQuery();
		if (rs.next() == false) {
			return true;
		}
		return false;
	}
}