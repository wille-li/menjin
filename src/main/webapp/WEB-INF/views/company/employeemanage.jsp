<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.a-upload {
	padding: 4px 10px;
	height: 30px;
	line-height: 20px;
	position: relative;
	cursor: pointer;
	color: #888;
	background: #fafafa;
	border: 1px solid #ddd;
	border-radius: 4px;
	overflow: hidden;
	display: inline-block;
	*display: inline;
	*zoom: 1
}

.a-upload  input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer
}

.a-upload:hover {
	color: #444;
	background: #eee;
	border-color: #ccc;
	text-decoration: none
}
</style>
<div class="diy_table_panel" style="font-size: 12px">
	<div class="diy_table_head">
		<div style="float: left; margin-left: 15px">
			<div class="input-group">
				<button class="btn btn-success" type="button" id="addEmployee"
					onclick="addEmployee()">新增员工</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-info" type="button" id="roledetail"
					onclick="updateEmployee()">修改员工信息</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-danger" type="button" id="delrole"
					onclick="deleteEmployee()">删除员工</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<!-- <button class="btn btn-warning" type="button" id="delrole"
					onclick="#">导入公司数据</button> -->
				<button type="submit" class="btn btn-warning"
					onclick="uploadBatchEmployees()">导入员工数据</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<!-- <button class="btn btn-warning" type="button" id="delrole"
					onclick="#">导入公司数据</button> -->
				<a href="./downloadEmployees.do" class="btn btn-warning"
					>导出员工数据</a>
			</div>
		</div>
		<div style="float: right; margin-left: 10px">

			<form class="form-inline">
				<div class="form-group">
					<select class="form-control" id="checkBycompany"
						name="checkBycompany" onchange="toCheck(0)">
					</select>
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="checkEmployeeInput"
						name="checkEmployeeInput" placeholder="输入员工名称">
				</div>
				<button type="button" class="btn btn-info" onclick="toCheck(1)">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
		</div>
	</div>
	<div class="diy_table_body box">
		<div class="">
			<div class="box-body">
				<table id="employeeTable"
					class="display table table-bordered table-hover">
					<thead>
						<tr>
							<th>员工编号</th>
							<th>员工姓名</th>
							<th>性别</th>
							<th>邮箱</th>
							<th>联系电话</th>
							<th>证件号码</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="modal fade bs-example-modal-sm" id="uploadBatchEmployees">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header" style="padding: 10px 15px 10px 15px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="visitortitle">导入员工数据</h4>
				</div>
				<div class="modal-body">
					<form id="uploadform" name="uploadform" method="post"
						class="form-horizontal" enctype="multipart/form-data"
						action="./uploadBatchEmployees.do" onsubmit="return toUpload();">
						<div class="control-group">
							<div class="controls">
								<!--  <label class="control-label" for="inputPassword">上传数据</label>
								<input type="file" name="uploadfile" id="uploadfile"> -->
								<a href="javascript:;" class="a-upload"> <input type="file"
									name="uploadfile" id="uploadfile">点击这里上传文件
								</a>

							</div>
						</div>
						<div class="control-group" style="margin-top: 10px;">
							<div class="controls">
								<a href="./resources/excel/test.xlsx">点击此处下载模板</a>
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="submituploadForm()">上传</button>
				</div>
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
					<form class="form-horizontal" id="companyform">
						<input class="easyui-textbox" type="hidden" id="id" name="id" /> <input
							class="easyui-textbox" type="hidden" id="companyId"
							name="companyId" />
						<fieldset>

							<div style="margin-bottom: 10px; width: 400px;">
								<div class="input-group">
									<span class="input-group-addon">所属公司</span> <select
										class="form-control" id="companyBox" name="companyBox">
									</select>
								</div>
							</div>

							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">员工编号</span> <input
										class="form-control" id="employeeNo" name="employeeNo" />
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">员工姓名</span> <input
										class="form-control" id="employeeName" name="employeeName" />
								</div>
							</div>

							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">性别</span> <input
										class="form-control" id="employeeSex" name="employeeSex" />
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">证件号码</span> <input
										class="form-control" id="IdCardNum" name="IdCardNum" />
								</div>
							</div>

							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">联系电话</span> <input
										class="form-control" id="mobile" name="mobile" />
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">邮箱</span> <input
										class="form-control" id="email" name="email" />
								</div>
							</div>

						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="submitDialog()">确认</button>
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
		var floatString = "";
		var floatData = "[";
		for (var i = 1; i < 101; i++) {
			var item = '{"value":' + i + ',"text":"' + i + '楼"},';
			if (i == 100) {
				item = '{"value":' + i + ',"text":"' + i + '楼"}';
			}
			floatData += item;
		}
		floatData += "]";
		floatData = $.parseJSON(floatData);
		$.each(floatData, function(index, item) {
			floatString += "<option value='"+item.value+"'>" + item.text
					+ "</option>";
		});
		$("#companyAddress").html(floatString);
		var table = $('#employeeTable')
				.DataTable(
						{
							"paging" : true,
							"lengthChange" : false,
							"searching" : false,
							"select" : true,
							"ordering" : false,
							"info" : true,
							"autoWidth" : true,
							"keys" : true,
							"responsive" : true,
							"iDisplayLength" : 15,// 每页显示行数 
							"oLanguage" : { // 汉化  
								"sUrl" : "./resources/adminlte/plugins/datatables/language.json"
							},
							"processing" : true,
							"serverSide" : true,
							"ajax" : {
								"url" : "./employeelistBydepartmentId.do",
								"data" : function(d) {
									//添加额外的参数传给服务器
									d.companyId = $('#checkBycompany').val();
									d.employeeName = $('#checkEmployeeInput')
											.val();
								},
								"type" : "Post"
							},
							"columns" : [ {
								"data" : "employeeNo"
							}, {
								"data" : "employeeName"
							}, {
								"data" : "employeeSex"
							}, {
								"data" : "email"
							}, {
								"data" : "mobile"
							}, {
								"data" : "idCardNum"
							} ]
						});

		$('#employeeTable tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#birth').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});

	});

	var companyData = getJSONData({
		url : "./getcompanylistByTree.do"
	});
	function Fillcombox() {
		var companyString = "<option value=''>请选择公司</option>";

		$.each(companyData, function(index, item) {
			companyString += "<option value='"+item.id+"' index='"+index+"'>"
					+ item.companyName + "</option>";
		});
		$("#checkBycompany").html(companyString);
		$("#companyBox").html(companyString);
	}

	Fillcombox();

	var isAdd = true;

	/* sendToMainMessage('error', '所搜索的内容暂时没有!', ''); */

	function addEmployee() {
		isAdd = true;
		$("#id").val("");
		$("#companyId").val("");
		$("#employeeNo").val("");
		$("#employeeName").val("");
		$("#employeeSex").val("");
		$("#email").val("");
		$("#mobile").val("");
		$("#IdCardNum").val("");
		$('#visitortitle').html('添加员工信息');
		$('#visitormodal').modal('show');
	}

	function updateEmployee() {
		isAdd = false;
		var selections = $('#employeeTable').DataTable().rows('.selected')
				.data();

		if (selections.length == 0) {
			alertMsg('请选择需要更新的数据', "info");
			return false;
		}
		$("#id").val(selections[0].id);
		$("#companyBox").val(selections[0].company.id);
		$("#employeeNo").val(selections[0].employeeNo);
		$("#employeeName").val(selections[0].employeeName);
		$("#employeeSex").val(selections[0].employeeSex);
		$("#email").val(selections[0].email);
		$("#mobile").val(selections[0].mobile);
		$("#IdCardNum").val(selections[0].idCardNum);
		$('#visitortitle').html('修改员工信息');
		$('#visitormodal').modal('show');
	}

	function deleteEmployee() {
		var selections = $('#employeeTable').DataTable().rows('.selected')
				.data();
		if (selections.length == 0) {
			alertMsg('请选择你要删除的员工！', "info");
			return false;
		}

		if (confirm("你确认删除该员工吗？")) {
			$("#id").val(selections[0].id);
			var id = $("#id").val();
			var submitData = {
				id : id
			};
			$.post('./deleteemployee.do', submitData, function(data) {
				if (data) {
					if (data.rInfo.ret == 0) {
						$('#employeeTable').DataTable().draw(false);
						alertMsg(data.rInfo.msg, "success");
					} else {
						alertMsg(data.rInfo.msg, "warning");
					}
				} else {
					alertMsg('网络连接失败，请与管理员联系！', "danger");
				}
			});
			$("#id").val("");
		}
	}

	function submitDialog() {
		/* if (!validation()) {
			return;
		} */
		var url = './addemployeement.do';
		$("#companyId").val($("#companyBox").val());
		if (!isAdd) {
			url = './updateemployee.do';
		}
		var submitData = $('#companyform').serialize();
		$.post(url, submitData, function(data) {
			if (data) {
				if (data.rInfo.ret == 0) {
					if (isAdd) {
						$('#employeeTable').DataTable().draw();//如果是添加则滚动到第一页并刷新
					} else {
						$('#employeeTable').DataTable().draw(false);
					}
					alertMsg(data.rInfo.msg, "success");
				} else {
					alertMsg(data.rInfo.msg, "warning");
				}
			} else {
				alertMsg('网络连接失败，请与管理员联系！', "danger");
			}
			$('#visitormodal').modal('hide');
			//清空form表单中的数据
		});
	}

	function toCheck(type) {
		/* $('#employeeTable').DataTable().ajax.url('./visitorlist.do?status='+$('#selectstatus').val()+'&visitorName='+visitorName).load(); */
		if (type == 0) {
			$('#checkEmployeeInput').val("");
		}
		$('#employeeTable').DataTable().ajax.reload();
		$('#checkEmployeeInput').val("");
	}

	function validation() {
		var phone = $("#companyPhone").val();
		if (!/(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})$/.test(phone)) {
			alertMsg('输入的公司联系电话格式有误，请重新输入!', "info");
			return false;
		}
		return true;
	}

	function uploadBatchEmployees() {
		$('#uploadBatchEmployees').modal('show');
	}

	function toUpload() {
		$("#uploadform").ajaxSubmit(function(data) {
			if (data.rInfo.ret == 0) {
				alertMsg(data.rInfo.msg, "success");
			}else{
				alertMsg(data.rInfo.msg, "warning");
			}
			$('#uploadBatchEmployees').modal('hide');
			$('#employeeTable').DataTable().ajax.reload();
		});
		return false;
	}

	function submituploadForm() {
		$("#uploadform").submit();
	}
</script>