<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="diy_table_panel" style="font-size:12px">
	<div class="diy_table_head">
		<div style="float: left; margin-left: 15px">
			<div class="input-group">
				<button class="btn btn-success" type="button" id="addMatter"
					onclick="addCompany()">新增公司</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-info" type="button" id="roledetail"
					onclick="updateCompany()">修改公司信息</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-danger" type="button" id="delrole"
					onclick="deleteCompany()">删除公司</button>
			</div>
		</div>
		<div style="float: right; margin-left: 10px">

			<form class="form-inline">
				<div class="form-group">
					<label for="checkvisitor">搜索</label> <input type="text"
						class="form-control" id="checkCompanyInput" name="checkCompanyInput"
						placeholder="输入公司名称" >
				</div>
				<button type="button" class="btn btn-info" onclick="checkByCompanyName()"><span class="glyphicon glyphicon-search" ></span></button>
			</form>
		</div>
	</div>
	<div class="diy_table_body box">
		<div class="">
			<div class="box-body">
				<table id="companyTable" class="display table table-bordered table-hover">
					<thead>
						<tr>
							<th>公司名称</th>
							<th>公司楼层</th>
							<th>公司门号</th>
							<th>公司联系电话</th>
							<th>记录创建人</th>
							<th>记录创建时间</th>
							<th>记录修改时间</th>
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
					<form class="form-horizontal" id="companyform">
					    <input class="easyui-textbox" type="hidden" id="id" name="id"/>
						<fieldset>
						
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">公司名称</span>
									<input class="form-control" id="companyName"
									name="companyName" />
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">公司楼层</span>
									<select class="form-control" id="companyAddress" name="companyAddress">
                                    </select>
								</div>
							</div>
							
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">公司门号</span>
									<input class="form-control" id="doorPlate"
									name="doorPlate"/>
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">公司联系电话</span>
									<input class="form-control" id="companyPhone"
									name="companyPhone"/>
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
	    $.each(floatData,function(index,item){
	    	floatString += "<option value='"+item.value+"'>"+item.text+"</option>";
	    });
	    $("#companyAddress").html(floatString);
		var table = $('#companyTable').DataTable({
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
	        	"url":"./companylist.do",
	        	"data": function ( d ) {
	                   //添加额外的参数传给服务器
	                   d.companyName = $('#checkCompanyInput').val();
	        	},
	        	"type":"Post"
	        },
	        "columns": [  
	                     { "data": "companyName" },  
	                     { "data": "companyAddress"},
	                     { "data": "doorPlate"},
	                     { "data": "companyPhone"},
	                     { "data": "createBy"},
	                     { "data": "createTime",
		                   "render": function(data, type, row, meta) {
				               return formatYMDatebox(data);
				         }},
				         { "data": "modifiedDate",
			                   "render": function(data, type, row, meta) {
					               return formatYMDatebox(data);
					         }},
				         
	              ] 
		});
		
		 $('#companyTable tbody').on( 'click', 'tr', function () {
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
		  $("#companyName").val("");
		  $("#companyAddress").val("");
		  $("#doorPlate").val("");
		  $("#companyPhone").val("");
		  $('#visitortitle').html('添加公司信息');
		  $('#visitormodal').modal('show');
	}
	

	function updateCompany() {
		isAdd = false;
		var selections = $('#companyTable').DataTable().rows('.selected')
				.data();

		if (selections.length == 0) {
			alertMsg('请选择需要更新的数据',"info");
			return false;
		}
		$("#id").val(selections[0].id);
		$("#companyName").val(selections[0].companyName);
		$("#companyAddress").val(selections[0].companyAddress);
		$("#doorPlate").val(selections[0].doorPlate);
		$("#companyPhone").val(selections[0].companyPhone);
		$('#visitortitle').html('修改公司信息');
		$('#visitormodal').modal('show');
	}

	function deleteCompany() {
		var selections = $('#companyTable').DataTable().rows('.selected')
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
			$.post('./deleteCompany.do', submitData, function(data) {
				if (data) {
					if (data.rInfo.ret == 0) {
						$('#companyTable').DataTable().draw(false);
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
		if (!validation()) {
			return;
		}
		var url = './addCompany.do';
		if (!isAdd) {
			url = './updateCompany.do';
		}
		var submitData = $('#companyform').serialize();
		$.post(url, submitData, function(data) {
			if (data) {
				if (data.rInfo.ret == 0) {
					if(isAdd){
						$('#companyTable').DataTable().draw();//如果是添加则滚动到第一页并刷新
					}else{
						$('#companyTable').DataTable().draw(false);
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
		/* $('#companyTable').DataTable().ajax.url('./visitorlist.do?status='+$('#selectstatus').val()+'&visitorName='+visitorName).load(); */
		$('#companyTable').DataTable().ajax.reload();
		$('#checkCompanyInput').val("");
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