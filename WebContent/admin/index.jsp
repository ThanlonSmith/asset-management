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
<body onload="showTime();" style="margin-top: 65px">
	<jsp:include page="nav.jsp" />
	<div class="container">
		<div class="row">
			<ol class="breadcrumb">
				<li class="active">首页</li>
			</ol>
			<h4>欢迎使用本系统!</h4>
			<h4>
				当前时间：<span id="show"></span>
			</h4>
		</div>
	</div>
	<%@include file="bottom.jsp"%>
</body>
<script>
		$(document).ready(function() {
			$('#nav-1').addClass("bg");
		});
</script>
<script type="text/javascript">
	function showTime() {
	var nowTime = new Date();
	　　　　var years = nowTime.getFullYear();
	　　　　var mouths = nowTime.getMonth();
	　　　　var days = nowTime.getDate();
	　　　　var hours = nowTime.getHours();
	　　　　var minites = nowTime.getMinutes();
	　　　　var seconds = nowTime.getSeconds();
	　　　　var time = years+"年";
	　　　　time += (mouths+1)+"月"+days+"日";
	　　　　time += (hours<10?"0":"")+hours;
	　　　　time += (minites<10?":0":":") + minites;
	　　　　time += (seconds<10?":0":":") + seconds;
	　　　　time += (hours>12)?"PM":"AM";
	　　　　document.getElementById("show").innerHTML = time;
	　　　　setTimeout("showTime()",1000);
	　　　　}
</script>
</html>