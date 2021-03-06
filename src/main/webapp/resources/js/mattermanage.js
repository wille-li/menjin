﻿  $(function(){
	  $('#mattertb').datagrid({  
	      title:'来访事由管理',  
	      iconCls:'icon-man',  
	      width:'100%',  
	      pageSize:20,  
	      pageList:[10,20,30,40,50] ,
	      nowrap:true,  
	      striped:true,  
	      collapsible:false,  
	      toolbar:"#tb",  
	      url:'./matterlist.do', //搜索前,触发此action请求所有用户信息  
	      loadMsg:'数据加载中......',  
	      emptyMsg: '没有记录，请添加！',
	      fitColumns:true,//允许表格自动缩放,以适应父容器  
	      remoteSort:true,  
	      pagination : true, 
	      rownumbers:true ,
	      singleSelect : true,
	      fit:true
	  });
	  
	  $('#matterDialog').dialog({
		    title: '添加公司信息',
		    buttons : '#addCDButton',
		    iconCls:'icon-save'
		});
	  
  })
//弹出窗口中是添加操作还是修改操作？
var isAdd = true; 
  
  function checkByMatterDecs(){
	  var matterDecs = $('#checkByMatterDecs').searchbox('getValue');
		var queryParams = {matterDecs:matterDecs};
		 $('#mattertb').datagrid({
	   	  queryParams:queryParams
			});
  }
  
function addBrand(){
	  isAdd = true;
	  $("#id").textbox("setValue","");
	  $("#matterDecs").textbox("setValue","");
	  $('#matterDialog').dialog({title:'添加拜访事由'});
	  $('#matterDialog').dialog("open");
}
  
function deleteCompany(){
	var selections = $('#mattertb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要删除的记录！');
         return false;
     }
	$.messager.confirm('确认删除', '你确定要删除这条记录?', function(r){
		if (r){
			$("#id").textbox("setValue",selections[0].id);
			var submitData = $('#matterForm').serialize();
			$.post('./deleteMatter.do', submitData, function(data){
				 if(data){
		   			$('#mattertb').datagrid('load');
		   			showmessage('提醒',data.rInfo.msg);
		   		 }else {
		   			showmessage('操作失败','网络连接失败，请与管理员联系！');
		   		 }
			   }); 
			  $("#id").textbox("setValue","");
		}
	});
	
  }

function updateBrand(){
	isAdd = false;
	var selections = $('#mattertb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要修改的记录！');
         return false;
       }
	$("#id").textbox("setValue",selections[0].id);
	$("#matterDecs").textbox("setValue",selections[0].matterDecs);
	$('#matterDialog').dialog({title:'修改拜访事由'});
	$('#matterDialog').dialog("open");
}

function quitDialog(){
	$('#matterDialog').dialog("close");
}

function submitDialog(){
    var url = './addMatter.do';
    if(!isAdd){
   	 url = './updateMatter.do';
    }
	var submitData = $('#matterForm').serialize();
    $.post(url, submitData, function(data){
    	if(data){
   			$('#mattertb').datagrid('load');
   			showmessage('提醒',data.rInfo.msg);
   		 }else {
   			showmessage('操作失败','网络连接失败，请与管理员联系！');
   		 }

        $("#matterDialog").dialog("close"); //关闭dialog
        //清空form表单中的数据
        $("#id").textbox("setValue","");
        $("#matterDecs").textbox("setValue","");
   }); 
}  


  