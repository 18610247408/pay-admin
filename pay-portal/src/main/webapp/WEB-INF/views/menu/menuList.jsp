<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="后台管理系统">
    <meta name="description" content="致力于提供通用版本后台管理解决方案">
    <link rel="shortcut icon" href="${ctx}/static/img/20180726141214.png">

    <link rel="stylesheet" href="${ctx}/static/layui_v2/css/layui.css">
    <link rel="stylesheet" href="${ctx}/static/css/global.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/personal.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/fontcss.css">

    <script src="${ctx}/static/layui_v2/layui.js"></script>

<body>
<div class="larry-grid layui-anim layui-anim-upbit larryTheme-A" >
    <div class="larry-personal" >
        <div class="layui-tab" >
            <blockquote class="layui-elem-quote mylog-info-tit">
                    <div class="layui-inline">
                        <form class="layui-form" id="cusSearchForm">
                            <div class="layui-form-item" style="margin-bottom:3px;">
                                <label class="layui-form-label">菜单名称:</label>
                                <div class="layui-input-inline" style="width:140px;">
                                    <input type="text" name="name" value="" placeholder="请输入关键字" class="layui-input search_input">
                                </div>
                                <label class="layui-form-label">菜单类型:</label>
                                <div class="layui-input-inline" style="width:90px;">
                                	<select name="resType" >
                                		<option selected="selected"></option>
                                		<option value="0">菜单</option>
                                		<option value="1">按钮</option>
                                	</select>
                                </div>
                                <label class="layui-form-label" >菜单级别:</label>
                                <div class="layui-input-inline" style="width:90px;">
                                    <select name="levels" >
                                		<option selected="selected"></option>
                                		<option value="1">一级菜单</option>
                                		<option value="2">二级菜单</option>
                                	</select>
                                </div>
                                <a class="layui-btn cusSearchList_btn" lay-submit lay-filter="cusSearchFilter"><i class="layui-icon  layui-icon-search"></i>查询</a>
                            </div>
                        </form>
                    </div>
            </blockquote>
            <div class="larry-separate"></div>
            <!-- 菜单列表 -->
            <div class="layui-tab-item layui-show" style="padding: 10px 15px;">
                <shiro:hasPermission name="sys:menu:add">
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-normal  resAdd_btn"> <i class="layui-icon  layui-icon-add-circle"></i>新增菜单</a>
                    </div>
                </shiro:hasPermission>
                <%--<shiro:hasPermission name="sys:menu:add">
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-normal excelResExport_btn"  style="background-color:#5FB878"> <i class="layui-icon  layui-icon-download-circle"></i>导出</a>
                    </div>
                </shiro:hasPermission>--%>
                <table id="resTableList" lay-filter="resTableId"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.config({
        base : "${ctx}/static/js/"
    }).use(['form', 'table', 'layer','common'], function () {
        var $ = layui.$,
                form = layui.form,
                table = layui.table,
                layer = layui.layer,
                common = layui.common;

        /**查询*/
        $(".cusSearchList_btn").click(function(){
            form.on('submit(cusSearchFilter)', function (data) {
                table.render({
                    elem: '#resTableList',
                    url: '${ctx}/menu/findMenuList.do',
                    id:'resTableId',
                    method: 'post',
                    loading:true,
                    skin:'row',
                    even:'true',
                    size: 'sm',
                    where: data.field,
                    cols: [[
                        {field:'id', title: '序号',align:'center'},
                        {field:'name', title: '菜单名称',align:'center'},
                        {field:'label', title: '授权标识',align:'center'},
                        {field:'status', title: '菜单状态',align:'center',templet: '#resStatusTpl'},
                        {field:'url', title: '菜单路径',align:'center'},
                        {field:'resType', title: '菜单类型',align:'center',templet: '#resTypeTpl'},
                        {field:'remark', title: '上级菜单',align:'center'},
                        {field:'levels', title: '菜单级别',align:'center',templet: '#resLevelTpl'},
                        {field:'createTime', title: '创建时间',align:'center'},
                        {fixed:'right', title: '操作', align:'center',toolbar: '#resBar'}
                    ]],
                    page: true,
                    limit: 10//默认显示10条
                });
            });
        });

        /**新增菜单*/
        $(".resAdd_btn").click(function(){
            var url = "${ctx}/menu/toInsertMenu.do";
            common.cmsLayOpen('新增菜单',url,'765px','410px');
        });

        /**监听工具条  编辑*/
        table.on('tool(resTableId)', function(obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            //编辑菜单
            if(layEvent === 'res_edit') {
                var resId = data.id;
                var url =  "${ctx}/menu/toUpdateMenu.do?resId="+resId;
                common.cmsLayOpen('编辑菜单',url,'765px','410px');
            }
        });

    });

</script>
<!-- 菜单状态tpl-->
<script type="text/html" id="resStatusTpl">
    {{# if(d.status == 'TRUE'){ }}
    <span class="label label-success ">有效</span>
    {{# } else if(d.status == 'FALSE'){ }}
    <span class="label label-danger ">失效</span>
    {{# } }}
</script>
<!-- 菜单类型tpl-->
<script type="text/html" id="resTypeTpl">
    {{# if(d.resType == 0){ }}
    <span class="label label-info ">菜单</span>
    {{# } else if(d.resType == 1){ }}
    <span class="label label-warning ">按钮</span>
    {{# } }}
</script>

<!-- 菜单级别tpl-->
<script type="text/html" id="resLevelTpl">
    {{# if(d.levels == 1){ }}
    <span>1级菜单</span>
    {{# } else if(d.levels == 2){ }}
    <span>2级菜单</span>
    {{# } else if(d.levels == 3){ }}
    <span>3级菜单</span>
    {{# } }}
</script>

<!--工具条 -->
<script type="text/html" id="resBar">
    <shiro:hasPermission name="sys:menu:update">
        <div class="layui-btn-group">
            <a class="layui-btn layui-btn-xs" lay-event="res_edit"><i class="layui-icon  layui-icon-edit"></i>编辑</a>
        </div>
    </shiro:hasPermission>
</script>

</body>
</html>