  $(function(){
	  $('#Roletb').datagrid({  
	      title:'角色管理',  
	      iconCls:'icon-man',  
	      width:'100%',  
	      height:'500',
	      pageSize:20,  
	      pageList:[10,20,30,40,50] ,
	      nowrap:true,  
	      striped:true,  
	      collapsible:false,  
	      toolbar:"#tb",  
	      url:'./role/getAllRoleList.do', //搜索前,触发此action请求所有用户信息  
	      loadMsg:'数据加载中......',  
	      emptyMsg: '没有记录，请添加！',
	      fitColumns:true,//允许表格自动缩放,以适应父容器  
	      remoteSort:true,  
	      pagination : true, 
	      rownumbers:true ,
	      singleSelect : true,
	      fit:true
	  });
	  
	 $('#resources').tree({
		    url: './resource/getResourceNodes.do',
			checkbox: true,
			rownumbers: true,
	 });
	 
	 $('#RoleDialog').dialog({
		    title: '新增角色',
		    buttons : '#RoleButton',
		    iconCls:'icon-save'
	 });
		
	 $('#ResourceDialog').dialog({
		    title: '分配权限',
		    buttons : '#resourceButton',
		    iconCls:'icon-save'
	 });
  })
//弹出窗口中是添加操作还是修改操作？
var isAdd = true; 
function disresource(){
	var checkeds = $('#resources').tree('getChecked');
	for ( var i = 0; i < checkeds.length; i++) {  
        var node = $('#resources').tree('find', checkeds[i].id);//查找节点  
        $('#resources').tree('uncheck', node.target);//将得到的节点选中  
    }  
	var selections = $('#Roletb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要分配权限的角色！');
         return false;
     }
	var resources = selections[0].resources;
	if(resources != null){
		for(var i in resources){
			var nodeDep = $('#resources').tree('find',resources[i].id); 
			 $('#resources').tree('check',nodeDep.target);  
		}
		/*resources.forEach(function (element, index, array) {  
			 var nodeDep = $('#resources').tree('find',element.id); 
			 $('#resources').tree('check',nodeDep.target);  
		});  */
	}
	$('#ResourceDialog').dialog({title:'分配权限'});
	$('#ResourceDialog').dialog("open");
}

function submitResource(){
	var nodes = $('#resources').tree('getChecked');
	var resourceIds = '';
	for(var i=0; i<nodes.length; i++){
		if (resourceIds != '') resourceIds += ',';
		resourceIds += nodes[i].id;
	}
	var selections = $('#Roletb').datagrid('getSelections');
	var roleId = selections[0].id;
	var submitData = {roleId : roleId,resourceIds : resourceIds};
	$.post('./role/controlResourceRole.do', submitData, function(data){
		 if(data){
			 if(data.rInfo.ret == 0){
				 $('#Roletb').datagrid('reload');
	   			 showmessage('提醒',data.rInfo.msg);
			 }else{
				 showmessage('操作失败',data.rInfo.msg); 
			 }
  		 }else {
  			showmessage('操作失败','网络连接失败，请与管理员联系！');
  		 }
		 $("#ResourceDialog").dialog("close"); //关闭dialog
	   }); 
}

function searchRole(){
	var checkmessage = $('#searchInput').searchbox('getValue');
	var queryParams = {checkmessage:checkmessage};
	 $('#Roletb').datagrid({
   	  queryParams:queryParams
		});
}
function addRole(){
	  isAdd = true;
	  $("#id").textbox("setValue","");
	  $("#name").textbox("setValue","");
	  $("#description").textbox("setValue","");
	  $('#RoleDialog').dialog({title:'添加新角色'});
	  $('#RoleDialog').dialog("open");
}
  
function deleteRole(){
	var selections = $('#Roletb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要删除的记录！');
         return false;
     }
	$.messager.confirm('确认删除', '你确定要删除这条记录?', function(r){
		if (r){
			$("#id").textbox("setValue",selections[0].id);
			var submitData = $('#addComForm').serialize();
			$.post('./role/deleteRole.do', submitData, function(data){
				 if(data){
					 if(data.rInfo.ret == 0){
						 $('#Roletb').datagrid('load');
			   			 showmessage('提醒',data.rInfo.msg);
					 }else{
						 showmessage('操作失败',data.rInfo.msg); 
					 }
		   		 }else {
		   			showmessage('操作失败','网络连接失败，请与管理员联系！');
		   		 }
			   }); 
			  $("#id").textbox("setValue","");
		}
	});
	
  }

function updateRole(){
	isAdd = false;
	var selections = $('#Roletb').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要修改的记录！');
         return false;
       }
	$("#id").textbox("setValue",selections[0].id);
	$("#name").textbox("setValue",selections[0].name);
	$("#description").textbox("setValue",selections[0].description);
	$('#RoleDialog').dialog({title:'修改角色数据'});
	$('#RoleDialog').dialog("open");
}

function quitDialog(){
	$('#RoleDialog').dialog("close");
}

function quitResource(){
	$('#ResourceDialog').dialog("close");
}

function submitDialog(){
    var url = './role/addRole.do';
    if(!isAdd){
   	 url = './role/updateRole.do';
    }
	var submitData = $('#addComForm').serialize();
    $.post(url, submitData, function(data){
   	 if(data){
   		if(data.rInfo.ret == 0){
   			if(isAdd){
            	$('#Roletb').datagrid('load');//如果是添加则滚动到第一页并刷新
                showmessage('提醒',data.rInfo.msg);
            }else{
            	$('#Roletb').datagrid('reload');//如果是修改则刷新当前页
            	showmessage('提醒',data.rInfo.msg);
            } 
   		}else{
   			showmessage("操作失败",data.rInfo.msg);
   		}
   	 }else {
   		showmessage('操作失败','网络连接失败，请与管理员联系！');
   	 }

        $("#RoleDialog").dialog("close"); //关闭dialog
        //清空form表单中的数据
        $("#id").textbox("setValue","");
        $("#companyName").textbox("setValue","");
		$("#companyAddress").textbox("setValue","");
		$("#companyPhone").textbox("setValue","");
   }); 
}  



  