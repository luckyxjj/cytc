<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>
	<form class="form-horizontal" id="add_member_form" method="post"
		action="company/updateCompany" enctype="multipart/form-data">
		<div>
			公司名称<input type="text" name="companyName" />
		</div>
		<div>
			电话<input type="text" name="phone" />
		</div>
		<div>
			传真<input type="text" name="fax" />
		</div>
		<div>
			地址<input type="text" name="address" />
		</div>
		<div>
			QQ<input type="text" name="qq" />
		</div>
		<div>
			邮箱<input type="text" name="mail" />
		</div>
		<div>
			微博<input type="text" name="microblog" />
		</div>
		<div>
			微信<input type="text" name="wechat" />
		</div>

		<div>
			公司logo<input type="file" name="logoFile" />
		</div>
		<div>
			公司形象图片<input type="file" name="imagePhotoFile" />
		</div>
		<div>
			地图<input type="file" name="mapFile" />
		</div>
		<div>
			<button type="submit" id="btn_add" class="btn btn-primary">添加</button>
			<span style="padding-left: 100px; color: green;">${tip }</span>
		</div>

	</form>


</body>
</html>