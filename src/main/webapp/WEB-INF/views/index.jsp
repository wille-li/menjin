<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header-navtabs">
        <div class="tabs-page">
          <ul class="tabs-list clearfix" id="navTabs">
             <li class="active"><span>我的主页</span></li>
           </ul>
           <a href="javascript:void(0);" class="prev fa fa-step-backward"></a>
           <a href="javascript:void(0);" class="next fa fa-step-forward"></a> 
        </div>
        <div class="context-menu" id="contextMenu">
          <ul class="ct-nav">
            <li rel="reload">刷新标签页</li>
            <li rel="closeCurrent">关闭标签页</li>
            <li rel="closeOther">关闭其他标签页</li>
            <li rel="closeAll">关闭全部标签页</li>
          </ul>
        </div>
    </section>
    <!-- Main content -->
    <section class="" id="content">
      <div class="tabs-panel">
        <h1>欢迎使用roncoo-admin LTE </h1>
        <div class="callout callout-warning">
          <h4>温馨提示!</h4>

          <p><b>adminLTE</b>使用jQuery 2.2.3版本，并引入很多优秀的第三方jQuery插件，开发者也可以改用自己熟悉的第三方插件，但原有插件对兼容IE低版本(6,7,8)浏览器并不友好，如果要考虑兼容低版本IE浏览器，建议替换jQuery版本到1.7.2以下并使用其他兼容低版本浏览器的jQuery插件。</p>
        </div>
        <div class="row">
          <div class="col-md-6">
            <div class="box box-primary">
              <div class="box-header with-border">
                <h3 class="box-title">更多开源项目</h3>
              </div>
              <table class="table table-striped">
                <tbody>
                  <tr>
                    <td><strong>roncoo-pay: </strong><span class="text-light-blue">龙果支付系统</span></td>
                    <td width="130">
                      <a href="https://git.oschina.net/roncoocom/roncoo-pay" class="btn bg-blue btn-xs">oschina</a>
                      <a href="https://github.com/roncoo/roncoo-pay" class="btn bg-blue btn-xs">github</a>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>roncoo-adminlte-springmvc: </strong><span class="text-light-blue">整合SSM的roncoo-adminlte</span></td>
                    <td width="130">
                      <a href="https://git.oschina.net/roncoocom/roncoo-adminlte-springmvc" class="btn bg-blue btn-xs">oschina</a>
                      <a href="https://github.com/roncoo/roncoo-adminlte-springmvc" class="btn bg-blue btn-xs">github</a>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>roncoo-cmdb: </strong><span class="text-light-blue">龙果学院推出开源运维平台</span></td>
                    <td width="130">
                      <a href="https://github.com/roncoo/roncoo-cmdb" class="btn bg-blue btn-xs">github</a>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>spring-boot-demo: </strong><span class="text-light-blue">Spring Boot的基础教程demo</span></td>
                    <td width="130">
                      <a href="https://git.oschina.net/roncoocom/spring-boot-demo" class="btn bg-blue btn-xs">oschina</a>
                      <a href="https://github.com/roncoo/spring-boot-demo" class="btn bg-blue btn-xs">github</a>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>roncoo-jui-springboot: </strong><span class="text-light-blue">Spring Boot整合jui</span></td>
                    <td width="130">
                      <a href="https://git.oschina.net/roncoocom/roncoo-jui-springboot" class="btn bg-blue btn-xs">oschina</a>
                      <a href="https://github.com/roncoo/roncoo-jui-springboot" class="btn bg-blue btn-xs">github</a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="col-md-6">
            <div class="box box-primary">
              <div class="box-header with-border">
                <h3 class="box-title">更多相关知识</h3>
              </div>
              <table class="table table-striped">
                <tbody>
                  <tr>
                    <td><strong>Spring Boot</strong></td>
                    <td width="80">
                      <a href="http://www.roncoo.com/course/view/c99516ea604d4053908c1768d6deee3d" class="btn bg-blue btn-xs">view</a>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>龙果运维平台安装使用</strong></td>
                    <td width="80">
                      <a href="http://www.roncoo.com/course/view/a2d58fe08172447696412fb7af1de620" class="btn bg-blue btn-xs">view</a>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>龙果开源支付系统业务介绍与部署</strong></td>
                    <td width="80">
                      <a href="http://www.roncoo.com/course/view/a09d8badbce04bd380f56034f8e68be0" class="btn bg-blue btn-xs">view</a>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>SSM框架实战应用</strong></td>
                    <td width="80">
                      <a href="http://www.roncoo.com/course/view/993d7660a19f41ed94d5bd4f0b414ede" class="btn bg-blue btn-xs">view</a>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>Dubbo</strong></td>
                    <td width="80">
                      <a href="http://www.roncoo.com/course/view/f614343765bc4aac8597c6d8b38f06fd" class="btn bg-blue btn-xs">view</a>
                    </td>
                  </tr>
                  
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
