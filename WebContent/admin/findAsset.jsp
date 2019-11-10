<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entity.AssetInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String admin = (String) session.getAttribute("admin");
	//System.out.print(admin);
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
				<li><a href="<%=path%>/QueryAssetInfoServ?page=1">资产管理</a></li>
				<li class="active">资产查询</li>
			</ol>
			<table class="table table-hover">
				<tr>
					<th>资产编号</th>
					<th>资产名称</th>
					<th>生产厂商</th>
					<th>单价</th>
					<th>数量</th>
					<th>使用单位</th>
					<th>存放地点</th>
					<th>购买人</th>
					<th>资产类型</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<%
					List<AssetInfo> list = (List<AssetInfo>) request.getAttribute("list");
					if (list == null || list.size() < 1) {
						out.print("<tr><td bgcolor='#FFFFFF' colspan='5'>您可根据资产名关键字检索资产信息！</td></tr>");
					} else {
						for (AssetInfo v : list) {
				%>
				<tr>
					<td><%=v.getAssetNo()%></td>
					<td><%=v.getAssetName()%></td>
					<td><%=v.getManufacturer()%></td>
					<td><%=v.getAssetUnitPrice()%></td>
					<td><%=v.getAssetNum()%></td>
					<td><%=v.getUserCompany()%></td>
					<td><%=v.getStoragePlace()%></td>
					<td><%=v.getPurchaser()%></td>
					<td><%=v.getAssetType()%></td>
					<td><%=v.getAssetStatus()%></td>
					<td><a
						href="<%=path%>/QueryEditAssetServ?assetNo=<%=v.getAssetNo()%>">编辑</a>
						<a href="<%=path%>/DelAssetInfoServ?assetNo=<%=v.getAssetNo()%>"
						onclick="return confirm('确认删除？')">&nbsp;&nbsp;删除</a></td>
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
						href="<%=path%>/QueryAssetByNameServ?page=<%=i%>"><%=i%></a></li>
					<%
						} else {
					%>
					<li class=""><a
						href="<%=path%>/QueryAssetByNameServ?page=<%=i%>"><%=i%></a></li>
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
			$('#nav-2').addClass("bg");
		});
	</script>
</body>
</html>