<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>公司管理</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/gray/easyui.css'></c:url>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/icon.css'></c:url>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/companymanage.css'></c:url>" />
    <script type="text/javascript" src="<c:url value='/resources/easyui/jquery.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/easyui/jquery.easyui.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/easyui/locale/easyui-lang-zh_CN.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/service.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/companymanage.js'></c:url>"></script>
  </head>
<body>
    <table id='tt' class="easyui-datagrid" >  
	<thead>  
		<tr>  
			<th field="companyName" width="300">公司名称</th>  
			<th field="companyAddress"  width="100" formatter="formatAddr">公司楼层</th> 
			<th field="doorPlate"  width="100">公司门号</th>  
			<th field="companyPhone"  width="150">公司联系电话</th>  
			<th field="createBy" width="100">记录创建人</th>  
			<th field="createTime" width="150" formatter="formatDatebox">记录创建时间</th>  
			<th field="modifiedDate" width="150" formatter="formatDatebox">记录修改时间</th>  
		</tr>  
	</thead>  
</table>

	<div id="tb">
		<div region="north" border="false" class="north">
			<div style="float:left;">
				<a href="javascript:void(0)" id="addcm" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addBrand()">新增</a>
				<a href="javascript:void(0)" id="updatecm" class="easyui-linkbutton" plain="true" icon="icon-save" onclick="updateBrand()">编辑</a>
				<a href="javascript:void(0)" id="deletecm" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="deleteCompany()">删除</a>
			</div>
			<div class="datagrid-btn-separator"></div>
			<div class="right">
			   <input id="checkCompanyInput" class="easyui-searchbox checktb" data-options="prompt:'输入公司名称'" ></input>
			   <a href="#" onclick="checkCompany()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</div>
		</div>
	</div>
    
    <div id="CompanyDialog" class="easyui-dialog comdialog" data-options="modal:true" closed="true">
		<div class="easyui-panel companel">
			<form id="addComForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div class="mbottom">
				<input class="easyui-textbox" id="companyName" name="companyName" style="width:100%" label='公司名称'/>
			</div>
			<div class="mbottom">
			    <input id="companyAddress" class="easyui-combobox" name="companyAddress" label='公司楼层' style="width:100%" />
			</div>
			<div class="mbottom">
				<input class="easyui-textbox" id="doorPlate" name="doorPlate" style="width:100%" label='公司门号'/>
			</div>
			<div class="mbottom">
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