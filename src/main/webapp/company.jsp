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
<script type="text/javascript" src="js/member_img_back.js"></script>

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
				<a href="teacher/logout" >注销</a>
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
							<a role="button" href="production/getProduction">作品 </a>
						</h4>
					</div>					
				</div>

			</div>
			<div class="panel-group " >
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

			<table
				class="table table-bordered table-hover table-condensed table-text-align">
				<caption>
					<p class="title-info">公司信息管理</p>
					
					<a class="add-align btn btn-success " href="add_company.jsp">新增</a>
				</caption>

				<thead>
					<tr style="background-color: #337AB7;">
						<th>编号</th>
						<th>公司名称</th>
						<th>电话</th>
						<th>传真</th>
						<th>地址</th>
						<th>QQ</th>
						<th>邮箱</th>
						<th>微博</th>
						<th>微信</th>
						<th>公司LOGO</th>
						<th>公司形象图片</th>
						<th>创建时间</th>
						<th>修改时间</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody id="member_img_info">
					<c:forEach items="${companyList}" var="company" varStatus="status">
						<tr>
							<th scope="row">${status.index + 1}</th>							
							<td>${company.companyName }</td>
							<td>${company.phone }</td>
							<td>${company.fax }</td>
							<td>${company.address }</td>
							<td>${company.qq }</td>
							<td>${company.mail }</td>
							<td>${company.microblog }</td>
							<td>${company.wechat }</td>
							<td><a href="${company.logo }" target="_blank"><img src="${company.logo }"  width="80" height="60"></a></td>
							<td><a href="${company.imagePhoto }" target="_blank"><img src="${company.imagePhoto }"  width="80" height="60"></a></td>
							<td>${company.createTime }</td>
							<td>${company.updateTime }</td>

							<td>
								<a href="company/getCompanyById?id=${company.id}" class="btn btn-primary"> 
									修改
								</a>
							</td>
							<td>
								<button id="delete" type="button"
									onclick="deleteCompany(${company.id })" class="btn btn-primary" >删除</button>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>

			<span class="warning-tip" id="warning_tip"></span>
		</div>

	</div>
</body>
</html>