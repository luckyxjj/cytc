/**
 * 
 */
$(function(){
	var otherHref = window.location.href;
	var arrHref = otherHref.split("?message_No=");
	var No = arrHref[1];
	
	$.post("message/getMessageAdm",{sendNo:No[0],receiverNo:No[2]},function(data,stutas){
		for(var i=0;i<data.length;i++){
			if(No[0]==data[i].sendNo){
				$("#message").append(
						"<div class='row'>"+
						"<div class='col-xs-10 col-xs-offset-1' align='right'>"+
						"<p class='about_font'>"
						+data[i].content+
						"</p>"+
						"</div>"+
						"</div>"+"<br/>")
						}
			if(No[2]==data[i].sendNo && data[i].receiverNo){
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
	var arrHref = otherHref.split("?message_No=");
	var No = arrHref[1];
	
	var text = $("#message_text").val();
	
	if(text==""){
		alert("请输入留言信息");
	}else{$.post("message/addMessageAdm",{sendNo:No[0],receiverNo:No[2],content:text},function(data,status){
		if(data=="添加失败"){
			alert("留言失败");
		}else{
			alert("留言成功");
			$("#message_send").append(
					
					"<div class='row'>"+
					"<div class='col-xs-10 col-xs-offset-1' align='right'>"+
					"<p class='about_font'>"
					+text+
					"</p>"+
					"</div>"+
					"</div>"+"<br/>")
					
			$("#message_text").val("");
		}
		
	});
		
	}
	
	
	
}

