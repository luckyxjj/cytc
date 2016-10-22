/**
 * 
 */

$(function(){
	var otherHref = window.location.href;
	var arrHref = otherHref.split("?user=");
	var username = arrHref[1];//arrHref[1]就是username
	
	
	
	$.post("message/getMessageList",{admId:username},function(data,stutas){
		
		for(var i=0; i<data.length;i++){
			
			$("#message").append(
					"<div class='row' onclick='getMessage()'>"+
					"<div class='col-xs-10 col-xs-offset-1 admin_message'>"+
						"<p class='about_font'>"+
						data[i].sendNo+":"+data[i].content+
						"</p>"+
						"<span id='message_sendNo' class='hidden'>"+data[i].sendNo+
						"</span>"+
						"<span id='message_receiverNo' class='hidden'>"+data[i].receiverNo+
						"</span>"+
					"</div>"+
				 "</div>"+
				 "<br/>"
					
			)
		}
	});
	
});

function getMessage(){
	var message_sendNo = $("#message_sendNo").text();
	var message_receiverNo = $("#message_receiverNo").text();
	
	window.location.href="message_admin.html?message_No="+message_sendNo+","+message_receiverNo;
}



