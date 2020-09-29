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
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/fontcss.css">

    <script src="${ctx}/static/layui_v2/layui.js"></script>
    <style type="text/css">
        .layui-form-item .layui-form-label{
            width: 150px;
        }
        .layui-form-item .layui-details{
            width: 190px;
        }
        .layui-form-item {
            margin-bottom: 0px;
        }
        .layui-input{
            height: 38px;
        }
    </style>
</head>
<body class="childrenBody" style="font-size: 12px;margin: 10px 10px 0;">
<%--<blockquote class="layui-elem-quote layui-quote-nm"--%>
<%--style="border-radius:0.25em;color: #31708f;background-color: #d9edf7;border-width:1px; padding:6px; border-color:#bce8f1;">--%>
<%--温馨提示:1.菜单类型为菜单时父级菜单可以为空;2.菜单类型为按钮时父级菜单不能为空;3.父级菜单选中时，资源路径不能为空--%>
<%--</blockquote>--%>
<form class="layui-form layui-form-pane">
    <input id="resId" name="id" type="hidden" value="${menu.id}">
    <input name="isLeaf" type="hidden" value="${menu.isLeaf}">
    <input name="displayOrder" type="hidden" value="${menu.displayOrder}">
    <input id="optimistic" name="optimistic" type="hidden" value="${menu.optimistic}">
    <input id="pageFlag"  type="hidden" value="${pageFlag}">
    <input id="resParentCount"  type="hidden" value="${resParentCount}">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单名称</label>
            <div class="layui-input-inline">
                <input type="text" id="resName" name="name" class="layui-input" maxlength="20" value="${menu.name}" lay-verify="required|name" placeholder="请输入菜单名称">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">菜单类型</label>
            <div class="layui-input-inline ">
                <select id="resType" name="resType" lay-filter="resTypeFilter" lay-verify="required">
                    <option value="">请选择</option>
                    <option value="0">菜单</option>
                    <option value="1">按钮</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单级别</label>
            <div class="layui-input-inline">
                <select id="resLevel" name="levels" lay-filter="resLevelFilter" lay-verify="required">
                    <option value="">请选择</option>
                    <option value="1">一级菜单</option>
                    <option value="2">二级菜单</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">父级菜单</label>
            <div class="layui-input-inline">
                <select id="resParentId" name="parentId" lay-verify="resParentId">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单路径</label>
            <div class="layui-input-inline">
                <input type="text" id="resLinkAddress" name="url" class="layui-input" maxlength="100" lay-verify="resLinkAddress"  value="${menu.url}" placeholder="请输入菜单路径"/>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">授权标识</label>
            <div class="layui-input-inline">
                <input type="text" id="resDisplayOrder" name="label" class="layui-input" lay-verify="required|resDisplayOrder" maxlength="30" value="${menu.label}" placeholder="如：user:config"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">菜单图标</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="resImage" name="icon" lay-verify="required" value="${menu.icon}" disabled>
            </div>
            <div class="layui-form-mid layui-word-aux">
                <a class="layui-btn layui-btn-xs select_img" data-id="" title="选择图标"><i class="layui-icon layui-icon-picture"></i></a>'
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">菜单状态</label>
            <div class="layui-input-inline" style="border: 1px solid #e6e6e6;background-color: #fff;height: 36px;">
                <c:if test="${pageFlag == 'addPage' }">
                    <input type="radio" name="status" value="TRUE" title="有效" checked disabled>
                    <input type="radio" name="status" value="FALSE" title="失效" disabled>
                </c:if>
                <c:if test="${pageFlag == 'updatePage' and  menu.status == 'TRUE' }">
                    <input type="radio" name="status" value="TRUE" title="有效"   <c:if test="${menu.status == 'TRUE' }">checked</c:if>/>
                    <input type="radio" name="status" value="FALSE" title="失效"   <c:if test="${menu.status == 'FALSE' }">checked</c:if>/>
                </c:if>
                <c:if test="${pageFlag == 'updatePage' and  menu.status == 'FALSE' }">
                    <input type="radio" name="status" value="TRUE" title="有效"   <c:if test="${menu.status == 'TRUE' }">checked</c:if>/>
                    <input type="radio" name="status" value="FALSE" title="失效"   <c:if test="${menu.status == 'FALSE' }">checked</c:if>/>
                </c:if>
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;margin-top: 18px;">
        <button class="layui-btn" lay-submit="" lay-filter="saveRes">保存</button>
        <button type="layui-btn" id="cancle" class="layui-btn layui-btn-primary">取消</button>
    </div>
</form>
<script type="text/javascript">
    layui.config({
        base : "${ctx}/static/js/"
    }).use(['form','layer','jquery','common'],function(){
        var $ = layui.$,
                form = layui.form,
                common = layui.common,
                layer = parent.layer === undefined ? layui.layer : parent.layer;

        /*初始化*/
        resInit();

        function resInit(){
            var pageFlag = $("#pageFlag").val();
            //新增
            if(pageFlag == "addPage"){
                //默认菜单级别不可点击
                $("#resLevel").attr("disabled","disabled");
            }
            //更新标识
            if(pageFlag == "updatePage"){
                resTypeVal = ${menu.resType}
                resLevelVal = ${menu.levels}
                resParentIdVal = ${menu.parentId}
                resParentCount = ${resParentCount}

                //菜单类型选中，不能点击
                $("#resType option[value='"+resTypeVal+"']").prop("selected","selected");
                $("#resType").attr("disabled","disabled");

                //菜单级别选中
                $("#resLevel option[value='"+resLevelVal+"']").prop("selected","selected");
                if(resTypeVal == 1 || (resTypeVal == 0 && resParentCount > 0)){
                    $("#resLevel").attr("disabled","disabled");

                }
                if(resLevelVal == 1){
                    $("#resLinkAddress").val('');
                    $("#resLinkAddress").attr("disabled","disabled");
                }
                //加载父级菜单
                loadParentMenu();
                $("#resParentId option[value='"+resParentIdVal+"']").prop("selected","selected");
            }
            form.render('select');
        }

        /**监听菜单类型选择*/
        form.on('select(resTypeFilter)', function(data){
            //如果菜单类型为按钮, 菜单级别选中三级菜单,并禁用选择
            if(data.value == 1){
                $("#resLevel option[value='2']").prop("selected","selected");
                $("#resLevel").attr("disabled","disabled");
            }else{
                $('#resParentId option').not(":first").remove();
                $("#resLevel").removeAttr("disabled");
                $("#resLevel option:first").prop("selected", 'selected');
                $("#resLinkAddress").removeAttr("disabled","disabled");
            }
            form.render('select');
            //加载父级菜单
            loadParentMenu();
        });

        /**监听菜单级别选择*/
        form.on('select(resLevelFilter)', function(data){
            if(data.value == 1){
                $("#resLinkAddress").val('');
                $("#resLinkAddress").attr("disabled","disabled");
            }else{
                $("#resLinkAddress").removeAttr("disabled","disabled");
            }
            //加载父级菜单
            loadParentMenu();
        });

        /**加载父级菜单*/
        function loadParentMenu(){
            var resType =  $("#resType option:selected").val();  //类型
            var resLevel =  $("#resLevel option:selected").val();  //级别

            if(resType != "" && resLevel != "" ){
                //1级菜单、父级菜单为空
                if(resLevel == 1){
                    $('#resParentId option').not(":first").remove();
                    form.render('select');
                    return;
                }
                //加载父级菜单
                $.ajax({
                    url : '${ctx}/menu/findParentMenu.do',
                    type : 'post',
                    async: false,
                    data : {
                        resType:resType,
                        levels:resLevel
                    },
                    success : function(data) {
                        if(JSON.parse(data).code == "0000"){
                            $('#resParentId option').not(":first").remove();
                            $(JSON.parse(data).data).each(function(index,item){
                                $("#resParentId").append(
                                        '<option value="'+item.id+'">'+item.name+'</option>'
                                );
                            });
                        }
                    }
                });
                form.render('select');
            }
        }

        /**选择图标*/
        $(".select_img").click(function(){
            var url = "${ctx}/menu/iconImage.do";
            common.cmsLayOpen('选择图标',url,'485px','330px');
        });

        /**表单验证*/
        form.verify({
            resName: function(value, item){
                //验证菜单名称
                if(!new RegExp("^[0-9a-zA-Z\u4e00-\u9fa5]+$").test(value)){
                    return '菜单名称只能为中文数字或者字母';
                }

            },
            resParentId:function(value,item){
                //验证父级菜单
                var  resLevel = $("#resLevel").val();
                if((resLevel == 2 || resLevel == 3)&& value == ''){
                    return '父级菜单不能为空';
                }
            },
            resLinkAddress: function(value, item){
                //验证菜单路径
                var  resLevel = $("#resLevel").val();
                var resParentCount = $("#resParentCount").val();

                if((resLevel == 2 || resLevel == 3)&& value == '' && resParentCount <0){
                    return '菜单路径不能为空';
                }
                if(value != '' && !new RegExp("^[a-zA-Z_/.-]+$").test(value)){
                    return '菜单路径只能为英文下划线斜杠和点';
                }

            },
            resDisplayOrder: function(value, item){
                //验证排序
                if(value == ''){
                    return '授权标识不能为空';
                }

            }
        });

        /**保存菜单资源信息*/
        form.on("submit(saveRes)",function(data){
            $.ajax({
                url : '${ctx}/menu/ajaxSaveAndEditMenu.do',
                type : 'post',
                async: false,
                data : data.field,
                success : function(data) {
                    if(JSON.parse(data).code == "0000"){
                        location.reload();
                        common.cmsLaySucMsg("保存成功");
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                        parent.location.reload();
                    }else{
                        common.cmsLayErrorMsg(JSON.parse(data).msg);
                    }
                },error:function(data){
                    layer.close(index);
                }
            });
            return false;
        });


        /**取消*/
        $("#cancle").click(function(){
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
        });

    });

</script>
</body>
</html>