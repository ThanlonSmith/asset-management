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
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container" style="margin-top: 65px">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="<%=path%>/QueryAssetInfoServ">个人中心</a></li>
				<li class="active">修改密码</li>
			</ol>
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel panel-body">
						<form action="<%=path%>/AlterPwdServ" method="post">
							<input type="text" value="<%=admin%>" name="name" hidden="hidden">
							<div class="form-group">
								<label>原密码</label> <input type="text" name="pwd"
									class="form-control" placeholder="请输入原始密码">
							</div>
							<div class="form-group">
								<label>新密码</label> <input type="text" name="newpwd"
									class="form-control" placeholder="请输入新密码(至少6位密码)">
							</div>
							<input type="submit" value="修改" name="newPwd" onclick="return confirm('确认修改？')" class="btn btn-primary"
								style="background: #a94442; border-color: #a94442">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="bottom.jsp"%>
</body>
</html>