var isAdd = true; 

function addResource(){
	  isAdd = true;
	  $("#saveResource").html("保存");
	  $("#resourceForm").form("clear");
	  $('#ResourceDialog').dialog({title:'添加权限资源信息'});
	  $('#ResourceDialog').dialog("open");
}

function closeDialog(){
	$('#ResourceDialog').dialog("close");
}