<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>权限管理</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/gray/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/icon.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.easyui.min.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/resourcesmanager.js"></script>
</head>
<body>
<div>
<span><a id="add" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addResource()">新增权限</a></span>
<span><a id="info" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-large-clipart'" onclick="updateResource()">权限资料</a></span>
<span><a id="delete" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteResource()">删除权限</a></span>
<span style="float:right;">
<select class="easyui-combobox" id="parentSelect" name="parentSelect"  labelPosition="top" style="width:100px;">
<option value="userManager">用户管理</option>
<option value="roleManager">角色管理</option>
</select>
<select class="easyui-combobox" id="childSelect" name="childSelect"  labelPosition="top" style="width:100px;">
<option value="add">新增</option>
<option value="update">修改</option>
</select>
<a id="search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchResource()">查询</a>
</span>
</div> 

<div>
<table id="resourceListDatagrid" class="easyui-datagrid" title="权限资源列表" 
			data-options="width:'100%',height:'470',rownumbers:true,singleSelect:true,url:'./resource/getAllResourceList.do',
			method:'post',pagination:true,fitColumns:true,striped:true,nowrap:true,loadMsg:'数据正在加载...',emptyMsg:'未找到记录！',
			pageSize:15,pageList:[15,25,35,45]">
		<thead>
			<tr>
				<th data-options="field:'resourceUrl',width:200,align:'center'">权限资源路径</th>
				<th data-options="field:'name',width:200,align:'center'" >权限资源名</th> 
				<th data-options="field:'parentDesc',width:200,align:'center'" >上一级目录</th>
				<th data-options="field:'description',width:200,align:'center'" >描述</th>
			</tr>
		</thead>
	</table>
</div>
 <div id="ResourceDialog" class="easyui-dialog" data-options="modal:true" closed="true" style="width:800px;height:260px;padding:10px">
		<div class="easyui-panel" style="padding:10px 10px;">
			<form id="resourceForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div style="margin-bottom:10px;float:left;width:350px">
				<input class="easyui-textbox" id="resourceUrl" name="resourceUrl" style="width:100%" label='权限资源路径'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<div style="width:70px;float:left">上一级目录</div>
				<div style="float:left;margin-left:12px;width:268px;">
					<input id="parentDesc" class="easyui-combobox" name="parentDesc"   
    				data-options="url:'./resource/getParentResources.do',method:'post',valueField:'name',
    				textField:'name',editable:false,autocomplete:false" />  
				</div>
			</div>
			<div style="width:740px;height:100px">
				<div style="margin-bottom:10px;float:left;width:350px;height:100px">
					<input class="easyui-textbox" id="name" name="name" style="width:100%" label='权限资源名'/>
				</div>
				<div style="margin-bottom:10px;float:left;width:350px;height:100px;margin-left:10px">
					<input class="easyui-textbox" id="description" name="description"  data-options="multiline:true" style="width:100%;height:100px" label='描述'/>
				</div>
			</div>
			</form>
	    </div>
	     	<div id="addButton" style="float:right;margin-top:10px;">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveResource" onclick="submitDialog()">保存</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" id="cancel" onclick="closeDialog()">取消</a>
   			 </div>
	</div>
	
</body>
</html>