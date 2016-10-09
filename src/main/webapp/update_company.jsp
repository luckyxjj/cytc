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
<script src="js/bootstrap.js"></script>
<script src="js/my.js"></script>
<script src="js/bootstrapValidator.js"></script>
<title>后台管理系统</title>
</head>
<body>
	<div class="container-fluid">
		<div class="col-lg-12 nav-bg bg-primary">
			<div class="col-lg-11">
				<h2 class="top-title">后台管理系统</h2>
			</div>
			<div class="col-lg-1 back-index">
				<a href="teacher/logout">注销</a>
			</div>
		</div>
		<div class="left-bg col-lg-3">
			<div class="panel-group ">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="homeImg/getHomeImg">主页图片 </a>
						</h4>
					</div>
				</div>
			</div>
			<div class="panel-group ">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="production/getProduction">作品 </a>
						</h4>
					</div>
				</div>
			</div>
			<div class="panel-group ">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="company/getCompany">公司信息 </a>
						</h4>
					</div>
				</div>

			</div>
		</div>
		<div class="col-lg-9 table-bordered right-bg">
			<form class="form-horizontal" id="update_company_form" method="post"
				action="company/updateCompany" enctype="multipart/form-data">
				<p class="caption">修改公司信息</p>
				<div class="form-group">
					<label class="col-lg-2 text-right">公司名： </label>
					<div class="col-lg-8">
						<input type="text" name="companyName" class="form-control"
							value="${company.companyName }" placeholder="请输入公司名" required
							data-bv-notempty-message="公司名不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">电话： </label>
					<div class="col-lg-8">
						<input type="text" name="phone" class="form-control"
							value="${company.phone }" placeholder="请输入电话" required
							data-bv-notempty-message="电话不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">传真： </label>
					<div class="col-lg-8">
						<input type="text" name="fax" class="form-control"
							value="${company.fax }" placeholder="请输入传真" required
							data-bv-notempty-message="传真不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">地址： </label>
					<div class="col-lg-8">
						<input type="text" name="address" class="form-control"
							value="${company.address }" placeholder="请输入地址" required
							data-bv-notempty-message="地址不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">QQ： </label>
					<div class="col-lg-8">
						<input type="text" name="qq" class="form-control"
							value="${company.qq }" placeholder="请输入QQ" required
							data-bv-notempty-message="QQ不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">邮箱： </label>
					<div class="col-lg-8">
						<input type="text" name="mail" class="form-control"
							value="${company.mail }" placeholder="请输入邮箱" required
							data-bv-notempty-message="邮箱不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">微博： </label>
					<div class="col-lg-8">
						<input type="text" name="microblog" class="form-control"
							value="${company.microblog }" placeholder="请输入微博" required
							data-bv-notempty-message="微博不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">微信： </label>
					<div class="col-lg-8">
						<input type="text" name="wechat" class="form-control"
							value="${company.wechat }" placeholder="请输入微信" required
							data-bv-notempty-message="微信不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 text-right">公司LOGO： </label>
					<div class="col-lg-9 update-img-border">
						<input type="text" name="logoUrl" value="${company.logo }"
							class="form-control id-hide" /> <a href="${company.logo }"
							target="_blank"> <img alt="" src="${company.logo }"
							width="100" height="80">
						</a>
						<p>改为：</p>
						<input type="file" name="logoFile" maxlength="80"
							data-bv-stringlength-message="长度不能超过80个字符" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 text-right">公司形象图片： </label>
					<div class="col-lg-9 update-img-border">
						<input type="text" name="imagePhotoUrl" value="${company.imagePhoto }"
							class="form-control id-hide" /> <a href="${company.imagePhoto }"
							target="_blank"> <img alt="" src="${company.imagePhoto }"
							width="100" height="80">
						</a>
						<p>改为：</p>
						<input type="file" name="imagePhotoFile" maxlength="80"
							data-bv-stringlength-message="长度不能超过80个字符" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 text-right">地图： </label>
					<div class="col-lg-9 update-img-border">
						<c:forEach items="${company.companyImgList}" var="companyImg"
							varStatus="status">
							<div id="del-file" style="float: left; padding-bottom: 8px;">
								<input type="text" name="mapUrl" value="${companyImg.imgUrl }"
									class="form-control id-hide" /> <a
									href="${companyImg.imgUrl }" target="_blank"> <img alt=""
									src="${companyImg.imgUrl }" width="100" height="80">
								</a> <input type="text" name="mapId" value="${companyImg.id }"
									class="form-control id-hide" />
								<p>改为：</p>
								<input type="file" name="mapFile" maxlength="80"
									data-bv-stringlength-message="长度不能超过80个字符" />
							</div>

						</c:forEach>
					</div>
				</div>
				<div style="clear: left; display: none;"></div>
				
				<div class="form-group id-hide">
					<div class="col-lg-6 col-lg-offset-2">
						<input type="text" name="id" value="${company.id }"
							class="form-control">
					</div>

				</div>
				
				<div class="form-group">
					<div class="col-lg-offset-6 col-lg-6">
						<button type="submit" class="btn btn-primary">修改</button>
						<span style="padding-left: 100px; color: green;">${tip }</span>
					</div>
				</div>
			</form>
			<span class="warning-tip" id="warning_tip"></span>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#update_company_form').bootstrapValidator({
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				live : 'enabled',
				message : 'This value is not valid',
				submitButtons : 'button[type="submit"]'

			});
		});
	</script>
</body>
</html>