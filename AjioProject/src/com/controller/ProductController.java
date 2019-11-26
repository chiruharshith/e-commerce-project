package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ProductsDao;
import com.model.Products;

@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			response.sendRedirect("index.jsp");
		}
		try {			
			List<Products> productDetails = ProductsDao.getAllProducts();
			request.setAttribute("productDetails", productDetails);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		try {
			String category = request.getParameter("getProducts");
			List<Products> productDetails = ProductsDao.getProductDetails(category);
			request.setAttribute("category", category);
			request.setAttribute("productDetails", productDetails);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
