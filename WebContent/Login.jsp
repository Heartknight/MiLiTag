<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <title>MiLi</title>
    <link href="css/Hui.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bface.css" rel="stylesheet">
    <style type="text/css">
        body { background: url(image/login_bg.jpg) no-repeat left top; background-size: cover; }
    </style>
</head>
<body class="H-height-100-percent H-box-sizing-border-box H-overflow-hidden">




<div class="container container-main">
		<!--人脸识别模态框-->
		<div class="modal fade" id="myModal" aria-hidden="true"
			data-backdrop="static" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel"
							style="display: inline-block">米砾便签</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						
						<!--人脸框-->
						<div class="modal-body-viode">
							<canvas id="canvas"></canvas>
							<video id="video" preload ="auto" autoplay loop	muted></video>
						</div>
						
						<div class="modal-body-title">
							<!--头部提示文字-->
							<p>请将头部放在视频区域内,匹配成功将会自动登入系统</p>
							<p>
								如果视频内未出现识别框或长时间未响应 <a style="cursor: pointer">请单击此处</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
    <div class="fix">
        <div class="H-horizontal-center H-padding-vertical-both-25">
            <div class="H-text-align-center">
                <img src="images/Logo.jpg" class="H-border-radius-12 H-margin-vertical-top-10" style="width:80px;height:80px;">
                <div class="H-font-size-18">MiLi</div>
            </div>
        </div>
        <div class="H-border-vertical-top-after H-margin-vertical-top-10">
        
            <form action=""  method="post">
            <div class="H-flexbox-horizontal  H-border-vertical-bottom-margin-both-10-after">
                <span class="H-icon H-vertical-middle H-padding-horizontal-left-10">
                	<i class="H-iconfont H-icon-phone H-font-size-18 H-vertical-middle H-theme-font-color-999">
                	</i>
                </span>
                <input name="u_tel" id="phone" type="text" class="H-textbox H-vertical-align-middle H-vertical-middle H-font-size-16 H-flex-item H-box-sizing-border-box H-border-none H-outline-none H-padding-15 H-theme-background-color-transparent" placeholder="请输入手机号码">
            </div>
            <div class="H-flexbox-horizontal H-border-vertical-bottom-after">
                <span class="H-icon H-vertical-middle H-padding-horizontal-left-10">
                	<i class="H-iconfont H-icon-lock H-font-size-18 H-vertical-middle H-theme-font-color-999"></i>
                </span>
              	<input name="u_pwd" id="pwd" type="password" class="H-textbox H-vertical-align-middle H-vertical-middle H-font-size-16 H-flex-item H-box-sizing-border-box H-border-none H-outline-none H-padding-15 H-theme-background-color-transparent" placeholder="请输入登录密码">
            </div>
            <div class="H-padding-15 H-margin-vertical-top-10">
                <button onclick ="LoginByPwd()" class="H-button H-width-100-percent  H-font-size-15 H-outline-none H-padding-vertical-both-15 H-padding-horizontal-both-20 H-theme-background-color1 H-theme-font-color-white H-theme-border-color1 H-theme-border-color1-click H-theme-background-color1-click H-theme-font-color1-click H-border-radius-3" >登录提交</button>
            </div>
            </form>
            
            <div class="H-margin-horizontal-both-15">
              <a href="register.jsp">
              	<span  class="H-theme-font-color1 H-float-left H-font-size-14">注册账号</span><span class="H-theme-font-color1 H-float-right H-font-size-14">忘记密码？</span>
            
              </a>  
              </div>
        </div>
    </div>
    <div class="H-padding-vertical-bottom-10"></div>
    <div class="H-theme-font-color1 H-text-align-center H-padding-vertical-both-10 H-font-size-14"><a data-toggle="modal"
						data-target="#myModal"  href="#" onclick="showLogin()">刷脸登录</a></div>
    <div class="H-flexbox-horizontal H-text-align-center  H-padding-vertical-both-15">
        <div class="H-flex-item">
            <span class="H-icon H-center-all H-theme-background-color1 H-border-radius-circle H-margin-horizontal-auto" style="height: 42px; width: 42px; "><i class="H-iconfont H-icon-qq H-font-size-22 H-theme-font-color-white"></i></span>
            <label class="H-display-block H-font-size-13 H-margin-vertical-top-5">腾讯QQ</label>
        </div>
        <div class="H-flex-item">
            <span class="H-icon H-center-all H-theme-background-color2 H-border-radius-circle H-margin-horizontal-auto" style="height: 42px; width: 42px; "><i class="H-iconfont H-icon-weixin H-font-size-22 H-theme-font-color-white"></i></span>
            <label class="H-display-block H-font-size-13 H-margin-vertical-top-5">腾讯微信</label>
        </div>
        <div class="H-flex-item">
            <span class="H-icon H-center-all H-theme-background-color3 H-border-radius-circle H-margin-horizontal-auto" style="height: 42px; width: 42px; "><i class="H-iconfont H-icon-weibo H-font-size-22 H-theme-font-color-white"></i></span>
            <label class="H-display-block H-font-size-13 H-margin-vertical-top-5">新浪微博</label>
        </div>
    </div>
    <script src="js/H.js" type="text/javascript"></script>
    <script type="text/javascript">
    </script>

<script type="text/javascript" src="js/tracking-min.js"></script>
	<script type="text/javascript" src="js/face-min.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bface.js"></script>
	<script type="text/javascript">
		function showLogin() {
			//调用人脸识别方法
			login("http://localhost:8080/MiLi/LoginServlet");
		}
		function showReg() {
			//调用人脸识别方法
			reg("http://localhost:8080/MiLi/RegServlet");
		}
	</script>


</body></html>