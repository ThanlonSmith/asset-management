<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String admin = (String) session.getAttribute("admin");
	if (admin == null || "".equals(admin)) {
		response.sendRedirect("" + path + "/admin/login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资产管理</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%=path%>/static/css/comm.css">
</head>
<body>

	<jsp:include page="nav.jsp" />
	<div class="container" style="margin-top: 65px">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="<%=path%>/QueryEmpServ">员工管理</a></li>
				<li class="active">添加员工信息</li>
			</ol>
			<form action="<%=path%>/EmpInfoAddServ" method="post">
				<div class="form-inline">
					<div class="form-group">
						<label for="">员工编号：</label> <input type="text" name="id"
							required="required" class="form-control" placeholder="请输入员工编号">
						<label for="">姓名：</label> <input type="text" name="name"
							required="required" class="form-control" placeholder="请输入姓名">
					</div>
				</div>
				<div class="form-inline" style="margin-top: 3px">
					<div class="form-group">
						<label for="">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
						<select name="sex_id" class="form-control">
							<option value="0">男</option>
							<option value="1">女</option>
						</select> <label for="">年龄：</label> <input type="text" name="age"
							required="required" class="form-control" placeholder="请输入年龄">
					</div>
				</div>
				<div class="form-inline" style="margin-top: 3px">
					<div class="form-group">
						<label for="">电话号码：</label> <input type="text" name="phone"
							required="required" class="form-control" placeholder="请输入电话号码">
					</div>
				</div>
				<div class="form-inline" style="margin-top: 3px">
					<div class="form-group">
						<label for="">所在部门：</label> <select name="department_id"
							class="form-control">
							<option value="1">教务处</option>
							<option value="2">工学院</option>
							<option value="3">商学院</option>
							<option value="4">艺术学院</option>
						</select> &nbsp;&nbsp; 现任职务：<select name="post_id" class="form-control">
							<option value="1">辅导员</option>
							<option value="2">高级教师</option>
							<option value="3">特级教师</option>
						</select>
					</div>
				</div>
				<div class="form-inline" style="margin-top: 3px">
					<div class="form-group">
						<label for="">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label><select
							name="status" class="form-control">
							<option value="在职">在职</option>
							<option value="离职">离职</option>
						</select>
					</div>
				</div>
				<div class="form-inline" style="margin-top: 3px">
					<div class="form-group">
						<label for="">家庭住址：</label>
						<textarea name="address" class="form-control" cols="57" rows="2" required="required"></textarea>
					</div>
				</div>
				<button type="submit" class="btn btn-primary"
					style="background: #a94442; margin-top: 5px">添加</button>
			</form>
		</div>
	</div>
	<%@include file="bottom.jsp"%>
	<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#nav-5').addClass("bg");
		});
	</script>
</body>
</html>
