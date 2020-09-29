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
<div class="larry-grid layui-anim layui-anim-upbit larryTheme-A">
    <div class="larry-personal">
        <div class="layui-tab">
            <blockquote class="layui-elem-quote mylog-info-tit">
                <div class="layui-inline">
                    <form class="layui-form" id="roleSearchForm">
                        <div class="layui-form-item" style="margin-bottom:3px;">
                            <label class="layui-form-label">角色名称:</label>
                            <div class="layui-input-inline" style="width:140px;">
                                <input type="text" name="name" value="" placeholder="请输入角色名称" class="layui-input search_input">
                            </div>
                            <label class="layui-form-label">角色编码:</label>
                            <div class="layui-input-inline" style="width:140px;">
                                <input type="text" name="roleCode" value="" placeholder="请输入角色编码" class="layui-input search_input">
                            </div>
                            <a class="layui-btn roleSearchList_btn" lay-submit lay-filter="roleSearchFilter"><i class="layui-icon  layui-icon-search"></i>查询</a>
                        </div>
                    </form>
                </div>
            </blockquote>
            <div class="larry-separate"></div>
            <!-- 角色列表 -->
            <div class="layui-tab-item  layui-show" style="padding: 10px 15px;">
                <div class="layui-inline">
                    <shiro:hasPermission name="sys:role:add">
                        <a class="layui-btn layui-btn-normal  roleAdd_btn"> <i class="layui-icon  layui-icon-add-circle"></i>新增角色</a>
                    </shiro:hasPermission>
                </div>
                <table id="roleTableList" lay-filter="roleTableId"></table>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
    layui.config({
        base : "${ctx}/static/js/"
    }).use(['form', 'table', 'layer','common'], function () {
        var $ =  layui.$,
                form = layui.form,
                table = layui.table,
                layer = layui.layer,
                common = layui.common;


        $(".roleSearchList_btn").click(function(){
            form.on('submit(roleSearchFilter)', function (data) {
                table.render({
                    elem: '#roleTableList',
                    url: '${ctx}/role/findRoleList.do',
                    id:'roleTableId',
                    method: 'post',
                    loading:true,
                    skin:'row',
                    even:'true',
                    size: 'sm',
                    where: data.field,
                    cols: [[
                        {field:'id', title: '序号',width: 60 ,align:'center'},
                        {field:'name', title: '角色名称',width: 130 ,align:'center' },
                        {field:'roleCode', title: '角色编码',width: 130 ,align:'center' },
                        {field:'status', title: '角色状态',width: 130 ,align:'center',templet: '#roleStatusTpl'},
                        {field:'remark', title: '备注',width: 130 ,align:'center'},
                        {fixed:'right',title: '操作', align:'center', width: 170 ,toolbar: '#roleBar'}
                    ]],
                    page: true,
                    limit: 10//默认显示10条
                });
            });
        });

        /**角色新增*/
        $(".roleAdd_btn").click(function(){
            var url = "${ctx}/role/roleAdd.do";
            common.cmsLayOpen('新增角色',url,'550px','340px');
        });

        /*/!**导出角色信息*!/
         $(".excelRoleExport_btn").click(function(){
         var url = '${ctx}/role/excel_role_export.do';
         $("#roleSearchForm").attr("action",url);
         $("#roleSearchForm").submit();
         });*/

        /**批量失效*/
        $(".roleBatchFail_btn").click(function(){
            //表格行操作
            var checkStatus = table.checkStatus('roleTableId');

            if(checkStatus.data.length == 0){
                common.cmsLayErrorMsg("请选择要失效的角色信息");
            }else{
                var roleStatus = false;
                var roleIds = [];
                $(checkStatus.data).each(function(index,item){
                    roleIds.push(item.roleId);
                    //角色已失效
                    if(item.roleStatus == 0){
                        roleStatus = true;
                    }else{
                        roleStatus = false;
                        return false;
                    }
                });
                if(roleStatus==false){
                    common.cmsLayErrorMsg("当前选择的角色已失效");
                    return false;
                }
                var url = "${ctx}/role/ajax_role_batch_fail.do";
                var param = {roleIds:roleIds};
                common.ajaxCmsConfirm('系统提示', '失效角色、解除角色、用户、菜单绑定关系?',url,param);

            }
        });

        /**监听工具条*/
        table.on('tool(roleTableId)', function(obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var roleId = data.id;
            //修改角色
            if(layEvent === 'role_edit') {
                var url = "${ctx}/role/roleUpdate.do?roleId="+roleId;
                common.cmsLayOpen('编辑角色',url,'550px','340px');
                //角色授权
            }else if(layEvent === 'role_grant'){
                var roleStatus = data.status;
                if(roleStatus == "FALSE"){
                    common.cmsLayErrorMsg("当前角色已失效,不能授权");
                    return false;
                }
                var url =  "${ctx}/role/roleGrant.do?roleId="+roleId;
                common.cmsLayOpen('角色授权',url,'255px','520px');
                //角色失效
            }else if(layEvent === 'role_fail') {
                var roleStatus = data.roleStatus;
                if(roleStatus == 1){
                    common.cmsLayErrorMsg("当前角色已失效");
                    return false;
                }
                var url = "${ctx}/role/ajax_role_fail.do";
                var param = {roleId:roleId};
                common.ajaxCmsConfirm('系统提示', '失效角色、解除角色、用户、菜单绑定关系?',url,param);

            }
        });
    });


</script>

<!-- 角色状态tpl-->
<script type="text/html" id="roleStatusTpl">

    {{# if(d.status == 'TRUE'){ }}
    <span class="label label-success ">有效</span>
    {{# } else if(d.status == 'FALSE'){ }}
    <span class="label label-danger ">失效</span>
    {{# }  }}
</script>


<!--工具条 -->
<script type="text/html" id="roleBar">
    <div class="layui-btn-group">
        <shiro:hasPermission name="sys:role:update">
            <a class="layui-btn layui-btn-xs" lay-event="role_edit"><i class="layui-icon  layui-icon-edit"></i>编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="sys:role:menu">
            <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="role_grant"><i class="layui-icon layui-icon-auz"></i>权限</a>
        </shiro:hasPermission>
    </div>
</script>


</body>
</html>