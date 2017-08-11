<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="diy_table_panel" style="font-size:12px">
	<div class="diy_table_head">
	</div>
	<div class="diy_table_body box">
		<div class="">
			<div class="box-body">
				<table id="openerTable" class="display table table-bordered table-hover">
					<thead>
						<tr>
							<th>访问者</th>
							<th>日志描述</th>
							<th>访问方法</th>
							<th>请求IP</th>
							<th>错误信息</th>
							<th>记录时间</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

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
        	"url":"./logList.do",
        	"data": function ( d ) {
                   //添加额外的参数传给服务器
                   d.searchName = $('#checkOpenerInput').val();
        	},
        	"type":"Post"
        },
        "columns": [  
                     { "data": "createBy" },  
                     { "data": "description"},
                     { "data": "method"},
                     { "data": "requestIP"},
                     { "data": "exceptionDetail"},
                     { "data": "createDate",
	                   "render": function(data, type, row, meta) {
			               return formatDatebox(data);
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

});
</script>