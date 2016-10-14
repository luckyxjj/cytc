$(function() {

	// 获取更新时间
	$.get("message/getMessageList?admId=111", function(data, status) {
		/* alert(data[1].unreadId); */
		$("#unreadId").text(data[0].unreadId);
	});

});

function getMessage() {
	var unreadId = $("#unreadId").text();
	$.post("message/getMessageAdm", {
		"unreadId" : unreadId,
		"sendNo" : "111",
		"receiverNo" : "444"
	}, function(data, status) {
		if (status == "success") {
			if (data == "请先登入") {
				window.location.href = "login.jsp";
			} else {
				window.location.reload();
			}
		} else {
			alert("获取失败，请重试");
		}
	});
}
