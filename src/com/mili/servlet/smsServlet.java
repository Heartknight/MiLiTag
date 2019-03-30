package com.mili.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mili.client.*;




/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/smsServlet")
public class smsServlet extends HttpServlet {

	static AbsRestClient InstantiationRestAPI() {
		return new JsonReqClient();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();// 获取一个能够向客户端显示信息的对象
		// 接口类型：互亿无线触发短信接口，支持发送验证码短信、订单通知短信等。
		 // 账户注册：请通过该地址开通账户http://sms.ihuyi.com/register.html
		 // 注意事项：
		 //（1）调试期间，请使用用系统默认的短信内容：您的验证码是：【变量】。请不要把验证码泄露给其他人。；
		 //（2）请使用APIID（查看APIID请登录用户中心->验证码短信->产品总览->APIID）及 APIkey来调用接口；
		 //（3）该代码仅供接入互亿无线短信接口参考使用，客户可根据实际需要自行编写；

		//String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

		int mobile_code = (int)((Math.random()*9+1)*100000); //获取随机数

		//String account = "C43777635"; //查看用户名是登录用户中心->验证码短信->产品总览->APIID
		//String password = "8ce474747300ed95633b2770da080f15";  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
		//String mobile = request.getParameter("mobile");
		//String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
		String sid = "b4c7bd3b7af101d5a88782cdc109b324";
		String token = "69ee9c1c3a2c42ee52dc742a6c90f141";
		String appid = "c7784f7267af481eb73a2d4ed8d35164";
		String templateid = "447595";
		String param = String.valueOf(mobile_code);
		String mobile = request.getParameter("mobile");;
		String uid = "";
		request.getSession(true).setAttribute("mobile_code", param);

		String result=InstantiationRestAPI().sendSms(sid, token, appid, templateid, param, mobile, uid);
		//System.out.println("Response content is: " + result);
		//JSONObject result = new JSONObject(" {\"code\":\"000000\",\"count\":\"1\",\"create_date\":\"2019-03-27 23:57:12\",\"mobile\":\"15733692827\",\"msg\":\"OK\",\"smsid\":\"ee7183ddc64bd3178f64124eaea77aa5\",\"uid\":\"\"}");
		System.out.println(result+param);
		out.print(result);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
