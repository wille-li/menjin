<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>访客管理</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/gray/easyui.css'></c:url>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/icon.css'></c:url>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/visitormanage.css'></c:url>" />
    <script type="text/javascript" src="<c:url value='/resources/easyui/jquery.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/easyui/jquery.easyui.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/easyui/locale/easyui-lang-zh_CN.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/service.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/visitormanage.js'></c:url>"></script>
  </head>
<body>
    <table id='visitortb' class="easyui-datagrid" >  
	<thead>  
		<tr>  
			<th field="visitorName" width="100">访客姓名</th>  
			<th field="idCardType"  width="100">证件类型</th>  
			<th field="idCardNum"  width="200">证件号</th>  
			<th field="sex"  width="60">性别</th>  
			<th field="nation"  width="150">访客单位</th>  
			<th field="birth"  width="150" formatter="formatYMDatebox">出生日月</th>  
			<th field="address"  width="150">地址</th>  
			<th field="mobile"  width="150">访客联系电话</th>  
			<th field="email"  width="150">访客邮箱地址</th>  
			<th field="rank"  width="150" formatter="formatRank">访客等级</th>  
			<!-- <th field="createBy" width="100">记录创建人</th>  
			<th field="createdTime" width="150" formatter="formatDatebox">记录创建时间</th>  
			<th field=modifiedDate width="150" formatter="formatDatebox">记录修改时间</th>  --> 
		</tr>  
	</thead>  
</table>

	<div id="tb">
		<div region="north" border="false" class="north">
			<div class="left">
				<a href="javascript:void(0)" id="addcm" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addBrand()">新增</a>
				<a href="javascript:void(0)" id="updatecm" class="easyui-linkbutton" plain="true" icon="icon-save" onclick="updateBrand()">编辑</a>
				<a href="javascript:void(0)" id="deletecm" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="deleteCompany()">删除</a>
			    <select id="vstate" class="easyui-combobox" data-options="panelHeight:'auto'" name="访客类型" label="访客类型" labelPosition="top" style="width:80px;margin-left:20px">
				<option value="">所有访客</option>
				<option value="1">普通访客</option>
				<option value="2">黑名单</option>
				<option value="3">白名单</option>
			    </select>
			</div>
			<div class="datagrid-btn-separator"></div>
			<div class="right">
			   <input id="checkByVisitorName" class="easyui-searchbox checkInput" data-options="prompt:'',searcher:''" ></input>
			   <a href="#" onclick="checkByVisitorName()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</div>
		</div>
	</div>
    
    <div id="VisitorDialog" class="easyui-dialog visdialog" data-options="modal:true" closed="true" >
		<div class="easyui-panel vispanel">
			<form id="visitorForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div class="leftin">
				<input class="easyui-textbox" id="visitorName" name="visitorName" style="width:100%" label='访客姓名'/>
			</div>
			<div class="rightin">
				<input class="easyui-combobox" id="idCardType" name="idCardType" style="width:100%" label='证件类型'/>
			</div>
			<div class="leftin">
				<input class="easyui-textbox" id="idCardNum" name="idCardNum" style="width:100%" label='证件号'/>
			</div>
			<div class="rightin">
				<input class="easyui-combobox" id="sex" name="sex" style="width:100%" label='性别'/>
			</div>
			<div class="leftin">
				<input class="easyui-textbox" id="nation" name="nation" style="width:100%" label='访客单位'/>
			</div>
			<div class="rightin">
				<input class="easyui-datebox" id="birth" name="birth" style="width:100%" label='出生年月'/>
			</div>
			<div class="leftin">
				<input class="easyui-textbox" id="address" name="address" style="width:100%" label='地址'/>
			</div>
			<div class="rightin">
				<input class="easyui-textbox" id="mobile" name="mobile" style="width:100%" label='联系电话'/>
			</div>
			<div class="leftin">
				<input class="easyui-textbox" id="email" name="email" style="width:100%" label='邮箱'/>
			</div>
			<div class="rightin">
				<input class="easyui-combobox" id="rank" name="rank" style="width:100%" label='访客等级'/>
			</div>
			</form>
	    </div>
	</div>
    
    <div id="addCDButton">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="saveCompany" onclick="submitDialog()">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="quitAdd" onclick="quitDialog()">取消</a>
    </div>
    
    <div id="mm" class="easyui-menu" style="width:120px;">
    <!-- <div onClick="checkVisitLog()" data-options="iconCls:'icon-search'">查看拜访记录</div>
    <div onClick="checkMessage()" data-options="iconCls:'icon-add'">查看访客详情</div> -->
    <div  data-options="iconCls:'icon-redo'">
		<span>设置访客等级</span>
		<div style="width:150px;">
			<div onClick="setRank(1)"><b>普通訪客</b></div>
			<div onClick="setRank(2)">黑名單</div>
			<div onClick="setRank(3)">白名單</div>
		</div>
    </div>
</div>
</body>
</html>