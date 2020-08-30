<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/25
  Time: 17:35
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
                url:"http://localhost:8080/findSalChanceAll",
                type: "get",//提交方式
                async: true,//是否异步发送
                data:{
                    "pageNum":index,
                    "name":$("#name").val(),
                    "linkman":$("#linkman").val(),
                    "title":$("#title").val()
                },
                success:function(SalChanceJson){
                    nav(SalChanceJson);
                }
            });

        }

        /**
         * 显示的数据
         */
        function nav(SalChanceJson) {
            //清空表格（除第一行数据外）
            $("#dataTable").find("tr:not(:first)").remove();
            //总记录数
            $("#total").html(SalChanceJson.total);
            //当前页
            $("#pageNum").html(SalChanceJson.pageNum);
            //总页数
            $("#totalNum").html(SalChanceJson.pages);
            $pageNum = SalChanceJson.pageNum;
            //是否有下一页
            $hasNextPage = SalChanceJson.hasNextPage;
            //上一页
            $perPage = SalChanceJson.prePage;
            //下一页
            $nextPage = SalChanceJson.nextPage;
            //最后一页（总页数）
            $lastPage = SalChanceJson.pages;

            /**
             * 循环读取数据，并将数据写入表格
             */
            $(SalChanceJson.list).each(function(index,salchance){
                //创建一行
                var $tr=$("<tr></tr>");
                //创建一列
                var $chcCustNametd=$("<td></td>");
                var $chcTitletd=$("<td></td>");
                var $chcLinkmantd=$("<td></td>");
                var $chcTeltd=$("<td></td>");
                var $chcCreateDatetd=$("<td></td>");
                var $cz=$("<td></td>");

                //给td赋值
                $chcCustNametd.text(salchance.chcCustName);
                $chcTitletd.text(salchance.chcTitle);
                $chcLinkmantd.text(salchance.chcLinkman);
                $chcTeltd.text(salchance.chcTel);
                $chcCreateDatetd.text(salchance.chcCreateDate);
                $cz.html("<a href='/findSaleChanceById?id="+salchance.chcId+"'><img title=\"分配\" src=\"../images/bt_linkman.gif\" style=\"border:0px;width:16px;height:16px\" /></a>&nbsp;&nbsp;\n" +
                    "<a href='/findSaleChanceByIds?id="+salchance.chcId+"'><img title=\"修改\" src=\"../images/edt.gif\" style=\"border:0px\"/></a>&nbsp;&nbsp;<a href='/delSaleChance?id="+salchance.chcId+"'><img title=\"删除\" src=\"../images/del.gif\" /></a>")

                //把列添加到行
                $tr.append($chcCustNametd);
                $tr.append($chcTitletd);
                $tr.append($chcLinkmantd);
                $tr.append($chcTeltd);
                $tr.append($chcCreateDatetd);
                $tr.append($cz);

                //把行添加到table
                $("#dataTable").append($tr);
            });
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>销售机会管理</span><br />
    创建和维护销售机会
</div>
<table class="tableEdit">
    <tr>
        <th>客户名称：</th>
        <td><input type="text" id="name"/></td>
        <th>联系人：</th>
        <td><input type="text" id="linkman"/></td>
        <th>概要：</th>
        <td><input type="text" id="title"/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="8"><input type="button" value="查询" onclick="global(1)"/></td>
    </tr>
    </tfoot>
</table>
<br />
<div id="dataDiv">
    <div id="headDiv">
        <img src="../images/22.gif" />&nbsp;&nbsp;<a href="/Sale/AddSal.jsp">新建</a>
    </div>
    <table class="dataTable" id="dataTable">
        <tr>
            <th>客户名称</th>
            <th>概要</th>
            <th>联系人</th>
            <th>联系电话</th>
            <th>创建时间</th>
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