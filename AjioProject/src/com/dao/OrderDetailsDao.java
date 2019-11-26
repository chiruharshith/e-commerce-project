package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.OrderDetails;
import com.model.Orders;
import com.model.Products;

public class OrderDetailsDao {
	public static boolean addOrderDetails(OrderDetails od) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("INSERT INTO order_details(order_id,product_id,quantity,sessionId) VALUES(?,?,?,?)");
		ps.setInt(1, od.getOrder_id().getId());
		ps.setInt(2, od.getProduct_id().getId());
		ps.setInt(3, od.getQuantity());
		ps.setString(4, od.getSessionId().getSessionId());

		int r = ps.executeUpdate();
		if (r != 0)
			return true;

		return false;
	}
	
	public static List<OrderDetails> getOrderDetailsInfo(OrderDetails od) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT product_id,image_url,name,price,quantity FROM order_details od JOIN orders o ON od.order_id = o.id JOIN product p ON od.product_id = p.id WHERE od.order_id = ? AND od.sessionId = ?");
		System.out.println(od.getOrder_id().getId());
		ps.setInt(1, od.getOrder_id().getId());
		ps.setString(2, od.getSessionId().getSessionId());
		ResultSet rs = ps.executeQuery();
		List<OrderDetails> orderDetailsInfo = new ArrayList<>();		
		OrderDetails temp = null;		
		Products p = null;
		while (rs.next()) {			
			temp = new OrderDetails();			
			p = new Products();
			
			p.setId(rs.getInt(1));	
			p.setImage_url(rs.getString(2));
			p.setName(rs.getString(3));
			p.setPrice(rs.getInt(4));
			temp.setProduct_id(p);
			
			temp.setQuantity(rs.getInt(5));					
			orderDetailsInfo.add(temp);
		}
		return orderDetailsInfo;
	}
}