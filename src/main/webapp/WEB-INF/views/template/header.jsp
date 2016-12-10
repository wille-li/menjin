<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<span style="float: right; padding-right: 20px;" class="head">
    <label style="font-size: 14px;font-weight: bold;">欢迎<sec:authentication property="name"/></label>
	<a href="#" id="editpass">修改密码</a> 
	<a href="#" id="loginOut">安全退出</a>
</span>
<span style="padding-left: 10px; font-size: 16px;">
	<img src="<%=request.getContextPath()%>/resources/images/logo.png"
			width="40" height="40" align="absmiddle" /> <label>易会智慧访客</label>
</span>
