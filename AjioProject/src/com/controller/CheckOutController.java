package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.OrderDetailsDao;
import com.dao.OrdersDao;
import com.model.OrderDetails;
import com.model.Orders;
import com.model.Products;
import com.model.Users;

@WebServlet("/CheckOutController")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Orders> orderInfo;
		List<OrderDetails> orderDetailsInfo;
		HttpSession session = request.getSession(false);
		int userId = Integer.parseInt((String) session.getAttribute("userId"));
		String sessionId = request.getSession().getId();
		System.out.println("CHECKOUT : "+sessionId);
		Users user_id =  new Users(userId);
		
		Orders o = new Orders(user_id, sessionId);
		try {			
			orderInfo = OrdersDao.getOrderInfo(o);			
			
			int order_id = OrdersDao.getOrderIdonSession(o);
			Orders oid = new Orders(order_id);
			Orders sid = new Orders(sessionId);
			
			OrderDetails od = new OrderDetails(oid,sid);
			orderDetailsInfo = OrderDetailsDao.getOrderDetailsInfo(od);			
			for(OrderDetails i : orderDetailsInfo)
				System.out.println(i);
			
			request.setAttribute("orderDetailsInfo", orderDetailsInfo);
			request.setAttribute("orderInfo", orderInfo);
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
}
