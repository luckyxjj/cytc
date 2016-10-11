/**
 * 
 */
//获取工作环境图片
$(function(){
	$.get("environment/getEnvironmentInfo",function(data,status){
		for(var i = 0; i < data.length; i++){
			$("#environment").append(
					"<div class='row'>"+
					"<div class='col-xs-12'>"+
					"<img src='"
					+data[i].imgUrl+
					"'+ class='img-rounded img-responsive'>"+
					"</div>"+
					"</div>"+"<br/>"
					)
		}
	});
});