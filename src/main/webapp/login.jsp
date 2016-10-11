<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/my.css" />
<link rel="stylesheet" href="css/bootstrapValidator.css" />
<script src="js/jquery-1.12.3.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/my.js"></script>
<script src="js/bootstrapValidator.js"></script>

<title>登入</title>
</head>
<body>
	<div class="container-fluid">
		<div
			class="col-lg-6 col-lg-offset-3 table-bordered right-bg block-center"
			style="margin-top: 100px;">
			<form class="form-horizontal" id="login_form" method="post"
				action="login/adminLoginValidate" enctype="multipart/form-data"
				style="padding: 30px 0px;">
				<p class="caption">后台登入</p>


				<div class="form-group">
					<label class="col-lg-4 text-right">用户名： </label>
					<div class="col-lg-6">
						<input type="text" name="userName" class="form-control"
							placeholder="请输入用户名" required data-bv-notempty-message="用户名不能为空"
							pattern="^[0-9]+$" data-bv-regexp-message="只能输入数字" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-4 text-right">密码： </label>
					<div class="col-lg-6">
						<input type="password" name="password" class="form-control"
							placeholder="请输入密码" required data-bv-notempty-message="密码不能为空"
							pattern="^[A-Za-z0-9]+$" data-bv-regexp-message="只能输入英文字母和数字"
							maxlength="20" data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
			
				<div class="form-group">
					<div class="col-lg-offset-4 col-lg-6" style="text-align: center;">
						<button type="submit" class="btn btn-primary">登入</button>

						<span style="padding-left: 15px; color: red;">${tip }</span>
					</div>

				</div>
			</form>

			<span class="warning-tip" id="warning_tip"></span>
		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#login_form').bootstrapValidator({
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				live : 'enabled',
				message : 'This value is not valid',
			/* submitButtons : 'button[type="submit"]' */

			});
		});
	</script>
</body>
</html>