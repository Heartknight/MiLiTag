function login(url) {
	var video = document.getElementById('video');
	var canvas = document.getElementById('canvas');
	var context = canvas.getContext('2d');
	var tracker = new tracking.ObjectTracker('face');
	var i = 0;
	tracker.setInitialScale(4);
	tracker.setStepSize(2);
	tracker.setEdgesDensity(0.1);
	tracking.track('#video', tracker, {
		camera : true
	});
	tracker.on('track', function(event) {
		context.clearRect(0, 0, canvas.width, canvas.height);
		event.data.forEach(function(rect) {
			while (i >= 0) {
				canvas.getContext('2d').drawImage(video, 0, 0, canvas.width,
						canvas.height);

				$.post(url, {
					"base" : canvas.toDataURL()
				}, function(res) {
					
					if (res.error_code == 0) {
						alert(res.result.user_list['0'].score);
						if (res.result.user_list['0'].score > 70) {
							
							location.href = 'main.jsp';
						}
					} else {
						alert('登陆失败,请重试');
						location.href = 'Login.jsp';
					}
				}, "json");
				i--;
			}
			context.strokeStyle = '#a64ceb';
			context.strokeRect(rect.x, rect.y, rect.width, rect.height);
			context.font = '11px Helvetica';
			context.fillStyle = "#fff";
			context.fillText('x: ' + rect.x + 'px', rect.x + rect.width + 5,
					rect.y + 11);
			context.fillText('y: ' + rect.y + 'px', rect.x + rect.width + 5,
					rect.y + 22);
		});
	});
}



function LoginByPwd()
{
		var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
		var LoginSuccess = "main.jsp";		
		var phone = $.trim($('#phone').val());
		var pwd = $.trim($('#pwd').val());
		if (!phoneReg.test(phone)) {
	        alert(" 请输入有效的手机号码");
	       
	    }
		else
	    {
				$.post('LoginByPwdServlet', {
					"Lmobile" : phone,
					"Lpwd":pwd,
				},function(res)
				{
					alert(res);
					
					if(res.code!=200)
					{
						alert(res.msg);	
					}
					else
					{
						location.href=LoginSuccess
					}
					
				},"json");
			
	    }
		
	
	

}

function reg(url) {
	var video = document.getElementById('video');
	var canvas = document.getElementById('canvas');
	var context = canvas.getContext('2d');
	var tracker = new tracking.ObjectTracker('face');
	var i = 0;
	tracker.setInitialScale(4);
	tracker.setStepSize(2);
	tracker.setEdgesDensity(0.1);
	tracking.track('#video', tracker, {
		camera : true
	});
	tracker.on('track', function(event) {
		context.clearRect(0, 0, canvas.width, canvas.height);
		event.data.forEach(function(rect) {
			while (i >= 0) {
				canvas.getContext('2d').drawImage(video, 0, 0, canvas.width,
						canvas.height);

				$.post(url, {
					"base" : canvas.toDataURL()
				}, function(result) {
					
					if (result.error_code == 0) {
						alert('注册成功,请使用人脸识别登陆');
						location.href = 'setting.jsp';
					} else {
						alert('注册失败,请刷新后重试');
						location.href = 'setting.jsp';
					}
				}, "json");
				i--;
			}
			context.strokeStyle = '#a64ceb';
			context.strokeRect(rect.x, rect.y, rect.width, rect.height);
			context.font = '11px Helvetica';
			context.fillStyle = "#fff";
			context.fillText('x: ' + rect.x + 'px', rect.x + rect.width + 5,
					rect.y + 11);
			context.fillText('y: ' + rect.y + 'px', rect.x + rect.width + 5,
					rect.y + 22);
		});
	});
}