/**
 * 
 */
$(function(){
	
	//获取更新时间
	$.get("production/getProductionInfo",function(data,status){
		$("#update_time").text(data[0].updateTime);
	});
	
	//获取作品图片与作品描述信息
	$.get("production/getProductionInfo",function(data,status){
		
	});
	
});