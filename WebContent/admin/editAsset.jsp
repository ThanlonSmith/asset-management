<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entity.AssetInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
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
				<li><a href="<%=path%>/QueryAssetInfoServ">资产管理</a></li>
				<li class="active">添加资产信息</li>
			</ol>
			<form action="<%=path%>/AssetEditServ" method="post">
				<%
					List<AssetInfo> list = (List<AssetInfo>) request.getAttribute("list");
					if (list != null && list.size() > 0) {
						for (AssetInfo v : list) {
				%>
				<div class="modal-body">
					<div class="form-inline">
						<div class="form-group">
							<label for="">资产编号：</label> <input value="<%=v.getAssetNo()%>"
								name="assetNo" type="text" value="<%=v.getAssetNo()%>" hidden="hidden"/>
								<input value="<%=v.getAssetNo()%>"
								name="assetNo" type="text" value="<%=v.getAssetNo()%>" disabled="disabled"/>
							<label for="">资产名称：</label><input name="assetName"
								type="text" value="<%=v.getAssetName()%>">
						</div>
					</div>
					<div class="form-inline" style="margin-top: 5px">
						<div class="form-group">
							<label for="">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label>
							<input name="assetUnitPrice" type="text"
								value="<%=v.getAssetUnitPrice()%>" /> <label for="">&nbsp;&nbsp;生产厂商：</label>
							<input name="manufacturer" type="text"
								value="<%=v.getManufacturer()%>" />
						</div>
					</div>
					<div class="form-inline" style="margin-top: 5px">
						<div class="form-group">
							<label for="">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</label>
							<input type="text" name="assetNum" value="<%=v.getAssetNum()%>" />
							<label for="">&nbsp;&nbsp;使用单位：</label> <input name="userCompany"
								type="text" value="<%=v.getUserCompany()%>" />
						</div>
					</div>
					<div class="form-inline" style="margin-top: 5px">
						<div class="form-group">
							<label for="">存放地点：</label><input name="storagePlace" type="text"
								value="<%=v.getStoragePlace()%>" /><label for="">&nbsp;&nbsp;&nbsp;&nbsp;购&nbsp;&nbsp;买&nbsp;&nbsp;人：</label>
							<input name="purchaser" type="text" value="<%=v.getPurchaser()%>" />
						</div>
					</div>
					<div class="form-inline" style="margin-top: 5px">
						<label>资产类型：</label> <select name="assetType">
							<%
								if ("仪器".equals(v.getAssetType())) {
							%>
							<option value="仪器" selected="selected">仪器</option>
							<option value="机电设备">机电设备</option>
							<option value="电子设备">电子设备</option>
							<option value="印刷设备">印刷设备</option>
							<option value="文体设备">文体设备</option>
							<option value="工具量具">工具量具</option>
							<%
								} else if ("机电设备".equals(v.getAssetType())) {
							%>
							<option value="仪器">仪器</option>
							<option value="机电设备" selected="selected">机电设备</option>
							<option value="电子设备">电子设备</option>
							<option value="印刷设备">印刷设备</option>
							<option value="文体设备">文体设备</option>
							<option value="工具量具">工具量具</option>
							<%
								} else if ("电子设备".equals(v.getAssetType())) {
							%>
							<option value="仪器">仪器</option>
							<option value="机电设备">机电设备</option>
							<option value="电子设备" selected="selected">电子设备</option>
							<option value="印刷设备">印刷设备</option>
							<option value="文体设备">文体设备</option>
							<option value="工具量具">工具量具</option>
							<%
								} else if ("印刷设备".equals(v.getAssetType())) {
							%>
							<option value="仪器">仪器</option>
							<option value="机电设备">机电设备</option>
							<option value="电子设备">电子设备</option>
							<option value="印刷设备" selected="selected">印刷设备</option>
							<option value="文体设备">文体设备</option>
							<option value="工具量具">工具量具</option>
							<%
								} else if ("文体设备".equals(v.getAssetType())) {
							%>
							<option value="仪器">仪器</option>
							<option value="机电设备">机电设备</option>
							<option value="电子设备">电子设备</option>
							<option value="印刷设备">印刷设备</option>
							<option value="文体设备" selected="selected">文体设备</option>
							<option value="工具量具">工具量具</option>
							<%
								} else {
							%>
							<option value="仪器">仪器</option>
							<option value="机电设备">机电设备</option>
							<option value="电子设备">电子设备</option>
							<option value="印刷设备">印刷设备</option>
							<option value="文体设备">文体设备</option>
							<option value="工具量具" selected="selected">工具量具</option>
							<%
								}
							%>
						</select> <label>资产状态：</label><select name="assetStatus">
							<%
								if ("正常".equals(v.getAssetStatus())) {
							%>
							<option value="正常" selected="selected">正常</option>
							<option value="损坏">损坏</option>
							<option value="维修">维修</option>
							<%
								} else if ("损坏".equals(v.getAssetStatus())) {
							%>
							<option value="正常">正常</option>
							<option value="损坏" selected="selected">损坏</option>
							<option value="维修">维修</option>
							<%
								} else {
							%>
							<option value="正常">正常</option>
							<option value="损坏">损坏</option>
							<option value="维修" selected="selected">维修</option>
							<%
								}
							%>
						</select>
					</div>
					<label>备注信息：</label>
					<textarea name="remark" cols="57" rows="5"><%=v.getRemark()%></textarea>
					<%
						}
						}
					%>
					<br>
					<button type="submit" class="btn btn-primary"
						style="background: #a94442; border-color: #a94442">修改</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file="bottom.jsp"%>
</body>
</html>