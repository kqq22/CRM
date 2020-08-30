<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        /**
         * 窗体加载时调用查询方法初始化
         */
        $(function(){
            global(1);
            //要显示的页码
            var index = 0;
            $("#in").click(
                function () {
                    index = $("#inPage").val();
                    //调用异步请求函数
                    global(index);
                }
            );
            //上一页
            $("#pre").click(
                function () {
                    global($perPage);
                }
            );
            //下一页
            $("#next").click(
                function () {
                    //如果没有下一页了，不做操作
                    if($hasNextPage){
                        global($nextPage);
                    }else{
                        global($pageNum);
                    }
                }
            );
            //最后一页
            $("#last").click(
                function () {
                    global($lastPage);
                }
            );
        });

        /**
         * 异步请求
         */
        function global (index) {
            var custName = $("#cName").text();
            $.ajax({
                url:"http://localhost:8080/findOrdersAll",
                type: "get",//提交方式
                async: true,//是否异步发送
                data:{
                    "pageNum":index,
                    "custName":custName
                },
                success:function(OrdersJson){
                    nav(OrdersJson);
                }
            });

        }

        /**
         * 显示的数据
         */
        function nav(OrdersJson) {
            //清空表格（除第一行数据外）
            $("#dataTable").find("tr:not(:first)").remove();
            //总记录数
            $("#total").html(OrdersJson.total);
            //当前页
            $("#pageNum").html(OrdersJson.pageNum);
            //总页数
            $("#totalNum").html(OrdersJson.pages);
            $pageNum = OrdersJson.pageNum;
            //是否有下一页
            $hasNextPage = OrdersJson.hasNextPage;
            //上一页
            $perPage = OrdersJson.prePage;
            //下一页
            $nextPage = OrdersJson.nextPage;
            //最后一页（总页数）
            $lastPage = OrdersJson.pages;

            /**
             * 循环读取数据，并将数据写入表格
             */
            $(OrdersJson.list).each(function(index,orders){
                //创建一行
                var $tr=$("<tr></tr>");
                //创建一列
                var $odrIdtd=$("<td></td>");
                var $odrDatetd=$("<td></td>");
                var $odrAddrtd=$("<td></td>");
                var $odrStatustd=$("<td></td>");
                var $cz=$("<td></td>");

                //给td赋值
                $odrIdtd.text(orders.odrId);
                $odrDatetd.text(orders.odrDate);
                $odrAddrtd.text(orders.odrAddr);
                if(orders.odrStatus==1){
                    $odrStatustd.text("已回款");
                }else {
                    $odrStatustd.text("未回款");
                }
                $cz.html("<a href='/findOrdersLineAll?orderId="+orders.odrId+"'><img title='详细信息' src='../images/bt_detail.gif' /></a>");

                //把列添加到行
                $tr.append($odrIdtd);
                $tr.append($odrDatetd);
                $tr.append($odrAddrtd);
                $tr.append($odrStatustd);
                $tr.append($cz);

                //把行添加到table
                $("#dataTable").append($tr);
            });
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>客户历史订单</span><br />
    记录客户的历史订单
</div>
<table class="tableEdit" >
    <tr>
        <th>
            客户编号：
        </th>
        <td>${cNo}</td>
        <th>
            客户名称：
        </th>
        <td id="cName">${cName}</td>
    </tr>
</table>
<div id="dataDiv">
    <div id="headDiv">
        <img src="../images/22.gif" />&nbsp;&nbsp;<a href="ActivitysAdd.html">新建</a>
    </div>
    <table class="dataTable" id="dataTable">
        <tr>
            <th>
                订单编号
            </th>
            <th>
                日期
            </th>
            <th>
                送货地址
            </th>
            <th>
                状态
            </th>
            <th>
                操作
            </th>
        </tr>
    </table>
    <div id="pageDiv">
        <div id="leftPage">
            共有<span id="total"></span>条记录，当前第<span id="pageNum"></span>/<span id="totalNum"></span>页
        </div>
        <div id="rightPage">
            <input type="image" src="../images/first.gif" onclick="global(1)"/>
            <input type="image" src="../images/back.gif" id="pre"/>
            <input type="image" src="../images/next.gif" id="next"/>&nbsp;&nbsp;
            <input type="image" src="../images/last.gif" id="last"/>&nbsp;&nbsp;
            转到第<input type="text" size="1" id="inPage" />&nbsp;&nbsp;<input type="image" src="../images/go.gif" id="in"/>
        </div>
    </div>
</div>
</body>
</html>