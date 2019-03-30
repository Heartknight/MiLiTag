<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>用户注册</title>
        <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
        <meta content="yes" name="apple-mobile-web-app-capable"/>
        <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
        <meta content="telephone=no" name="format-detection"/>
        <link href="css/register.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/register.js"></script>
    </head>
    <body>
       
<section class="aui-flexView">
        
            <header class="aui-navBar aui-navBar-fixed">
                <a href="Login.jsp" class="aui-navBar-item">
                    <i class="icon icon-return"></i>
                </a>
                <div class="aui-center">
                    <span class="aui-center-title">注册</span>
                </div>
                <a href="javascript:;" class="aui-navBar-item">
                    <i class="icon icon-news"></i>
                </a>
            </header>
            <section class="aui-scrollView">
                <div class="aui-code-box">
                    <form action="" method="post">
                        <p class="aui-code-line">
                            <input type="text" class="aui-code-line-input" name="Rmobile" value="" id="phone" autocomplete="off" placeholder="请输入手机号"/>
                        </p>
                        <p class="aui-code-line">
                            <input type="text" class="aui-code-line-input" name="Rpwd" value="" id="Rpwd" autocomplete="off" placeholder="请输入8-16位数字与字母组成的密码"/>
                        </p>
                        <p class="aui-code-line aui-code-line-clear">
                            <input name ="mobile_code" id="code" type="text" class="aui-code-line-input" autocomplete="off" placeholder="短信验证码"/>
                            <input id="btnSendCode1" type="button" class="aui-btn-default" value="获取验证码" onClick="sendMessage1()"/>
                        </p>
                        <div class="aui-che-item">
                            <div class="aui-cell-che">
                                <input type="checkbox" class="aui-well-check" name="checkbox1" id="" checked="checked">
                                <i class="aui-well-icon-checked"></i>
                            </div>我已经阅读并同意 <a href="javascript:;">《米砾便签注册协议条款》</a>
                        </div>
                        <div class="aui-code-btn">
                            <button onclick = "CheckInput()">注册</button>
                        </div>
                    </form>
                </div>
            </section>
        </section>
        <script type="text/javascript" charset="utf-8">
            $(function() {

                $(".aui-code-line-input").addClear();

            });
        </script>
    </body>
</html>
	