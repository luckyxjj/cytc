/**
 * 
 */

//获取留言内容
$(function(){
	var otherHref = window.location.href;
	var arrHref = otherHref.split("?user=");
	var sendNo = arrHref[1];//arrHref[1]就是username
	
	$.post("message/getMessage",{sendNo:sendNo,receiverNo:0},function(data,status){
		for(var i=0;i<data.length;i++){
			if(sendNo==data[i].sendNo){
				$("#message").append(
						"<div class='row'>"+
						"<div class='col-xs-10 col-xs-offset-1' align='right'>"+
						"<p class='about_font'>"
						+data[i].content+
						"</p>"+
						"</div>"+
						"</div>"+"<br/>")
						}
			if(0==data[i].sendNo && data[i].receiverNo){
							$("#message").append(
									"<div class='row'>"+
									"<div class='col-xs-10 col-xs-offset-1'>"+
									"<p class='about_font'>"
									+data[i].content+
									"</p>"+
									"</div>"+
									"</div>"+"<br/>")
						}
			}
	
		
	});
	
});

function message(){
	var otherHref = window.location.href;
	var arrHref = otherHref.split("?user=");//arrHref[1]就是username
	var sendNo = arrHref[1];
	
	var text = $("#message_text").val();
	
	if(text==""){
		alert("请输入留言信息");
	}else{$.post("message/addMessage",{sendNo:sendNo,receiverNo:0,content:text},function(data,status){
		if(status == "success"){
			if(data=="添加失败"){
				alert("留言失败");
			}else{
				alert("留言成功");
				$("#message").append(
						
						"<div class='row'>"+
						"<div class='col-xs-10 col-xs-offset-1' align='right'>"+
						"<p class='about_font'>"
						+text+
						"</p>"+
						"</div>"+
						"</div>"+"<br/>")
						
				$("#message_text").val("");
			}
		}else{
			alert("留言失败");
		}
		});
		
		
		}
	
}