<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String admin = (String) session.getAttribute("admin");
	if (admin != null && !"".equals(admin)) {
		response.sendRedirect("" + path + "/admin/index.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录成功</title>
</head>
<body>
<!-- 
	登录成功，3秒后进入系统！
	<script>
setInterval(() => {
	window.location.href="<%=path%>/admin/index.jsp";
}, 3000);
 -->
</script>
</body>
</html>