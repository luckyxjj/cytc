/*删除主页轮播图片*/
function deleteHomeImg(id) {
	$.post("homeImg/deleteHomeImg", {
		"id" : id
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

/*删除关于创艺的描述*/
function deleteAbout(id) {
	$.post("about/deleteAbout", {
		"id" : id
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

/*删除管理员*/
function deleteAdmin(id) {
	$.post("admin/deleteAdmin", {
		"id" : id
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

/*删除业务范围*/
function deleteBusiness(id) {
	$.post("business/deleteBusiness", {
		"id" : id
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

/*删除工作环境*/
function deleteEnvironment(id) {
	$.post("environment/deleteEnvironment", {
		"id" : id
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

/* 删除产品 */
function deleteProduction(id) {
	$.post("production/deleteProduction", {
		"id" : id
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
/* 删除公司信息 */
function deleteCompany(id) {
	$.post("company/deleteCompany", {
		"id" : id
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

/* 添加活动图片上传按钮 */
$(document)
		.ready(
				function() {
					$("#add")
							.click(
									function() {
										$("#del-file")
												.append(
														"<input type='file' name='homeImgFile' style='padding-bottom: 8px;' required data-bv-notempty-message='图片不能为空'/>")
									});
					$("#addProduction")
							.click(
									function() {
										$("#pro_img")
												.append(
														"<input type='file' name='productionFile' style='padding-bottom: 8px;' required data-bv-notempty-message='图片不能为空'/>")
									});
					$("#addMap")
							.click(
									function() {
										$("#com_img")
												.append(
														"<input type='file' name='mapFile' style='padding-bottom: 8px;' required data-bv-notempty-message='图片不能为空'/>")
									});

				});









