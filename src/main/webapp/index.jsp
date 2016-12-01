<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	这是index首页!欢迎<sec:authentication property="name"/>!<br/>
	<sec:authorize ifAllGranted="ROLE_ADMIN">
	<a href="admin.jsp">进入admin.jsp页面</a>
	</sec:authorize>
</body>
</html>