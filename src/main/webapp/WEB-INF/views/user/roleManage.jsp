<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="diy_table_panel" style="font-size:12px">
	<div class="diy_table_head">
		<form action="/include/tables/diytable.html" data-toggle="ajaxsearch"
			class="row">
			<div style="float:left;margin-left: 15px">
				<div class="input-group">
					<button class="btn btn-success" type="button" id="addrole" onclick="addRole()">新增角色</button>
				</div>
			</div>
			<div style="float:left;margin-left: 10px">
				<div class="input-group">
					<button class="btn btn-info" type="button" id="roledetail" onclick="updateRole()">角色详情</button>
				</div>
			</div>
			<div style="float:left;margin-left: 10px">
				<div class="input-group">
					<button class="btn btn-danger" type="button" id="delrole" onclick="deleteRole()">删除角色</button>
				</div>
			</div>
			<!-- <div style="float:left;margin-left: 10px">
				<div class="input-group">
					<button class="btn btn-warning" type="button" id="rolesource" onclick="assignSource()">分配权限</button>
				</div>
			</div> -->
		</form>
	</div>
	<div class="diy_table_body box">
		<div class="">
			<div class="box-body">
				<table id="RoleTable" class="display table table-bordered table-hover">
					<thead>
						<tr>
							<th>角色名称</th>
							<th>角色描叙</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="modal fade" id="rolemodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="roletitle">新增角色</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="roleform">
					    <input class="easyui-textbox" type="hidden" id="id" name="id"/>
						<fieldset>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="rolename">角色名称</label>
								<div class="col-sm-4">
									<input class="form-control" id="name" type="text" name = "name"
										placeholder="" />
								</div>
								<label class="col-sm-2 control-label" for="description">角色描叙</label>
								<div class="col-sm-4">
									<input class="form-control" id="description" type="text" name="description"
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
		var table = $('#RoleTable').DataTable({
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
	        	"url":"./role/getAllRoleList.do",
	        },
	        "columns": [  
	                     { "data": "name" },  
	                     { "data": "description"}
	              ] ,
		});
		
		 $('#RoleTable tbody').on( 'click', 'tr', function () {
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
	
	function addRole(){
		  isAdd = true;
		  $("#id").val("");
		  $("#name").val("");
		  $("#description").val("");
		  $('#roletitle').html('添加新角色');
		  $('#rolemodal').modal('show');
	}
	
	
	function updateRole(){
		isAdd = false;
		var selections = $('#RoleTable').DataTable().rows('.selected').data();
		
		if (selections.length == 0) {
			alertMsg('请选择需要更新的数据',"info");
	         return false;
	       }
		$("#id").val(selections[0].id);
		$("#name").val(selections[0].name);
		$("#description").val(selections[0].description);
		$('#roletitle').html('更新角色信息');
		$('#rolemodal').modal('show');
	}
	
	
	function deleteRole() {
		var selections = $('#RoleTable').DataTable().rows('.selected').data();
		if (selections.length == 0) {
			alertMsg('请选择你要删除的角色！',"info");
			return false;
		}

		if (confirm("你确认删除该角色吗？")) {
			$("#id").val(selections[0].id);
			var id = $("#id").val();
			var submitData = {
				id : id
			};
			$.post('./role/deleteRole.do', submitData, function(data) {
				if (data) {
					$('#RoleTable').DataTable().draw(false);
					alertMsg('角色删除成功！',"success");
				} else {
					alertMsg('删除角色失败！',"warning");
				}
			});
			$("#id").val("");
		}
	}

	function submitDialog() {
		var url = './role/addRole.do';
	    if(!isAdd){
	   	 url = './role/updateRole.do';
	    }
		var submitData = $('#roleform').serialize();
		$.post(url, submitData, function(data) {
			if (data) {
				if (isAdd) {
					$('#RoleTable').DataTable().draw();//如果是添加则滚动到第一页并刷新
					alertMsg('数据添加成功!',"success");
				} else {
					$('#RoleTable').DataTable().draw(false);
					alertMsg('数据修改成功',"success");
				}
			} else {
				alertMsg('数据操作失败！',"danger");
			}

			$('#rolemodal').modal('hide');
			//清空form表单中的数据
		});
	}
	
</script>