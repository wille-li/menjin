<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>拜访记录管理</title>
	<link href="<%=request.getContextPath()%>/resources/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/gray/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/easyui/themes/icon.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/service.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/visitrecordmanage.js"></script>
    <style>
    .textbox-label {
    display: inline-block;
    width: 50px !important;
    height: 22px;
    line-height: 22px;
    vertical-align: middle;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin: 0;
    padding-right: 5px;
}
    </style>
  </head>
<body>
    <table id='visitortb' class="easyui-datagrid" >  
	<thead>  
		<tr>  
			<th field="matterTxnNum" width="100">拜访序号</th>  
			<th field="visitorName"  width="100" formatter="formatVisitor">访客姓名</th>  
			<th field="companyName"  width="200" formatter="formatCompany">被访公司</th>  
			<th field="departmentName"  width="100" formatter="formatDepartment">被访部门</th>  
			<th field="employeeName"  width="150" formatter="formatEmployee">被访员工</th>  
			<th field="peopleSum"  width="50">随行人数</th>  
			<th field="licensePlate"  width="100">随行车牌</th>  
			<th field="matterDecs"  width="100" formatter="formatMatter">拜访缘由</th>  
			<th field="appointmentTime"  width="100" formatter="formatDatebox">预约时间</th>  
			<th field="expireTime"  width="100" formatter="formatDatebox">预约到期时间</th>
			<th field="actualTime"  width="100" formatter="formatDatebox">实际拜访时间</th>
			<th field="leaveTime"  width="100" formatter="formatDatebox">离开时间</th>
			<th field="status"  width="80">拜访单状态</th>  
		</tr>  
	</thead>  
</table>

	<div id="tb">
		<div region="north" border="false" style="border-bottom:1px solid #ddd;height:110px;padding:2px 2px 2px 2px;background:#fafafa;">
			<div>
				<a href="javascript:void(0)" id="addcm" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addBrand()">新增</a>
				<a href="javascript:void(0)" id="updatecm" class="easyui-linkbutton" plain="true" icon="icon-save" onclick="updateBrand()">编辑</a>
				<a href="javascript:void(0)" id="deletecm" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="deleteCompany()">删除</a>
			    <select id="vstate" class="easyui-combobox" data-options="panelHeight:'auto'" name="拜访状况" label="拜访状况" labelPosition="top" style="width:80px;margin-left:20px">
				<option value="">所有记录</option>
				<option value="1">已拜访</option>
				<option value="2">未拜访</option>
				<option value="3">被拒绝</option>
				<option value="4">已过期</option>
			    </select>
			</div>
			<!-- <div class="datagrid-btn-separator"></div> -->
			<div>
			    <input class="easyui-textbox" id="checktxnNo" name="checktxnNo" style="width:200px" label='拜访单号'/>
			    <input class="easyui-textbox" id="checkvisitorName" name="checkvisitorName" style="width:150px" label='访客姓名'/>
			    <input class="easyui-textbox" id="checkemployee" name="checkemployee" style="width:200px" label='被访员工'/>
			    <input class="easyui-textbox" id="checkcompany" name="checkcompany" style="width:200px" label='被访公司'/>
			    <input class="easyui-textbox" id="checkdepartment" name="checkdepartment" style="width:200px" label='被访部门'/>
			    <input id="dd" value="01" style="width:150px" label='来访日期'/>
			    <a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</div>
		</div>
	</div>
    
    <div id="VisitDialog" class="easyui-dialog" data-options="modal:true" closed="true" style="width:800px;height:350px;padding:10px">
		<div class="easyui-panel" style="padding:10px 10px;">
			<form id="visitorForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div style="margin-bottom:20px;float:left;width:350px">
				<input class="easyui-textbox" id="visitorName" name="visitorName" style="width:100%" label='访客姓名'/>
			</div>
			<div style="margin-bottom:20px;float:left;width:350px;margin-left:10px">
				<input class="easyui-textbox" id="idCardType" name="idCardType" style="width:100%" label='证件类型'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px">
				<input class="easyui-textbox" id="idCardNum" name="idCardNum" style="width:100%" label='证件号'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<input class="easyui-textbox" id="sex" name="sex" style="width:100%" label='性别'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;">
				<input class="easyui-textbox" id="birth1" name="birth1" style="width:100%" label='出身日月'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<input class="easyui-textbox" id="mobile" name="mobile" style="width:100%" label='联系电话'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;">
			    <input id="companyBox" class="easyui-combobox" name="companyBox"  label='拜访公司' style="width:100%" />
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<input id="departmentBox" class="easyui-combobox" name="departmentBox" label='拜访部门' style="width:100%" />
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;">
				<input id="employeeBox" class="easyui-combobox" name="employeeBox" label='受访人' style="width:100%" />
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<input id="matterBox" class="easyui-combobox" name="matterBox" label='拜访缘由' style="width:100%" />
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;">
			    <input class="easyui-textbox" id="peopleSum" name="peopleSum" style="width:100%" label='随行人数'/>
			</div>
			<div style="margin-bottom:10px;float:left;width:350px;margin-left:10px">
				<input class="easyui-datetimebox" id="visitTime" value="01" style="width:100%" label='拜访时间'/>
			</div>
			</form>
	    </div>
	</div>
    
    <div id="addCDButton">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="saveCompany" onclick="submitDialog()">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="quitAdd" onclick="quitDialog()">取消</a>
    </div>
    
    <div id="mm" class="easyui-menu" style="width:120px;">
    <div onClick="checkVisitLog()" data-options="iconCls:'icon-search'">查看详细拜访记录</div>
    <div onClick="checkMessage()" data-options="iconCls:'icon-add'">查看访客详情</div>
    <div  data-options="iconCls:'icon-redo'">
		<span>设置拜访状态</span>
		<div style="width:150px;">
			<div onClick="setStatus(2)"><b>访客已到达</b></div>
			<div onClick="setStatus(3)">拜访完成</div>
			<div onClick="setStatus(1)">白名單</div>
		</div>
    </div>
</div>
</body>
</html>