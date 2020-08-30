<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/26
  Time: 12:40
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
            //显示当前时间
            var date = new Date();
            this.year = date.getFullYear();
            this.month = date.getMonth() + 1;
            this.date = date.getDate();
            this.month = this.month < 10 ? ('0' + this.month) : this.month;
            this.date = this.date < 10 ? ('0' + this.date) : this.date;
            var currentTime =  this.year + "-" + this.month + "-" + this.date;
            $("#nowdate").val(currentTime);
        });
    </script>
</head>
<body>
<form method="post" action="/updateSaleChance">
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">销售机会编辑</td>
    </tr>
    </thead>
    <tr>
        <th>客户名称：</th>
        <td>
            <input name="chcId" type="text" value="${salChance.chcId}" style="display: none;"/>
            <input name="chcCustName" type="text" value="${salChance.chcCustName}"/>
        </td>
        <th>机会来源</th>
        <td><input name="chcSource" type="text" value="${salChance.chcSource}"/></td>
    </tr>
    <tr>
        <th>成功几率：</th>
        <td>
            <input name="chcRate" type="text" value="${salChance.chcRate}"/>
        </td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>联系人：</th>
        <td><input name="chcLinkman" type="text" value="${salChance.chcLinkman}"/></td>
        <th>联系电话：</th>
        <td><input name="chcTel" type="text" value="${salChance.chcTel}"/></td>
    </tr>
    <tr>
        <th>概要：</th>
        <td colspan="3"><input name="chcTitle" type="text" style="width:500px" value="${salChance.chcTitle}"/></td>
    </tr>
    <tr>
        <th>机会描述：</th>
        <td colspan="3"><textarea name="chcDesc" style="width:500px">${salChance.chcDesc}</textarea></td>
    </tr>
    <tr>
        <th>创建人</th>
        <td><input type="text" readonly="readonly" value="${sessionScope.uname}" /></td>
        <th>创建时间</th>
        <td><input type="text" id="nowdate" readonly="readonly"/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <a href="/Sale/SaleChance.jsp"><input type="button" value="返回"  /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>
