<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/26
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        function showOk() {
            window.showModalDialog("SaleOk.htm?data=" + Math.random(), "参数", "dialogWidth:550px;dialogHeight:400px;help:no");
        }
        function showNo() {
            window.showModalDialog("SaleNo.htm?data=" + Math.random(), "参数", "dialogWidth:550px;dialogHeight:400px;help:no");
        }
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
            $.ajax({
                url:"http://localhost:8080/findSaleManagerAll",
                type: "get",//提交方式
                async: true,//是否异步发送
                data:{
                    "pageNum":index,
                    "name":$("#name").val(),
                    "linkman":$("#linkman").val(),
                    "title":$("#title").val(),
                    "status":$("#status").val()
                },
                success:function(SaleManagerJson){
                    nav(SaleManagerJson);
                }
            });

        }

        /**
         * 显示的数据
         */
        function nav(SaleManagerJson) {
            console.log(SaleManagerJson);
            //清空表格（除第一行数据外）
            $("#dataTable").find("tr:not(:first)").remove();
            //总记录数
            $("#total").html(SaleManagerJson.total);
            //当前页
            $("#pageNum").html(SaleManagerJson.pageNum);
            //总页数
            $("#totalNum").html(SaleManagerJson.pages);
            $pageNum = SaleManagerJson.pageNum;
            //是否有下一页
            $hasNextPage = SaleManagerJson.hasNextPage;
            //上一页
            $perPage = SaleManagerJson.prePage;
            //下一页
            $nextPage = SaleManagerJson.nextPage;
            //最后一页（总页数）
            $lastPage = SaleManagerJson.pages;

            /**
             * 循环读取数据，并将数据写入表格
             */
            $(SaleManagerJson.list).each(function(index,saleManager){
                //创建一行
                var $tr=$("<tr></tr>");
                //创建一列
                var $chcCustNametd=$("<td></td>");
                var $chcTitletd=$("<td></td>");
                var $chcLinkmantd=$("<td></td>");
                var $chcTeltd=$("<td></td>");
                var $chcCreateDatetd=$("<td></td>");
                var $chcStatustd=$("<td></td>");
                var $cz=$("<td></td>");

                //给td赋值
                $chcCustNametd.text(saleManager.chcCustName);
                $chcTitletd.text(saleManager.chcTitle);
                $chcLinkmantd.text(saleManager.chcLinkman);
                $chcTeltd.text(saleManager.chcTel);
                $chcCreateDatetd.text(saleManager.chcCreateDate);
                if(saleManager.chcStatus==2){
                    $chcStatustd.text("开发中");
                }else if (saleManager.chcStatus==3){
                    $chcStatustd.text("开发成功");
                }else if (saleManager.chcStatus==4){
                    $chcStatustd.text("开发终止");
                }
                if(saleManager.chcStatus==3||saleManager.chcStatus==4){
                    $cz.html("<a href='/findSaleChanceStatusById?id="+saleManager.chcId+"'><img title='查看' src='../images/bt_orders.gif' />\n</a>");
                }else {
                    $cz.html("<a href='/findSaleManagerById?id="+saleManager.chcId+"'><img title='制定计划' src='../images/bt_plan.gif' style='border:0px' /></a>" +
                        "<a href='/findSaleManagersyIds?id="+saleManager.chcId+"'><img title='执行计划' src='../images/bt_feedback.gif' style='border:0px' /></a> " +
                        "<a href='/updateSaleChanceStatus?chcId="+saleManager.chcId+"&chcStatus=3'><img title='开发成功' src='../images/bt_yes.gif' style='border:0px' /></a> " +
                        "<a href='/updateSaleChanceStatus?chcId="+saleManager.chcId+"&chcStatus=4'><img title='开发终止' src='../images/11.gif' style='border:0px' /></a>");
                }
                //把列添加到行
                $tr.append($chcCustNametd);
                $tr.append($chcTitletd);
                $tr.append($chcLinkmantd);
                $tr.append($chcTeltd);
                $tr.append($chcCreateDatetd);
                $tr.append($chcStatustd);
                $tr.append($cz);

                //把行添加到table
                $("#dataTable").append($tr);
            });
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>客户开发计划</span><br />
    制定客户开发计划和记录开发进度
</div>
<table class="tableEdit">
    <tr>
        <th>客户名称：</th>
        <td><input type="text" id="name" /></td>
        <th>联系人：</th>
        <td><input type="text" id="linkman"/></td>
    </tr>
    <tr>
        <th>概要：</th>
        <td><input type="text" id="title"/></td>
        <th>开发状态：</th>
        <td>
            <select id="status">
                <option selected="selected">全部</option>
                <option>开发中</option>
                <option>开发成功</option>
                <option>开发失败</option>
            </select>
        </td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="8"><input type="button" value="查询"  onclick="global(1)"/></td>
    </tr>
    </tfoot>
</table>
<br />
<div id="dataDiv">
    <div id="headDiv">
    </div>
    <table class="dataTable" id="dataTable">
        <tr>
            <th>客户名称</th>
            <th>概要</th>
            <th>联系人</th>
            <th>联系电话</th>
            <th>创建时间</th>
            <th>状态</th>
            <th>操作</th>
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
