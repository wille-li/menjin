var isAdd = true; 

function formatStatus(value,rows,index){
	if(rows.status==1){
		return "启用";  
	}else if(rows.status==0){  
        return "禁用";  
    }  
}

function formatRoles(value,rows,index){
	var roles='';
	value.forEach(function(element,index,array){
			roles+=element.name+'  ';
		});
		return roles;
}

function addUser(){
	  isAdd = true;
	 /* $("#id").textbox("setValue","");
	  $("#username").textbox("setValue","");
	  $("#password").passwordbox("setValue","");
	  $("#passwdConfirm").passwordbox("setValue","");
	  $("#name").textbox("setValue","");
	  $("#idCardNumber").textbox("setValue","");
	  $("#birthday").datebox("setValue","");
	  $("#address").textbox("setValue","");
	  $("#email").textbox("setValue","");
	  $("#mobilephone").textbox("setValue","");
	  $("#status").combobox("setValue","");*/
	  $("#saveUser").html("保存");
	  $("#userForm").form("clear");
	  $('#UserDialog').dialog({title:'添加用户信息'});
	  $('#UserDialog').dialog("open");
}

function closeDialog(){
	$('#UserDialog').dialog("close");
}

function submitDialog(){
    var url = './user/addUser.do';
    if(!isAdd){
   	 url = './user/updateUser.do';
    }
	var submitData = $('#userForm').serialize();
    $.post(url, submitData, function(data){
   	 if(data){
            if(isAdd){
            	$('#userListDatagrid').datagrid('load');//如果是添加则滚动到第一页并刷新
                showmessage('提醒','数据添加成功！');
            }else{
            	$('#userListDatagrid').datagrid('reload');//如果是修改则刷新当前页
            	showmessage('提醒','数据修改成功！');
            } 
   	 }else {
   		showmessage('操作失败','数据操作失败！');
   	 }

        $("#UserDialog").dialog("close"); //关闭dialog
        //清空form表单中的数据
   }); 
} 

function updateUser(){
	isAdd = false;
	var selections = $('#userListDatagrid').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要查看的记录！');
         return false;
       }
	$("#id").textbox("setValue",selections[0].id);
	$("#username").textbox("setValue",selections[0].username).textbox("setText",selections[0].username);
	$("#password").passwordbox("setValue",selections[0].password).passwordbox("setText",selections[0].password).passwordbox("hidePassword");
	$("#passwdConfirm").passwordbox("setValue",selections[0].password).passwordbox("setText",selections[0].password).passwordbox("hidePassword");;
	$("#name").textbox("setValue",selections[0].name).textbox("setText",selections[0].name);;
    $("#idCardNumber").textbox("setValue",selections[0].idCardNumber).textbox("setText",selections[0].idCardNumber);
	$("#birthday").datebox("setValue",selections[0].birthday).datebox("setText",selections[0].birthday);
	$("#address").textbox("setValue",selections[0].address).textbox("setText",selections[0].address);
	$("#email").textbox("setValue",selections[0].email).textbox("setText",selections[0].email);
	$("#mobilephone").textbox("setValue",selections[0].mobilephone).textbox("setText",selections[0].mobilephone);
	$("#status").combobox("setValue",selections[0].status).combobox("setText",selections[0].status==1?"启用" : "禁用");
	$("#saveUser").html("保存修改");
	$('#UserDialog').dialog({title:'查看用户信息'});
	$('#UserDialog').dialog("open");
}

function deleteUser(){
	var selections = $('#userListDatagrid').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒','请选择你要删除的用户！');
         return false;
     }
	$.messager.confirm('确认删除', '你确定要删除这个用户吗?', function(r){
		if (r){
			$("#id").textbox("setValue",selections[0].id);
			var submitData = $('#userForm').serialize();
			$.post('./user/deleteUser.do', submitData, function(data){
				 if(data){
		   			$('#userListDatagrid').datagrid('load');
		   			 showmessage('提醒','用户删除成功！');
		   		 }else {
		   			showmessage('操作失败','删除用户失败！');
		   		 }
			   }); 
			  $("#id").textbox("setValue","");
		}
	});
}

function searchUser(){
	var searchValue = $("#searchInput").searchbox("getValue");
	var searchBy = $("#state").combobox("getValue");
	console.log('searchValue='+searchValue);
	console.log('searchBy='+searchBy);
	var queryParams ;
	var access;
	if(searchBy=='username'){
		access = './user/getUserByUsername.do';
		queryParams = {username: searchValue};
	}else if(searchBy=='roleName'){
		access = './user/getUserByRolename.do';
		queryParams = {roleName: searchValue}
	}
	 $('#userListDatagrid').datagrid({  
	      url:access, 
	      queryParams: queryParams
	  });
}