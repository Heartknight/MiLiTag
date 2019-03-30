<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import = "com.mili.domain.User" %>
<%@page import = "com.mili.domain.Lable" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0">
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <title>米砾便签</title>
    <link href="css/Hui.css" rel="stylesheet" type="text/css">
    <link href="css/main.css" rel="stylesheet" type="text/css" />
    <style type="text/css"></style>
</head>
<body class="H-flexbox-vertical">

<% 
User user = (User)session.getAttribute("user");

if(null == user)
{
	System.out.println("111111");
    response.sendRedirect("Login.jsp");
} 
else{
%>

   <header class="H-header H-theme-background-color1" id="header">
        <a href="setting.jsp"><span class="H-icon H-position-relative H-display-inline-block H-float-left H-vertical-middle H-theme-font-color-white H-padding-horizontal-left-5 H-z-index-100"><img src="images/Logo.jpg" alt="" title="" class="H-border-radius-circle H-display-block" style="height:36px;width:36px;"></span></a>
        <div class="H-header-title H-center-all H-font-size-18 H-text-show-row-1 H-theme-font-color-white H-position-absolute H-width-100-percent">米砾标签</div>
       <a href="ExportServelt"> <span class="H-icon H-position-relative H-display-inline-block H-float-right H-vertical-middle H-theme-font-color-white H-padding-horizontal-right-10 H-z-index-100">
			<i class="H-iconfont H-icon-gobottom H-font-size-28 H-vertical-middle"></i></span></a>
    </header>
    <section>
	<div class="H-main H-flex-item H-position-relative H-overflow-scrolling">
		<div class="mainTitle">
			<div class="H-search H-flexbox-horizontal H-box-sizing-border-box  H-overflow-hidden H-position-relative">
		    <div class="H-position-absolute H-z-index-100 H-width-100-percent H-height-100-percent H-vertical-middle H-text-align-center H-theme-font-color-999  H-border-radius-15" id="ser"><span class="H-display-block H-width-100-percent H-font-size-14"><i class="H-iconfont H-icon-search H-font-size-14"></i> 搜索关键字</span></div>
		    <input type="search" name="keyword" id="ipt" class="H-border-none  H-border-radius-15 H-theme-background-color-f4f4f4 H-flex-item H-font-size-14 H-padding-horizontal-both-10 H-padding-vertical-both-5 H-theme-background-color-f4f4f4" /><span class="H-display-block H-font-size-14 H-vertical-middle H-theme-font-color1 H-padding-horizontal-both-10 btn" style="display:none;">取消</span>
		</div>
		</div>
		<div class="mainContent">
		<%if(user!=null){for(Lable lable : user.getLables()){ %>
		<div class="H-qq-item H-vertical-middle H-overflow-hidden">
            <div class="H-flexbox-horizontal H-box-sizing-border-box H-theme-background-color-white H-border-vertical-bottom-after H-clear-both H-padding-horizontal-both-10 H-padding-vertical-both-8 H-touch-active">
                <div class="mui-input-row mui-checkbox mui-left">
                  <input type="checkbox" checked="checked" class="H-checkbox H-checkbox-null H-vertical-align-middle H-font-size-18 H-theme-font-color1">
               </div>
                <div class="H-flex-item H-padding-horizontal-both-10 H-vertical-middle H-overflow-hidden">
                    <div class="H-width-100-percent">
                        <strong class="H-font-weight-normal H-display-block H-font-weight-500 H-font-size-16 H-text-show-row-1"><%=lable.getLtitle() %></strong>
                        <div class="H-theme-font-color-999 H-font-size-14 H-padding-vertical-top-3 H-text-show-row-1"><%=lable.getlContent() %></div>
                        
                    </div>
                </div>
                <div class="H-font-size-12 H-theme-font-color-999 white-space-nowrap H-text-align-right">
                    <label class="H-display-block"><%=lable.getLtime() %></label>
                </div>
            </div>
 		<div class="H-qq-menu H-vertical-middle H-position-relative H-overflow-hidden">
 			<form action="StickLableServlet" method="post">
 				<input name="Lnuo" type="hidden" value="<%=lable.getLnuo()%>">
 				<button  class="H-font-size-16 H-padding-horizontal-both-20 H-theme-background-color1 H-display-block H-theme-font-color-white white-space-nowrap">置顶</button>
            </form>
            <form action="DeleteLableServlet" method="post">
                <input name="Lnuo" type="hidden" value="<%=lable.getLnuo()%>">
                <button onclick="" class="H-font-size-16 H-padding-horizontal-both-20 H-theme-background-color-red H-display-block H-theme-font-color-white white-space-nowrap">删除</button>
 			</form>
                
            </div>
        </div>
<%}} %>
		

		</div>
		 	
	</div>
	 <div class="multi-menu H-background-color-transparent-1 H-flexbox-vertical H-display-none-important H-position-absolute H-vertical-top-0 H-horizontal-left-0 H-horizontal-right-0 H-vertical-bottom-0 H-height-100-percent H-width-100-percent H-z-index-1000000">
	    <div class="space H-flex-item">
			
		</div>
	    <div class="share-menu">
	    	<form action="AddLableServelt" method="get">
		        <div class="H-flexbox-horizontal H-text-align-center H-theme-background-color-white H-padding-vertical-both-10">
				<div class="label_Content">
				<%
					if(user!=null){
				%>
					<input name = "L_uid" type="hidden" value="<%=user.getU_id()%>"/>
				<%} %>
				<input name="L_title" type="text" class="H-textbox H-vertical-align-middle H-vertical-middle H-font-size-16 H-flex-item H-box-sizing-border-box H-border-none H-outline-none H-padding-12" placeholder="标题">
					
				<textarea name="L_content" class="H-textbox H-vertical-align-middle H-vertical-middle H-font-size-16 H-flex-item H-box-sizing-border-box H-border-none H-outline-none H-padding-12" placeholder="总得写点什么..."></textarea>
				</div>
				</div>
		        <div class="box_btn">
					<button type="submit" onclick="H.swiperShare('.multi-menu', '.share-menu', 'space');" class="btn H-theme-background-color-eee H-padding-12 H-font-size-16 H-center-all active">确定</button> 
					<input   onclick="H.swiperShare('.multi-menu', '.share-menu', 'space');" type="button" class="btn H-theme-background-color-eee H-padding-12 H-font-size-16 H-center-all " value="取消">
				</div>
			</form>
	     </div>
	
	
	
	</div>
   
	</section>
	<footer class="H-theme-background-color-white " id="footer">
	        <div class="H-flex-item H-center-all H-text-align-center H-theme-font-color-999 H-touch-active  H-theme-font-color1">
	            <div>
	            </div>
	        </div>
	        <div class="H-flex-item H-center-all H-text-align-center H-theme-font-color-999 H-touch-active ">
	            <div>
	               
	            </div>
	        </div>
	        <div class="H-flex-item H-center-all H-text-align-center  H-theme-font-color-white  H-position-relative">
	            <div class="H-footer-cover-half-line H-position-absolute H-z-index-1000 H-theme-background-color-white H-width-100-percent H-height-100-percent"></div>
	            <div class="H-footer-half-circle H-position-absolute H-z-index-100 H-border-radius-circle-after H-theme-background-color-white"></div>
	            <div>
	                <a   onclick="H.swiperShare('.multi-menu', '.share-menu', 'space');"><span class="H-icon H-display-block H-line-height-normal H-center-all H-theme-background-color1 H-border-radius-circle H-position-relative H-z-index-10000" style="height:45px;width:45px; top:-2px;"><i class="H-iconfont H-icon-add H-font-size-26"></i></span></a>
	            </div>
	        </div>
	        <div class="H-flex-item H-center-all H-text-align-center  H-theme-font-color-999 H-touch-active ">
	            <div>
	               
	            </div>
	        </div>
	        <div class="H-flex-item H-center-all H-text-align-center  H-theme-font-color-999 H-touch-active ">
	            <div>
	                
	            </div>
	        </div>
	    </footer>
	    <script src="js/H.js" type="text/javascript"></script>
	    <script type="text/javascript">
	
	        H.D("#ser").addEventListener("touchend", function (e) {
	            this.style.cssText = "display:none;";
	            H.D(".btn").removeAttribute("style");
	            H.D("#ipt").setAttribute("placeholder", "请输入关键字");
	        });
	
	        H.D(".btn").addEventListener("touchend", function (e) {
	            this.style.cssText = "display:none;";
	            H.D("#ipt").setAttribute("placeholder", "");
	            H.D("#ser").removeAttribute("style");
	        });
	
	        //################## 侧滑全部代码
	        //返回角度
	        function GetSlideAngle(dx, dy) {
	            return Math.atan2(dy, dx) * 180 / Math.PI;
	        }
	
	        //根据起点和终点返回方向 1：向上，2：向下，3：向左，4：向右,0：未滑动
	        function GetSlideDirection(startX, startY, endX, endY) {
	            var dy = startY - endY;
	            var dx = endX - startX;
	            var result = 0;
	
	            //如果滑动距离太短
	            if (Math.abs(dx) < 2 && Math.abs(dy) < 2) {
	                return result;
	            }
	
	            var angle = GetSlideAngle(dx, dy);
	            if (angle >= -45 && angle < 45) {
	                result = 4;
	            } else if (angle >= 45 && angle < 135) {
	                result = 1;
	            } else if (angle >= -135 && angle < -45) {
	                result = 2;
	            }
	            else if ((angle >= 135 && angle <= 180) || (angle >= -180 && angle < -135)) {
	                result = 3;
	            }
	
	            return result;
	        }
	
	        var _startX = 0;
	        var _startY = 0;
	        var scrollHeight = 0;
	        var scrollWidth = 0;
	        var currentRange = 0;
	        var parentEle = null;
	        var direction = 1;
	
	        // 设置侧滑动画
	        function setScroll(parentElement, value, time) {
	            parentElement.firstElementChild.style.cssText = "-webkit-transition: transform " + time + "s;-webkit-transform:translateX(" + value + "px);";
	            parentElement.lastElementChild.style.cssText = "-webkit-transition: transform " + time + "s;-webkit-transform:translateX(" + value + "px);";
	        }
	
	        // 触摸开始
	        window.addEventListener("touchstart", function (event) {
	            if (event.targetTouches.length == 1) {
	                var touch = event.targetTouches[0];
	                parentEle = H.getParents(event.target, "H-qq-item");
	                if (H.isElement(parentEle)) {
	
	                    // 其他兄弟隐藏
	                    var siblings = H.siblings(parentEle);
	                    for (var i = 0; i < siblings.length; i++) {
	                        var _siblingObj = siblings[i];
	                        setScroll(_siblingObj, 0, 0);
	                    }
	
	                    scrollHeight = parentEle.firstElementChild.clientHeight;
	                    scrollWidth = H.D(".H-qq-menu", parentEle).clientWidth;
	
	                    for (var i = 0; i < parentEle.lastElementChild.childNodes.length; i++) {
	                        var child = parentEle.lastElementChild.childNodes[i];
	                        if (H.isElement(child)) {
	                            child.style.cssText = "height:" + scrollHeight + "px;line-height:" + scrollHeight + "px;";
	                        }
	                    }
	
	                    currentRange = Number(parentEle.firstElementChild.style.WebkitTransform.replace(/translateX\(/g, "").replace(/px\)/g, ""));
	                    _startX = touch.pageX;
	                    _startY = touch.pageY;
	                }
	            }
	        }, false);
	        // 触摸过程
	        window.addEventListener("touchmove", function (event) {
	            if (event.targetTouches.length == 1) {
	                var touch = event.targetTouches[0];
	                direction = GetSlideDirection(_startX, _startY, touch.pageX, touch.pageY);
	                if (direction == 3 || direction == 4) {
	                    event.preventDefault();
	                    if (H.isElement(parentEle)) {
	                        var range = touch.pageX - _startX;
	                        if (range <= 0) {
	                            if (range - currentRange >= -scrollWidth && range - currentRange <= 0) {
	                                setScroll(parentEle, (range - currentRange), 0);
	                            }
	                        }
	                        else {
	                            if (range + currentRange <= 0) {
	                                setScroll(parentEle, (range + currentRange), 0);
	                            }
	                        }
	                    }
	                }
	            }
	        }, false);
	        // 触摸接触
	        window.addEventListener("touchend", function (event) {
	            var touch = event.targetTouches[0];
	            if (H.isElement(parentEle)) {
	                currentRange = Number(parentEle.firstElementChild.style.WebkitTransform.replace(/translateX\(/g, "").replace(/px\)/g, ""));
	                if (direction == 3 && (currentRange < 0)) {
	                    setScroll(parentEle, -scrollWidth, 0.4);
	                }
	                else if (direction == 4 && currentRange < 80) {
	                    setScroll(parentEle, 0, 0.4);
	                }
	                else {
	                    setScroll(parentEle, 0, 0.4);
	                }
	            }
	        }, false);
	        // 点击
	        window.addEventListener("click", function (event) {
	            var parentEle = H.getParents(event.target, "H-qq-item");
	            if (H.isElement(parentEle)) {
	                setScroll(parentEle, 0, 0.4);
	            }
	        }, false);
	    </script>
	    <%} %>
</body></html>