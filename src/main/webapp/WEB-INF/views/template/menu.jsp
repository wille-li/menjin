<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="resources/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><sec:authentication property="name"/></p>
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">主导航</li>
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-pie-chart"></i>
            <span>系统管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a target="navTab" href="./user.do"><i class="fa fa-circle-o"></i> 用户管理</a></li>
            <li><a target="navTab" href="./role.do"><i class="fa fa-circle-o"></i> 角色管理</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-laptop"></i>
            <span>公司管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a target="navTab" href="./company.do"><i class="fa fa-circle-o"></i> 公司管理</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-edit"></i> <span>访客管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a target="navTab" href="./matter.do"><i class="fa fa-circle-o"></i> 拜访缘由管理</a></li>
            <li><a target="navTab" href="./vistor.do"><i class="fa fa-circle-o"></i> 访客管理</a></li>
            <li><a target="navTab" href="./vist.do"><i class="fa fa-circle-o"></i> 拜访记录管理</a></li>
          </ul>
        </li>
        <%-- <li class="treeview">
          <a href="#">
            <i class="fa fa-table"></i> <span>表格</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a target="navTab" href="<%=request.getContextPath() %>/resources/adminlte/include/tables/simple.html"><i class="fa fa-circle-o"></i> 简单表格</a></li>
            <li><a target="navTab" href="<%=request.getContextPath() %>/resources/adminlte/include/tables/data.html"><i class="fa fa-circle-o"></i> 数据表格</a></li>
            <li><a target="navTab" href="<%=request.getContextPath() %>/resources/adminlte/include/tables/diytable.html"><i class="fa fa-circle-o"></i> DIY表格</a></li>
          </ul>
        </li> --%>
        <li class="header">标签</li>
        <li><a href="javascript:void(0);"><i class="fa fa-circle-o text-red"></i> <span>重要</span></a></li>
        <li><a href="javascript:void(0);"><i class="fa fa-circle-o text-yellow"></i> <span>警告</span></a></li>
        <li><a href="javascript:void(0);"><i class="fa fa-circle-o text-aqua"></i> <span>消息</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

