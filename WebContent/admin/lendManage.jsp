<%@page import="java.util.List"%>
<%@page import="entity.AssetLend"%>
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
				<li><a href="#">借还管理</a></li>
				<li class="active">借还信息</li>
			</ol>
			<form class="form-inline" style="margin-bottom: 5px"
				action="<%=path%>/AssetLendAddServ" method="post">
				<div class="form-group">
					<label for="">资产编号：</label> <input type="text" class="form-control"
						name="asset_id" placeholder="请输入资产编号" required="required">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">员工编号</label> <input type="text"
						class="form-control" name="employee_id" placeholder="请输入员工编号" required="required">
				</div>
				<button type="submit" class="btn btn-primary"
					style="background: #a94442">借出</button>
			</form>
			<table class="table table-hover">
				<tr>
					<th>借用人</th>
					<th>资产编号</th>
					<th>员工编号</th>
					<th>借用时间</th>
					<th>应还时间</th>
					<th>归还</th>
				</tr>
				<%
					List<AssetLend> list = (List<AssetLend>) request.getAttribute("list");
					if (list == null || list.size() < 1) {
						out.print("<tr><td bgcolor='#FFFFFF' colspan='5'>没有任何资产信息！</td></tr>");
					} else {
						for (AssetLend v : list) {
				%>
				<tr>
					<td><%=v.getLender()%></td>
					<td><%=v.getAsset_id()%></td>
					<td><%=v.getEmployee_id()%></td>
					<td><%=v.getLendTime()%></td>
					<td><%=v.getShouldReturnTime() %></td>
					<td><a href="<%=path%>/DelAssetLendServ?lendNo=<%=v.getId()%>"
						onclick="return confirm('确认归还?')">归还 </a></td>
				</tr>
				<%
					}
					}
				%>
			</table>
			<%
				int count = (int) request.getAttribute("datacount");
				int datacount;
				if (count % 5 == 0) {
					datacount = count / 5;//如果数据总条数是每页显示数量的倍数
				} else {
					datacount = count / 5 + 1;//根据数据总条数，计算分页数量
				}

				int page_num = (int) request.getAttribute("page_num");//获取请求的页码
			%>
			<nav aria-label="Page navigation" class="text-center">
				<ul class="pagination">
					<%
						for (int i = 1; i <= datacount; i++) {
					%>
					<%
						if (page_num == i) {
					%>
					<li class="active"><a
						style="background: #a94442; border-color: #a94442"
						href="<%=path%>/QueryAssetLendInfoServ?page=<%=i%>"><%=i%></a></li>
					<%
						} else {
					%>
					<li class=""><a
						href="<%=path%>/QueryAssetLendInfoServ?page=<%=i%>"><%=i%></a></li>
					<%
						}
					%>
					<%
						}
					%>
				</ul>
			</nav>
		</div>
	</div>
	<%@include file="bottom.jsp"%>
	<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#nav-3').addClass("bg");
		});
	</script>
</body>
</html>
