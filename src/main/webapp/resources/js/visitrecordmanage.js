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
	      rowStyler: function(index,row){
				if (row.rank == 2){
					return 'color:#FF0000;font-weight:bold;';
				}
				
				if (row.rank == 3){
					return 'color:#FFFF00;font-weight:bold;';
				}
			}
	  });
	  
	  $('#VisitDialog').dialog({
		    title: '填写拜访信息',
		    buttons : '#addCDButton',
		    iconCls:'icon-save'
		});
	  
	  $("#vstate").combobox({
		  onChange: function (n,o) {
			  $('#visitortb').datagrid({
				  url: './visitorlist.do?status='+n
				});
			  $("#visitortb").datagrid('reload'); 
		  }
	  });
	  
	  $('#dd').datebox({
		    required:true
		}); 
	  
	  $('#companyBox').combobox({
		  url:'./getcompanylistByTree.do',
		  textField:'companyName',
		  valueField:'id',
		  editable:false ,
		  onSelect: showDepartmentByCompanyId
	  });
	  
	  $('#matterBox').combobox({
		  url:'./matterlistForCombox.do',
		  textField:'matterDecs',
		  valueField:'id',
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
  
  function checkMessage(){
	  var selections = $('#visitortb').datagrid('getSelections');
	  showmessage('提醒',selections[0].id+"的詳細個人資料。");
  }
  
  function setStatus(status){
	  
	  $.messager.confirm('提醒', '你确定要将该访客设置吗?', function(r){
		  if (r){
	            var selections = $('#visitortb').datagrid('getSelections');
	            var submitData = {id:selections[0].id,status:status};
	            $.post('./updateVisitStatus.do', submitData, function(data){
		   	        if(data){
		                $('#visitortb').datagrid('load');//如果是添加则滚动到第一页并刷新
		        	        showmessage('提醒','成功'); 
		   	        }else {
		   		        showmessage('操作失败','数据操作失败！');
		   	        }
		   });
		  }
	  });
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
	  $("#visitorName").textbox("setValue","");
	  $("#idCardType").textbox("setValue","");
	  $("#idCardNum").textbox("setValue","");
	  $("#sex").textbox("setValue","");
	  $("#nation").textbox("setValue","");
	  $("#birth").textbox("setValue","");
	  $("#address").textbox("setValue","");
	  $("#mobile").textbox("setValue","");
	  $("#email").textbox("setValue","");
	  $("#rank").textbox("setValue","");
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
	$("#visitorName").textbox("setValue",selections[0].visitorName);
	$("#idCardType").textbox("setValue",selections[0].idCardType);
	$("#idCardNum").textbox("setValue",selections[0].idCardNum);
	$("#sex").textbox("setValue",selections[0].sex);
    $("#nation").textbox("setValue",selections[0].nation);
	$("#birth").textbox("setValue",selections[0].birth);
	$("#address").textbox("setValue",selections[0].address);
	$("#mobile").textbox("setValue",selections[0].mobile);
	$("#email").textbox("setValue",selections[0].email);
	$("#rank").textbox("setValue",selections[0].rank);
	$('#VisitDialog').dialog({title:'修改拜访信息'});
	$('#VisitDialog').dialog("open");
}

function quitDialog(){
	$('#VisitDialog').dialog("close");
}

function submitDialog(){
	
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
	var submitData = {visitorName:visitorName,idCardType:idCardType,idCardNum:idCardNum,
			sex:sex,mobile:mobile,companyId:companyId,departmentId:departmentId,employeeId:employeeId,
			matterId:matterId,peopleSum:peopleSum,visitTime:visitTime};
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
	var peopleSum = $('#visitTime').datetimebox('getValue');
}



  