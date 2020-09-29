<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="后台管理系统">
    <meta name="description" content="">

    <link rel="shortcut icon" href="${ctx}/static/img/20180726141214.png">
    <link rel="stylesheet" href="${ctx}/static/layui_v2/css/layui.css">
    <link rel="stylesheet" href="${ctx}/static/css/global.css">
    <link rel="stylesheet" href="${ctx}/static/css/fontcss.css">
    <link rel="stylesheet" href="${ctx}/static/css/main.css">
    <link rel="stylesheet" href="${ctx}/static/css/backstage.css">

    <script type="text/javascript" src="${ctx}/static/layui_v2/layui.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/index.js"></script>


	<script type="text/javascript">
    layui.config({
        base : '${ctx}/static/js/'
    });
    layui.use(['form', 'layer','common','element'], function () {
        var $ = layui.jquery,
                form = layui.form,
                layer = layui.layer,
                element = layui.element,
                common = layui.common;

        /*初始化左边操作栏*/
        layer.ready(function(){
        	 ajaxMenuInit();
          });  
        
        function ajaxMenuInit(){
            $.ajax({
                url : '${ctx}/menu/menuLeft.do',
                type : 'post',
                async: false,
                data:{
                },
                success : function(data) {
                    if(data != "" ){
                        var pdata = $.parseJSON(data);
                        var ulHtml = '<ul class="layui-nav layui-nav-tree layui-left-nav">';
                        $.each(pdata,function(index,item){
                            ulHtml += '<li class="layui-nav-item">';

                            if(item.children != null ){
                            	ulHtml += '<a href="javascript:;">';
                                ulHtml += '<i class="layui-icon '+item.icon+' " data-icon="'+item.icon+'"></i>';
                                ulHtml += '<cite>'+item.name+'</cite>';
                                ulHtml += '<span class="layui-nav-more"></span>';
                                ulHtml += '</a>';
                                ulHtml += '<dl class="layui-nav-child">';
                                $.each(item.children,function(index,child){
                                    ulHtml += '<dd><a href="javascript:;" data-url="'+child.url+'">';
                                    if(child.icon != null){
                                        ulHtml += '<i class="layui-icon '+child.icon+'" data-icon="'+child.icon+'"></i>';
                                    }
                                    ulHtml += '<cite>'+child.name+'</cite></a></dd>';
                                });
                                ulHtml += "</dl>"
                            }else{
                                ulHtml += '<a href="javascript:;" data-url="">';
                                ulHtml += '<i class="layui-icon '+item.icon+'" data-icon="'+item.icon+'"></i>';
                                ulHtml += '<cite>'+item.name+'</cite></a>';
                            }
                            ulHtml += '</li>'
                        });
                        ulHtml += '</ul>';
                        $(".navBar").html(ulHtml);
                        element.init();  //初始化页面元素
                    }  else{
                        $("#navBarId").empty();
                    } 
                }
            });
        }


        $('#lock').mouseover(function () {
            layer.tips('请按Alt+L快速锁屏！', '#lock', {tips: [1, '#3c8dbc'], time: 2000})
        });
        $(document).keydown(function (e) {
            if (e.altKey && e.which == 76) {
                lockPage();
            }
        });
        $(document).keyup(function(event){
            if(event.keyCode ==13){
                $("#unlock").click();
            }
        });

        //退出
        $('#logout').on('click', function () {
            var url = '${ctx}/logout.do';
            console.info("logout...");
            common.logOut("退出","退出",url);
        });

        //修改密码
        $("#upadtePassword").on('click', function () {
            var url = '${ctx}/operator/toUpdatePassword.do';
            common.cmsLayOpen('修改密码',url,'380px','270px');
        });
    });

</script >

</head>
<body class="main_body larryTheme-A">

<div class="layui-layout layui-layout-admin ">
    <!-- 顶部-->
    <div class="layui-header header header-menu ">
        <div class="layui-main ">
            <a href="#" class="logo" style="font-size:18px">全鑫付运营平台</a>
            <!-- 左侧导航收缩开关 -->
            <div class="side-menu-switch" >
                <ul class="layui-nav clearfix ">
                    <li style="" class="layui-nav-item">
                        <a class="onFullScreen"><i class="layui-icon layui-icon-shrink-right"></i></a>
                    </li>
                </ul>
            </div>
            <!-- 顶级菜单 -->
            <div class="larry-top-menu posb topMenu" id="topMenu"></div>
            <!-- 右侧常用菜单导航 -->
            <div class="larry-right-menu posb" >
                <ul class="layui-nav clearfix ">
                    <li class="layui-nav-item">
                        <a id="upadtePassword" style="height: 50px;"><i class="layui-icon layui-icon-password"></i><cite>修改密码</cite></a>
                    </li>
                    <li class="layui-nav-item exit">
                        <a id="logout" style="height: 50px;" ><i class="layui-icon layui-icon-return"></i><cite>退出</cite></a>
                    </li>
                </ul>

            </div>
        </div>
    </div>
    <!-- 左侧导航-->
    <div class="layui-side layui-side-menu layui-bg-black" style="top:50px;">
        <div class="user-photo">
            <a class="img" title="我的头像" ><img src="${ctx}/static/img/quanxinpay.png"></a>
            <p><i class="layui-icon layui-icon-username"></i> ${LOGIN_NAME.realname}</p>
        </div>

        <!-- 左侧菜单-->
        <div class="navBar layui-side-scroll" id="navBarId">
        </div>
    </div>
    <!--中间内容 -->
    <div class="layui-body layui-form" id="larry-body">
        <div class="layui-tab marg0" id="larry-tab" lay-filter="bodyTab">
            <! -- 选项卡-->
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="layui-icon layui-icon-home"></i></li>
            </ul>
            <div class="larry-title-box" style="height: 41px;" >
                <div class="go-left key-press pressKey" id="titleLeft" title="滚动至最右侧"><i class="layui-icon layadmin-tabs-control layui-icon-prev"></i> </div>
                <div class="title-right" id="titleRbox">
                    <div class="go-right key-press pressKey" id="titleRight" title="滚动至最左侧"><i class="layui-icon layadmin-tabs-control layui-icon-next"></i></div>
                </div>
            </div>
            <div class="layui-tab-content clildFrame" style="height:793px;">
                <div class="layui-tab-item layui-show layui-anim layui-anim-upbit" >
                    <iframe src="${ctx}/home.do" data-id="0" name="ifr_0" id="ifr_0"></iframe>
                </div>
                <div style="height: 10px;"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>