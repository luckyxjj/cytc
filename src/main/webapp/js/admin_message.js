/**
 * 
 */

$(function(){
	var otherHref = window.location.href;
	var arrHref = otherHref.split("?user=");
	var username = arrHref[1];//arrHref[1]就是username
	
	
	
	$.post("message/getMessageList",{admId:username},function(data,stutas){
		
		for(var i=0; i<data.length;i++){
			if(data[i].unreadNum==0){
				$("#message").append(
						"<div class='row' onclick='getMessage()'>"+
						"<div class='col-xs-10 col-xs-offset-1 admin_message'>"+
							"<p class='about_font'>"+
							data[i].sendNo+":"+data[i].content+
							"</p>"+
							"<div style='text-align:right;'>"+
	
							"</div>"+
							"<span id='message_sendNo' class='hidden'>"+data[i].sendNo+
							"</span>"+
							"<span id='message_receiverNo' class='hidden'>"+data[i].receiverNo+
							"</span>"+
							"<span id='message_unreadId' class='hidden'>"+data[i].unreadId+
							"</span>"+
						"</div>"+
					 "</div>"+
					 "<br/>"
						
				)
			}else{
				$("#message").append(
						"<div class='row' onclick='getMessage()'>"+
						"<div class='col-xs-10 col-xs-offset-1 admin_message'>"+
							"<p class='about_font'>"+
							data[i].sendNo+":"+data[i].content+
							"</p>"+
							"<div style='text-align:right;'>"+
							"<span class='badge' style='background-color: #C7254E;' class='span_hidden'>"
							+data[i].unreadNum
							
							+"</span>"+
							
							"</div>"+
							"<span id='message_sendNo' class='hidden'>"+data[i].sendNo+
							"</span>"+
							"<span id='message_receiverNo' class='hidden'>"+data[i].receiverNo+
							"</span>"+
							"<span id='message_unreadId' class='hidden'>"+data[i].unreadId+
							"</span>"+
						"</div>"+
					 "</div>"+
					 "<br/>"
						
				)
			}
			
		}
	});
	
});

function getMessage(){
	var message_sendNo = $("#message_sendNo").text();
	var message_receiverNo = $("#message_receiverNo").text();
	var message_unreadId = $("#message_unreadId").text();
	
window.location.href="message_admin.html?message_No="+message_sendNo+"&"+message_receiverNo+"&"+message_unreadId;
}



