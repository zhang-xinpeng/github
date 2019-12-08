<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="../static/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/jqgrid/css/ui.jqgrid-bootstrap.css">
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <script src="../static/jqgrid/js/jquery.jqGrid.min.js"></script>
    <script src="../static/jqgrid/js/grid.locale-cn.js"></script>
    <script src="../static/boot/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            /*初始化表格*/
            $('#deptList').jqGrid({
                autowidth: true,/*自适应父级宽度*/
                height: 300,/*设置高度*/
                styleUI: 'Bootstrap',/*设置为bootstrap风格的表格*/
                url: '${pageContext.request.contextPath}/dept/findAll',/*数据获取路径*/
                datatype: 'json',/*响应数据格式*/
                mtype: 'post',/*请求数据方式*/
                colNames: ['编号', '名称'],/*设置标题*/
                colModel: [
                    {name: 'id'},
                    {name: 'name', editable: true},
                ],/*列数组值匹配对象*/
                pager: '#tool',/*设置分页工具栏*/
                rowNum: 5,/*每页显示数据*/
                rowList: [2, 4, 10],/*生成可以选择单页条数的下拉列表*/
                viewrecords: true,/*显示总条数*/
                caption: '部门管理',/*设置表格标题*/
                cellEdit: true,/*开启单元格编辑功能*/
                editurl: '${pageContext.request.contextPath}/dept/edit',/*数据各更改时的请求路径*/
                multiselect: true
            }).navGrid('#tool',
                {add: true, edit: true, del: true, search: true, refresh: true},//开启编辑操作
                {closeAfterEdit: true},//对编辑面板的控制
                {closeAfterAdd: true},//对添加面板的控制
                {closeAfterDel: true},//对删除面板的控制
                {
                    sopt: ['eq', 'ne']
                }//对查找面板的控制,查找时走的也是查询所有的路径，只是传参不同
            );/*开启增删改查工具栏*/
        });
    </script>
</head>
<body>
<%--导航--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">EMS</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <p class="navbar-text">欢迎：<span style="color: orange">${user.username}</span></p>
                </li>
                <li><a href="#">注销 <span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <%--左侧--%>
        <div class="col-sm-2">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                部门管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="#home" data-toggle="tab" class="list-group-item active">
                                    部门管理
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--右侧--%>
        <div class="col-sm-10">
            <div>
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                              data-toggle="tab">部门管理</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <table id="deptList"></table>
                        <div id="tool"></div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>