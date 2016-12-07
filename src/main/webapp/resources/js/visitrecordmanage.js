  $(function(){
	  $('#visitortb').datagrid({  
	      title:'访客管理',  
	      iconCls:'icon-man',  
	      width:'100%',  
	      height:'500',
	      pageSize:20,  
	      pageList:[10,20,30,40,50] ,
	      nowrap:true,  
	      striped:true,  
	      collapsible:false,  
	      toolbar:"#tb",  
	      url:'./visitlist.do', //搜索前,触发此action请求所有用户信息  
	      loadMsg:'数据加载中......',  
	      emptyMsg: '没有记录，请添加！',
	      onRowContextMenu: onRowContextMenu,
	      fitColumns:true,//允许表格自动缩放,以适应父容器  
	      remoteSort:true,  
	      pagination : true, 
	      rownumbers:true ,
	      singleSelect : true,
	      fit:true,
	  });
	  
	  $('#VisitDialog').dialog({
		    title: '填写拜访信息',
		    buttons : '#addCDButton',
		    iconCls:'icon-save'
		});
	  
	  /*$("#vstate").combobox({
		  onChange: function (n,o) {
			  $('#visitortb').datagrid({
				  url: './visitlist.do?status='+n
				});
			  $("#visitortb").datagrid('reload'); 
		  }
	  });*/
	  
	  $('#startDate').datebox({
		    required:true
		}); 
	  
	  $('#endDate').datebox({
		    required:true
		}); 
	  
	  $('#companyBox').combobox({
		  url:'./getcompanylistByTree.do',
		  textField:'companyName',
		  valueField:'id',
		  editable:false ,
		  /*onSelect: showDepartmentByCompanyId*/
	  });
	  
	  $('#matterBox').combobox({
		  url:'./matterlistForCombox.do',
		  textField:'matterDecs',
		  valueField:'id',
		  editable:false 
	  });
	  
	  $('#validateMode').combobox({
		    valueField: 'value',
			textField: 'label',
			data: [{
				label: '自动检验',
				value: '1'
			},{
				label: '手动检验',
				value: '2'
			}],
		  editable:false 
	  });
	  
	  $('#status').combobox({
		    valueField: 'value',
			textField: 'label',
			data: [{
				label: '未拜访',
				value: '1'
			},{
				label: '未离开',
				value: '2'
			},{
				label: '已离开',
				value: '3'
			}],
		  editable:false 
	  });
  })
//弹出窗口中是添加操作还是修改操作？
var isAdd = true; 
 
  function showDepartmentByCompanyId(param){
	  $('#departmentBox').combobox({
		  url:'./departmentlistBycompanyIdForCombox.do?companyId='+param.id,
		  textField:'departmentName',
		  valueField:'id',
		  editable:false ,
		  onSelect: showEmployeeByDepartmentId
	  });
  }
  
  function showEmployeeByDepartmentId(param){
	  $('#employeeBox').combobox({
		  url:'./employeelistBydepartmentIdForCombox.do?departmentId='+param.id,
		  textField:'employeeName',
		  valueField:'id',
		  editable:false 
	  });
  }
  function onRowContextMenu(e, rowIndex, rowData){
	   e.preventDefault();
	   $("#visitortb").datagrid("selectRow",rowIndex);
	   $('#mm').menu('show', {
	        left:e.pageX,
	        top:e.pageY
	    });       
	}
  
  function checkVisitLog(){
	  var selections = $('#visitortb').datagrid('getSelections');
	  showmessage('提醒',selections[0].id+"Log");
  }
  
  function checkByselect(type){
	  
	  var startDate = $('#startDate').datebox('getValue');   
	  var endDate = $('#endDate').datebox('getValue'); 
	  var checktxnNo = $('#checktxnNo').textbox('getValue');
	  var checkIdCard = $('#checkIdCard').textbox('getValue');
	  var checkvisitorName = $('#checkvisitorName').textbox('getValue');
	  var checkemployee = $('#checkemployee').textbox('getValue');
	  var checkcompany = $('#checkcompany').textbox('getValue');
	  var checkvalidate = $('#checkvalidate').combobox('getValue');
	  var vstate = $('#vstate').combobox('getValue');
	  /*var url;*/
	  var queryParams;
      if(type == 1){
		  /*url = './visitlist.do?matterTxnNum='+checktxnNo+'&IdCardNum='+checkIdCard+
		        '&visitorName='+checkvisitorName+'&employeeName='+checkemployee+'&companyName='+checkcompany+
		        '&validateMode='+checkvalidate+'&status='+vstate;*/
		  queryParams = {matterTxnNum:checktxnNo,IdCardNum:checkIdCard,visitorName:checkvisitorName,
				  employeeName:checkemployee,companyName:checkcompany,validateMode:checkvalidate,
				  status:vstate};
	  }else{
		/*  url = './visitlist.do?startDate='+startDate+'&endDate='+endDate+
		        'matterTxnNum='+checktxnNo+'&IdCardNum='+checkIdCard+
	            '&visitorName='+checkvisitorName+'&employeeName='+checkemployee+'&companyName='+checkcompany+
	            '&validateMode='+checkvalidate+'&status='+vstate;*/
		  queryParams = {startDate:startDate,endDate:endDate,matterTxnNum:checktxnNo,IdCardNum:checkIdCard,
				  visitorName:checkvisitorName,employeeName:checkemployee,companyName:checkcompany,
				  validateMode:checkvalidate,status:vstate};
	  }
      $('#visitortb').datagrid({
    	  queryParams:queryParams
		});
	  $("#visitortb").datagrid('load'); 
	  
  }
//显示访客信息的
function formatVisitor(value,row,index){
      return new Object(row["visitor"]).visitorName;     
}
//显示被拜访公司信息的
function formatCompany(value,row,index){
      return new Object(row["company"]).companyName;     
}
//显示被拜访部门信息的
function formatDepartment(value,row,index){
      return new Object(row["department"]).departmentName;     
}
//显示被拜访员工信息的
function formatEmployee(value,row,index){
      return new Object(row["employee"]).employeeName;     
}
//显示拜访缘由信息的
function formatMatter(value,row,index){
      return new Object(row["matter"]).matterDecs;     
}

  
function addBrand(){
	  isAdd = true;
	  $("#id").textbox("setValue","");
	  $('#visitorName').textbox('setValue',"");
	  $('#idCardType').textbox('setValue',"");
	  $('#idCardNum').textbox('setValue',"");
	  $('#sex').textbox('setValue',"");
	  $('#mobile').textbox('setValue');
      $('#companyBox').combobox('setValue',"");
	  $('#employeeName').textbox('setValue',"");
	  $('#employeePhone').textbox('setValue',"");
	  $('#matterBox').combobox('setValue',"");
	  $('#peopleSum').textbox('setValue',"");
	  $('#visitTime').datetimebox('setValue',"");
	  $('#validateMode').textbox('setValue',"");
	  $('#status').textbox('setValue',"");
	  $('#leaveTime').textbox('setValue',"");
	  $('#checkRecord').hide();
	  $('#VisitDialog').dialog({title:'填写拜访信息'});
	  $('#VisitDialog').dialog("open");
}
  
function deleteCompany(){
	var selections = $('#visitortb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要删除的记录！');
         return false;
     }
	$.messager.confirm('确认删除', '你确定要删除这条记录?', function(r){
		if (r){
			$("#id").textbox("setValue",selections[0].id);
			var submitData = $('#visitorForm').serialize();
			$.post('./deleteVisit.do', submitData, function(data){
				 if(data){
		   			$('#visitortb').datagrid('load');
		   			 showmessage('提醒','数据删除成功！');
		   		 }else {
		   			showmessage('操作失败','删除数据失败！');
		   		 }
			   }); 
			  $("#id").textbox("setValue","");
		}
	});
	
  }

function updateBrand(){
	isAdd = false;
	var selections = $('#visitortb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要修改的记录！');
         return false;
       }
	$("#id").textbox("setValue",selections[0].id);
	$('#visitorName').textbox('setValue',selections[0].visitor.visitorName);
	$('#idCardType').textbox('setValue',selections[0].visitor.idCardType);
	$('#idCardNum').textbox('setValue',selections[0].visitor.idCardNum);
	$('#sex').textbox('setValue',selections[0].visitor.sex);
    $('#mobile').textbox('setValue',selections[0].visitor.mobile);
    $('#companyBox').combobox('setValue',selections[0].company.companyName);
	$('#employeeName').textbox('setValue',selections[0].employeeName);
	$('#employeePhone').textbox('setValue',selections[0].employeePhone);
	$('#matterBox').combobox('setValue',selections[0].matter.matterDecs);
	$('#peopleSum').textbox('setValue',selections[0].peopleSum);
	$('#visitTime').datetimebox('setValue',formatDatebox(selections[0].actualTime));
	$('#validateMode').textbox('setValue',selections[0].validateMode);
	$('#status').textbox('setValue',selections[0].status);
	$('#leaveTime').textbox('setValue',formatDatebox(selections[0].leaveTime));
	$('#checkRecord').show();
	$('#VisitDialog').dialog({title:'修改拜访信息'});
	$('#VisitDialog').dialog("open");
}


function quitDialog(){
	$('#VisitDialog').dialog("close");
}

function submitDialog(){
	
	var id = $('#id').textbox('getValue');
	var visitorName = $('#visitorName').textbox('getValue');
	var idCardType = $('#idCardType').textbox('getValue');
	var idCardNum = $('#idCardNum').textbox('getValue');
	var birth1 = $('#birth1').textbox('getValue');
	var sex = $('#sex').textbox('getValue');
	var mobile = $('#mobile').textbox('getValue');
	var companyId = $('#companyBox').combobox('getValue');
	/*var departmentId = $('#departmentBox').combobox('getValue');
	var employeeId = $('#employeeBox').combobox('getValue');*/
	var employeeName = $('#employeeName').textbox('getValue');
	var employeePhone = $('#employeePhone').textbox('getValue');
	var matterId = $('#matterBox').combobox('getValue');
	var peopleSum = $('#peopleSum').textbox('getValue');
	var visitTime = $('#visitTime').datetimebox('getValue');
	var validateMode = $('#validateMode').textbox('getValue');
	var status = $('#status').textbox('getValue');
	var leaveTime = $('#leaveTime').textbox('getValue');
	
	var submitData = {id:id,visitorName:visitorName,idCardType:idCardType,idCardNum:idCardNum,
			sex:sex,mobile:mobile,companyId:companyId,employeeName:employeeName,employeePhone:employeePhone,/*departmentId:departmentId,employeeId:employeeId,*/
			matterId:matterId,peopleSum:peopleSum,visitTime:visitTime,validateMode:validateMode,status:status,birth:birth1};
    var url = './addVisit.do';
    if(!isAdd){
   	 url = './updateVisit.do';
    }
	/*var submitData = $('#visitorForm').serialize();*/
    $.post(url, submitData, function(data){
   	 if(data){
            if(isAdd){
            	$('#visitortb').datagrid('load');//如果是添加则滚动到第一页并刷新
                showmessage('提醒','数据添加成功！');
            }else{
            	$('#visitortb').datagrid('reload');//如果是修改则刷新当前页
            	showmessage('提醒','数据修改成功！');
            } 
   	 }else {
   		showmessage('操作失败','数据操作失败！');
   	 }

        $("#VisitDialog").dialog("close"); //关闭dialog
        //清空form表单中的数据
   }); 
}

function validation(){
	var visitorName = $('#visitorName').textbox('getValue');
	var idCardType = $('#idCardType').textbox('getValue');
	var idCardNum = $('#idCardNum').textbox('getValue');
	var sex = $('#sex').textbox('getValue');
	var mobile = $('#mobile').textbox('getValue');
	var companyId = $('#companyBox').combobox('getValue');
	var departmentId = $('#departmentBox').combobox('getValue');
	var employeeId = $('#employeeBox').combobox('getValue');
	var matterId = $('#matterBox').combobox('getValue');
	var peopleSum = $('#peopleSum').textbox('getValue');
	var visitTime = $('#visitTime').datetimebox('getValue');
}



  