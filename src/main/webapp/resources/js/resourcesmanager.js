$(function(){
	var p = $("#resourceListDatagrid").datagrid('getPager');
	$(p).pagination({
		beforePageText:'第',
		afterPageText :'页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	
	$("#parentSelect").combobox({
			  onLoadSuccess:function(){ //默认选中第一条数据
				    var data= $(this).combobox("getData");
	                if (data.length > 0) {
	                	$('#parentSelect').combobox('select', data[0].parentDesc);
	                }
	                var parentValue = $('#parentSelect').combobox('getValue');
	                console.log('parentValue='+parentValue);
	                $("#childSelect").combobox({
	                	url:'./resource/getChildrenResources.do',
	                	method : "post",
	                	valueField : 'resourceUrl',
	                    textField : 'name',
	                    queryParams :{
	                    	"parentDesc" : parentValue
	                    },
	                    onLoadSuccess:function(){ //默认选中第一条数据
	    				    var datas= $("#childSelect").combobox("getData");
	    	                if (datas.length > 0) {
	    	                	$('#childSelect').combobox('select', datas[0].resourceUrl);
	    	                }
	                    }
	                });
			  },
			  
			  onChange : function(newValue,oldValue){
				if(oldValue!=null && oldValue!=''){
				     $("#childSelect").combobox({
		                	url:'./resource/getChildrenResources.do',
		                	method : "post",
		                	valueField : 'resourceUrl',
		                    textField : 'name',
		                    queryParams :{
		                    	"parentDesc" : newValue
		                    },
		                    onLoadSuccess:function(){ //默认选中第一条数据
		    				    var datas= $("#childSelect").combobox("getData");
		    	                if (datas.length > 0) {
		    	                	$('#childSelect').combobox('select', datas[0].resourceUrl);
		    	                }
		                    }
		                });
				}
			  }
	
	});
});

var isAdd = true; 

function addResource(){
	  isAdd = true;
	  $("#saveResource").html("保存");
	  $("#resourceForm").form("clear");
	  $('#ResourceDialog').dialog({title:'添加权限资源信息'});
	  $('#ResourceDialog').dialog("open");
	  $("#parentCombob").combobox({
		  onLoadSuccess:function(){ //默认选中第一条数据
			    var data= $(this).combobox("getData");
                if (data.length > 0) {
                 $('#parentCombob').combobox('select', data[0].parentDesc);
                 }
		  }
	  }
	  );
}

function closeDialog(){
	$('#ResourceDialog').dialog("close");
}

function submitDialog(){
    var url = './resource/addResource.do';
    if(!isAdd){
   	 url = './resource/updateResource.do';
    }
	var submitData = $('#resourceForm').serialize();
    $.post(url, submitData, function(data){
   	 if(data){
            if(isAdd){
            	$('#resourceListDatagrid').datagrid('load');//如果是添加则滚动到第一页并刷新
                showmessage('提醒','数据添加成功！');
            }else{
            	$('#resourceListDatagrid').datagrid('reload');//如果是修改则刷新当前页
            	showmessage('提醒','数据修改成功！');
            } 
   	 }else {
   		showmessage('操作失败','数据操作失败！');
   	 }

        $("#ResourceDialog").dialog("close"); //关闭dialog
        //清空form表单中的数据
   }); 
} 

function updateResource(){
	isAdd = false;
	var selections = $('#resourceListDatagrid').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要查看的记录！');
         return false;
       }
	$("#id").textbox("setValue",selections[0].id);
	$("#resourceUrl").textbox("setValue",selections[0].resourceUrl).textbox("setText",selections[0].resourceUrl);
	$("#parentCombob").combobox("setValue",selections[0].parentDesc).combobox("setText",selections[0].parentDesc);
	$("#description").textbox("setValue",selections[0].description).textbox("setText",selections[0].description);
	$("#name").textbox("setValue",selections[0].name).textbox("setText",selections[0].name);;
	$("#saveResource").html("保存修改");
	$('#ResourceDialog').dialog({title:'查看权限资源信息'});
	$('#ResourceDialog').dialog("open");
}

function deleteResource(){
	var selections = $('#resourceListDatagrid').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要删除的权限资源！');
         return false;
     }
	$.messager.confirm('确认删除', '你确定要删除这条权限资源吗?', function(r){
		if (r){
			$("#id").textbox("setValue",selections[0].id);
			var submitData = $('#resourceForm').serialize();
			$.post('./resource/deleteResource.do', submitData, function(data){
				 if(data){
		   			$('#resourceListDatagrid').datagrid('load');
		   			 showmessage('提醒','资源删除成功！');
		   		 }else {
		   			showmessage('操作失败','删除资源失败！');
		   		 }
			   }); 
			  $("#id").textbox("setValue","");
		}
	});
}

function searchResource(){
	var child = $('#childSelect').combobox('getValue');
	console.log('searchResource child='+child);
	 $('#resourceListDatagrid').datagrid({  
	      url:'./resource/getResourceByUrl.do', 
	      queryParams: {
	    	  resourceUrl : child
	      }
	  });
}

