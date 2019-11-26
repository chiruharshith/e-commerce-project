package com.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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

@WebServlet("/OrderDetailsController")
public class OrderDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		Date d = new Date();
		String str = request.getParameter("productItemDetails");
		try{

			int user_id = Integer.parseInt((String) session.getAttribute("userId"));			
			int total = Integer.parseInt(request.getParameter("totalAmount"));
			String sessionId = request.getSession().getId();
System.out.println("SESSION ID IN ORDERDETAILSCONTROLLER: "+sessionId);
	        Users uId = new Users(user_id);
	        Orders o = new Orders(uId, total, new Timestamp(d.getTime()),sessionId);
			OrdersDao.addOrders(o);	
			
			StringBuilder stringbu = new StringBuilder(str);
			stringbu.deleteCharAt(0);
			stringbu.deleteCharAt(str.length() - 2);
			String replacedStr = stringbu.toString().replaceAll("\"", "\'");
			
			String[] temp = replacedStr.split("}");
			String individualSet = "";
			String dummy = "";
			for (String k : temp) {
				String str1 = k.substring(1);
				individualSet = str1.replace("{", "");
				
				String[] strList = individualSet.toString().split(",");			
				int count = 0;
				int product_id = 0,quantity = 0;			
				
				for (String i : strList) {
					dummy = i.substring(i.indexOf(":") + 1);
					count++;
					if(count == 1){
						product_id = Integer.parseInt(dummy);
					}
					else if(count == 5){
						quantity = Integer.parseInt(dummy);
					}
				}

				try {
					Products pid = new Products(product_id);
					int order_id = OrdersDao.getOrderId(o);
					Orders oid = new Orders(order_id);
					Orders sid = new Orders(sessionId);
					
					OrderDetails od = new OrderDetails(oid, pid, quantity,sid);					
					OrderDetailsDao.addOrderDetails(od);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch(NullPointerException e){
			request.setAttribute("emptyCartMsg", "Your Cart Is Empty");
			request.getRequestDispatcher("CheckOutController").forward(request, response);
		}
		catch(StringIndexOutOfBoundsException e){
			request.setAttribute("emptyCartMsg", "Your Cart Is Empty");
			request.getRequestDispatcher("CheckOutController").forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			response.sendRedirect("CheckOutController");
		}
	}
}
