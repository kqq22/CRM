<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/29
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>确认流失</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="/updateCstLosts">
<table class="tableEdit">
    <!-- 获取数据，传到Controller类 -->
    <input name="lstId" value="${cstLost.lstId}" style="display: none;">
<%--    <input name="lstCustNo" value="${cstLost.lstCustNo}" style="display: none;">--%>
<%--    <input name="lstCustName" value="${cstLost.lstCustName}" style="display: none;">--%>
<%--    <input name="lstCustManagerId" value="${cstLost.lstCustManagerId}" style="display: none;">--%>
<%--    <input name="lstCustManagerName" value="${cstLost.lstCustManagerName}" style="display: none;">--%>
<%--    <input name="lstLastOrderDate" value="${cstLost.lstLastOrderDate}" style="display: none;">--%>
<%--    <input name="lstDelay" value="${cstLost.lstDelay}" style="display: none;">--%>
<%--    <input name="lstStatus" value="${cstLost.lstStatus}" style="display: none;">--%>

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
        <td colspan="3"><textarea name="lstReason"></textarea></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <a href="/Customer/LostsPage.jsp"><input type="button" value="返回" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>
