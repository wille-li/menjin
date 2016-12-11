$(function() {

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

	$('#tt').datagrid({
		title : '公司管理',
		iconCls : 'icon-man',
		width : '100%',
		height : '500',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50 ],
		nowrap : true,
		striped : true,
		collapsible : false,
		toolbar : "#tb",
		url : './companylist.do', // 搜索前,触发此action请求所有用户信息
		loadMsg : '数据加载中......',
		emptyMsg : '没有记录，请添加！',
		fitColumns : true,// 允许表格自动缩放,以适应父容器
		remoteSort : true,
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		fit : true
	});

	$('#CompanyDialog').dialog({
		title : '添加公司信息',
		buttons : '#addCDButton',
		iconCls : 'icon-save'
	});

	$('#companyAddress').combobox({
		data : floatData,
		textField : 'text',
		valueField : 'value',
		editable : false,
	});

})
// 弹出窗口中是添加操作还是修改操作？
var isAdd = true;

function formatAddr(value, rows, index) {
	return value+"楼";
}

function checkCompany() {
	var checkCompany = $('#checkCompanyInput').searchbox('getValue');
	var queryParams = {
		companyName : checkCompany
	};
	$('#tt').datagrid({
		queryParams : queryParams
	});
}
function addBrand() {
	isAdd = true;
	$("#id").textbox("setValue", "");
	$("#companyName").textbox("setValue", "");
	$("#companyAddress").combobox("setValue", "");
	$("#doorPlate").textbox("setValue", "");
	$("#companyPhone").textbox("setValue", "");
	$('#CompanyDialog').dialog({
		title : '添加公司信息'
	});
	$('#CompanyDialog').dialog("open");
}

function deleteCompany() {
	var selections = $('#tt').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒', '请选择你要删除的记录！');
		return false;
	}
	$.messager.confirm('确认删除', '你确定要删除这条记录?', function(r) {
		if (r) {
			$("#id").textbox("setValue", selections[0].id);
			var submitData = $('#addComForm').serialize();
			$.post('./deleteCompany.do', submitData, function(data) {
				if (data) {
					if (data.rInfo.ret == 0) {
						$('#tt').datagrid('load');
						showmessage('提醒', data.rInfo.msg);
					} else {
						showmessage('操作失败', data.rInfo.msg);
					}
				} else {
					showmessage('操作失败', '网络连接失败，请与管理员联系！');
				}
			});
			$("#id").textbox("setValue", "");
		}
	});

}

function updateBrand() {
	isAdd = false;
	var selections = $('#tt').datagrid('getSelections');
	if (selections.length == 0) {
		showmessage('提醒', '请选择你要修改的记录！');
		return false;
	}
	$("#id").textbox("setValue", selections[0].id);
	$("#companyName").textbox("setValue", selections[0].companyName);
	$("#companyAddress").combobox("setValue", selections[0].companyAddress);
	$("#doorPlate").textbox("setValue", selections[0].doorPlate);
	$("#companyPhone").textbox("setValue", selections[0].companyPhone);
	$('#CompanyDialog').dialog({
		title : '修改公司信息'
	});
	$('#CompanyDialog').dialog("open");
}

function quitDialog() {
	$('#CompanyDialog').dialog("close");
}

function submitDialog() {
	if (!validation()) {
		return;
	}
	var url = './addCompany.do';
	if (!isAdd) {
		url = './updateCompany.do';
	}
	var submitData = $('#addComForm').serialize();
	$.post(url, submitData, function(data) {
		if (data) {
			if (data.rInfo.ret == 0) {
				if (isAdd) {
					$('#tt').datagrid('load');// 如果是添加则滚动到第一页并刷新
					showmessage('提醒', data.rInfo.msg);
				} else {
					$('#tt').datagrid('reload');// 如果是修改则刷新当前页
					showmessage('提醒', data.rInfo.msg);
				}
			} else {
				showmessage("操作失败", data.rInfo.msg);
			}
		} else {
			showmessage('操作失败', '网络连接失败，请与管理员联系！');
		}

		$("#CompanyDialog").dialog("close"); // 关闭dialog
		// 清空form表单中的数据
		$("#id").textbox("setValue", "");
		$("#companyName").textbox("setValue", "");
		$("#doorPlate").textbox("setValue", "");
		$("#companyAddress").combobox("setValue", "");
		$("#companyPhone").textbox("setValue", "");
	});
}

function validation() {
	var phone = $("#companyPhone").textbox("getValue");
	if (!/(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})$/.test(phone)) {
		$.messager.alert('提醒', '输入的公司联系电话格式有误，请重新输入!', 'info');
		return false;
	}
	return true;
}
