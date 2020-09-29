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
<div class="larry-grid layui-anim layui-anim-upbit larryTheme-A ">
    <div class="larry-personal">
        <div class="layui-tab">
            <blockquote class="layui-elem-quote mylog-info-tit">
                <div class="layui-inline">
                    <form class="layui-form" id="operatorSearchForm">
                        <div class="layui-form-item" style="margin-bottom:3px;">
                            <label class="layui-form-label">登陆账号:</label>
                            <div class="layui-input-inline" style="width:140px;">
                                <input type="text" name="username" value="" placeholder="请输入登录帐号" class="layui-input search_input">
                            </div>
                            <label class="layui-form-label">用户姓名:</label>
                            <div class="layui-input-inline" style="width:140px;">
                                <input type="text" name="realname" value="" placeholder="请输入用户姓名" class="layui-input search_input">
                            </div>
                            <a class="layui-btn operatorSearchList_btn" lay-submit lay-filter="operatorSearchFilter"><i class="layui-icon  layui-icon-search"></i>查询</a>
                        </div>
                    </form>
                </div>
            </blockquote>
            <div class="larry-separate"></div>
            <!-- 用户列表 -->
            <div class="layui-tab-item layui-show " style="padding: 10px 15px;">
                 <shiro:hasPermission name="sys:operator:add"> 
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-normal operatorAdd_btn"> <i class="layui-icon  layui-icon-add-circle"></i>新增用户</a>
                    </div>
                  </shiro:hasPermission> 
                <table id="operatorTableList"  lay-filter="operatorTableId" ></table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var $;
    layui.config({
        base : "${ctx}/static/js/"
    }).use(['form', 'table', 'layer','common'], function () {
        $ =  layui.$;
        var form = layui.form,
                table = layui.table,
                layer = layui.layer,
                common = layui.common;

        /**用户表格加载*/
        $(".operatorSearchList_btn").click(function(){
            //监听提交
            form.on('submit(operatorSearchFilter)', function (data) {
                table.render({
                    elem: '#operatorTableList',
                    url: '${ctx}/operator/findOperatorList.do',
                    id:'operatorTableId',
                    method: 'post',
                    loading:true,
                    skin:'row',
                    even:'true',
                    size: 'sm',
                    where: data.field,
                    cols: [[
                        {field:'id', title: '序号',align:'center'},
                        {field:'username', title: '登陆账号',align:'center' },
                        {field:'realname', title: '用户姓名',align:'center'},
                        {field:'status', title: '用户状态',align:'center',templet: '#operatorStatusTpl'},
                        {field:'createOperator', title: '创建人',align:'center'},
                        {field:'createTime', title: '创建时间',align:'center'},
                        {field:'lastUpdateTime', title: '修改时间',align:'center'},
                        {fixed:'right',title: '操作', align:'center',toolbar: '#operatorBar'}
                    ]],
                    page: true,
                    limit: 10//默认显示10条
                });
            });
        });

        /**新增用户*/
        $(".operatorAdd_btn").click(function(){
            var url = "${ctx}/operator/operatorAdd.do";
            common.cmsLayOpen('新增用户',url,'550px','265px');
        });

        /**监听工具条*/
        table.on('tool(operatorTableId)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var operatorId = data.id;
            var operatorStatus = data.status;
            //修改用户
            if(layEvent === 'operator_edit') {
                var url =  "${ctx}/operator/operatorUpdate.do?operatorId="+operatorId;
                common.cmsLayOpen('编辑用户',url,'550px','265px');
                //分配角色
            }else if(layEvent === 'operator_grant'){
                if(operatorStatus == 'FALSE'){
                    common.cmsLayErrorMsg("当前用户已失效,不能被分配角色");
                    return false;
                }
                var url =  "${ctx}/operator/operatorGrant.do?operatorId="+operatorId;
                common.cmsLayOpen('分配角色',url,'500px','440px');
                //用户失效
            }
        });


    });
</script>
<!-- 用户状态tpl-->
<script type="text/html" id="operatorStatusTpl">

    {{# if(d.status == 'TRUE'){ }}
    <span class="label label-success ">有效</span>
    {{# } else if(d.status == 'FALSE'){ }}
    <span class="label label-danger ">失效</span>
    {{# }  }}
</script>


<!--工具条 -->
<script type="text/html" id="operatorBar">
    <div class="layui-btn-group">
        <shiro:hasPermission name="sys:operator:update">
            <a class="layui-btn layui-btn-xs" lay-event="operator_edit"><i class="layui-icon  layui-icon-edit"></i>编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="sys:operator:role">
            <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="operator_grant"><i class="layui-icon layui-icon-user"></i>角色</a>
        </shiro:hasPermission>

    </div>
</script>



</body>
</html>