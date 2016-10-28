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
						"<div class='row' onclick='getMessage("+data[i].sendNo+","+data[i].receiverNo+","+0+")'>"+
						"<div class='col-xs-10 col-xs-offset-1 admin_message'>"+
							"<p class='about_font' style='padding-top: 20px;'>"+
							data[i].sendNo+":"+data[i].content+
							"</p>"+
							"<div style='text-align:right;'>"+
	
							"</div>"+
							/*"<span id='message_sendNo' class='hidden'>"+data[i].sendNo+
							"</span>"+
							"<span id='message_receiverNo' class='hidden'>"+data[i].receiverNo+
							"</span>"+
							"<span id='message_unreadId' class='hidden'>"+data[i].unreadId+
							"</span>"+*/
						"</div>"+
					 "</div>"+
					 "<br/>"
						
				)
			}else{
				/*alert(data[i].sendNo+","+data[i].receiverNo);*/
				$("#message").append(
						"<div class='row' onclick='getMessage("+data[i].sendNo+","+data[i].receiverNo+","+data[i].unreadId+")'>"+
						"<div class='col-xs-10 col-xs-offset-1 admin_message'>"+
						 "<div class='row'>"+
						   "<div class='col-xs-10'>"+
							"<p class='about_font' style='padding-top: 20px;'>"+
							data[i].sendNo+":"+data[i].content+
							"</p>"+
							"</div>"+
							
							"<div class='col-xs-2' style='text-align:right; padding-top:25px;'>"+
							"<span class='badge' style='background-color: #C7254E; font-size: 40px;' class='span_hidden'>"
							+data[i].unreadNum
							
							+"</span>"+
							
							"</div>"+
							/*"<span id='message_sendNo'>"+data[i].sendNo+
							"</span>"+
							"<span id='message_receiverNo'>"+data[i].receiverNo+
							"</span>"+
							"<span id='message_unreadId' class='hidden'>"+data[i].unreadId+
							"</span>"*/
							
							"</div>"+
						"</div>"+
					 "</div>"+
					 "<br/>"
						
				)
			}
			
		}
	});
	
});

function getMessage(sendNo,receiverNo,unreadId){
	/*var message_sendNo = $("#message_sendNo").text();
	var message_receiverNo = $("#message_receiverNo").text();
	var message_unreadId = $("#message_unreadId").text();*/
	
	
window.location.href="message_admin.html?message_No="+sendNo+"&"+receiverNo+"&"+unreadId;
}



