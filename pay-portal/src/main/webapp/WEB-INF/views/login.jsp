<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/comm/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>全鑫付运营平台登陆</title>
    <link rel="stylesheet" href="${ctx}/static/css/layui.css">
    <%--<link rel="stylesheet" href="${ctx}/static/layui_v2/css/layui.css">--%>
    <link rel="stylesheet" href="${ctx}/static/css/logindex.css">
    <script type="text/javascript" src="${ctx}/static/layui_v2/layui.js"></script>
</head>
<body class="layui-bg-cyan">
<div class="admin-login-block">
    <div class="admin-login-text">
       	全鑫付<span>运营平台</span>
    </div>
    <div class="login-font">
        <!-- <span>系统登录</span> -->
    </div>
    <div class="admin-login-form">
        <form class="layui-form" action="" method="post">
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input">
                    <input type="text" name="username" required style=" text-align: center;" lay-verify="required" placeholder="登录账号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input">
                    <input type="password" name="password" required style=" text-align: center;" lay-verify="required" placeholder="登录密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input">
                    <button class="layui-btn login_btn admin-login-btn" lay-submit="" lay-filter="login">登陆</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<script>
    layui.config({
        base : "${ctx}/static/js/"
    }).use(['form','layer','jquery','common'], function () {
        var $ = layui.jquery,
                form = layui.form,
                common = layui.common;

		//如果当前页面存在父页面，则当前页面的父页面重新加载（即子页面父页面连带跳转）
		if(top.location!=self.location){
		       window.parent.location.reload()
		 }

        /**监听登陆提交*/
        form.on('submit(login)', function (data) {
            //弹出loading
            var loginLoading = top.layer.msg('登陆中，请稍候', {icon: 16, time: false, shade: 0.8});
            //记录ajax请求返回值
            var ajaxResult;

            //登陆验证
            $.ajax({
                url: '${ctx}/loginCheck.do',
                type: 'post',
                async: false,
                data: data.field,
                success: function (data) {
                    ajaxResult = data;
                }
            });
            //登陆成功
            if (ajaxResult.code == "0000") {
                window.location.href="${ctx}/index.do";
                top.layer.close(loginLoading);
                return false;
            } else {
                top.layer.close(loginLoading);
                common.cmsLayErrorMsg(ajaxResult.msg);
                return false;
            }
        });

    });

</script>