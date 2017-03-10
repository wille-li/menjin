<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="diy_table_panel" style="font-size:12px">
	<div class="diy_table_head">
		<form action="/include/tables/diytable.html" data-toggle="ajaxsearch"
			class="row">
			<div style="float:left;margin-left: 15px">
				<div class="input-group">
					<button class="btn btn-success" type="button" id="adduser" onclick="addUser()">新增用户</button>
				</div>
			</div>
			<div style="float:left;margin-left: 10px">
				<div class="input-group">
					<button class="btn btn-info" type="button" id="userdetail" onclick="updateUser()">个人资料</button>
				</div>
			</div>
			<div style="float:left;margin-left: 10px">
				<div class="input-group">
					<button class="btn btn-danger" type="button" id="deluser" onclick="deleteUser()">删除用户</button>
				</div>
			</div>
			<div style="float:left;margin-left: 10px">
				<div class="input-group">
					<button class="btn btn-warning" type="button" id="userrole" onclick="assignRole()">分配角色</button>
				</div>
			</div>
		</form>
	</div>
	<div class="diy_table_body box">
		<div class="">
			<div class="box-body">
				<table id="UserTable" class="display table table-bordered table-hover">
					<thead>
						<tr>
							<th>用户名</th>
							<th>所属角色</th>
							<th>创建时间</th>
							<th>修改时间</th>
							<th>是否禁用</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="modal fade" id="usermodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="usertitle">新增用户</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="userform">
					    <input class="easyui-textbox" type="hidden" id="id" name="id"/>
						<fieldset>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="username">账号名</label>
								<div class="col-sm-4">
									<input class="form-control" id="username" name="username" type="text"
										placeholder="" />
								</div>
								<!-- <label class="col-sm-2 control-label" for="ds_name">状态</label>
								<div class="col-sm-4">
									<input class="form-control" id="ds_name" type="text"
										placeholder="" />
								</div> -->
                                  <label class="col-sm-2 control-label" for="status">状态</label>
                                  <div class="col-sm-4">
                                   <select class="form-control" id="status" name="status">
                                      <option value="1">启用</option>   
    					              <option value="0">禁用</option>   
                                  </select>
                                  </div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="password">密码</label>
								<div class="col-sm-4">
									<input class="form-control" id="password" type="password" name = "password"
										placeholder="" />
								</div>
								<label class="col-sm-2 control-label" for="passwdConfirm">确认密码</label>
								<div class="col-sm-4">
									<input class="form-control" id="passwdConfirm" type="password" name="passwdConfirm"
										placeholder="" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="name">用户姓名</label>
								<div class="col-sm-4">
									<input class="form-control" id="name" type="text" name="name"
										placeholder="" />
								</div>
								<label class="col-sm-2 control-label" for="idCardNumber">身份证号</label>
								<div class="col-sm-4">
									<input class="form-control" id="idCardNumber" type="text" name="idCardNumber"
										placeholder="" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="birthday11">出生年月</label>
								<div class="col-sm-4">
									<div class="input-group date">
                                        <div class="input-group-addon">
                                             <i class="fa fa-calendar"></i>
                                        </div>
                                        <input type="text" class="form-control pull-right" id=birthday11 name="birthday11">
                                    </div>
								</div>
								<label class="col-sm-2 control-label" for="mobilephone">手机号码</label>
								<div class="col-sm-4">
									<input class="form-control" id="mobilephone" type="text" name="mobilephone"
										placeholder="" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="email">邮箱</label>
								<div class="col-sm-4">
									<input class="form-control" id="email" type="text" name="email"
										placeholder="" />
								</div>
								<label class="col-sm-2 control-label" for="address">住址</label>
								<div class="col-sm-4">
									<input class="form-control" id="address" type="text" name="address"
										placeholder="" />
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="submitDialog()">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>



	<div class="modal fade" id="userrolemodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="usertitle">角色分配</h4>
				</div>
				<div class="modal-body">
					<div style="padding: 5px 5px;">
						<form id="assignRoleForm" method="post">
							<div style="width: 560px; height: 36px; position: relative;">
								<div
									style="margin: auto; width: 280px; height: 25px; position: absolute; top: 0; left: 0; bottom: 0; right: 0;">
									<input id="userId" name="userId" type="hidden" /> <input
										class="form-control" id="selectName" name="selectName"
										style="width: 100%;" label='账号名' />
								</div>
							</div>
							<div style="width: 560px; height: 234px; position: relative;">
								<div
									style="margin: auto; width: 500px; height: 229px; position: absolute; top: 0; left: 0; bottom: 0; right: 0;">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="180px" height="220px">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="20px" align="center"
															style="font-weight: bold;">可选角色</td>
													</tr>
													<tr>
														<td height="190px"><select name="from" id="from"
															multiple="multiple" style="width: 100%; height: 100%">
														</select></td>
													</tr>
												</table>
											</td>
											<td align="center">
												<div style="padding-top: 40px">
													<input type="button" id="addAll" value=" >> "
														style="width: 50px;" /><br />
													<br /> <input type="button" id="addOne" value=" > "
														style="width: 50px;" /><br />
													<br /> <input type="button" id="removeOne" value="&lt;"
														style="width: 50px;" /><br />
													<br /> <input type="button" id="removeAll"
														value="&lt;&lt;" style="width: 50px;" /><br />
													<br />
												</div>
											</td>
											<td width="180px" height="220px">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="20px" align="center"
															style="font-weight: bold;">已有角色</td>
													</tr>
													<tr>
														<td height="190px"><select name="to" id="to"
															multiple="multiple" style="width: 100%; height: 100%">
														</select></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div
								style="width: 560px; height: 30px; position: relative; display: table-cell; vertical-align: middle"
								align="center">
								<button id="save" class="btn btn-info" type="button">保存</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


	<!-- ./wrapper -->
	<!--   <div class="diy_table_foot">
    <span>每页</span>
    <div class="btn-group select_pagesize">
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">20</button>
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
        <span class="caret"></span>
        <span class="sr-only"></span>
      </button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">20</a></li>
        <li><a href="#">50</a></li>
        <li><a href="#">100</a></li>
        <li><a href="#">500</a></li>
      </ul>
    </div>
    <span>条,共1999条，当前第1页</span>
    <div class="pull-right pagination" data-toggle="pagination" data-total="1999" data-page-size="20" data-page-current="50"></div>
  </div> -->
</div>


<!-- page script -->
<script>
	$(function() {
		var table = $('#UserTable').DataTable({
			"paging" : true,
			"lengthChange" : false,
			"searching" : false,
			"select": true,
			"ordering" : false,
			"info" : true,
			"autoWidth" : true,
			"keys" : true,
			"responsive" : true,
			"iDisplayLength" : 15,// 每页显示行数 
			"oLanguage" : { // 汉化  
	            "sUrl" : "./resources/adminlte/plugins/datatables/language.json"  
	            },
	        "processing": true,
	        "serverSide": true,
	        "ajax": {
	        	"url":"./user/getAllUserList.do",
	        },
	        "columns": [  
	                     { "data": "username" },  
	                     { "data": "roles",
	                       "render": function(data, type, row, meta) {
	                    	   var roles='';
	                    	   data.forEach(function(element,index,array){
	                   				roles+=element.description+'  ';
	                   			});
	                   			return roles;
		                       }},
	                     { "data": "createdDate",
	                       "render": function(data, type, row, meta) {
		                           return formatDatebox(data);
		                       }},
	                     { "data": "modifiedDate" ,
	                       "render": function(data, type, row, meta) {
		                           return formatDatebox(data);
		                       }}, 
	                     { "data": "status",
	                       "render": function(data, type, row, meta) {
	                           if (type === 'display') {
	                               if (data == 1) {
	                                   return "启用";
	                               } else {
	                                   return "禁用";
	                               }
	                           }
	                           return data;
	                       }}
	              ] ,
		});
		
		 $('#UserTable tbody').on( 'click', 'tr', function () {
		        if ( $(this).hasClass('selected') ) {
		            $(this).removeClass('selected');
		        }
		        else {
		            table.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		        }
		    } );
		 
		 $('#birthday11').datepicker({
		      autoclose: true
		    });

	});
	
	var isAdd = true; 
	
	/* sendToMainMessage('error', '所搜索的内容暂时没有!', ''); */
	
	function addUser(){
		  isAdd = true;
		  $("#id").val("");
		  $("#username").val("");
		  $("#password").val("");
		  $("#passwdConfirm").val("");
		  $("#name").val("");
		  $("#idCardNumber").val("");
		  $("#birthday11").val("");
		  $("#address").val("");
		  $("#email").val("");
		  $("#mobilephone").val("");
		  $("#status").val("");
		  //$("#saveUser").html("保存");
		  //$("#userForm").form("clear");
		  $('#usertitle').html('添加用户信息');
		  $('#usermodal').modal('show');
	}
	
	
	function updateUser(){
		isAdd = false;
		var selections = $('#UserTable').DataTable().rows('.selected').data();
		
		if (selections.length == 0) {
			alertMsg('请选择需要更新的数据',"info");
	         return false;
	       }
		$("#id").val(selections[0].id);
		$("#username").val(selections[0].username);
		$("#password").val();
		$("#passwdConfirm").val();
		$("#name").val(selections[0].name);
	    $("#idCardNumber").val(selections[0].idCardNumber);
		$("#birthday").val(selections[0].birthday);
		$("#address").val(selections[0].address);
		$("#email").val(selections[0].email);
		$("#mobilephone").val(selections[0].mobilephone);
		$("#status").val(selections[0].status);
		$('#usertitle').html('更新用户信息');
		  $('#usermodal').modal('show');
	}
	
	
	function deleteUser() {
		var selections = $('#UserTable').DataTable().rows('.selected').data();
		if (selections.length == 0) {
			alertMsg('请选择你要删除的用户！',"info");
			return false;
		}

		if (confirm("你确认删除该用户吗？")) {
			$("#id").val(selections[0].id);
			var id = $("#id").val();
			var submitData = {
				id : id
			};
			$.post('./user/deleteUser.do', submitData, function(data) {
				if (data) {
					$('#UserTable').DataTable().draw(false);
					alertMsg('用户删除成功！',"success");
				} else {
					alertMsg('删除用户失败！',"warning");
				}
			});
			$("#id").val("");
		}
	}

	function submitDialog() {
		var url = './user/addUser.do';
		if (!isAdd) {
			url = './user/updateUser.do';
		}
		var submitData = $('#userform').serialize();
		$.post(url, submitData, function(data) {
			if (data) {
				if (isAdd) {
					$('#UserTable').DataTable().draw();//如果是添加则滚动到第一页并刷新
					alertMsg('数据添加成功',"success");
				} else {
					$('#UserTable').DataTable().draw(false);
					alertMsg('数据修改成功',"success");
				}
			} else {
				alertMsg('数据操作失败！',"warning");
			}

			$('#usermodal').modal('hide');
			//清空form表单中的数据
		});
	}
	
	function assignRole(){
		$("#to").empty();
		$("#from").empty();
		var selections = $('#UserTable').DataTable().rows('.selected').data();
		if (selections.length == 0) {
			alertMsg('请选择你要分配角色的用户！',"info");
	         return false;
	     }
		$("#userId").val(selections[0].id);
		$("#selectName").val(selections[0].username);
		/* $("#selectName").textbox({readonly:true});  */
		$("#selectName").attr("readonly","readonly")
		var username = $("#selectName").val();
		var sendData = {"username" : username};
		var url = "./user/searchRole.do?timeId="+new Date().getTime();
		$.post(url,sendData,function(data){
			var arr = data;
			$.each(arr.existRoles,function(i,v){
				var opt = $('<option></option>');
				opt.val(v.id);
				opt.html(v.description);
				$("#to").append(opt);
			}); 
			$.each(arr.notExistRoles,function(i,v){
				var opt = $('<option></option>');
				opt.val(v.id);
				opt.html(v.description);
				$("#from").append(opt);
			}); 
		},"json");
		$('#userrolemodal').modal('show');
	}
	
	function getRolesAsString(){
		var rolesStr ="";
		var objectSelect = document.getElementById("to");
		if(null!=objectSelect && typeof(objectSelect)!="undefined"){
			var length = objectSelect.options.length;
			console.log("length="+length);
			for(var i=0;i<length;i++){
				if(0 == i){
					rolesStr = objectSelect.options[i].value;
				}else{
					rolesStr = rolesStr + ","+objectSelect.options[i].value;
				}
			}
		}
		console.log("one ="+rolesStr);
		return rolesStr;
	}
	
	//选择一项  
	$("#addOne").click(function(){  
	    $("#from option:selected").clone().appendTo("#to");  
	    $("#from option:selected").remove();  
	});  
	//选择全部  
	$("#addAll").click(function(){  
	    $("#from option").clone().appendTo("#to");  
	    $("#from option").remove();  
	});  
	  
	//移除一项  
	$("#removeOne").click(function(){  
	    $("#to option:selected").clone().appendTo("#from");  
	    $("#to option:selected").remove();  
	});  
	//移除全部  
	$("#removeAll").click(function(){  
	    $("#to option").clone().appendTo("#from");  
	    $("#to option").remove();  
	});  
	
	$("#save").bind('click', function(){    
        var userId = $("#userId").val();
        var existRoleIds = getRolesAsString();
        var params = {
        		"userId" : userId,
        		"roleIds" : existRoleIds
        };
        var url = "./user/addUserRoles.do?timeId="+new Date().getTime();
        $.post(url,params,function(data){
        	var arr = data;
        	if(arr.result=="success"){
        		$('#UserTable').DataTable().draw(false);
        		alertMsg('角色修改成功',"success");
        	}else{
        		alertMsg('角色修改失败',"warning");
        	} 
        },"json");
        $('#userrolemodal').modal('hide');
    }); 
</script>