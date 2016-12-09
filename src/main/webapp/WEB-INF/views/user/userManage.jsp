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
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/service.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/usermanager.js"></script>
</head>
<body>
<div>
<span><a id="add" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addUser()">新增用户</a></span>
<span><a id="info" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-person'" onclick="updateUser()">个人资料</a></span>
<span><a id="delete" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteUser()">删除用户</a></span>
<span><a id="role" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-man'">分配角色</a></span>
<span style="float:right;">
<input id="searchInput" class="easyui-searchbox" style="width:200px" data-options="prompt:'条件查询'"></input>
<select class="easyui-combobox" name="state"  labelPosition="top" style="width:100px;">
<option value="username">用户名</option>
<option value="roleName">角色名</option>
</select>
<a id="search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchUser()">查询</a>
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
				<th data-options="field:'roles',width:200,align:'center'" formatter="formatRoles">所属角色</th> 
				<th data-options="field:'createdDate',width:200,align:'center'" formatter="formatDatebox">创建时间</th>
				<th data-options="field:'modifiedDate',width:200,align:'center'" formatter="formatDatebox">修改时间</th>
				<th data-options="field:'status',width:150,align:'center'" formatter="formatStatus">是否禁用</th>
			</tr>
		</thead>
	</table>
</div>
 <div id="UserDialog" class="easyui-dialog" data-options="modal:true" closed="true" style="width:800px;height:290px;padding:10px">
		<div class="easyui-panel" style="padding:10px 10px;">
			<form id="userForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div style="margin-bottom:15px;float:left;width:350px">
				<input class="easyui-textbox" id="username" name="username" style="width:100%" label='账号名'/>
			</div>
			<div style="margin-bottom:15px;float:left;width:350px;margin-left:10px">
				<div style="width:70px;float:left">状态</div>
				<div style="float:left;margin-left:12px;width:268px;">
					<select id="status" class="easyui-combobox" name="status" style="width:100%"> 
    					<option value="1">启用</option>   
    					<option value="0">禁用</option>   
					 </select>
				</div>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px">
				<div style="width:70px;float:left">密码</div>
				<div style="float:left;margin-left:12px;width:268px;">
					<input class="easyui-passwordbox" iconWidth="20" style="width:100%" name="password" id="password" />
				</div>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<div style="width:70px;float:left">确认密码</div>
				<div style="float:left;margin-left:12px;width:268px;">
					<input class="easyui-passwordbox" iconWidth="20" style="width:100%" name="passwdConfirm" id="passwdConfirm" />
				</div>				
			</div>
			<div style="margin-bottom:10px;float:left;width:350px">
				<input class="easyui-textbox" id="name" name="name" style="width:100%" label='用户姓名'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<input class="easyui-textbox" id="idCardNumber" name="idCardNumber" style="width:100%" label='身份证号'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;">
				<div style="width:70px;float:left">出生日期</div>
				<div style="float:left;margin-left:12px;width:268px;">
					<input id="birthday" name="birthday" type="text" class="easyui-datebox" required="required" style="width:100%"></input> 
				 </div>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<input class="easyui-textbox" id="address" name="address" style="width:100%" label='住址'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;">
				<input class="easyui-textbox" id="mobilephone" name="mobilephone" style="width:100%" label='手机号码'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<input class="easyui-textbox" id="email" name="email" style="width:100%" label='邮箱'/>
			</div>
			</form>
	    </div>
	     	<div id="addButton" style="float:right;margin-top:10px;">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveUser" onclick="submitDialog()">保存</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" id="cancel" onclick="closeDialog()">取消</a>
   			 </div>
	</div>
	
	 
</body>
</html>