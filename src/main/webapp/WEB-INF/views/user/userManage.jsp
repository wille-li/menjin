<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/gray/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/icon.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/service.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/usermanager.js"></script>
</head>
<body>
<div>
<span><a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增用户</a></span>
<span><a id="info" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-person'">个人资料</a></span>
<span><a id="delete" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除用户</a></span>
<span><a id="role" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-man'">分配角色</a></span>
<span style="float:right;">
<input id="searchInput" class="easyui-searchbox" style="width:200px" data-options="prompt:'条件查询'"></input>
<select class="easyui-combobox" name="state"  labelPosition="top" style="width:100px;">
<option value="username">用户名</option>
<option value="roleName">角色名</option>
</select>
<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
</span>
</div> 

<div>
<table id="userListDatagrid" class="easyui-datagrid" title="用户列表" 
			data-options="width:'100%',height:'470',rownumbers:true,singleSelect:true,url:'./user/getAllUserList.do',
			method:'post',pagination:true,fitColumns:true,striped:true,nowrap:true,loadMsg:'数据正在加载...',emptyMsg:'未找到记录！',
			pageSize:15,pageList:[15,25,35,45]">
		<thead>
			<tr>
				<th data-options="field:'username',width:200,align:'center'">用户名</th>
				<th data-options="field:'roles',width:200,align:'center'">所属角色</th> 
				<th data-options="field:'createdDate',width:200,align:'center'" formatter="formatDatebox">创建时间</th>
				<th data-options="field:'modifiedDate',width:200,align:'center'" formatter="formatDatebox">修改时间</th>
				<th data-options="field:'status',width:150,align:'center'" formatter="formatStatus">是否禁用</th>
			</tr>
		</thead>
	</table>

</div>
</body>
</html>