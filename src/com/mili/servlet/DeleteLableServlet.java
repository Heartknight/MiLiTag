package com.mili.servlet;


import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mili.util.C3P0Utils;


/**
 * Servlet implementation class ExportServelt
 */
@WebServlet("/DeleteLableServlet")
public class DeleteLableServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		
		try {
			
			String LoginSuccess = "main.jsp";		
			int Lnuo = 0;
			
			
			java.util.Date utilDate=new java.util.Date();
			
			
			Lnuo = Integer.valueOf(request.getParameter("Lnuo")).intValue();
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写SQL语句
			String sql = "DELETE FROM lable WHERE lable.lnuo = ?";
			// 3.为站位符设置值
			
			Object[] params = {Lnuo};
			// 4.执行添加操作
			
			int rows = qr.update(sql, params);
			if (rows > 0) {
				System.out.println("删除成功!");
				response.sendRedirect(LoginSuccess);
			} else {
				System.out.println("删除失败!");
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		doGet(request, response);
	}

}



