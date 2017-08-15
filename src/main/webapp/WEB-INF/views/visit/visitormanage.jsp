<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="diy_table_panel" style="font-size:12px">
	<div class="diy_table_head">
		<div style="float: left; margin-left: 15px">
			<div class="input-group">
				<button class="btn btn-success" type="button" id="addMatter"
					onclick="addVisitor()">新增访客</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-info" type="button" id="roledetail"
					onclick="updateVisitor()">修改访客</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-danger" type="button" id="delrole"
					onclick="deleteVisitor()">删除访客</button>
			</div>
		</div>
		<div style="float: right; margin-left: 10px">

			<form class="form-inline">
				<div class="form-group">
					<label for="exampleInputName2">访客类型</label> <select
						class="form-control" id="selectstatus" name="selectstatus" onchange="rankChange()">
						<option value="">所有访客</option>
						<option value="1">普通访客</option>
						<option value="2">黑名单</option>
						<option value="3">白名单</option>
					</select>
				</div>
				<div class="form-group">
					<label for="checkvisitor">搜索</label> <input type="text"
						class="form-control" id="selectvisitorName" name="selectvisitorName"
						placeholder="" >
				</div>
				<button type="button" class="btn btn-info" onclick="checkByall()"><span class="glyphicon glyphicon-search" ></span></button>
			</form>
		</div>
	</div>
	<div class="diy_table_body box">
		<div class="">
			<div class="box-body">
				<table id="visitorTable" class="display table table-bordered table-hover">
					<thead>
						<tr>
							<th width="60px">访客姓名</th>
							<th width="60px">证件类型</th>
							<th>证件号</th>
							<th width="40px">性别</th>
							<th>访客单位</th>
							<th>地址</th>
							<th width="60px">联系电话</th>
							<th width="60px">访客等级</th>
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
					<form class="form-horizontal" id="visitorform">
					    <input class="easyui-textbox" type="hidden" id="id" name="id"/>
						<fieldset>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="visitorName">访客姓名</label>
								<div class="col-sm-4">
									<input class="form-control" id="visitorName" type="text" name = "visitorName"
										placeholder="" />
								</div>
								<label class="col-sm-2 control-label" for="idCardType">证件类型</label>
								<div class="col-sm-4">
									 <select class="form-control" id="idCardType" name="idCardType">
										<option value="身份证">身份证</option>
										<option value="护照">护照</option>
									 </select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label" for="idCardNum">证件号码</label>
								<div class="col-sm-4">
									<input class="form-control" id="idCardNum" type="text" name = "idCardNum"
										placeholder="" />
								</div>
								<label class="col-sm-2 control-label" for="sex">性别</label>
								<div class="col-sm-4">
									<select class="form-control" id="sex" name="sex">
										<option value="男">男</option>
										<option value="女">女</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label" for="nation">访客单位</label>
								<div class="col-sm-4">
									<input class="form-control" id="nation" type="text" name = "nation"
										placeholder="" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label" for="address">地址</label>
								<div class="col-sm-4">
									<input class="form-control" id="address" type="text" name = "address"
										placeholder="" />
								</div>
								<label class="col-sm-2 control-label" for="mobile">联系电话</label>
								<div class="col-sm-4">
									<input class="form-control" id="mobile" type="text" name = "mobile"
										placeholder="" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label" for="rank">访客等级</label>
								<div class="col-sm-4">
									<select class="form-control" id="rank" name="rank">
										<option value="1">普通访客</option>
										<option value="2">黑名单</option>
										<option value="3">白名单</option>
									</select>
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
		var table = $('#visitorTable').DataTable({
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
	        	"url":"./visitorlist.do",
	        	"data": function ( d ) {
	                   //添加额外的参数传给服务器
	                   d.status = $('#selectstatus').val();
	                   d.visitorName = $('#selectvisitorName').val();
	        	},
	        	"type":"Post"
	        },
	        "columns": [  
	                     { "data": "visitorName" },  
	                     { "data": "idCardType"},
	                     { "data": "idCardNum"},
	                     { "data": "sex"},
	                     { "data": "nation"},
	                     { "data": "address"},
	                     { "data": "mobile"},
	                     { "data": "rank",
		                   "render": function(data, type, row, meta) {
		                        if (type === 'display') {
		                            if (data == 1) {
		                               return "普通访客";
		                             } else if(data == 2){
		                                 return "黑名单";
		                           }else{
		                        	   return "白名单";
		                           }
		                         }
		                     return data;
		                 }},
	              ] 
		});
		
		 $('#visitorTable tbody').on( 'click', 'tr', function () {
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
	
	function addVisitor(){
		  isAdd = true;
		  $("#id").val("");
		  $("#visitorName").val("");
		  $("#idCardType").val("");
		  $("#idCardNum").val("");
		  $("#sex").val("");
		  $("#nation").val("");
		  $("#address").val("");
		  $("#mobile").val("");
		  $("#rank").val("");
		  $('#visitortitle').html('添加访客');
		  $('#visitormodal').modal('show');
	}
	

	function updateVisitor() {
		isAdd = false;
		var selections = $('#visitorTable').DataTable().rows('.selected')
				.data();

		if (selections.length == 0) {
			alertMsg('请选择需要更新的数据',"info");
			return false;
		}
		$("#id").val(selections[0].id);
		$("#visitorName").val(selections[0].visitorName);
		$("#idCardType").val(selections[0].idCardType);
		$("#idCardNum").val(selections[0].idCardNum);
		$("#sex").val(selections[0].sex);
		$("#nation").val(selections[0].nation);
		$("#address").val(selections[0].address);
		$("#mobile").val(selections[0].mobile);
		$("#rank").val(selections[0].rank);
		$('#visitortitle').html('修改访客信息');
		$('#visitormodal').modal('show');
	}

	function deleteVisitor() {
		var selections = $('#visitorTable').DataTable().rows('.selected')
				.data();
		if (selections.length == 0) {
			alertMsg('请选择你要删除的访客！',"info");
			return false;
		}

		if (confirm("你确认删除该用户吗？")) {
			$("#id").val(selections[0].id);
			var id = $("#id").val();
			var submitData = {
				id : id
			};
			$.post('./deleteVisitor.do', submitData, function(data) {
				if (data) {
					if (data.rInfo.ret == 0) {
						$('#visitorTable').DataTable().draw(false);
						alertMsg('访客删除成功！',"success");
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
		if(!validation()){
			return ;
		}
		
		var url = './addVisitor.do';
		if (!isAdd) {
			url = './updateVisitor.do';
		}
		var submitData = $('#visitorform').serialize();
		$.post(url, submitData, function(data) {
			if (data) {
				if (data.rInfo.ret == 0) {
					if(isAdd){
						$('#visitorTable').DataTable().draw();//如果是添加则滚动到第一页并刷新
					}else{
						$('#visitorTable').DataTable().draw(false);
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
	
	function rankChange(){
		/* $('#visitorTable').DataTable().ajax.url('./visitorlist.do?status='+$('#selectstatus').val()).load(); */
		$('#visitorTable').DataTable().ajax.reload();
    }
	
	
	function checkByall(){
		/* $('#visitorTable').DataTable().ajax.url('./visitorlist.do?status='+$('#selectstatus').val()+'&visitorName='+visitorName).load(); */
		$('#visitorTable').DataTable().ajax.reload();
		$('#selectvisitorName').val("");
	}
	
	function validation(){
		var phone = $("#mobile").val();
		if(!/(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})$/.test(phone)){
			alertMsg('输入的联系电话格式有误，请重新输入!',"info");
			return false;
		}
		var email = $("#email").val();
		if(email != null){
			if(!/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email)){
				alertMsg('提醒','邮箱格式有误，请重新输入!',"info");
				return false;
			}
		}
		
		return true;
	}
	
</script>