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
	      url:'./visitorlist.do', //搜索前,触发此action请求所有用户信息  
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
	  
	  $('#VisitorDialog').dialog({
		    title: '添加公司信息',
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
	  
  })
//弹出窗口中是添加操作还是修改操作？
var isAdd = true; 
 
  function onRowContextMenu(e, rowIndex, rowData){
	   e.preventDefault();
	   $("#visitortb").datagrid("selectRow",rowIndex);
	   $('#mm').menu('show', {
	        left:e.pageX,
	        top:e.pageY
	    });       
	}
  
//显示拜访缘由信息的
  function formatRank(value,row,index){
	    if(value == '3'){
	    	return "白名单用户";
	    }else if(value == '2'){
	    	return "黑名单用户";
	    }else{
	    	return "普通用户";
	    }
  }
  
  function checkVisitLog(){
	  var selections = $('#visitortb').datagrid('getSelections');
	  showmessage('提醒',selections[0].visitorName+"Log");
  }
  
  function checkMessage(){
	  var selections = $('#visitortb').datagrid('getSelections');
	  showmessage('提醒',selections[0].visitorName+"的詳細個人資料。");
  }
  
  function setRank(rank){
	  var rankName = "普通用户";
	  if(rank == 3){
		  rankName = "白名单"
       }else if(rank == 2){
    	   rankName = "黑名单";
       }else{
    	   rankName = "普通用户";
       }
	  $.messager.confirm('提醒', '你确定要将该访客设置为'+rankName+'吗?', function(r){
		  if (r){
	            var selections = $('#visitortb').datagrid('getSelections');
	            var submitData = {id:selections[0].id,rank:rank};
	            $.post('./updateVisitorRank.do', submitData, function(data){
		   	        if(data){
		                $('#visitortb').datagrid('load');//如果是添加则滚动到第一页并刷新
		        	        showmessage('提醒',"访客"+selections[0].visitorName+'已被设为'+rankName+'!'); 
		   	        }else {
		   		        showmessage('操作失败','数据操作失败！');
		   	        }
		   });
		  }
	  });
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
	  $('#VisitorDialog').dialog({title:'添加公司信息'});
	  $('#VisitorDialog').dialog("open");
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
			$.post('./deleteVisitor.do', submitData, function(data){
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
	$('#VisitorDialog').dialog({title:'修改访客信息'});
	$('#VisitorDialog').dialog("open");
}

function quitDialog(){
	$('#VisitorDialog').dialog("close");
}

function submitDialog(){
    var url = './addVisitor.do';
    if(!isAdd){
   	 url = './updateVisitor.do';
    }
	var submitData = $('#visitorForm').serialize();
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

        $("#VisitorDialog").dialog("close"); //关闭dialog
        //清空form表单中的数据
   }); 
}  



  