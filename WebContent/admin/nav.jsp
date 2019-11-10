<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<nav class="navbar navbar-default navbar-fixed-top"
	style="background: #a94442">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" style="color: white">高校资产管理系统</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="<%=path%>/admin/index.jsp" style="color: white;"
					id="nav-1">首页<span class="sr-only">(current)</span></a></li>
				<li><a href="<%=path%>/QueryAssetInfoServ?page=1"
					style="color: white" id="nav-2">资产管理</a></li>
				<li><a href="<%=path%>/QueryAssetLendInfoServ"
					style="color: white" id="nav-3">借还管理</a></li>
				<li><a href="<%=path%>/QueryAssetRepairInfoServ"
					style="color: white" id="nav-4">维修管理</a></li>
				<li><a href="<%=path%>/QueryEmpServ" style="color: white"
					id="nav-5">员工管理</a></li>
			</ul>
			<form action="<%=path%>/QueryAssetByNameServ" method="get"
				class="navbar-form navbar-left">
				<div class="form-group">
					<input type="text" class="form-control" name="assetName" placeholder="输入资产名关键字">
				</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<%
					String admin = (String) session.getAttribute("admin");
					if (admin == null || "".equals(admin)) {
						response.sendRedirect("" + path + "+/admin/login.jsp");
					} else {
				%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" style="color: white"><%=admin%> <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<%=path%>/admin/changePwd.jsp">修改密码</a></li>
						<li><a href="<%=path%>/SignOutServ"
							onclick="return confirm('确认退出系统？')">退出系统</a></li>

					</ul></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
</nav>
