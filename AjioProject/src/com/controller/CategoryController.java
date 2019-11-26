package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProductsDao;
import com.model.Products;

@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Products> list = new ArrayList<Products>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String page = request.getParameter("getProducts");
			try {
				list = ProductsDao.getProductDetails(page);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			request.setAttribute("category", page);
			request.setAttribute("list", list);
			request.getRequestDispatcher("categories.jsp").forward(request, response);
	}
}
