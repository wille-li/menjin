<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="tabs" class="easyui-tabs" fit="true" border="false">
	<div class="tab-class" title="欢迎使用" >
			这是index首页!欢迎<sec:authentication property="name"/>!<br/>
	<%-- <sec:authorize access="hasRole('ROLE_ADMIN')"></sec:authorize> --%>
	<a href="${pageContext.request.contextPath}/admin.jsp">进入admin.jsp页面</a>
	
	
	<a href="${pageContext.request.contextPath}/logout">退出系统</a>
	</div>
</div>
