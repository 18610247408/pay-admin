<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/commonTpl.jsp" %>
<%@include file="/comm/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>信用卡业务</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="后台管理系统">
    <meta name="description" content="致力于提供通用版本后台管理解决方案">
    <link rel="shortcut icon" href="${ctx}/static/img/20180726141214.png">

    <link rel="stylesheet" href="${ctx}/static/layui_v2/css/layui.css">

    <script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/static/layui_v2/layui.js"></script>
    <style type="text/css">
        .mat20{margin-top: 20px;display: inline;}
        #posters ul li img{width: 10%;margin: 0 auto;}
        img{height: 200px;padding: 5px;}
        .layui-form-label{width: 120px!important;}
        .postersImg{width: 200px;height: 300px;}
    </style>
</head>
<body class="childrenBody" style="font-size: 12px;margin: 10px 10px 0;">
<form class="layui-form layui-form-pane">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>基本信息</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">编号</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" value="${agentInfo.agentNo}" readonly="readonly"/>
        </div>
       <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" value="${agentInfo.realName}" readonly="readonly"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" value="${agentInfo.phoneNo}" readonly="readonly"/>
        </div>
       <label class="layui-form-label">公司名称</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" value="${agentInfo.companyName}" readonly="readonly"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" value="${agentInfo.idCard}" readonly="readonly"/>
        </div>
       <label class="layui-form-label">创建时间</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input"  value = '<fmt:formatDate value="${agentInfo.createTime}" pattern="yyyy年MM月dd日"/>' readonly="readonly"/>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>账户信息</legend>
    </fieldset>
	<div class="layui-form-item">
        <label class="layui-form-label">可用余额</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" value="${agentInfo.balance}" readonly="readonly"/>
        </div>
       <label class="layui-form-label">在途金额</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" value="${agentInfo.transitBalance}" readonly="readonly"/>
        </div>
        <label class="layui-form-label">冻结金额</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" value="${agentInfo.freezeBalance}" readonly="readonly"/>
        </div>
    </div>


</form>
</body>
</html>
