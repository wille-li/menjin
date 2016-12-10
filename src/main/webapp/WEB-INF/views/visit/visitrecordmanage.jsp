<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>拜访记录管理</title>
	<link href="<%=request.getContextPath()%>/resources/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/easyui/themes/gray/easyui.css"></c:url>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/easyui/themes/icon.css"></c:url>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/visitrecordmanage.css"></c:url>" />
    <script type="text/javascript" src="<c:url value="/resources/easyui/jquery.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/resources/easyui/jquery.easyui.min.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/service.js"></c:url>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/visitrecordmanage.js"></c:url>"></script>
  </head>
<body>
    <table id='visitortb' class="easyui-datagrid" >  
	<thead>  
		<tr>  
			<th field="matterTxnNum" width="120">拜访序号</th>  
			<th field="visitorName"  width="100" formatter="formatVisitor">访客姓名</th>  
			<th field="companyName"  width="200" formatter="formatCompany">被访公司</th>  
			<!-- <th field="departmentName"  width="100" formatter="formatDepartment">被访部门</th> -->  
			<!-- <th field="employeeName"  width="150" formatter="formatEmployee">被访员工</th>   -->
			<th field="employeeName"  width="100">被访人姓名</th>  
			<th field="employeePhone"  width="100">被访人电话</th>  
			<th field="peopleSum"  width="100">随行人数</th>  
			<th field="licensePlate"  width="100">随行车牌</th>  
			<th field="matterDecs"  width="100" formatter="formatMatter">拜访缘由</th> 
			<!-- <th field="appointmentTime"  width="100" formatter="formatDatebox">预约时间</th>  
			<th field="expireTime"  width="100" formatter="formatDatebox">预约到期时间</th> -->
			<th field="actualTime"  width="130" formatter="formatDatebox">实际拜访时间</th>
			<th field="leaveTime"  width="130" formatter="formatDatebox">离开时间</th>
			<!-- <th field="recordType" width="100">拜访单类型</th> -->
			<th field="validateMode" width="100" formatter="formatValidate">检验方式</th>
			<th field="status"  width="100" formatter="formatStatus">拜访单状态</th>  
		</tr>  
	</thead>  
</table>

	<div id="tb">
		<div region="north" border="false" class="north">
			<div class="toolRight">
			<form id="checkvisitForm"  method="post">
			  <div style="text-align: center;">
			    <input class="easyui-textbox" id="checktxnNo" name="checktxnNo" style="width:200px" label='拜访单号'/>
			    <input class="easyui-textbox" id="checkIdCard" name="checkIdCard" style="width:200px" label='证件号码'/>
			    <input class="easyui-textbox" id="checkvisitorName" name="checkvisitorName" style="width:200px" label='访客姓名'/>
			    <select id="vstate" class="easyui-combobox toolSelect" label='拜访状况' data-options="panelHeight:'auto'" name="vstate" labelPosition="top" style="width:70px">
				    <option value="">全部</option>
				    <option value="1">未拜访</option>
				    <option value="2">未离开</option>
				    <option value="3">已离开</option>
			     </select>
			  </div>
			  
			  <div class="toolSelect">
			    <input class="easyui-textbox" id="checkemployee" name="checkemployee" style="width:200px" label='被访员工'/>
			    <input class="easyui-textbox" id="checkcompany" name="checkcompany" style="width:200px" label='被访公司'/>
			    <select id="checkvalidate" class="easyui-combobox" data-options="panelHeight:'auto'" label='验证方式' name="checkvalidate" labelPosition="top" style="width:145px">
				    <option value="">全部</option>
				    <option value="1">自动验证</option>
				    <option value="2">手动验证</option>
			     </select>
			     <a href="#" onclick="checkByselect(1)" class="easyui-linkbutton notdatebtn" iconCls="icon-search" >不带时间查询</a>
			  </div>
			    
			  <div class="toolSelect">
			     <label class="textbox-label textbox-label-before starttimelabel" >开始时间</label>
			     <input id="startDate" value="01" style="width:125px"/>
			     <label class="textbox-label textbox-label-before endtimelabel">结束时间</label>
			     <input id="endDate" value="01" style="width:125px"/>
			     <a href="#" onclick="checkByselect(2)" class="easyui-linkbutton" iconCls="icon-search">带时间查询</a>
			  </div>
			  </form>
			</div>
		</div>
	</div>
    
    <div id="VisitDialog" class="easyui-dialog visitrecord" data-options="modal:true" closed="true" >
		<div class="easyui-panel vrpanel">
			<form id="visitorForm"  method="post">
			<input class="easyui-textbox" type="hidden" id="id" name="id"/>
			<div class="vsmesleft">
			<div class="vsinput">
			<input class="easyui-textbox" id="matterTxnNum" name="matterTxnNum" style="width:100%" label='拜访单号' data-options="editable:false"/>
			</div>
			<div class="vsinput">
			<input class="easyui-combobox" id="visitorName" name="visitorName" style="width:100%" label='访客姓名' data-options="editable:false"/>
			</div>
			<div class="vsinput">
				<input class="easyui-textbox" id="sex" name="sex" style="width:100%" label='性别' data-options="editable:false"/>
			</div>
			<div class="vsinput">
				<input class="easyui-textbox" id="birth1" name="birth1" style="width:100%" label='出身日月' data-options="editable:false"/>
			</div>
			</div>
			<div class="vsmesright">
			  <img src="img/sxd.jpg" alt="" width="100%" height="140px"/>
			</div>
			<div class="record">
			<div class="recordinputleft">
				<input class="easyui-textbox" id="idCardType" name="idCardType" style="width:100%" label='证件类型' data-options="editable:false"/>
			</div>
			<div class="recordinputright">
				<input class="easyui-textbox" id="idCardNum" name="idCardNum" style="width:100%" label='证件号' data-options="editable:false"/>
			</div>
			<div class="recordinputleft">
				<input class="easyui-textbox" id="mobile" name="mobile" style="width:100%" label='联系电话' data-options="editable:false"/>
			</div>
			<div class="recordinputright">
			    <input id="companyBox" class="easyui-combobox" name="companyBox"  label='拜访公司' style="width:100%" />
			</div>
			<div class="recordinputleft">
				<input class="easyui-textbox" id="employeeName" name="employeeName" style="width:100%" label='被访者'/>
			</div>
			<div class="recordinputright">
			    <input class="easyui-textbox" id="employeePhone" name="employeePhone" style="width:100%" label='被访者电话'/>
			</div>
			<div class="recordinputleft">
				<input id="matterBox" class="easyui-combobox" name="matterBox" label='拜访缘由' style="width:100%" />
			</div>
			<div class="recordinputright">
			    <input class="easyui-textbox" id="peopleSum" name="peopleSum" style="width:100%" label='随行人数'/>
			</div>
			<div class="recordinputleft">
				<input class="easyui-datetimebox" id="visitTime" value="01" style="width:100%" label='拜访时间'/>
			</div>
			<div class="recordinputright">
				<input class="easyui-datetimebox" id="leaveTime" value="01" style="width:100%" label='离开时间'/>
			</div>
			<div class="recordinputleft">
			    <input id="validateMode" class="easyui-combobox" name="validateMode" label='检验方式' style="width:100%" />
			    <!-- <input class="easyui-textbox" id="validateMode" name="validateMode" style="width:100%" label='检验方式'/> -->
			</div>
			<div class="recordinputright">
			    <input id="status" class="easyui-combobox" name="status" label='拜访单状态' style="width:100%" />
				<!-- <input class="easyui-textbox" id="status" name="status" style="width:100%" label='拜访单状态'/> -->
			</div>
			</div>
			</form>
			<div id="checkRecord" class="checkrecord">
			    <a href="#" class="easyui-linkbutton">查看该次拜访的过机记录</a>
			</div>
	    </div>
	</div>
    
    <div id="addCDButton">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="saveCompany" onclick="submitDialog()">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="quitAdd" onclick="quitDialog()">取消</a>
    </div>
    
				
    <div id="mm" class="easyui-menu" style="width:120px;">
    <div onClick="updateBrand()" data-options="iconCls:'icon-search'">查看访客详情</div>
    <!--  <div onClick="checkVisitLog()" data-options="iconCls:'icon-search'">查看详细拜访记录</div> -->
    <div onClick="addBrand()" data-options="iconCls:'icon-add'">新增访客记录</div>
    <!-- <div onClick="updateBrand()" data-options="iconCls:'icon-add'">编辑</div> -->
    <div onClick="deleteCompany()" data-options="iconCls:'icon-remove'">删除访客记录</div>
    </div> 
</div>
</body>
</html>