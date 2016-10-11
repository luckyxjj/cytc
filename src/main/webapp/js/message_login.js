/**
 * 
 */
function message_login(){
	if($("#uer:checked")){
	var userName = $("#userName").val();
	var password = $("#password").val();
	
	$.post("login/userLoginValidate",{userName:userName,password:password},function(data,stutas){
		if(data=="登入成功"){
			window.location.href="message.html?user="+userName;
		}else{
			alert("用户名或者密码错误！");
		}
		});
	}else if($("#admin:checked")){
		var userName = $("#userName").val();
		var password = $("#password").val();
		
		$.post("",{userName:userName,password:password},function(data,stutas){
			if(data=="登入成功"){
				window.location.href="admin_message.html?user="+userName;
			}else{
				alert("用户名或者密码错误！");
			}
			});
	}
}