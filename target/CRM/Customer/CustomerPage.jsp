<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/27
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户信息管理</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style4.css" rel="stylesheet" type="text/css" />
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
                url:"http://localhost:8080/getCstCustomerAll",
                type: "get",//提交方式
                async: true,//是否异步发送
                data:{
                    "pageNum":index,
                    "custNo":$("#custNo").val(),
                    "custName":$("#custName").val(),
                    "custRegion":$("#custRegion").val(),
                    "custManagerName":$("#custManagerName").val(),
                    "custLevel":$("#custLevel").val()
                },
                success:function(CstCustomerJson){
                    nav(CstCustomerJson);
                }
            });
        }

        /**
         * 显示的数据
         */
        function nav(CstCustomerJson) {
            //清空表格（除第一行数据外）
            $("#dataTable").find("tr:not(:first)").remove();
            //总记录数
            $("#total").html(CstCustomerJson.total);
            //当前页
            $("#pageNum").html(CstCustomerJson.pageNum);
            //总页数
            $("#totalNum").html(CstCustomerJson.pages);
            $pageNum = CstCustomerJson.pageNum;
            //是否有下一页
            $hasNextPage = CstCustomerJson.hasNextPage;
            //上一页
            $perPage = CstCustomerJson.prePage;
            //下一页
            $nextPage = CstCustomerJson.nextPage;
            //最后一页（总页数）
            $lastPage = CstCustomerJson.pages;

            /**
             * 循环读取数据，并将数据写入表格
             */
            $(CstCustomerJson.list).each(function(index,cstcustomer){
                //创建一行
                var $tr=$("<tr></tr>");
                //创建一列
                var $custNotd=$("<td></td>");
                var $custNametd=$("<td></td>");
                var $custRegiontd=$("<td></td>");
                var $custManagerNametd=$("<td></td>");
                var $custLeveltd=$("<td></td>");
                var $cz=$("<td></td>");

                //给td赋值
                $custNotd.text(cstcustomer.custNo);
                $custNametd.text(cstcustomer.custName);
                $custRegiontd.text(cstcustomer.custRegion);
                $custManagerNametd.text(cstcustomer.custManagerName)
                //判断客户等级
                if(cstcustomer.custLevel==1){
                    $custLeveltd.text("战略合作伙伴");
                }else if (cstcustomer.custLevel==2){
                    $custLeveltd.text("大客户");
                }else if (cstcustomer.custLevel==3){
                    $custLeveltd.text("合作伙伴");
                }else if (cstcustomer.custLevel==4){
                    $custLeveltd.text("普通客户");
                }
                //操作列
                $cz.html("<a href='/findCstCustomerById?no="+cstcustomer.custNo+"'><img src='../images/33.gif' title='编辑' style='border:0px' /></a>" +
                    "<a href='/findCstLinkmanByNo?no="+cstcustomer.custNo+"&name="+cstcustomer.custName+"'><img src='../images/bt_linkman.gif' title='联系人' style='border:0px;width:16px;height:16px' /></a> " +
                    "<a href='/findCstActivityAll?no="+cstcustomer.custNo+"&name="+cstcustomer.custName+"'><img src='../images/bt_acti.gif' title='交往记录' style='border:0px' /></a> " +
                    "<a href='/findOrdersAlls?cName="+cstcustomer.custName+"&cNo="+cstcustomer.custNo+"'><img src='../images/bt_orders.gif' title='历史订单' style='border:0px' /></a> " +
                    "<a href='/delCstCustomer?no="+cstcustomer.custNo+"'><img src='../images/bt_del.gif' title='删除' style='border:0px' /></a>");
                //把列添加到行
                $tr.append($custNotd);
                $tr.append($custNametd);
                $tr.append($custRegiontd);
                $tr.append($custManagerNametd);
                $tr.append($custLeveltd);
                $tr.append($cz);

                //把行添加到table
                $("#dataTable").append($tr);
            });
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>客户信息管理</span><br />
    维护客户信息，记录客户联系电话和交往记录
</div>
<table class="tableEdit">
    <tr>
        <th>
            客户名称：
        </th>
        <td>
            <input type="text" id="custName"/>
        </td>
        <th>
            客户编号：
        </th>
        <td>
            <input type="text" id="custNo"/>
        </td>
        <th>
            地区：
        </th>
        <td>
            <select id="custRegion">
                <option selected="selected">全部</option>
                <option>华北</option>
                <option>华南</option>
                <option>中南</option>
                <option>西北</option>
            </select>
        </td>
    </tr>
    <tr>
        <th>
            客户经理：
        </th>
        <td>
            <input type="text" id="custManagerName"/>
        </td>
        <th>
            客户等级：
        </th>
        <td>
            <select id="custLevel">
                <option value="0" selected="selected">全部</option>
                <option value="1">战略合作伙伴</option>
                <option value="2">合作伙伴</option>
                <option value="3">大客户</option>
                <option value="4">普通客户</option>
            </select>
        </td>
        <th></th><td></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="8">
            <input type="button" value="查询" onclick="global(1)"/>
        </td>
    </tr>
    </tfoot>
</table>
<br />
<table class="dataTable" id="dataTable">
    <tr>
        <th>
            客户编号
        </th>
        <th>
            客户名称
        </th>
        <th>
            地区
        </th>
        <th>
            客户经理
        </th>
        <th>
            客户等级
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
</body>
</html>
