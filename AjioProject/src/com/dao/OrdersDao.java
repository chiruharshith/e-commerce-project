package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Orders;
import com.model.Users;

public class OrdersDao {
	public static boolean addOrders(Orders o) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("INSERT INTO orders(user_id,total_amount,order_date,sessionId) VALUES(?,?,?,?)ON DUPLICATE KEY UPDATE total_amount = total_amount + ?;");
		
		ps.setInt(1, o.getUser_id().getId());
		ps.setInt(2, o.getTotal_amount());
		ps.setTimestamp(3, o.getOrder_date());
		ps.setString(4, o.getSessionId());
		ps.setInt(5, o.getTotal_amount());
		try {
			int r = ps.executeUpdate();
			if (r != 0)
				return true;
		} catch (Exception e1) {
			return false;
		}
		return false;
	}
	
	public static int getOrderId(Orders o) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT id FROM orders WHERE user_id = ? AND sessionId = ?");
		ps.setInt(1, o.getUser_id().getId());
		ps.setString(2, o.getSessionId());
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	public static int getOrderIdonSession(Orders o) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT id FROM orders WHERE user_id = ? AND sessionId = ?");
		ps.setInt(1, o.getUser_id().getId());
		ps.setString(2, o.getSessionId());
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	public static List<Orders> getOrderInfo(Orders o) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT user_id,name,email,total_amount,order_date FROM orders o INNER JOIN users u ON o.user_id = u.id WHERE user_id = ? AND sessionId = ?");
		ps.setInt(1, o.getUser_id().getId());
		ps.setString(2, o.getSessionId());
		ResultSet rs = ps.executeQuery();
		List<Orders> orderInfo = new ArrayList<>();
				
		Orders od = null;
		Users usr = null;
		while (rs.next()) {
			od = new Orders();
			usr = new Users();
			usr.setId(rs.getInt(1));
			usr.setName(rs.getString(2));
			usr.setEmail(rs.getString(3));
			od.setUser_id(usr);
			od.setTotal_amount(rs.getInt(4));
			od.setOrder_date(rs.getTimestamp(5));
			orderInfo.add(od);
		}
		return orderInfo;
	}
}