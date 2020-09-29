<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>全鑫付运营平台 - </title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="layui-bg-cyan">

<div class="admin-login-block">
    <div class="admin-login-text">
        全鑫付运营 <span>平台</span>
    </div>
    <div class="login-font">
        <span>系统登录</span>
    </div>
    <div class="admin-login-form">
        <form class="layui-form" action="" lay-filter="login">
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input">
                    <input type="text" name="username" required lay-verify="required" placeholder="登录账号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input">
                    <input type="password" name="password" required lay-verify="required" placeholder="登录密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input">
                    <button class="layui-btn admin-login-btn" lay-submit>登录</button>
                </div>
            </div>
        </form>
        <div class="admin-login-copy">
            <p>全鑫付运营平台</p>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer', 'form'], function(){
        var $ = layui.jquery
                ,layer = layui.layer
                ,form = layui.form();

        form.on('submit(login)', function() {
            var loading = layer.load(2);
            setTimeout(function() {
                layer.close(loading);
                location.href = '${pageContext.request.contextPath}/index.jsp';
            }, 1000)
            return false;
        });
    });
</script>
</body>
</html>