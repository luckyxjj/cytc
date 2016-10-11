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
				<a href="login/logout">注销</a>
			</div>	
		</div>
		
		<div class="left-bg col-lg-3">
			<div class="panel-group " >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="homeImg/getHomeImg">主页图片 </a>
						</h4>
					</div>
				</div>
			</div>
			
			<div class="panel-group " >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="about/getAbout">关于创艺</a>
						</h4>
					</div>
				</div>
			</div>
			
			<div class="panel-group " >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="business/getBusiness">业务范围</a>
						</h4>
					</div>
				</div>
			</div>
			
			<div class="panel-group " >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="environment/getEnvironment">工作环境</a>
						</h4>
					</div>
				</div>
			</div>
			
			<div class="panel-group " >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="company/getCompany">公司信息</a>
						</h4>
					</div>
				</div>
			</div>
			
			<div class="panel-group " >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="production/getProduction">作品管理</a>
						</h4>
					</div>
				</div>
			</div>
			
			<div class="panel-group " >
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a role="button" href="admin/getAdmin">管理员</a>
						</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-9 table-bordered right-bg">
			<form class="form-horizontal" id="update_production_form" method="post"
				action="production/updateProduction" enctype="multipart/form-data">
				<p class="caption">修改作品信息</p>


				<div class="form-group">
					<label class="col-lg-2 text-right">作品描述： </label>
					<div class="col-lg-9">
						<textarea type="text" name="description" rows="2" cols="20"
							class="form-control" required
							data-bv-notempty-message="作品描述不能为空" maxlength="200"
							data-bv-stringlength-message="长度不能超过200个字符">${production.description }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">作品类别： </label>
					<div class="col-lg-8">
						<input type="text" name="category" class="form-control"
							value="${production.category }" placeholder="请输入作品类别" required
							data-bv-notempty-message="作品类别不能为空" maxlength="20"
							data-bv-stringlength-message="长度不能超过20个字符" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 text-right">图片： </label>
					<div class="col-lg-9">
						<c:forEach items="${production.productionImgList}" var="productionImg"
							varStatus="status">
							<input type="text" name="imgUrl" value="${productionImg.imgUrl }"
								class="form-control id-hide" />
							<a href="${productionImg.imgUrl }" target="_blank"> <img alt=""
								src="${productionImg.imgUrl }" width="100" height="80">
							</a>
							<input type="text" name="imgId" value="${productionImg.id }"
									class="form-control id-hide" />
							<p>改为：</p>
							<input type="file" name="productionFile" maxlength="80"
								data-bv-stringlength-message="长度不能超过80个字符" />
						</c:forEach>
					</div>
				</div>

				<div class="form-group id-hide">
					<div class="col-lg-9 col-lg-offset-2">
						<input type="text" name="id" value="${production.id }"
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
			$('#update_production_form').bootstrapValidator({
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