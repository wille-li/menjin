<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="diy_table_panel" style="font-size:12px">
	<div class="diy_table_head">
		<div style="float: left; margin-left: 15px">
			<div class="input-group">
				<button class="btn btn-success" type="button" id="addMatter"
					onclick="addCompany()">新增闸机</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-info" type="button" id="roledetail"
					onclick="updateCompany()">修改闸机信息</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-danger" type="button" id="delrole"
					onclick="deleteCompany()">删除闸机</button>
			</div>
		</div>
		<div style="float: right; margin-left: 10px">

			<form class="form-inline">
				<div class="form-group">
					<label for="checkvisitor">搜索</label> <input type="text"
						class="form-control" id="checkOpenerInput" name="checkOpenerInput"
						placeholder="输入闸机名称" >
				</div>
				<button type="button" class="btn btn-info" onclick="checkByCompanyName()"><span class="glyphicon glyphicon-search" ></span></button>
			</form>
		</div>
	</div>
	<div class="diy_table_body box">
		<div class="">
			<div class="box-body">
				<table id="openerTable" class="display table table-bordered table-hover">
					<thead>
						<tr>
							<th>闸机名称</th>
							<th>闸机IP</th>
							<th>闸机链接端口</th>
							<th>闸机开关号</th>
							<th>记录创建人</th>
							<th>记录创建时间</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="modal fade" id="visitormodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="visitortitle"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="openerform">
					    <input class="easyui-textbox" type="hidden" id="id" name="id"/>
						<fieldset>
						
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">闸机名称</span>
									<input class="form-control" id="name"
									name="name" />
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">闸机IP</span>
									<input class="form-control" id="ip" name="ip" />
								</div>
							</div>
							
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">闸机链接端口</span>
									<input class="form-control" id="port"
									name="port"/>
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">闸机开关号</span>
									<input class="form-control" id="num"
									name="num"/>
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





<!-- page script -->
<script>
	$(function() {
		var table = $('#openerTable').DataTable({
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
	        	"url":"./openerlist.do",
	        	"data": function ( d ) {
	                   //添加额外的参数传给服务器
	                   d.searchName = $('#checkOpenerInput').val();
	        	},
	        	"type":"Post"
	        },
	        "columns": [  
	                     { "data": "name" },  
	                     { "data": "ip"},
	                     { "data": "port"},
	                     { "data": "num"},
	                     { "data": "createBy"},
	                     { "data": "createTime",
		                   "render": function(data, type, row, meta) {
				               return formatYMDatebox(data);
				         }}
	              ] 
		});
		
		 $('#openerTable tbody').on( 'click', 'tr', function () {
		        if ( $(this).hasClass('selected') ) {
		            $(this).removeClass('selected');
		        }
		        else {
		            table.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		        }
		    } );
		 
		 $('#birth').datepicker({
			 format: 'yyyy-mm-dd',
		      autoclose: true
		    });

	});
	
	var isAdd = true; 
	
	/* sendToMainMessage('error', '所搜索的内容暂时没有!', ''); */
	
	function addCompany(){
		  isAdd = true;
		  $("#id").val("");
		  $("#name").val("");
		  $("#ip").val("");
		  $("#port").val("");
		  $("#num").val("");
		  $('#visitortitle').html("添加闸机信息");
		  $('#visitormodal').modal("show");
	}
	

	function updateCompany() {
		isAdd = false;
		var selections = $('#openerTable').DataTable().rows('.selected')
				.data();

		if (selections.length == 0) {
			alertMsg('请选择需要更新的数据',"info");
			return false;
		}
		$("#id").val(selections[0].id);
		$("#name").val(selections[0].name);
		$("#port").val(selections[0].port);
		$("#num").val(selections[0].num);
		$("#ip").val(selections[0].ip);
		$('#visitortitle').html('修改公司信息');
		$('#visitormodal').modal('show');
	}

	function deleteCompany() {
		var selections = $('#openerTable').DataTable().rows('.selected')
				.data();
		if (selections.length == 0) {
			alertMsg('请选择你要删除的公司！',"info");
			return false;
		}

		if (confirm("你确认删除该公司吗？")) {
			$("#id").val(selections[0].id);
			var id = $("#id").val();
			var submitData = {
				id : id
			};
			$.post('./deleteOpener.do', submitData, function(data) {
				if (data) {
					if (data.rInfo.ret == 0) {
						$('#openerTable').DataTable().draw(false);
						alertMsg(data.rInfo.msg,"success");
					} else {
						alertMsg(data.rInfo.msg,"warning");
					}
				} else {
					alertMsg('网络连接失败，请与管理员联系！',"danger");
				}
			});
			$("#id").val("");
		}
	}

	function submitDialog() {
		
		var url = './addOpener.do';
		if (!isAdd) {
			url = './updateOpener.do';
		}
		var submitData = $('#openerform').serialize();
		$.post(url, submitData, function(data) {
			if (data) {
				if (data.rInfo.ret == 0) {
					if(isAdd){
						$('#openerTable').DataTable().draw();//如果是添加则滚动到第一页并刷新
					}else{
						$('#openerTable').DataTable().draw(false);
					}
					alertMsg(data.rInfo.msg,"success");
				} else {
					alertMsg(data.rInfo.msg,"warning");
				}
			} else {
				alertMsg('网络连接失败，请与管理员联系！',"danger");
			}
			$('#visitormodal').modal('hide');
			//清空form表单中的数据
		});
	}
	
	
	
	function checkByCompanyName(){
		$('#openerTable').DataTable().ajax.reload();
		$('#checkOpenerInput').val("");
	}
	
	function validation() {
		var phone = $("#companyPhone").val();
		if (!/(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})$/.test(phone)) {
			alertMsg('输入的公司联系电话格式有误，请重新输入!',"info");
			return false;
		}
		return true;
	}

	
</script>