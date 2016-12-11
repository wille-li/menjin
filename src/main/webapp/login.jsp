<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/login.css'></c:url>" />
<script type="text/javascript"
	src="<c:url value='/resources/easyui/jquery.min.js'></c:url>"></script>
<script type="text/javascript">
$(function(){ 
    $(window).resize(); 
}); 
$(window).resize(function(){ 
    $("#login").css({
        position: "absolute", 
        left: ($(window).width() - $("#login").outerWidth())/2, 
        top: ($(window).height() - $("#login").outerHeight())/2 
    });        
});
</script>
</head>
<body class="login">
	<div class="login_m" id="login">
		<div class="login_boder">

			<div class="login_padding" id="login_model">
				<form action="${pageContext.request.contextPath}/login" method="post">
					<h1>易会智慧访客系统</h1>
					<label> <input type="text" class="txt_input txt_input2"
						required oninvalid="setCustomValidity('用户名格式不正确。');"
						placeholder="Username" id="username" name="username"
						AUTOCOMPLETE="off" /></label> <label> <input type="password"
						class="txt_input txt_input3" placeholder="Password"  id="password" name="password" /></label>
					<div class="rem_sub" style="height: 35px; margin-top: 10px">
						<input type="submit" class="sub_button" name="button" id="submit"
							value="登录" style=" margin-left: 110px;" />
					</div>

					<div class="rem_sub"
						style="height: 35px; margin-top: 20px; font-size: 14px; color: #DC143C; text-align: center">
						${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>