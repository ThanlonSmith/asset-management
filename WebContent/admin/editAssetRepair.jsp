<%@page import="java.util.List"%>
<%@page import="entity.AssetRepairInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<li><a href="<%=path%>/QueryAssetInfoServ">维修管理</a></li>
				<li class="active">修改资产维修信息</li>
			</ol>
			<form action="<%=path%>/AssetRepairEditServ" method="post">
				<%
					List<AssetRepairInfo> list = (List<AssetRepairInfo>) request.getAttribute("list");
					if (list != null && list.size() > 0) {
						for (AssetRepairInfo v : list) {
				%>
				<div class="form-inline">
					<div class="form-group">
						<label for="">资产编号：</label><input name="id" hidden="hidden"
							value="<%=v.getId()%>" /> <input type="text" name="assetId"
							value="<%=v.getAssetId()%>" required="required"
							class="form-control" placeholder="请输入资产编号">
					</div>
					<!-- 
					<div class="form-group" >
						<label for="" hidden="hidden">送修时间：</label> <input hidden type="text"
							class="form-control" placeholder="点击选择时间" required="required" >
					</div>
					 -->
				</div>
				<div class="form-inline" style="margin-top: 5px">
					<div class="form-group">
						<label for="">送&nbsp;&nbsp;修&nbsp;&nbsp;人：</label> <input
							type="text" name="sendRepairPerson"
							value="<%=v.getSendRepairPerson()%>" required="required"
							class="form-control" id="" placeholder="请输入送修人信息">
					</div>
					<div class="form-group">
						<label for="">经&nbsp;&nbsp;手&nbsp;&nbsp;人:</label> <input
							type="text" name="passHandPerson"
							value="<%=v.getPassHandPerson()%>" required="required"
							class="form-control" id="" placeholder="请输入经手人信息">
					</div>
				</div>
				<div style="margin-top: 10px">
					<label>故障原因：</label>
					<textarea class="form-control" rows="3" placeholder="简要描述故障原因"
						required="required" name="repairReason"><%=v.getRepairReason()%></textarea>
				</div>
				<button type="submit" class="btn btn-primary"
					style="background: #a94442; margin-top: 5px">修改</button>
				<%
					}
					}
				%>
			</form>
		</div>
	</div>
	<%@include file="bottom.jsp"%>
	<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#nav-4').addClass("bg");
		});
	</script>
</body>
</html>