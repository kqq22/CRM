<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/25
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        $(function () {
            //当前日期
            var date = new Date();
            this.year = date.getFullYear();
            this.month = date.getMonth() + 1;
            this.date = date.getDate();
            this.month = this.month < 10 ? ('0' + this.month) : this.month;
            this.date = this.date < 10 ? ('0' + this.date) : this.date;
            var currentTime =  this.year + "-" + this.month + "-" + this.date;
            $("#currentTime").val(currentTime);
        });
    </script>
</head>
<body>
<form method="post" action="/addSalChance">
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">销售机会添加</td>
    </tr>
    </thead>
    <tr>
        <th>客户名称：</th>
        <td>
            <input type="text" name="chcCustName"/>
        </td>
        <th>机会来源</th>
        <td><input type="text" name="chcSource"/></td>
    </tr>
    <tr>
        <th>成功几率：</th>
        <td>
            <input type="text" name="chcRate"/>
        </td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>联系人：</th>
        <td><input type="text" name="chcLinkman"/></td>
        <th>联系电话：</th>
        <td><input type="text" name="chcTel"/></td>
    </tr>
    <tr>
        <th>概要：</th>
        <td colspan="3"><input type="text" style="width:500px" name="chcTitle"/></td>
    </tr>
    <tr>
        <th>机会描述：</th>
        <td colspan="3"><textarea style="width:500px" name="chcDesc"></textarea></td>
    </tr>
    <tr>
        <th>创建人</th>
        <td><input type="text" readonly="readonly" id="username" value="<%=session.getAttribute("uname") %>" name="chcCreateBy"/></td>
        <th>创建时间</th>
        <td ><input type="text" readonly="readonly" id="currentTime" name="createdate"/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定并返回" />&nbsp;&nbsp;
            <input type="button" value="确定并继续" />&nbsp;&nbsp;
            <input type="button" value="返回" />
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>