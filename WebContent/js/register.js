(function($) {
        $.fn.extend({
            addClear: function(options) {
                var options = $.extend({
                    closeSymbol: "&#10006;",
                    color: "#CCC",
                    top: 1,
                    right: 4,
                    returnFocus: true,
                    showOnLoad: false,
                    onClear: null
                }, options);

                $(this).wrap("<span style='position:relative;' class='add-clear-span'>");
                $(this).after("<a href='#clear'>" + options.closeSymbol + "</a>");
                $("a[href='#clear']").css({
                    color: options.color,
                    'text-decoration': 'none',
                    display: 'none',
                    'line-height': 1,
                    overflow: 'hidden',
                    position: 'absolute',
                    right: options.right,
                    top: options.top
                }, this);

                if ($(this).val().length >= 1 && options.showOnLoad === true) {
                    $(this).siblings("a[href='#clear']").show();
                }

                $(this).keyup(function() {
                    if ($(this).val().length >= 1) {
                        $(this).siblings("a[href='#clear']").show();
                    } else {
                        $(this).siblings("a[href='#clear']").hide();
                    }
                });

                $("a[href='#clear']").click(function() {
                    $(this).siblings("input").val("");
                    $(this).hide();
                    if (options.returnFocus === true) {
                        $(this).siblings("input").focus();
                    }
                    if (options.onClear) {
                        options.onClear($(this).siblings("input"));
                    }
                    return false;
                });
                return this;
            }
        });
    }
)(jQuery);

var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
var pattern = /^[\w_-]{6,16}$/;
var count = 60;
var InterValObj1;
var curCount1;
function sendMessage1() {
    curCount1 = count;
    var phone = $.trim($('#phone').val());
    if (!phoneReg.test(phone)) {
        alert(" 请输入有效的手机号码");
        return false;
    }else
    {
    	
    	$.post('smsServlet', {mobile:jQuery.trim($('#phone').val())}, function(res) {
            //alert(jQuery.trim(unescape(msg)));
    		
			if(res.msg=='OK'){
				SetRemainTime1();
			}else{
				location.reload();
			}
        },"json");
    }
    $("#btnSendCode1").attr("disabled", "true");
    $("#btnSendCode1").val(+curCount1 + "秒再获取");
    InterValObj1 = window.setInterval(SetRemainTime1, 1000);

}
function SetRemainTime1() {
    if (curCount1 == 0) {
        window.clearInterval(InterValObj1);
        $("#btnSendCode1").removeAttr("disabled");
        $("#btnSendCode1").val("重新发送");
    } else {
        curCount1--;
        $("#btnSendCode1").val(+curCount1 + "秒再获取");
    }
}

var LoginSuccess = "Login.jsp";		
var LoginFail = "register.jsp";
function CheckInput()
{
	var phone = $.trim($('#phone').val());
	var pwd = $.trim($('#Rpwd').val());
	var mobile_code = $.trim($('#code').val());
	if (!phoneReg.test(phone)) {
        alert(" 请输入有效的手机号码");
       
    }
	else
    {
		
		if (!pattern.test(pwd)) {
	        alert("密码不规范");
	       
	    }
		else
		{
			$.post('RegisterServlet', {
				"Rmobile" : phone,
				"Rpwd":pwd,
				"mobile_code":mobile_code
			},function(res)
			{
				
				
				if(res.code!=200)
				{
					alert(res.msg);	
				}
				else
				{
					var istrue = confirm('注册成功！返回登录');
					if(istrue)
					{
						location.href=LoginSuccess
					}
					else
					{
						location.href=LoginFail
					}
					
				}
				
			},"json");
		}
    }
	
	
}
function binding() {
    alert(1)
}
