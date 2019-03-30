package com.mili.servlet;

import java.io.IOException;

import java.sql.Date;



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
@WebServlet("/AddLableServelt")
public class AddLableServelt extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String 	LoginSuccess = "main.jsp";		
			String 	L_title = null;
			String 	L_content = null;
			int 	L_uid = 0;
			
			L_title 	= request.getParameter("L_title");
			L_content 	= request.getParameter("L_content");
			
			if(!L_title.isEmpty()||!L_content.isEmpty()) 
			{
				java.util.Date utilDate	= new java.util.Date();
				Date 			L_time 	= new java.sql.Date(utilDate.getTime());
				
				L_uid = Integer.valueOf(request.getParameter("L_uid")).intValue();
				
				//
				QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
				
				// 2.编写SQL语句
				String sql = "insert into lable values(null,?,?,?,?,?)";
				// 3.为站位符设置值
				
				Object[] params = { L_uid,L_title,L_content ,L_time,0};
				// 4.执行添加操作
				
				int rows = qr.update(sql, params);
				if (rows > 0) {
					System.out.println("添加成功!");
					response.sendRedirect(LoginSuccess);
				} else {
					System.out.println("添加失败!");
				}
				
			}
			else
			{
				response.sendRedirect(LoginSuccess);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}



