<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <title><tiles:getAsString name="title" /></title>
	</head>
	<body>
	            <tiles:insertAttribute name="header" />
	     
	            <tiles:insertAttribute name="footer" />
	            
	            <tiles:insertAttribute name="menu" />
	             
	            <tiles:insertAttribute name="body" /> 
	         
	</body>
</html>