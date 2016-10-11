/**
 * 
 */
$(function(){
	//获取关于创艺的简介
	$.get("about/getAboutInfo",function(data,status){
		$("#about_introduce").text(data[0].introduce);
		});
	
	//获取关于创艺的企业文化
	$.get("about/getAboutInfo",function(data,status){
		$("#about_culture").text(data[0].culture);
		});
	
	//获取更新时间
	$.get("about/getAboutInfo",function(data,status){
		$("#about_time").text(data[0].updateTime);
		});
	
	//获取公司logo
	$.get("about/getAboutInfo",function(data,status){
		for (var i = 0; i < data.length; i++) {
		
			$("#about_img").append(
					"<div class='row'>"+
					"<div class='col-xs-12'>"+
					"<img src='"
					+data[0].aboutImgList[0].imgUrl+
					"' class='img-rounded img-responsive'>"+
					"</div>"+
					"</div>"+"<br/>"
					
			)
		}
	
		
		
	});
	
	
	
	
	
	
	});
