package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ProductsDao;
import com.dao.UsersDao;
import com.model.*;

@WebServlet("/user")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Products> list = new ArrayList<Products>();
	ArrayList<Users> userList = new ArrayList<>();
	HttpSession session;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("action");
		if (page == null || page.equals("index")) {
			try {
				list = ProductsDao.getAllRandomProducts();
			} catch (Exception e) {
				e.printStackTrace();
			}

			session = request.getSession();
			session.setAttribute("list", list);

			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			doPost(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = request.getParameter("action");

		if (page.equals("signup")) {
			try {
				String name = request.getParameter("usr_name");
				String email = request.getParameter("usr_email");
				String password = request.getParameter("usr_password");
				Users u = new Users(name, email, password);
				boolean isUserNotExists = UsersDao.isUserNotExists(u);
				if (isUserNotExists && UsersDao.createNewUser(u)) {
					session = request.getSession();
					session.setAttribute("session", session);
					String sessionId = request.getSession().getId();					
					session.setAttribute("sessionId", sessionId);
					session.setAttribute("username", name);
					session.setAttribute("email", email);
					session.setAttribute("userId", UsersDao.getUserId(email));
System.out.println("SESSION ID IN USERSCONTROLLER: "+sessionId);
					list = ProductsDao.getAllRandomProducts();
					session.setAttribute("list", list);
					request.getRequestDispatcher("index.jsp").forward(request, response);

				} else {
					request.setAttribute("msg", "You Entered Wrong Email Address");
					request.setAttribute("name", name);
					request.setAttribute("email", email);
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (page.equals("signin")) {
			try {
				String usr_email = request.getParameter("usr_email");
				String usr_password = request.getParameter("usr_password");
				Users u = new Users(usr_email, usr_password);

				if (UsersDao.isValidLogin(u)) {
					String name = UsersDao.loggedInUser(u);
					session = request.getSession();
					String sessionId = request.getSession().getId();
					session.setAttribute("sessionId", sessionId);
					session.setAttribute("session", session);
					session.setAttribute("username", name);
					session.setAttribute("email", usr_email);
					session.setAttribute("userId", UsersDao.getUserId(usr_email));
System.out.println("SESSION ID IN USERSCONTROLLER: "+sessionId);
					list = ProductsDao.getAllRandomProducts();
					session.setAttribute("list", list);
					request.getRequestDispatcher("index.jsp").forward(request, response);

				} else {
					request.setAttribute("msg", "Invalid Crediantials");
					session.setAttribute("email", usr_email);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (page.equals("Formal_Shoes") || page.equals("Casual_Shoes") || page.equals("Sport_Shoes")
				|| page.equals("Watches") || page.equals("Sun_Glasses") || page.equals("all-products")) {

			try {
				if (page.equals("all-products"))
					list = ProductsDao.getAllRandomProducts();
				else
					list = ProductsDao.getProductDetails(page);
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("category", page);
			request.setAttribute("list", list);
			request.getRequestDispatcher("index.jsp").include(request, response);
		}

		else if (page.equals("logout")) {
			session = request.getSession();
			session.invalidate();
			
			session = request.getSession();
			session.setAttribute("list", list);
			response.sendRedirect("index.jsp");			
		}

	}
}
