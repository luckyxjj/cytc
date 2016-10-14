/**
 * 
 */

$(function(){
	var otherHref = window.location.href;
	var arrHref = otherHref.split("?user=");
	var username = arrHref[1];//arrHref[1]就是username
	
	$.post("message/getMessageList",{adminId:0},function(data,status){
		alert(data[0].content);
		/*for(var i=0; i<data.length;i++){
			$("#message").append(
					"<div class='container-fluid'>"+
					 "<div class='row'>"+
						"<div class='col-xs-10 col-xs-offset-1 admin-message'>"+
							"<p class='about_font'>"+
							data[i].sendNo
							+"</p>"+
							"<p class='about_font'>"+
							data[i].content+
							+"</p>"+
						"</div>"+
					"</div>"+
				"</div>"
					
			)
		}*/
	});
	
});