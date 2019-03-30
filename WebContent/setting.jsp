<%@ page language="java" contentType="text/html; charset=UTF-8"
%>
<%@page import = "com.mili.domain.User" %>
<!DOCTYPE html>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <title>米砾便签</title>
    <link href="css/Hui.css" rel="stylesheet" type="text/css">
	 <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bface.css" rel="stylesheet">
</head>
<body class="H-flexbox-vertical">
<% 
User user = (User)session.getAttribute("user");
if(null == user)
{
    response.sendRedirect("Login.jsp");
}            
%>
        <header class="H-header H-theme-background-color1">
        <span  onclick="H.closeWin();" class="H-icon H-position-relative H-display-inline-block H-float-left H-vertical-middle H-theme-font-color-white H-padding-horizontal-left-10 H-z-index-100"><i class="H-iconfont H-icon-arrow-left H-font-size-18 H-vertical-middle"></i></span>
        <div class="H-header-title H-center-all H-font-size-18 H-text-show-row-1 H-theme-font-color-white H-position-absolute H-width-100-percent">设置</div>
    </header>
	<section class="H-main H-flex-item H-position-relative H-overflow-scrolling">
		 <div class="opintion">
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

    <div>
        <div class="H-text-list H-flexbox-horizontal  H-theme-background-color-white H-border-vertical-both-after H-margin-vertical-top-10 H-vertical-middle H-touch-active">
            <div class="H-padding-horizontal-left-10 H-padding-vertical-both-5"><img src="images/Logo.jpg" class="H-height-50 H-vertical-align-middle H-width-50 H-border-radius-circle" /></div>
            <div class="H-flex-item H-padding-horizontal-both-8">米砾</div>
            <span class="H-icon H-padding-horizontal-right-10 H-display-block"><i class="H-iconfont H-icon-arrow-right H-theme-font-color-999 H-font-size-14 H-vertical-middle"></i></span>
        </div>
        <div class="H-margin-vertical-top-10 H-theme-background-color-white H-border-vertical-both-after">
            <div class="H-text-list H-flexbox-horizontal  H-theme-background-color-white H-border-vertical-bottom-margin-left-10-after H-vertical-middle H-touch-active">
                <div class="H-flex-item H-padding-horizontal-both-10 H-font-size-16 H-padding-vertical-both-12">设定目标</div>
                <span class="H-icon H-padding-horizontal-right-10 H-display-block"><i class="H-iconfont H-icon-arrow-right H-theme-font-color-999 H-font-size-14 H-vertical-middle"></i></span>
            </div>
          
            <div class="H-text-list H-flexbox-horizontal  H-theme-background-color-white H-vertical-middle H-touch-active">
                <div class="H-flex-item H-padding-horizontal-both-10 H-font-size-16 H-padding-vertical-both-12">推送设置</div>
                <span class="H-icon H-padding-horizontal-right-10 H-display-block"><i class="H-iconfont H-icon-arrow-right H-theme-font-color-999 H-font-size-14 H-vertical-middle"></i></span>
            </div>
        </div>
        <div class="H-theme-background-color-white H-margin-vertical-top-10 H-border-vertical-both-after">
            <div class="H-text-list H-flexbox-horizontal H-vertical-middle H-touch-active H-border-vertical-bottom-margin-left-10-after">
                <div class="H-flex-item H-padding-horizontal-both-10 H-font-size-16 H-padding-vertical-both-12">密码设置</div>
                 <span class="H-icon H-padding-horizontal-right-10 H-display-block"><i class="H-iconfont H-icon-arrow-right H-theme-font-color-999 H-font-size-14 H-vertical-middle"></i></span>
            
            </div>
            <div class="H-text-list H-flexbox-horizontal H-vertical-middle H-touch-active H-border-vertical-bottom-margin-left-10-after">
                <div class="H-flex-item H-padding-horizontal-both-10 H-font-size-16 H-padding-vertical-both-12">
                    <span class="H-display-block H-line-height-normal">手机号   </span>
                   
                </div>
				 <span class="H-theme-font-color-999 H-font-size-14  H-padding-horizontal-right-10 ">157****2927</span>
				<span class="H-icon H-padding-horizontal-right-10 H-display-block"><i class="H-iconfont H-icon-arrow-right H-theme-font-color-999 H-font-size-14 H-vertical-middle"></i></span>
				
            </div>
           
			<div class="H-text-list H-flexbox-horizontal H-vertical-middle H-touch-active">
				<div class=" H-flex-item H-padding-horizontal-both-10 H-font-size-16 H-padding-vertical-both-12" data-toggle="modal"
						data-target="#myModal" onclick="showReg()">面部数据</div>
			    
			   <span class="H-icon H-padding-horizontal-right-10 H-display-block"><i class="H-iconfont H-icon-arrow-right H-theme-font-color-999 H-font-size-14 H-vertical-middle"></i></span>
			</div>
        </div>
        <div class="H-margin-vertical-top-10 H-theme-background-color-white H-border-vertical-both-after">
            <div class="H-text-list H-flexbox-horizontal  H-theme-background-color-white H-border-vertical-bottom-margin-left-10-after H-vertical-middle H-touch-active">
                <div class="H-flex-item H-padding-horizontal-both-10 H-font-size-16 H-padding-vertical-both-12">给我评分</div>
                <span class="H-icon H-padding-horizontal-right-10 H-display-block"><i class="H-iconfont H-icon-arrow-right H-theme-font-color-999 H-font-size-14 H-vertical-middle"></i></span>
            </div>
            <div class="H-text-list H-flexbox-horizontal  H-theme-background-color-white H-border-vertical-bottom-margin-left-10-after H-vertical-middle H-touch-active">
                <div class="H-flex-item H-padding-horizontal-both-10 H-font-size-16 H-padding-vertical-both-12">意见反馈</div>
                <span class="H-icon H-padding-horizontal-right-10 H-display-block"><i class="H-iconfont H-icon-arrow-right H-theme-font-color-999 H-font-size-14 H-vertical-middle"></i></span>
            </div>
           
        </div>
        <div class="H-text-align-center H-margin-vertical-top-15 H-position-relative">
           <a href="ExitServlet" > <span class="H-theme-font-color-red H-font-size-22 H-margin-vertical-bottom-15 H-display-block H-theme-background-color-white H-position-absolute H-padding-vertical-both-10" style="left:10px;right:10px;">退出登陆</span>
        	</a>
        </div>
    </div>
    <script src="js/H.js" type="text/javascript"></script>
  <script type="text/javascript" src="js/tracking-min.js"></script>
	<script type="text/javascript" src="js/face-min.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bface.js"></script>
	<script type="text/javascript">
		function showReg() {
			//调用人脸识别方法
			reg("http://localhost:8080/MiLi/RegServlet");
		}
	</script>
		   
		</div></section>

    <script src="js/H.js" type="text/javascript"></script>
  
    </html>