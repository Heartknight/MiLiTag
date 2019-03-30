package com.mili.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/ExitServlet")
public class ExitServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		String LoginFail = "Login.jsp";
		try {
			request.getSession(true).invalidate();
			
			response.sendRedirect(LoginFail);
			//response.sendRedirect(LoginSuccess);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		doGet(request, response);
	}

}
