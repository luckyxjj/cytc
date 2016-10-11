/**
 * 
 */
$(function() {
	$("#myCarousel ").carousel({
		interval : 2500
	});
	
	$.get("homeImg/getHomeImgInfo",function(data,status){
		for (var i = 0; i < data.length; i++) {
			if(i==0){
				$("#lunbo").append(
						
						"<div class='item active'>"
						+"<img src='"
						+data[i].imgUrl+
						"'+ class='img-rounded img-responsive'>"
						+"</div>"
						
						)
						
				
			}else{$("#lunbo").append(
		
					"<div class='item'>"
					+"<img src='"
					+data[i].imgUrl+
					"'+ class='img-rounded img-responsive'>"
					+"</div>"
					
					)}
		}
	});

});



