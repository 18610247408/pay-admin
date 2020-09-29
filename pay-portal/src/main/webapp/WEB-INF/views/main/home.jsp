<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/comm/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>全鑫付运营平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="全鑫付运营平台">
    <meta name="description" content="">

    <link rel="shortcut icon" href="${ctx}/static/img/20180726141214.png">
    <link rel="stylesheet" href="${ctx}/static/layui_v2/css/layui.css">

    <link rel="stylesheet" href="${ctx}/static/css/global.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/personal.css" media="all">
    <link rel="stylesheet" href="${ctx}/static/css/main.css">
    <link rel="stylesheet" href="${ctx}/static/css/fontcss.css">
    <link rel="stylesheet" href="${ctx}/static/css/home.css">

    <script src="${ctx}/static/js/jquery-1.8.3.js"></script>
    <script src="${ctx}/static/js/jquery.leoweather.min.js"></script>

    <script type="text/javascript" src="${ctx}/static/layui_v2/layui.js"></script>
    <script type="text/javascript" src="${ctx}/static/echarts/echarts.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/static/echarts/macarons.js" charset="utf-8"></script>

</head>
<body>
<div class="layui-fluid">
    <div class="layui-low">
        <div class="layui-col-md12 home-head">
            <div class="layui-tab">
                <div class="layui-show" style="padding: 10px 15px;">
                    <div class="panel_box row">
                        <div class="panel col">
                            <a href="javascript:;" data-url="${ctx}/announcement/announcement_unread_list.do">
                                <div class="panel_icon">
                                    <i class="layui-icon" data-icon="&#xe611;">&#xe611;</i>
                                </div>
                                <div class="panel_word unreadAnnInfo">
                                    <span>0</span>
                                    <cite>未读公告</cite>
                                </div>
                            </a>
                        </div>
                        <div class="panel col">
                            <a href="javascript:;" data-url="">
                                <div class="panel_icon">
                                    <i class="layui-icon" data-icon="&#xe63a;">&#xe63a;</i>
                                </div>
                                <div class="panel_word newMessage">
                                    <span>0</span>
                                    <cite>未读消息</cite>
                                </div>
                            </a>
                        </div>
                        <div class="panel col">
                            <a href="javascript:;" data-url="">
                                <div class="panel_icon" style="background-color:#F7B824;">
                                    <i class="layui-icon" data-icon="&#xe613;">&#xe613;</i>
                                </div>
                                <div class="panel_word waitNews">
                                    <span>0</span>
                                    <cite>登录用户数</cite>
                                </div>
                            </a>
                        </div>
                        <div class="panel col max_panel">
                            <a href="javascript:;" data-url="">
                                <div class="panel_icon" style="background-color:#5FB878;">
                                    <i class="layui-icon" data-icon="&#xe629;">&#xe629;</i>
                                </div>
                                <div class="panel_word imgAll">
                                    <span>0</span>
                                    <cite>报表</cite>
                                </div>
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md8 home-left">
            <div class="layui-col-md12 home-table">
                <div class="table-content" id="table1"></div>
            </div>
            <div class="layui-col-md12 home-table">
                <div class="table-content" id="table2"></div>
            </div>
            <div class="layui-col-md12 home-table">
                <div class="table-content" id="table3"></div>
            </div>
        </div>
        <div class="layui-col-md4 home-right">
            <div class="layui-col-md12 home-rank">
                <p>掘金榜</p>
                <table align="center" class="rankTable" id="tank1">
                    <tr>
                        <th>排名</th>
                        <th>用户</th>
                        <th>新增下级数</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>44354</td>
                        <td>10</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>52654534</td>
                        <td>9</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>5342653</td>
                        <td>8</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>52534654</td>
                        <td>7</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>53262362</td>
                        <td>6</td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>43454353535</td>
                        <td>5</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>243565342</td>
                        <td>4</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>453477890</td>
                        <td>3</td>
                    </tr>
                    <tr>
                        <td>9</td>
                        <td>532564657</td>
                        <td>2</td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td>4535464</td>
                        <td>1</td>
                    </tr>
                </table>
            </div>
            <div class="layui-col-md12 home-rank">
                <p>奋进榜</p>
                <table align="center" class="rankTable" id="tank2">
                    <tr>
                        <th>排名</th>
                        <th>用户</th>
                        <th>新增商户数</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>44354</td>
                        <td>10</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>52654534</td>
                        <td>9</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>5342653</td>
                        <td>8</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>52534654</td>
                        <td>7</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>53262362</td>
                        <td>6</td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>43454353535</td>
                        <td>5</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>243565342</td>
                        <td>4</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>453477890</td>
                        <td>3</td>
                    </tr>
                    <tr>
                        <td>9</td>
                        <td>532564657</td>
                        <td>2</td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td>4535464</td>
                        <td>1</td>
                    </tr>
                </table>
            </div>
            <div class="layui-col-md12 home-rank">
                <p>富豪榜</p>
                <table align="center" class="rankTable" id="tank3">
                    <tr>
                        <th>排名</th>
                        <th>用户</th>
                        <th>收益</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>44354</td>
                        <td>10</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>52654534</td>
                        <td>9</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>5342653</td>
                        <td>8</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>52534654</td>
                        <td>7</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>53262362</td>
                        <td>6</td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>43454353535</td>
                        <td>5</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>243565342</td>
                        <td>4</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>453477890</td>
                        <td>3</td>
                    </tr>
                    <tr>
                        <td>9</td>
                        <td>532564657</td>
                        <td>2</td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td>4535464</td>
                        <td>1</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var $;
    layui.config({
        base: "${ctx}/static/js/"
    }).use(['form', 'jquery', 'layer'], function () {
        $ = layui.$;
        var common = layui.common;

        //首页卡片tab添加
        $(".panel a").on("click", function () {
            window.parent.addTab($(this));
        });

        //浏览器大小改变时重置大小
        window.onresize = function () {
            psLineChar.resize();

        };


        var table1 = echarts.init(document.getElementById('table1'),'macarons');
        var table2 = echarts.init(document.getElementById('table2'),'macarons');
        var table3 = echarts.init(document.getElementById('table3'),'macarons');

        function growUser() {
            $.ajax({
                url: '${ctx}/homeEChartsData.do',
                type: 'post',
                async: false,
                data: {type: 'growUser'},
                dataType: 'json',
                success: function (data) {
                    console.log("table1=" + data);
                    var option = {
                        title: {text: '每日新增推手'},
                        tooltip: {},
                        legend: {data: ['推手数']},
                        xAxis: {
                            type: 'category',
                            data: data.xAxisDatas
                        },
                        yAxis: {},
                        series: [{
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            },
                            name: '推手数', type: 'bar',
                            data: data.seriesDatas
                        }]
                    };
                    table1.setOption(option);
                }, error: function () {
                    common.cmsLayErrorMsg("请求异常");
                }
            });
        };
        growUser();

        function growPos() {
            $.ajax({
                url: '${ctx}/homeEChartsData.do',
                type: 'post',
                async: false,
                data: {type: 'growPos'},
                dataType: 'json',
                success: function (data) {
                    console.log("table2=" + data);
                    var option = {
                        title : {
                            text: '昨日新增机具',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data: data.xAxisDatas
                        },
                        series : [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:data.seriesDatas,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };
                    table2.setOption(option);
                }, error: function () {
                    common.cmsLayErrorMsg("请求异常");
                }
            });
        };
        growPos();

        function totalAmount() {
            $.ajax({
                url: '${ctx}/homeEChartsData.do',
                type: 'post',
                async: false,
                data: {type: 'transDay'},
                dataType: 'json',
                success: function (data) {
                    console.log("table3=" + data);
                    var option = {
                        title:{text: '每日交易量(单位:元)'},
                        legend: {data: ['交易量(单位:元)']},
                        xAxis: {
                            type: 'category',
                            data: data.xAxisDatas
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            },
                            data: data.seriesDatas,
                            type: 'line'
                        }]
                    };
                    table3.setOption(option);
                }, error: function () {
                    common.cmsLayErrorMsg("请求异常");
                }
            });
        };
        totalAmount();

        function loadTable(type,elementId){
            $.ajax({
                url: '${ctx}/homeRank.do',
                type: 'post',
                async: false,
                data: {type: type},
                dataType: 'json',
                success: function (data) {
                    for(var i = 0;i<data.length;i++){
                        $("#"+elementId).append('<tr>');
                        for(var j = 0;j<data[i].length;j++){
                            $("#"+elementId).append('<td>'+data[i][j]+'</td>');
                        }
                        $("#"+elementId).append('</tr>');
                    }
                }, error: function () {
                    common.cmsLayErrorMsg("请求异常");
                }
            });
        };

        loadTable('user','rank1');
        loadTable('customer','rank2');
        loadTable('reward','rank3');
    })
    ;

</script>
</body>
</html>