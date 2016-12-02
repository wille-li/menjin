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
    <script type="text/javascript" src="<c:url value='/resources/easyui/jquery.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/easyui/jquery.easyui.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/service.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/mattermanage.js'></c:url>"></script>
  </head>
<body>
    <table id='mattertb' class="easyui-datagrid" >  
	<thead>  
		<tr>  
			<th field="matterDecs" width="300">拜访事由</th>  
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
			   <a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
	</div>
    
    <div id="matterDialog" class="easyui-dialog" data-options="modal:true" closed="true" style="width:400px;height:150px;padding:10px">
		<div class="easyui-panel" style="padding:10px 10px 5px 10px;">
			<form id="matterForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="matterDecs" name="matterDecs" style="width:100%" label='拜访事由'/>
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