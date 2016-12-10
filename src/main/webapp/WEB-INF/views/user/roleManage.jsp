<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>来访事由管理</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/gray/easyui.css'></c:url>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/icon.css'></c:url>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mattermanage.css'></c:url>" />
    <script type="text/javascript" src="<c:url value='/resources/easyui/jquery.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/easyui/jquery.easyui.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/service.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/rolemanager.js'></c:url>"></script>
  </head>
<body>
    <table id='Roletb' class="easyui-datagrid" >  
	<thead>  
		<tr>  
			<th field="name" width="200">角色名称</th>  
			<th field="description" width="200">角色描叙</th>  
		</tr>  
	</thead>  
</table>

	<div id="tb">
		<div region="north" border="false" class="north">
			<span><a id="add" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addRole()">新增用户</a></span>
            <span><a id="info" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-person'" onclick="updateRole()">个人资料</a></span>
            <span><a id="delete" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteRole()">删除用户</a></span>
            <span><a id="role" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-man'" onclick="disresource()">分配角色</a></span>
            <span style="float:right;">
            <input id="searchInput" class="easyui-searchbox" style="width:200px" data-options="prompt:'条件查询'"></input>
            <a id="search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchRole()">查询</a>
            </span>
		</div>
	</div>
    
    <div id="RoleDialog" class="easyui-dialog comdialog" data-options="modal:true" closed="true">
		<div class="easyui-panel matpanel">
			<form id="addComForm"  method="post">
			    <input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div class="mbottom">
				<input class="easyui-textbox" id="name" name="name" style="width:100%" label='角色名称'/>
			</div>
			<div class="mbottom">
				<input class="easyui-textbox" id="description" name="description" style="width:100%" label='角色描述'/>
			</div>
			</form>
	    </div>
	</div>
	
	<div id="ResourceDialog" class="easyui-dialog comdialog" data-options="modal:true" closed="true">
	      <ul id="resources" class="easyui-tree"></ul>
	</div>
	
	<div id="resourceButton">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="saveresource" onclick="submitResource()">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="quitresource" onclick="quitResource()">取消</a>
    </div>
    
    <div id="RoleButton">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="saveCompany" onclick="submitDialog()">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="quitAdd" onclick="quitDialog()">取消</a>
    </div>
</body>
</html>