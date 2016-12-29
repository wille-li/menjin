<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script>
function assignRole(){
	$("#to").empty();
	$("#from").empty();
	var selections = $('#userListDatagrid').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要分配角色的用户！');
         return false;
     }
	$("#userId").val(selections[0].id);
	$("#selectName").textbox("setValue",selections[0].username).textbox("setText",selections[0].username);
	$("#selectName").textbox({readonly:true}); 
	var username = $("#selectName").textbox("getValue");
	var sendData = {"username" : username};
	var url = "${pageContext.request.contextPath}/user/searchRole.do?timeId="+new Date().getTime();
	$.post(url,sendData,function(data){
		var arr = data;
		$.each(arr.existRoles,function(i,v){
			var opt = $('<option></option>');
			opt.val(v.id);
			opt.html(v.description);
			$("#to").append(opt);
		}); 
		$.each(arr.notExistRoles,function(i,v){
			var opt = $('<option></option>');
			opt.val(v.id);
			opt.html(v.description);
			$("#from").append(opt);
		}); 
	},"json");
	$('#win').window("open");
	
}
function getRolesAsString(){
	var rolesStr ="";
	var objectSelect = document.getElementById("to");
	if(null!=objectSelect && typeof(objectSelect)!="undefined"){
		var length = objectSelect.options.length;
		console.log("length="+length);
		for(var i=0;i<length;i++){
			if(0 == i){
				rolesStr = objectSelect.options[i].value;
			}else{
				rolesStr = rolesStr + ","+objectSelect.options[i].value;
			}
		}
	}
	console.log("one ="+rolesStr);
	return rolesStr;
}
$(function(){
	//选择一项  
	$("#addOne").click(function(){  
	    $("#from option:selected").clone().appendTo("#to");  
	    $("#from option:selected").remove();  
	});  
	//选择全部  
	$("#addAll").click(function(){  
	    $("#from option").clone().appendTo("#to");  
	    $("#from option").remove();  
	});  
	  
	//移除一项  
	$("#removeOne").click(function(){  
	    $("#to option:selected").clone().appendTo("#from");  
	    $("#to option:selected").remove();  
	});  
	//移除全部  
	$("#removeAll").click(function(){  
	    $("#to option").clone().appendTo("#from");  
	    $("#to option").remove();  
	});  
	
	$("#save").bind('click', function(){    
        var userId = $("#userId").val();
        var existRoleIds = getRolesAsString();
        var params = {
        		"userId" : userId,
        		"roleIds" : existRoleIds
        };
        var url = "${pageContext.request.contextPath}/user/addUserRoles.do?timeId="+new Date().getTime();
        $.post(url,params,function(data){
        	var arr = data;
        	if(arr.result=="success"){
        		$("#userListDatagrid").datagrid("reload");
        		$.messager.alert('消息','修改成功！','info');
        	}else{
        		$.messager.alert('消息','操作失败！','info');
        	} 
        },"json");
        $('#win').window("close");
    });    
});
</script>
<div>
<span><a id="add" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addUser()">新增用户</a></span>
<span><a id="info" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-person'" onclick="updateUser()">个人资料</a></span>
<span><a id="delete" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteUser()">删除用户</a></span>
<span><a id="role" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-man'" onclick="assignRole()">分配角色</a></span>
<span style="float:right;">
<span id="selectWay"><input id="searchInput" class="easyui-searchbox" style="width:200px" data-options="prompt:'条件查询'"></input></span>
<select class="easyui-combobox" id="state" name="state"  labelPosition="top" style="width:100px;">
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
	
	<div id="win" class="easyui-window" title="分配角色" style="width:600px;height:360px"    
        data-options="iconCls:'icon-man',modal:true,closed:true"> 
        <div style="padding:5px 5px;">  
    	<form id="assignRoleForm"  method="post">
    		<div style="width:560px;height:36px;position: relative;">
    			<div style="margin:auto;width:280px;height:25px;position: absolute;top: 0; left: 0; bottom: 0; right: 0;">
    			<input id="userId" name="userId" type="hidden" />
				<input class="easyui-textbox" id="selectName" name="selectName" style="width:100%;" label='账号名'/>
				</div>
    		</div>
   			<div style="width:560px;height:234px;position: relative;">
   			 	<div style="margin:auto;width:500px;height:229px;position: absolute;top: 0; left: 0; bottom: 0; right: 0;">
						<table  width="100%" border="0" cellpadding="0" cellspacing="0">  
   							 <tr>  
        						<td width="180px" height="220px">
        							<table width="100%" border="0" cellpadding="0" cellspacing="0">
        							<tr><td height="20px" align="center" style="font-weight:bold;">可选角色</td></tr>  
        							<tr>
        								<td height="190px">
	        								<select name="from" id="from" multiple="multiple" style="width:100%;height:100%">  
	            							</select>  
        								</td>
        							</tr>
        							</table>
        						</td>  
        						<td align="center">
        								<div style="padding-top:40px">  
							            <input type="button" id="addAll" value=" >> " style="width:50px;" /><br /><br />
							            <input type="button" id="addOne" value=" > " style="width:50px;" /><br /><br />  
							            <input type="button" id="removeOne" value="&lt;" style="width:50px;" /><br /><br />  
							            <input type="button" id="removeAll" value="&lt;&lt;" style="width:50px;" /><br /><br />  
							            </div>
        						</td>  
						        <td width="180px" height="220px">
						        	<table width="100%" border="0" cellpadding="0" cellspacing="0">
        								<tr><td height="20px" align="center" style="font-weight:bold;">已有角色</td></tr>  
        								<tr>
        									<td height="190px">  
						            			<select name="to" id="to" multiple="multiple"  style="width:100%;height:100%">  
						            			</select>  
						            		</td>
						          		</tr>
						          	</table>
						        </td>  
    						</tr>  
						</table>  
				</div>
   			</div>
    		<div style="width:560px;height:30px;position: relative;display:table-cell;vertical-align:middle" align="center" >
    				<a id="save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:80,height:28">保存</a>  
    		</div>
    	</form>  
    	</div>
	</div>  
	 
</body>
</html>