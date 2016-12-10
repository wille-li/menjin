<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value="/resources/css/defaultLayout.css"></c:url>" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/easyui/themes/gray/easyui.css"></c:url>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/easyui/themes/icon.css"></c:url>" />
    <script type="text/javascript" src="<c:url value="/resources/easyui/jquery.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/resources/easyui/jquery.easyui.min.js"></c:url>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/defaultLayout.js"></c:url>" ></script>
</head>
<body class="easyui-layout body-tab" scroll="no">
	<noscript>
		<div class="no-script">
		    <img src="<c:url value="/resources/images/noscript.gif"></c:url>" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
    <div class="header-tab" region="north" split="true" border="false" >
        <tiles:insertAttribute name="header" />
    </div>
    <div class="footer-tab" region="south" split="true" >
         <tiles:insertAttribute name="footer" />
    </div>
    <div class="menu-tab" region="west" hide="true" split="true" title="导航菜单"  id="west">
	     <tiles:insertAttribute name="menu" />
    </div>
    <div class="body-tab" id="mainPanle" region="center" >
		 <tiles:insertAttribute name="body" /> 
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window open-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  >
        <div class="easyui-layout" fit="true">
            <div class="open-window-content" region="center" border="false" >
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type=‘Password’ class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type=‘Password’ class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div class="open-window-button" region="south" border="false" >
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>




	            
	     
	           
	            
	           
	             
	           
	         
