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
	<link rel="stylesheet" href="<%=path%>/static/css/comm.css">
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container" style="margin-top: 65px">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="javascript:;">资产管理</a></li>
				<li class="active">资产信息</li>
			</ol>
			<a href="<%=path%>/admin/addAsset.jsp" type="button" class="btn btn-primary" style="background: #a94442">增加资产信息</a>
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
						out.print("<tr><td bgcolor='#FFFFFF' colspan='5'>没有任何资产信息！</td></tr>");
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
						href="<%=path%>/QueryAssetInfoServ?page=<%=i%>"><%=i%></a></li>
					<%
						} else {
					%>
					<li class=""><a
						href="<%=path%>/QueryAssetInfoServ?page=<%=i%>"><%=i%></a></li>
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
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form action="<%=path%>/AssetAddServ" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">添加资产信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-inline" style="margin-top: 5px">
							<div class="form-group">
								<label for="">资产编号：</label><input name="assetNo" type="text" />
								<label for="">资产名称：</label><input name="assetName" type="text" />
							</div>
						</div>
						<div class="form-inline" style="margin-top: 5px">
							<div class="form-group">
								<label for="">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label>
								<input name="assetUnitPrice" type="text" /> <label for="">生产厂商：</label>
								<input name="manufacturer" type="text" />
							</div>
						</div>
						<div class="form-inline" style="margin-top: 5px">
							<div class="form-group">
								<label for="">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</label>
								<input type="text" name="assetNum" /> <label for="">使用单位：</label>
								<input name="userCompany" type="text" />
							</div>
						</div>
						<div class="form-inline" style="margin-top: 5px">
							<div class="form-group">
								<label for="">存放地点：</label><input name="storagePlace"
									type="text" /><label for="">购买人：</label> <input
									name="purchaser" type="text" />
							</div>
						</div>
						<div class="form-inline" style="margin-top: 5px">
							<label>资产类型：</label> <select name="assetType">
								<option value="仪器">仪器</option>
								<option value="机电设备">机电设备</option>
								<option value="电子设备">电子设备</option>
								<option value="印刷设备">印刷设备</option>
								<option value="文体设备">文体设备</option>
								<option value="工具量具">工具量具</option>
							</select> <label>资产状态：</label><select name="assetStatus">
								<option value="正常">正常</option>
								<option value="损坏">损坏</option>
								<option value="维修">维修</option>
							</select>
						</div>
						<label>备注信息：</label>
						<textarea name="remark" class="form-control" rows="3"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">添加</button>
					</div>
				</div>
			</form>
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