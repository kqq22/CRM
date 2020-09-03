<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/29
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>确认流失</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">确认流失</td>
    </tr>
    </thead>
    <tr>
        <th>客户编号：</th>
        <td>${cstLost.lstCustNo}</td>
        <th>客户名称：</th>
        <td>${cstLost.lstCustName}</td>
    </tr>
    <tr>
        <th>客户经理：</th>
        <td>${cstLost.lstCustManagerName}</td>
        <th>最后下单时间：</th>
        <td><fmt:formatDate value="${cstLost.lstLastOrderDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
        <th>暂缓措施：</th>
        <td colspan="3">${cstLost.lstDelay}</td>
    </tr>
    <tr>
        <th>流失原因：</th>
        <td colspan="3">${cstLost.lstReason}</td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <a href="/Customer/LostsPage.jsp"><input type="button" value="返回" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>
