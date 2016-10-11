/**
 * 
 */


//获取地图图片
$(function(){
	$.get("company/getCompanyInfo",function(data,status){
		for(var i = 0; i < data.length; i++){
			for(var j=0;j<data[i].companyImgList.length;j++){
				$("#map").append(
						"<div class='row'>"+
						"<div class='col-xs-12'>"+
						"<img src='"
						+data[i].companyImgList[j].imgUrl+
						"'+ class='img-rounded img-responsive'>"+
						"</div>"+
						"</div>"+"<br/>"
						)
			}
		}
	});
});