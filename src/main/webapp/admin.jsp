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




			<table
				class="table table-bordered table-hover table-condensed table-text-align">
				<caption>
					<p class="title-info">管理员</p>
					
					<a class="add-align btn btn-success " href="add_admin.jsp">新增</a>
					
				</caption>

				<thead>
					<tr style="background-color: #337AB7;">
						<th>编号</th>
						<th>用户名</th>
						<th>密码</th>
						
						<th>创建时间</th>
						<th>修改时间</th>
						<th></th>
						<th></th>
						
						
					</tr>
				</thead>
				<tbody id="member_img_info">
					<c:forEach items="${admintList}" var="admin" varStatus="status">

						<tr>
							<th scope="row">${status.index + 1}</th>
							<td>${admin.userName}</td>
							<td>${admin.password}</td>
							
							<td>${admin.createTime}</td>
							<td>${admin.updateTime}</td>

							<td><a href="admin/getAdminById?id=${admin.id}"
								class="btn btn-primary"> 修改 </a></td>
							<td>
								<button id="delete" type="button"
									onclick="deleteAdmin(${admin.id})" class="btn btn-primary">删除</button>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			
			
			

			<span class="warning-tip" id="warning_tip"></span>
		</div>
		
		


</div>
<script type="text/javascript">
		$(document).ready(function() {
			$('#add_link_form').bootstrapValidator({
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