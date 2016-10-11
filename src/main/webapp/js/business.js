/**
 * 
 */
$(function(){
	$.get("business/getBusinessInfo",function(data,status){
		$("#business_one").text(data[0].description);
		});
	
	$.get("business/getBusinessInfo",function(data,status){
		$("#business_am").attr("src",data[0].businessImgList[0].imgUrl);
	});
	
	$.get("business/getBusinessInfo",function(data,status){
		$("#business_tow").text(data[1].description);
		});

	$.get("business/getBusinessInfo",function(data,status){
		$("#business_yd").attr("src",data[1].businessImgList[0].imgUrl);
	});
	
	$.get("business/getBusinessInfo",function(data,status){
		$("#business_works").text(data[2].description);
		});
	
	$.get("business/getBusinessInfo",function(data,status){
		$("#business_produce").text(data[3].description);
		});
	$.get("business/getBusinessInfo",function(data,status){
		$("#business_hongbao").attr("src",data[4].businessImgList[0].imgUrl);
	});
	$.get("business/getBusinessInfo",function(data,status){
		$("#mingxingpian").attr("src",data[5].businessImgList[0].imgUrl);
	});
	
	$.get("business/getBusinessInfo",function(data,status){
		$("#business_gongxi").text(data[6].description);
		});
	$.get("business/getBusinessInfo",function(data,status){
		$("#business_anjianhuan").attr("src",data[6].businessImgList[0].imgUrl);
	});
});