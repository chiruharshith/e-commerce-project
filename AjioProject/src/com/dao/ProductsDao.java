package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Products;

public class ProductsDao {
	public static List<Products> getProductDetails(String category) throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT id,image_url,name,price FROM product WHERE category = ?");
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		List<Products> productDetails = new ArrayList<Products>();
		Products p = null;		
		while(rs.next()) {
			p = new Products();
			p.setId(rs.getInt(1));
			p.setImage_url(rs.getString(2));
			p.setName(rs.getString(3));
			p.setPrice(rs.getInt(4));
			productDetails.add(p);
		}
		return productDetails;
	}
	
	public static List<Products> getAllRandomProducts() throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM product ORDER BY RAND()");		
		ResultSet rs = ps.executeQuery();
		List<Products> productDetails = new ArrayList<Products>();
		Products p = null;		
		while(rs.next()) {
			p = new Products();
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setPrice(rs.getInt(3));
			p.setImage_url(rs.getString(4));
			p.setCategory(rs.getString(5));
			productDetails.add(p);
		}
		return productDetails;
	}
	
	public static List<Products> getAllProducts() throws Exception {
		Connection con = ConnectionUtility.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT image_url,name,price FROM product");		
		ResultSet rs = ps.executeQuery();
		List<Products> productDetails = new ArrayList<Products>();
		Products p = null;
		String str = "";
		while(rs.next()) {
			p = new Products();
			p.setImage_url(rs.getString(1));			
			str = rs.getString(2).replace('_', ' ');
			System.out.println(str);
			p.setName(str);
			p.setPrice(rs.getInt(3));
			productDetails.add(p);
		}
		return productDetails;
	}
}