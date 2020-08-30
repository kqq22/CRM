<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/29
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Style4.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        function showMsg() {
            window.showModalDialog("LostMsg.htm?data=" + Math.random(), "参数", "dialogWidth:550px;dialogHeight:400px;help:no");
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
            var statu = "";
            if($("#lstStatus").val()!=0){
                statu = $("#lstStatus").val();
            }
            $.ajax({
                url:"http://localhost:8080/findCstLostAll",
                type: "get",//提交方式
                async: true,//是否异步发送
                data:{
                    "pageNum":index,
                    "lstCustName":$("#lstCustName").val(),
                    "lstCustManagerName":$("#lstCustManagerName").val(),
                    "lstStatus":statu
                },
                success:function(CstLostJson){
                    nav(CstLostJson);
                }
            });

        }

        /**
         * 显示的数据
         */
        function nav(CstLostJson) {
            //清空表格（除第一行数据外）
            $("#dataTable").find("tr:not(:first)").remove();
            //总记录数
            $("#total").html(CstLostJson.total);
            //当前页
            $("#pageNum").html(CstLostJson.pageNum);
            //总页数
            $("#totalNum").html(CstLostJson.pages);
            $pageNum = CstLostJson.pageNum;
            //是否有下一页
            $hasNextPage = CstLostJson.hasNextPage;
            //上一页
            $perPage = CstLostJson.prePage;
            //下一页
            $nextPage = CstLostJson.nextPage;
            //最后一页（总页数）
            $lastPage = CstLostJson.pages;

            /**
             * 循环读取数据，并将数据写入表格
             */
            $(CstLostJson.list).each(function(index,cstlost){
                //创建一行
                var $tr=$("<tr></tr>");
                //创建一列
                var $lstCustNotd=$("<td></td>");
                var $lstCustNametd=$("<td></td>");
                var $lstCustManagerNametd=$("<td></td>");
                var $lstLastOrderDatetd=$("<td></td>");
                var $lstLostDatetd=$("<td></td>");
                var $lstStatustd=$("<td></td>");
                var $cz=$("<td></td>");

                //给td赋值
                $lstCustNotd.text(cstlost.lstCustNo);
                $lstCustNametd.text(cstlost.lstCustName);
                $lstCustManagerNametd.text(cstlost.lstCustManagerName);
                $lstLastOrderDatetd.text(cstlost.lstLastOrderDate);
                $lstLostDatetd.text(cstlost.lstLostDate);

                if(cstlost.lstStatus==1){
                    $lstStatustd.text("预警");
                    $cz.html("<a href='/findCstLostById?id="+cstlost.lstId+"'><img src='../images/bt_relay.gif' title='暂缓流失' style='border:0px' /></a>\n" +
                        "<a href='/findCstLostByIds?id="+cstlost.lstId+"'><img src='../images/bt_confirm.gif' title='确认流失' style='border:0px' /></a>");
                }else if (cstlost.lstStatus==2){
                    $lstStatustd.text("暂缓流失");
                    $cz.html("<a href='/findCstLostById?id="+cstlost.lstId+"'><img src='../images/bt_relay.gif' title='暂缓流失' style='border:0px' /></a>\n" +
                        "<a href='/findCstLostByIds?id="+cstlost.lstId+"'><img src='../images/bt_confirm.gif' title='确认流失' style='border:0px' /></a>");
                }else {
                    $cz.html("<a href='/findCstLostByIdLook?id="+cstlost.lstId+"'><img src='../images/bt_plan.gif' title='查看' style='border:0px' /></a>");
                    $lstStatustd.text("确认流失");
                }

                //把列添加到行
                $tr.append($lstCustNotd);
                $tr.append($lstCustNametd);
                $tr.append($lstCustManagerNametd);
                $tr.append($lstLastOrderDatetd);
                $tr.append($lstLostDatetd);
                $tr.append($lstStatustd);
                $tr.append($cz);

                //把行添加到table
                $("#dataTable").append($tr);
            });
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>客户流失管理</span><br />
    显示即将流失的客户信息，并制定挽救措施
</div>
<table class="tableEdit">
    <tr>
        <th>
            客户名称：
        </th>
        <td>
            <input type="text" id="lstCustName"/>
        </td>
        <th>
            客户经理：
        </th>
        <td>
            <input type="text" id="lstCustManagerName"/>
        </td>
        <th>
            状态：
        </th>
        <td>
            <select id="lstStatus">
                <option value="0" selected="selected">全部</option>
                <option value="1">预警</option>
                <option value="2">暂缓流失</option>
                <option value="3">确认流失</option>
            </select>
        </td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="8">
            <input type="button" value="查询" onclick="global(1)"/>
        </td>
    </tr>
    </tfoot>
</table>
<div id="dataDiv">
     <table class="dataTable" id="dataTable">
         <tr>
             <th>
                 客户编号
             </th>
             <th>
                 客户名称
             </th>
             <th>
                 客户经理
             </th>
             <th>
                 最后下单时间
             </th>
             <th>
                 确认流失时间
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