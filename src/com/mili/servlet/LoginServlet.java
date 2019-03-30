package com.mili.servlet;


import java.io.IOException;

import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.HashMap;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.json.JSONObject;

import com.mili.domain.User;
import com.mili.util.AipFaceHelper;
import com.mili.util.C3P0Utils;
import com.mili.util.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 设置防止提交的中文数据乱码
		response.setContentType("text/html;charset=UTF-8"); // 设置响应的信息不乱码
		PrintWriter out = response.getWriter();// 获取一个能够向客户端显示信息的对象
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "LOW");// 图片质量控制
		options.put("liveness_control", "LOW");// 活体检测控制
		options.put("user_id", "no1");
		options.put("max_user_num", "1"); // 查找后返回的用户数量。返回相似度最高的几个用户

		String image = request.getParameter("base");
		image = StringUtil.base64SubString(image);
		String imageType = "BASE64";
		String groupIdList = "test01"; // 从指定的group中进行查找 用逗号分隔，上限20个
		// 人脸搜索
		JSONObject res = AipFaceHelper.getInstance().search(image, imageType, groupIdList, options);
		System.out.print(res.get("error_code").toString().trim().equals("0"));
		if(res.get("error_code").toString().trim().equals("0"))
		{
			
			String u_id = res.getJSONObject("result").getJSONArray("user_list").getJSONObject(0).get("user_id").toString(); 
			
			User user  = FindUserById(u_id.substring(2));
			request.getSession(true).setAttribute("user", user);			
			
		}
		out.print(res.toString(2));
	}

	private User FindUserById(String u_id) 
	{
		try {
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			// 2.编写sql语句
			String sql = "SELECT * FROM `user` WHERE `user`.u_id = ?";
			//3.为占位符设置值
			Object[] params = {u_id};
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
