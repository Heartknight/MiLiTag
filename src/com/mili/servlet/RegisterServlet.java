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



@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		
		request.setCharacterEncoding("UTF-8"); // 设置防止提交的中文数据乱码
		response.setContentType("text/html;charset=UTF-8"); // 设置响应的信息不乱码
		PrintWriter out = response.getWriter();// 获取一个能够向客户端显示信息的对象
		
		String mobile = null;
		String Rmobile_code = null;
		String pwd = null;
		String mobile_code = null;
		
		mobile = request.getParameter("Rmobile");
		pwd = request.getParameter("Rpwd");
		Rmobile_code = request.getParameter("mobile_code");
		mobile_code = request.getSession().getAttribute("mobile_code").toString();
		
		User user = FindUserByTel(mobile);
		JSONObject result = null;
		if(user!=null) 
		{
			result = new JSONObject(" {\"code\":\"1\",\"msg\":\"用户名已存在\"}");
			System.out.println("该手机号已经注册");
			//			String script = "<script> var istrue = confirm('该用户已注册！跳转登录');if(istrue){location.href='"+LoginSuccess+"'}else{location.href='"+LoginFail+"'}</script>";
//			out.print(script);
//			
		}
		else 
		{
			if(mobile_code.trim().equals(Rmobile_code.trim())) 
			{
				result = new JSONObject(" {\"code\":\"200\",\"msg\":\"注册成功，返回登录。\"}");
				AddUser(mobile,pwd);
//				String script = "<script> var istrue = confirm('注册成功！返回登录');if(istrue){location.href='"+LoginSuccess+"'}else{location.href='"+LoginFail+"'}</script>";
//				out.print(script);
			}
			else
			{
				result = new JSONObject(" {\"code\":\"2\",\"msg\":\"验证码错误\"}");
//				result = new JSONObject(" {\"code\":\"000000\",\"count\":\"1\",\"create_date\":\"2019-03-27 23:57:12\",\"mobile\":\"15733692827\",\"msg\":\"OK\",\"smsid\":\"ee7183ddc64bd3178f64124eaea77aa5\",\"uid\":\"\"}");
//				
//				System.out.println("验证码错误");
//				String script = "<script>alert('验证码错误！请核对后重新登录');location.href='"+LoginFail+"'</script>";
//				out.print(script);
//				 
			}
		}
		out.print(result);
		
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
	
	private int AddUser(String tel,String pwd) 
	{
		try {
			// 1.创建核心类QueryRunner
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写SQL语句
			String sql = "insert into user values(null,?,?,null)";
			// 3.为站位符设置值
			Object[] params = { tel, pwd };
			// 4.执行添加操作
			int rows = qr.update(sql, params);
			if (rows > 0) {
				System.out.println("添加成功!");
			} else {
				System.out.println("添加失败!");
			}
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
}
