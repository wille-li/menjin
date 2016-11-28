  $(function(){
	  $('#departmenttb').datagrid({  
	      width:'100%',  
	      pageSize:10,  
	      pageList:[10,20,30,40,50] , 
	      nowrap:true,  
	      striped:true,  
	      collapsible:false,  
	      toolbar:"#tb",  
	      loadMsg:'数据加载中......',  
	      emptyMsg: '没有记录，请添加！',
	      fitColumns:true,//允许表格自动缩放,以适应父容器  
	      sortOrder:'asc',  
	      remoteSort:true,  
	      pagination : true,  
	      rownumbers : true,  
	      singleSelect : true,
	      fit:true
	  });  
	  var returnData = getJSONData({url:'./departmentlistbytree.do'});
	  $('#companytree').tree({
		  data:[returnData],
		  method:'get',
		  onClick: function(node){
			  var companyId = node.id;
			  $('#departmenttb').datagrid({
				  url: './departmentlistBycompanyId.do?companyId='+companyId
				});
			  $("#departmenttb").datagrid('reload'); 
		    }
	  });
	  
	  $('#departmentDialog').dialog({
		    title: '添加部门信息',
		    buttons : '#departmentDialogButton',
		    iconCls:'icon-save'
		});
  })
  
  //弹出窗口中是添加操作还是修改操作？
var isAdd = true; 
  
function addBrand(){
	  isAdd = true;
	  $("#id").textbox("setValue","");
	  $("#companyId").textbox("setValue","");
	  $("#departmentName").textbox("setValue","");
	  $("#departmentHeader").textbox("setValue","");
	  $("#departmentPhone").textbox("setValue","");
	  $('#departmentDialog').dialog({title:'添加部门信息'});
	  $('#departmentDialog').dialog("open");
}
  
function deleteCompany(){
	var selections = $('#departmenttb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要删除的记录！');
         return false;
     }
	$.messager.confirm('确认删除', '你确定要删除这条记录?', function(r){
		if (r){
			$("#id").textbox("setValue",selections[0].id);
			var submitData = $('#departmentDialogForm').serialize();
			
			$.post('./deletedepartment.do', submitData, function(data){
				 if(data){
		   			$('#departmenttb').datagrid('load');
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
	var selections = $('#departmenttb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要修改的记录！');
         return false;
       }
	$("#id").textbox("setValue",selections[0].id);
	$("#departmentName").textbox("setValue",selections[0].departmentName);
	$("#departmentHeader").textbox("setValue",selections[0].departmentHeader);
	$("#departmentPhone").textbox("setValue",selections[0].departmentPhone);
	$('#departmentDialog').dialog({title:'修改部门信息'});
	$('#departmentDialog').dialog("open");
}

function quitDialog(){
	$('#departmentDialog').dialog("close");
}

function submitDialog(){
	var companyId = $("#companytree").tree('getSelected').id
	$("#companyId").textbox("setValue",companyId);
    var url = './adddepartment.do';
    if(!isAdd){
   	 url = './updatedepartment.do';
    }
	var submitData = $('#departmentDialogForm').serialize();
    $.post(url, submitData, function(data){
   	 if(data){
            if(isAdd){
            	$('#departmenttb').datagrid('load');//如果是添加则滚动到第一页并刷新
                showmessage('提醒','数据添加成功！');
            }else{
            	$('#departmenttb').datagrid('reload');//如果是修改则刷新当前页
            	showmessage('提醒','数据修改成功！');
            } 
   	 }else {
   		showmessage('操作失败','数据操作失败！');
   	 }

        $("#departmentDialog").dialog("close"); //关闭dialog
        //清空form表单中的数据
        $("#id").textbox("setValue","");
        $("#departmentName").textbox("setValue","");
		$("#departmentHeader").textbox("setValue","");
		$("#departmentPhone").textbox("setValue","");
   }); 
}  
