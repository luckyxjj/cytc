/**
 * 
 */
$(function(){
	var otherHref = window.location.href;
	var arrHref = otherHref.split("?message_No=");
	var no = arrHref[1];
	
	var msg = no.split("&");
    var sendNo = ""; 
    var receiverNo = "";
	if(msg[0]==0){
		sendNo=msg[1];
		receiverNo=msg[0];
	}else{
		sendNo=msg[0];
		receiverNo=msg[1];
	}
	$.post("message/getMessageAdm",{sendNo:sendNo,receiverNo:receiverNo,unreadId:msg[2]},function(data,stutas){
		for(var i=0;i<data.length;i++){
			
			if(receiverNo==data[i].sendNo){
				$("#message").append(
						"<div class='row'>"+
						"<div class='col-xs-10 col-xs-offset-1' align='right'>"+
						"<p class='about_font'>"
						+data[i].content+
						"</p>"+
						"<p class='about_fontsmall'>"
						+data[i].createTime+
						"</p>"+
						"</div>"+
						"</div>"+"<br/>")
						}
			if(sendNo==data[i].sendNo){
							$("#message").append(
									"<div class='row'>"+
									"<div class='col-xs-10 col-xs-offset-1'>"+
									"<p class='about_font'>"+data[i].sendNo+":"
									+data[i].content+
									"</p>"+
									"<p class='about_fontsmall'>"
									+data[i].createTime+
									"</p>"+
									"</div>"+
									"</div>"+"<br/>")
						}
			}
	
	});
});

function message(){
	var otherHref = window.location.href;
	var arrHref = otherHref.split("?message_No=");
	var no = arrHref[1];
	
	var msg = no.split("&");
	
	var text = $("#message_text").val();
	
	
	if(text==""){
		alert("请输入留言信息");
	}else{$.post("message/addMessageAdm",{sendNo:msg[1],receiverNo:msg[0],content:text},function(data,status){
		
		if(status == "success"){
			if(data=="添加失败"){
				alert("留言失败");
			}else{
				
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

