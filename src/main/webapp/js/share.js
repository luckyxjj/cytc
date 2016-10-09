$(document).ready(function() {
	var loc = location.href;
	var length = loc.length; // 地址的总长度
	var position = loc.indexOf("="); // 取得=号的位置
	var memberId = loc.substring(position + 1); // 截取id
	//id中若带有其它字符则截取掉
	
		/*首次加载n张图片*/
		$.get("memberImg/getPartMemberImg?id="+memberId+"&mark=0", function(data, status) {
			if (status == "success") {
				for (var i = 0; i < data.length; i++) {
					$("#img_row").append(
							
							"<div class='col-lg-4 col-md-6 col-sm-6 control-col-padding'><div class='thumbnail'><img src='"
							+data[i].imgUrl+
							"' class='img-fluid sigmapad'><div class='caption text-center'><h3>教师评语 <span>"
							+data[i].course
							+"</span></h3><p class='create_time'>"
							+data[i].createTime
							+"</p><p>"
							+data[i].comment
							+"</p></div></div></div>"
							)
				}
			} else {
				alert("获取失败，请重试");
			}
		});
		
		
		
	
	
	
	
	
	

});
