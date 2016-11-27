<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>员工管理</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/gray/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/icon.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/service.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/employeemanage.js"></script>
  </head>
<body>

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true,iconCls:'icon-ok'" title="公司列表" style="width:300px;">
		   <ul id="companytree" class="easyui-tree" >
		</ul>
		</div>
		<div data-options="region:'center',split:true,title:'部门信息',iconCls:'icon-ok'">
		     <table id='departmenttb' class="easyui-datagrid" >  
	             <thead>  
		           <tr>  
			       <th field="employeeNo" width="80">员工编号</th>  
			       <th field="employeeName" width="50">员工姓名</th>  
			       <th field="employeeSex"  width="80">性别</th>  
			       <th field="email"  width="80">邮箱</th>  
			       <th field="mobile"  width="80">联系电话</th>  
			       <th field="idCardNum"  width="80">证件号码</th>  
			       <th field="createBy" width="80">记录创建人</th>  
			       <th field="createTime" width="80" formatter="formatDatebox">记录创建时间</th>  
			       <th field="modifiedDate" width="80" formatter="formatDatebox">记录修改时间</th> 
		           </tr>  
	             </thead>  
              </table>
		</div>
	</div>
	<div id="tb">
		<div region="north" border="false" style="border-bottom:1px solid #ddd;height:28px;padding:2px 2px 2px 2px;background:#fafafa;">
			<div style="float:left;">
				<a href="javascript:void(0)" id="addcm" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addBrand()">新增</a>
				<a href="javascript:void(0)" id="updatecm" class="easyui-linkbutton" plain="true" icon="icon-save" onclick="updateBrand()">编辑</a>
				<a href="javascript:void(0)" id="deletecm" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="deleteCompany()">删除</a>
			</div>
			<div class="datagrid-btn-separator"></div>
			<div style="float:right;">
			   <input class="easyui-searchbox" data-options="prompt:'Please Input Value',searcher:''" style="width:130px;vertical-align:middle;"></input>
			   <a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
	</div>
	
	<div id="departmentDialog" class="easyui-dialog" data-options="modal:true" closed="true" style="width:400px;height:400px;padding:10px">
		<div class="easyui-panel" style="padding:10px 10px 50px 10px;">
			<form id="departmentDialogForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<input class="easyui-textbox" type="hidden" id="departmentId" name="departmentId"/>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="employeeNo" name="employeeNo" style="width:100%" label='员工编号'/>
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="employeeName" name="employeeName" style="width:100%" label='员工姓名'/>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeSex" name="employeeSex" style="width:100%" label='性别'/>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="email" name="email" style="width:100%" label='邮箱'/>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="mobile" name="mobile" style="width:100%" label='联系电话'/>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="IdCardNum" name="IdCardNum" style="width:100%" label='证件号码'/>
			</div>
			</form>
	    </div>
	</div>
    
    <div id="departmentDialogButton">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="saveCompany" onclick="submitDialog()">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="quitAdd" onclick="quitDialog()">取消</a>
    </div>
	
</body>
</html>