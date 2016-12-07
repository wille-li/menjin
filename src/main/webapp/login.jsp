<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
</head>
<body>
<%-- <div id="formbackground" style="position:absolute;left:0;top:0;width:100%; height:100%;z-index:-1;">  
<img src="<%=request.getContextPath()%>/resources/images/login_backgroup.jpg" height="100%" width="100%"/>  
</div> --%>
<div style="position:absolute;left:35%;top:25%;border:1px solid black;width:400px;height:300px">
<div style="border:1px solid black;width:400px;height:50px"></div>
<div style="border:1px solid black;width:400px;height:50px"></div>
<div style="border:1px solid black;width:400px;">
<div>
<form action="${pageContext.request.contextPath}/login" method="post">
		用户名 <input type="text" name="username" /><br />
		密码 <input type="password" name="password" /><br />
		<input type="submit" value="登录">
	</form>
</div>
</div>
</div>
	<%-- <h3>用户登录</h3>
	${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
	<form action="${pageContext.request.contextPath}/login" method="post">
		用户名：<input type="text" name="username" /><br />
		密码：<input type="password" name="password" /><br />
		<input type="submit" value="登录">
	</form> --%>
</body>
</html>