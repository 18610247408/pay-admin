<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/mytags.jsp" %>
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
<div>
    <div class="larry-personal">
        <div class="layui-tab">
            <blockquote class="layui-elem-quote mylog-info-tit">
                <div class="layui-inline">
                    <form class="layui-form">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户编号:</label>
                            <div class="layui-input-inline" style="width:140px;">
                                <input type="text" name="userNo" class="layui-input search_input">
                            </div>
                            <label class="layui-form-label">订单编号:</label>
                            <div class="layui-input-inline" style="width:140px;">
                                <input type="text" name="orderNo" class="layui-input search_input">
                            </div>
                            <label class="layui-form-label">审核结果:</label>
                            <div class="layui-input-inline" style="width:140px;">
                               	<select name="auditStatus">
                               		<option value=""></option>
                               		<option value="0">审核中</option>
                               		<option value="2">审核成功</option>
                               		<option value="3">审核拒绝</option>
                               	</select>
                            </div>
                            <a class="layui-btn orderSearchList_btn" lay-submit lay-filter="orderSearchFilter"><i class="layui-icon  layui-icon-search"></i>查询</a>
                        </div>
                    </form>
                </div>
            </blockquote>
            <div class="larry-separate"></div>
            <!-- 订单列表 -->
            <div class="layui-tab-item  layui-show" style="padding: 10px 15px;">
                <table id="orderTableList" lay-filter="orderTableId"></table>
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


        $(".orderSearchList_btn").click(function(){
            form.on('submit(orderSearchFilter)', function (data) {
                table.render({
                    elem: '#orderTableList',
                    url: '${ctx}/jsd/findAll.do',
                    id:'orderTableId',
                    method: 'post',
                    loading:true,
                    skin:'row',
                    even:'true',
                    size: 'sm',
                    where: data.field,
                    cols: [[
                        {field:'userNo', title: '用户编号',align:'center'},
                        {field:'bankName',title: '银行名称',align:'center'},
                        {field:'itemName',title: '商品名称',align:'center'},
                        {field:'productName', title: '子商品名称',align:'center'},
                        {field:'orderNo', title: '订单编号',align:'center' },
                        {field:'point', title: '兑换积分数',align:'center' },
                        {field:'agentIncome', title: '服务商收益',align:'center'},
                        {field:'userIncome', title: '用户收益',align:'center'},
                        {field:'auditStatus', title: '审核状态',align:'center',templet: '#auditStatusTpl'},
                        {field:'createTime', title: '报单时间',align:'center',templet:function(d){return common.formatTime(d.createTime,'yyyy-MM-dd hh:mm:ss')}},
                        {field:'auditTime', title: '审核时间',align:'center',templet:function(d){return common.formatTime(d.auditTime,'yyyy-MM-dd hh:mm:ss')}},
                     ]],
                     page: true,
                     limit: 10//默认显示10条
                });
            });
        });
    });
</script>

<!-- 状态tpl-->
<script type="text/html" id="auditStatusTpl">
    {{# if(d.auditStatus == 0){ }}
    	<span class="label label-success ">审核中</span>
    {{# }else if(d.auditStatus == 2){ }}
   		<span class="label label-success ">审核成功</span>
	{{# }else if(d.auditStatus == 3){ }}
   		<span class="label label-danger ">审核失败</span>
	{{# }}}
</script>

</body>
</html>
