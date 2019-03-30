package com.mili.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import com.mili.util.AipFaceHelper;
import com.mili.util.StringUtil;
import com.mili.domain.User;



/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet") //注解
public class RegServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 设置防止提交的中文数据乱码
		response.setContentType("text/html;charset=UTF-8"); // 设置响应的信息不乱码
		PrintWriter out = response.getWriter();// 获取一个能够向客户端显示信息的对象
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("user_info", "user's info");// 用户资料，长度限制256B
		options.put("quality_control", "LOW");// 图片质量控制
		options.put("liveness_control", "LOW");// 活体检测控制
		// 取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串
		String image = request.getParameter("base");
		image = StringUtil.base64SubString(image);
		User user = (User)request.getSession().getAttribute("user");
		String imageType = "BASE64";
		String groupId = "test01";
		String userId = String.valueOf("no"+user.getU_id());
		// 人脸注册
		JSONObject res = AipFaceHelper.getInstance().addUser(image, imageType, groupId, userId, options);
		
		System.out.println(res.toString(2));
		
		
		out.print(res.toString(2));
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
