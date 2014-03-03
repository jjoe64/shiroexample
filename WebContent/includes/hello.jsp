<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>
This page is only visible to registered users. You are <%= request.getAttribute("currentEmail") %>
</h2>

<% if (((Boolean) request.getAttribute("adminAccess")) == true) { %>
	<a href="admin.jsp">Admin</a><br/>
<% } %>

<a href="login?logout=1">Logout</a>

</body>
</html>