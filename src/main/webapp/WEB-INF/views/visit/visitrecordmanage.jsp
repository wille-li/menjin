<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="diy_table_panel" style="font-size: 12px">
    <div class="diy_table_head">
      <div style="float: left; margin-left: 10px">
			<form class="form-inline">
				<div class="input-group">
					<span class="input-group-addon">拜访单号</span>
					<input class="form-control" id="checktxnNo" name="checktxnNo"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">证件号码</span>
					<input class="form-control" id="checkIdCard" name="checkIdCard"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">访客姓名</span>
					<input class="form-control" id="checkvisitorName" name="checkvisitorName"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">拜访状况</span> 
					<select class="form-control" id="vstate" name="vstate">
						<option value="">全部</option>
						<option value="1">未拜访</option>
						<option value="2">未离开</option>
						<option value="3">已离开</option>
					</select>
				</div>
				<!-- <button type="button" class="btn btn-info" onclick="checkByall()"><span class="glyphicon glyphicon-search" ></span></button> -->
			</form>
			<form class="form-inline" style="margin-top:10px">
			    <div class="input-group">
					<span class="input-group-addon">被访员工</span>
					<input class="form-control" id="checkemployee" name="checkemployee"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">被访公司</span>
					<input class="form-control" id="checkcompany" name="checkcompany"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">验证方式</span> 
					<select class="form-control" id="checkvalidate"
						name="checkvalidate">
						<option value="">全部</option>
						<option value="1">自动验证</option>
						<option value="2">手动验证</option>
					</select>
				</div>
				<div class="input-group">
					<button type="button" class="btn btn-info" onclick="checkByselect(0)"><span class="glyphicon glyphicon-search" ></span>&nbsp;不带时间查询</button>
				</div>
				<!-- <button type="button" class="btn btn-info" onclick="checkByall()"><span class="glyphicon glyphicon-search" ></span></button> -->
			</form>
			<form class="form-inline" style="margin-top:10px">
				<div class="input-group">
					<span class="input-group-addon">开始时间</span>
					<div class="input-group date">
						<div class="input-group-addon">
							<i class="fa fa-calendar"></i>
						</div>
						<input type="text" class="form-control pull-right" id="startDate"
							name="startDate" readonly="readonly">
						<input type="hidden" id="startDateHidden" name="startDateHidden"/>
					</div>
				</div>
				<div class="input-group">
					<span class="input-group-addon">结束时间</span>
					<div class="input-group date">
						<div class="input-group-addon">
							<i class="fa fa-calendar"></i>
						</div>
						<input type="text" class="form-control pull-right" id="endDate"
							name="endDate" readonly="readonly">
						<input type="hidden" id="endDateHidden" name="endDateHidden"/>
					</div>
				</div>
				<div class="input-group">
					<button type="button" class="btn btn-info" onclick="checkByselect(1)"><span class="glyphicon glyphicon-search" ></span>&nbsp;带时间查询</button>
				</div>
				<!-- <button type="button" class="btn btn-info" onclick="checkByall()"><span class="glyphicon glyphicon-search" ></span></button> -->
			</form>
		</div>
    </div>
	<div class="diy_table_head">
		<div style="float: left; margin-left: 15px">
			<div class="input-group">
				<button class="btn btn-success" type="button" id="addMatter"
					onclick="addVisitor()">新增拜访记录</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-info" type="button" id="roledetail"
					onclick="updateVisitor()">修改拜访记录</button>
			</div>
		</div>
		<div style="float: left; margin-left: 10px">
			<div class="input-group">
				<button class="btn btn-danger" type="button" id="delrole"
					onclick="deleteVisitor()">删除拜访记录</button>
			</div>
		</div>
	</div>
	<div class="diy_table_body box">
		<div class="">
			<div class="box-body">
				<table id="visitorRecordTable" class="display table table-bordered table-hover">
					<thead>
						<tr>
							<th width="60px">拜访序号</th>
							<th width="60px">访客姓名</th>
							<th>被访公司</th>
							<th width="80px">被访人姓名</th>
							<th>被访人电话</th>
							<th width="60px">随行人数</th>
							<th width="80px">拜访缘由</th>
							<th width="80px">实际拜访时间</th>
							<th>离开时间</th>
							<th width="60px">检验方式</th>
							<th width="80px">拜访单状态</th>
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
				<div class="modal-body" style="height: 500px">
						<input class="easyui-textbox" type="hidden" id="id" name="id" />
						<div class="vsmesleft">
							<div class="vsinput">
								<div class="input-group">
									<span class="input-group-addon">拜访单号</span> <input
									class="form-control" id="matterTxnNum"
									name="matterTxnNum" readonly="readonly"/>
								</div>
							</div>
							<div class="vsinput">
								<div class="input-group">
									<span class="input-group-addon">访客姓名</span>
									<select class="form-control" id="visitorName" name="visitorName" onchange="showVisitorMessage()">
                                    </select>
								</div>
							</div>
							<div class="vsinput">
								<div class="input-group">
									<span class="input-group-addon">性别</span>
									<input class="form-control" id="sex"
									name="sex" readonly="readonly"/>
								</div>
							</div>
							<div class="vsinput">
								<div class="input-group">
									<span class="input-group-addon">出身日月</span>
									<input class="form-control" id="birth"
									name="birth" readonly="readonly"/>
								</div>
							</div>
						</div>
						<div class="vsmesright">
							<img src="./resources/images/image.jpg" alt="" width="100%"
								height="160px" />
						</div>
						<div class="record">
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">证件类型</span>
									<input class="form-control" id="idCardType"
									name="idCardType" readonly="readonly"/>
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">证件号</span>
									<input class="form-control" id="idCardNum"
									name="idCardNum" readonly="readonly"/>
								</div>
							</div>
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">联系电话</span>
									<input class="form-control" id="mobile"
									name="mobile" readonly="readonly"/>
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">拜访公司</span>
									<select class="form-control" id="companyBox" name="companyBox">
                                    </select>
								</div>
							</div>
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">被访者</span>
									<input class="form-control" id="employeeName"
									name="employeeName" />
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">被访者电话</span>
									<input class="form-control" id="employeePhone"
									name="employeePhone" />
								</div>
							</div>
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">拜访缘由</span>
									<select class="form-control" id="matterBox" name="matterBox">
                                    </select>
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">随行人数</span>
									<input class="form-control" id="peopleSum"
									name="peopleSum" />
								</div>
							</div>
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">拜访时间</span>
									<div class="input-group date">
                                        <input type="text" readonly="readonly" class="form-control" id=visitTime name="visitTime">
                                    </div>
								</div>
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">离开时间</span>
									<div class="input-group date">
                                        <input type="text" readonly="readonly" class="form-control" id=leaveTime name="leaveTime">
                                    </div>
								</div>
							</div>
							<div class="recordinputleft">
								<div class="input-group">
									<span class="input-group-addon">检验方式</span>
									<select class="form-control" id="validateMode" name="validateMode">
									    <option value="1">自动检验</option>
									    <option value="2">手动检验</option>
                                    </select>
								</div>
								<!-- <input class="easyui-textbox" id="validateMode" name="validateMode" style="width:100%" label='检验方式'/> -->
							</div>
							<div class="recordinputright">
								<div class="input-group">
									<span class="input-group-addon">拜访单状态</span>
									<select class="form-control" id="status" name="status">
									    <option value="1">未拜访</option>
									    <option value="2">未离开</option>
									    <option value="3">已离开</option>
                                    </select>
								</div>
								<!-- <input class="easyui-textbox" id="status" name="status" style="width:100%" label='拜访单状态'/> -->
							</div>
						</div>
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
		var myDate = new Date();
		var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var month =  myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var date = myDate.getDate();        //获取当前日(1-31)
		var startDate = year +'-'+ month + '-' + date;
		 
		$("#startDate").val(startDate);
		$("#endDate").val(startDate);
		$("#startDateHidden").val(startDate);
		$("#endDateHidden").val(startDate);
		var table = $('#visitorRecordTable').DataTable({
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
	        	"url":"./visitlist.do",
	        	"data": function ( d ) {
	                   //添加额外的参数传给服务器
	                   d.matterTxnNum = $('#checktxnNo').val();
	                   d.IdCardNum = $('#checkIdCard').val();
	                   d.visitorName = $('#checkvisitorName').val();
	                   d.employeeName = $('#checkemployee').val();
	                   d.companyName = $('#checkcompany').val();
	                   d.validateMode = $('#checkvalidate').val();
	                   d.status = $('#vstate').val();
	                   d.startDate = $('#startDateHidden').val();
	                   d.endDate = $('#endDateHidden').val();
	        	},
	        	"type":"Post"
	        },
	        "columns": [  
	                     { "data": "matterTxnNum" },  
	                     { "data": "visitor",
	                       "render": function(data, type, row, meta) {
		                           return data.visitorName;
		                       }},
	                     { "data": "company",
			                       "render": function(data, type, row, meta) {
			                           return data.companyName;
			                       }},
	                     { "data": "employeeName"},
	                     { "data": "employeePhone"},
	                     { "data": "peopleSum"},
	                     { "data": "matter",
		                       "render": function(data, type, row, meta) {
		                           return data.matterDecs;
		                       }},
	                     { "data": "actualTime",
			                       "render": function(data, type, row, meta) {
			                           return formatDatebox(data);
			                       }},
	                     { "data": "leaveTime",
				                       "render": function(data, type, row, meta) {
				                           return formatDatebox(data);
				                       }},
	                     { "data": "validateMode",
		                   "render": function(data, type, row, meta) {
		                	   if (type === 'display') {
	                               if (data == 1) {
	                                   return "自动检验";
	                               } else {
	                                   return "手动检验";
	                               }
	                           }
	                           return data;
		                       }},
	                     { "data": "status",
			               "render": function(data, type, row, meta) {
			            	   if (type === 'display') {
	                               if (data == 1) {
	                                   return "未拜访";
	                               } else if(data == 2){
	                                   return "未离开";
	                               }else{
	                            	   return "已离开";
	                               }
	                           }
	                           return data;
			               }}
	              ] 
		});
		
		 $('#visitorRecordTable tbody').on( 'click', 'tr', function () {
		        if ( $(this).hasClass('selected') ) {
		            $(this).removeClass('selected');
		        }
		        else {
		            table.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		        }
		    } );
		 
		 $('#visitTime').datetimepicker({
		    });
		 $('#leaveTime').datetimepicker({
		    });
		 $('#startDate').datepicker({
			 format: 'yyyy-mm-dd',
		      autoclose: true,
		      setStartDate : new Date()
		    });
		 $('#endDate').datepicker({
			 format: 'yyyy-mm-dd',
		      autoclose: true,
		      setStartDate : new Date()
		    });
		 
		 
		 
	});
	
	var isAdd = true;
	var visitorData = getJSONData({url: "./visitorlistforcombox.do"});
	var companyData = getJSONData({url: "./getcompanylistByTree.do"});
	var matterData = getJSONData({url: "./matterlistForCombox.do"});
	
	function Fillcombox(){
		var visitorString = "";
		var companyString = "";
		var matterString = "";
		$.each(visitorData,function(index,item){
			visitorString+="<option value='"+item.id+"' index='"+index+"'>"+item.visitorName+"</option>";
		});
		$("#visitorName").html(visitorString);
		
		$.each(companyData,function(index,item){
			companyString+="<option value='"+item.id+"' index='"+index+"'>"+item.companyName+"</option>";
		});
		$("#companyBox").html(companyString);
		$.each(matterData,function(index,item){
			matterString+="<option value='"+item.id+"' index='"+index+"'>"+item.matterDecs+"</option>";
		});
		$("#matterBox").html(matterString);
	}
	
	Fillcombox();
	
	  function showVisitorMessage(){
		  
		 var index = $("#visitorName").find("option:selected").attr("index");
			$('#idCardType').val(visitorData[index].idCardType);
			$('#idCardNum').val(visitorData[index].idCardNum);
			$('#sex').val(visitorData[index].sex);
			$('#birth').val(visitorData[index].birth);
		    $('#mobile').val(visitorData[index].mobile); 
	  }
	  
	function addVisitor(){
		  
		  isAdd = true;
		  $("#id").val("");
		  $("#matterTxnNum").val("");
		  $("#visitorName").val("");
		  $("#sex").val("");
		  $("#birth").val("");
		  $("#idCardType").val("");
		  $("#idCardNum").val("");
		  $("#mobile").val("");
		  $("#companyBox").val("");
		  $("#employeeName").val("");
		  $("#employeePhone").val("");
		  
		  $("#matterBox").val("");
		  $("#peopleSum").val("");
		  $("#visitTime").val("");
		  $("#leaveTime").val("");
		  $("#validateMode").val("");
		  $("#status").val("");
		  
		  $('#visitortitle').html('添加访客');
		  $('#visitormodal').modal('show');
	}
	


	function updateVisitor() {
		isAdd = false;
		var selections = $('#visitorRecordTable').DataTable().rows('.selected')
				.data();

		if (selections.length == 0) {
			alertMsg('请选择需要更新的数据',"info");
			return false;
		}
		$("#id").val(selections[0].id);
		$("#matterTxnNum").val(selections[0].matterTxnNum);
		$("#visitorName").val(selections[0].visitor.id);
		$("#sex").val(selections[0].visitor.sex);
		$("#birth").val(selections[0].visitor.birth);
		$("#idCardType").val(selections[0].visitor.idCardType);
		$("#idCardNum").val(selections[0].visitor.idCardNum);
		$("#mobile").val(selections[0].visitor.mobile);
		$("#companyBox").val(selections[0].company.id);
		$("#employeeName").val(selections[0].employeeName);
		$("#employeePhone").val(selections[0].employeePhone);

		$("#matterBox").val(selections[0].matter.id);
		$("#peopleSum").val(selections[0].peopleSum);
		$("#visitTime").val(formatDatebox(selections[0].actualTime));
		$("#leaveTime").val(formatDatebox(selections[0].leaveTime));
		$("#validateMode").val(selections[0].validateMode);
		$("#status").val(selections[0].status);
		$('#visitortitle').html('修改访客信息');
		$('#visitormodal').modal('show');
	}

	function deleteVisitor() {
		var selections = $('#visitorRecordTable').DataTable().rows('.selected')
				.data();
		if (selections.length == 0) {
			alertMsg('请选择你要删除的拜访记录！',"info");
			return false;
		}

		if (confirm("你确认删除该拜访记录吗？")) {
			$("#id").val(selections[0].id);
			var id = $("#id").val();
			var submitData = {
				id : id
			};
			$.post('./deleteVisit.do', submitData, function(data) {
				if (data) {
					if (data.rInfo.ret == 0) {
						$('#visitorRecordTable').DataTable().draw(false);
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
		if(!validation()){
			return ;
		}
		
		var id = $('#id').val();
		var visitorId = $('#visitorName').val();
		var idCardType = $('#idCardType').val();
		var idCardNum = $('#idCardNum').val();
		var sex = $('#sex').val();
		var mobile = $('#mobile').val();
		var companyId = $('#companyBox').val();
		/*var departmentId = $('#departmentBox').combobox('getValue');
		var employeeId = $('#employeeBox').combobox('getValue');*/
		var employeeName = $('#employeeName').val();
		var employeePhone = $('#employeePhone').val();
		var matterId = $('#matterBox').val();
		var peopleSum = $('#peopleSum').val();
		var visitTime = $('#visitTime').val();
		var validateMode = $('#validateMode').val();
		var status = $('#status').val();
		var leaveTime = $('#leaveTime').val();
		
		var submitData = {id:id,visitorId:visitorId,idCardType:idCardType,idCardNum:idCardNum,
				sex:sex,mobile:mobile,companyId:companyId,employeeName:employeeName,employeePhone:employeePhone,/*departmentId:departmentId,employeeId:employeeId,*/
				matterId:matterId,peopleSum:peopleSum,visitTime:visitTime,validateMode:validateMode,status:status,leaveTime:leaveTime};
	    var url = './addVisit.do';
		if (!isAdd) {
			url = './updateVisit.do';
		}
		/* var submitData = $('#visitorform').serialize(); */
		$.post(url, submitData, function(data) {
			if (data) {
				if (data.rInfo.ret == 0) {
					if (isAdd) {
						$('#visitorRecordTable').DataTable().draw();//如果是添加则滚动到第一页并刷新
					} else {
						$('#visitorRecordTable').DataTable().draw(false);
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

	function checkByselect(type) {
		/* $('#visitorRecordTable').DataTable().ajax.url('./visitorlist.do?status='+$('#selectstatus').val()+'&visitorName='+visitorName).load(); */
		if(type == 0){
			$("#startDateHidden").val("");
			$("#endDateHidden").val("");
		}else{
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			if(startDate == null || endDate == null){
				alertMsg('请选择时间',"info");
				return null;
			}else{
				$("#startDateHidden").val($("#startDate").val());
				$("#endDateHidden").val($("#endDate").val());
			}
		}
		$('#visitorRecordTable').DataTable().ajax.reload();
	}
	
	function validation(){
		var visitorName = $('#visitorName').val();
		if(visitorName == null){
			alertMsg("访客信息不能为空","info");
			return false;
		}
		var companyId = $('#companyBox').val();
		if(companyId == null){
			alertMsg("请选择拜访公司","info");
			return false;
		}
		var employeeName = $('#employeeName').val();
		if(employeeName == null){
			alertMsg("被拜访人姓名不能为空！","info");
			return false;
		}
		var employeePhone = $('#employeePhone').val();
		if(!/(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})$/.test(employeePhone)){
			alertMsg("被拜访人联系电话不正确！","info");
			return false;
		}
		var matterId = $('#matterBox').val();
		if(matterId == null){
			alertMsg("请选择拜访缘由！","info");
			return false;
		}
		var peopleSum = $('#peopleSum').val();
		if(peopleSum == null){
			alertMsg("随行人数不能为空！","info");
			return false;
		}
		var visitTime = $('#visitTime').val();
		if(visitTime == null){
			alertMsg("请选择拜访时间！","info");
			return false;
		}
		var status = $('#status').val();
		if(status == null){
			alertMsg("请选择拜访状态！","info");
			return false;
		}
		return true;
	}

</script>