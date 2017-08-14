<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>访客管理后台</title>
  <!-- Tell the browser to be responsive to screen width -->
  <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/adminlte/dist/img/favicon.ico"></c:url>">
  
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<c:url value="/resources/adminlte/dist/css/font-awesome.min.css"></c:url>">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<c:url value="/resources/adminlte/dist/css/ionicons.min.css"></c:url>">
  
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="<c:url value="/resources/adminlte/dist/css/skins/_all-skins.min.css"></c:url>">
  
  <%-- <link rel="stylesheet" href="<c:url value="/resources/adminlte/plugins/datatables/dataTables.bootstrap.css"></c:url>"> --%>
  
  <link rel="stylesheet" href="<c:url value="/resources/adminlte/plugins/datatables/jquery.dataTables.min.css"></c:url>">
  <link rel="stylesheet" href="<c:url value="/resources/adminlte/plugins/datepicker/datepicker3.css"></c:url>">
  <link rel="stylesheet" href="<c:url value="/resources/adminlte/plugins/datetimepicker/bootstrap-datetimepicker.css"></c:url>">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="<c:url value="/resources/adminlte/plugins/bootstrap/css/bootstrap.min.css"></c:url>">
  <!-- Theme style -->
  <link rel="stylesheet" href="<c:url value="/resources/adminlte/dist/css/AdminLTE.min.css"></c:url>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/visitrecordmanage.css"></c:url>" />
<!-- 以上为公共css -->
<script>
function iFrameHeight() { 
var ifm= document.getElementById("content"); 
var subWeb = document.frames ? document.frames["content"].document : ifm.contentDocument; 
if(ifm != null && subWeb != null) { 
ifm.height = subWeb.body.scrollHeight; 
} 
} 
</script>
</head>
<body class="sidebar-mini ajax-template skin-blue fixed">
<div class="wrapper">


  <div class="header-tab" region="north" split="true" border="false" >
        <tiles:insertAttribute name="header" />
    </div>
    <div class="footer-tab" region="south" split="true" >
         <tiles:insertAttribute name="footer" />
    </div>
    <div class="menu-tab" region="west" hide="true" split="true" title="导航菜单"  id="west">
	     <tiles:insertAttribute name="menu" />
    </div>
    <div class="body-tab" id="mainPanle" region="center" >
		 <tiles:insertAttribute name="body" /> 
    </div>
 
  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane" id="control-sidebar-home-tab">

      </div>
      <!-- /.tab-pane -->

      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<div id="loading" class="loading-panel">
  <div class="box">
    <i class="fa fa-refresh fa-spin"></i>
    <span class="tip">
       正在加载 · · ·    
    </span>
  </div>
</div>


<!-- jQuery 2.2.3 -->
<script src="<c:url value="/resources/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></c:url>"></script>
<script src="<c:url value="/resources/adminlte/plugins/jQuery/jquery.form.js"></c:url>"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<c:url value="/resources/adminlte/plugins/bootstrap/js/bootstrap.min.js"></c:url>"></script>
<script src="<c:url value="/resources/adminlte/plugins/fastclick/fastclick.js"></c:url>"></script>
<!-- Slimscroll -->
<script src="<c:url value="/resources/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></c:url>"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/resources/adminlte/dist/js/app.js"></c:url>"></script>
<!-- 以上JS为页面必须 -->

<script src="<c:url value="/resources/adminlte/plugins/datatables/jquery.dataTables.min.js"></c:url>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/service.js"></c:url>"></script>
<script src="<c:url value="/resources/adminlte/plugins/datepicker/bootstrap-datepicker.js"></c:url>"></script>
<script src="<c:url value="/resources/adminlte/plugins/datetimepicker/bootstrap-datetimepicker.js"></c:url>"></script>

</body>
</html>




	            
	     
	           
	            
	           
	             
	           
	         
