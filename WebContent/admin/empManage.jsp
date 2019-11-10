<%@page import="entity.Employee"%>
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
				<li><a href="#">资产管理</a></li>
				<li class="active">资产信息</li>
			</ol>
			<a href="<%=path %>/admin/addEmpInfo.jsp" type="button" class="btn btn-primary"
				style="background: #a94442">增加员工信息</a>
			<table class="table table-hover">
				<tr>
					<th>员工编号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>所在部门</th>
					<th>现任职务</th>
					<th>家庭住址</th>
					<th>联系电话</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<%
					List<Employee> list = (List<Employee>) request.getAttribute("list");
					if (list == null || list.size() < 1) {
						out.print("<tr><td bgcolor='#FFFFFF' colspan='5'>没有任何资产信息！</td></tr>");
					} else {
						for (Employee v : list) {
				%>
				<tr>
					<td><%=v.getId()%></td>
					<td><%=v.getName()%></td>
					<td>
						<%
							if (v.getSex_id() == 0) {
						%> 男 <%
							} else {
						%> 女 <%
							}
						%>
					</td>
					<td><%=v.getAge()%></td>
					<td><%=v.getDepart_name()%></td>
					<td><%=v.getPost_name()%></td>
					<td><%=v.getAddress()%></td>
					<td><%=v.getPhone()%></td>
					<td><%=v.getStatus()%></td>
					<td><a href="<%=path%>/QueryEditEmpServ?id=<%=v.getId()%>">编辑</a><a
						onclick="return confirm('确认删除？')"
						href="<%=path%>/DelEmpServ?id=<%=v.getId()%>">&nbsp;&nbsp;删除</a></td>
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
						href="<%=path%>/QueryEmpServ?page=<%=i%>"><%=i%></a></li>
					<%
						} else {
					%>
					<li class=""><a href="<%=path%>/QueryEmpServ?page=<%=i%>"><%=i%></a></li>
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
			<form action="<%=path%>/EmpInfoAddServ" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">添加员工信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-inline" style="margin-top: 5px">
							<div class="form-group">
								<label for="">资产编号：</label> <input type="text"
									class="form-control" id="exampleInputEmail1"
									placeholder="Email"><label for="">资产名称：</label><input
									type="text" class="form-control" id="exampleInputEmail1"
									placeholder="Email">
							</div>
						</div>
						<div class="form-inline" style="margin-top: 5px">
							<div class="form-group">
								<label for="">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label>
								<input type="text" class="form-control"
									id="exampleInputPassword1" placeholder="Password"> <label
									for="">生产厂商：</label> <input type="text" class="form-control"
									id="exampleInputPassword1" placeholder="Password">
							</div>
						</div>
						<div class="form-inline" style="margin-top: 5px">
							<div class="form-group">
								<label for="">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</label>
								<input type="text" class="form-control"
									id="exampleInputPassword1" placeholder="Password"> <label
									for="">使用单位：</label> <input type="text" class="form-control"
									id="exampleInputPassword1" placeholder="Password">
							</div>
						</div>
						<div class="form-inline" style="margin-top: 5px">
							<div class="form-group">
								<label for="">存放地点：</label> <input type="text"
									class="form-control" id="exampleInputPassword1"
									placeholder="Password"> <label for="">购买人：</label> <input
									type="text" class="form-control" id="exampleInputPassword1"
									placeholder="Password">
							</div>
						</div>
						<div class="form-inline" style="margin-top: 5px">
							<label>资产类型：</label><select class="form-control">
								<option>仪器</option>
								<option>机电设备</option>
								<option>电子设备</option>
								<option>印刷设备</option>
								<option>问题设备</option>
							</select> <label>资产状态：</label><select class="form-control">
								<option>正常</option>
								<option>损坏</option>
								<option>维修</option>
							</select>
						</div>
						<label>备注信息：</label>
						<textarea class="form-control" rows="3"></textarea>
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
			$('#nav-5').addClass("bg");
		});
	</script>
</body>
</html>
