<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/25
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
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
        <td >
            <input type="text" readonly="readonly" value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/>"/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定并返回" />&nbsp;&nbsp;
            <a href="/Sale/SaleChance.jsp"><input type="button" value="返回" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>