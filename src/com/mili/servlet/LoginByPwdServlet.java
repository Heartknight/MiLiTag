package com.mili.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.json.JSONObject;

import com.mili.util.C3P0Utils;




import com.mili.domain.User;



@WebServlet("/LoginByPwdServlet")
public class LoginByPwdServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

		request.setCharacterEncoding("UTF-8"); // 设置防止提交的中文数据乱码
		response.setContentType("text/html;charset=UTF-8"); // 设置响应的信息不乱码
		PrintWriter out = response.getWriter();// 获取一个能够向客户端显示信息的对象
		
		String u_tel = null;
		String u_pwd = null;
		
		
		u_tel = request.getParameter("Lmobile");
		u_pwd = request.getParameter("Lpwd");
		
		
		
		User user = FindUserByTel(u_tel);
		
		
		JSONObject result = null;
		if(user != null) 
		{
			if(user.IsPwd(u_pwd)) 
			{
				
				
				request.getSession(true).setAttribute("user", user);
				
				result = new JSONObject(" {\"code\":\"200\"}");
				out.print(result);
				System.out.println("yonghudenglu");
				//response.sendRedirect(LoginSuccess);
			}
			else 
			{
				result = new JSONObject(" {\"code\":\"1\",\"msg\":\"密码错误\"}");
				
				out.print(result);
				//out.print(user.getU_pwd());
				//response.setHeader("Refresh", "3;url=" + LoginFail);
			}
			
		}
		else 
		{
			result = new JSONObject(" {\"code\":\"2\",\"msg\":\"用户不存在\"}");
			out.print(result);
			//out.print("不存在该用户！");
			//response.setHeader("Refresh", "3;url=" + LoginFail);
		}
		
		
	}
	
	
	
	private User  FindUserByTel(String u_tel) {
		try {
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写sql语句
			String sql = "select * from user where u_tel=?";
			//3.为占位符设置值
			Object[] params = {u_tel};
			
			
			// 4.执行查询操作
			User user = qr.query(sql, new BeanHandler<User>(User.class), params);
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
}
