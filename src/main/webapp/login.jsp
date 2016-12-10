<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/login.css'></c:url>" />
<script type="text/javascript" src="<c:url value='/resources/easyui/jquery.min.js'></c:url>"></script>
<script type="text/javascript">
$(function(){ 
    $(window).resize(); 
}); 
$(window).resize(function(){ 
    $("#mydiv").css({
        position: "absolute", 
        left: ($(window).width() - $("#mydiv").outerWidth())/2, 
        top: ($(window).height() - $("#mydiv").outerHeight())/2 
    });        
});
</script>
</head>
<body>
<div id="mydiv" class="container">
	<section id="content">
		<form action="${pageContext.request.contextPath}/login" method="post">
			<h1>易会智慧访客系统</h1>
			<div>
				<input type="text" required oninvalid="setCustomValidity('用户名格式不正确。');"  placeholder="Username"  id="username" name="username" AUTOCOMPLETE="off"/>
			</div>
			<div>
				<input type="password" placeholder="Password"  id="password" name="password"/>
			</div>
			<div>
				<input style="margin-left: 120px;" type="submit" value="登录" />
			</div>
		</form><!-- form -->
		 <div class="button">
		    
		</div> 
	</section><!-- content -->
</div><!-- container -->
</body>
</html>