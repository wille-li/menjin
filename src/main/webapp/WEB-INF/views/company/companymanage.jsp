<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>公司管理</title>
	<link href="<%=request.getContextPath()%>/resources/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/gray/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/icon.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/service.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/companymanage.js"></script>
  </head>
<body>
    <table id='tt' class="easyui-datagrid" >  
	<thead>  
		<tr>  
			<th field="companyName" width="300">公司名称</th>  
			<th field="companyAddress"  width="100">公司楼层</th>  
			<th field="companyPhone"  width="150">公司联系电话</th>  
			<th field="createBy" width="100">记录创建人</th>  
			<th field="createTime" width="150" formatter="formatDatebox">记录创建时间</th>  
			<th field="modifiedDate" width="150" formatter="formatDatebox">记录修改时间</th>  
		</tr>  
	</thead>  
</table>

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
			   <a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</div>
		</div>
	</div>
    
    <div id="CompanyDialog" class="easyui-dialog" data-options="modal:true" closed="true" style="width:400px;height:300px;padding:10px">
		<div class="easyui-panel" style="padding:30px 10px;">
			<form id="addComForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id="companyName" name="companyName" style="width:100%" label='公司名称'/>
			</div>
			<div style="margin-bottom:20px">
				<!-- <input class="easyui-textbox" id="companyAddress" name="companyAddress" style="width:100%" label='公司楼层'/> -->
			    <input id="companyAddress" class="easyui-combobox" name="companyAddress" label='公司楼层' style="width:100%" />
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="companyPhone" name="companyPhone" style="width:100%" label='公司联系电话'/>
			</div>
			</form>
	    </div>
	</div>
    
    <div id="addCDButton">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="saveCompany" onclick="submitDialog()">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="quitAdd" onclick="quitDialog()">取消</a>
    </div>
</body>
</html>