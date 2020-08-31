<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/30
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="desDiv">
    <span>客户流失分析</span><br />
    查询显示流失客户相关信息
</div>
<form method="post" action="/findCstLostByStatus">
<table class="tableEdit">
    <tr>
        <th>
            客户名称：
        </th>
        <td>
            <input name="lstCustName" type="text" />
        </td>
        <th>
            客户经理：
        </th>
        <td>
            <input name="lstCustManagerName" type="text" />
        </td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="查询" />
        </td>
    </tr>
    </tfoot>
</table>
</form>
<table class="dataTable">
    <tr>
        <th>序号</th>
        <th>年份</th>
        <th>客户</th>
        <th>客户经理</th>
        <th>流失原因</th>
    </tr>
    <c:forEach items="${cstLostList}" var="cstlost" varStatus="status">
    <tr>
        <td>${status.index+1}</td>
        <td><fmt:formatDate value="${cstlost.lstLostDate}" pattern="yyyy-MM-dd"/></td>
        <td>${cstlost.lstCustName}</td>
        <td>${cstlost.lstCustManagerName}</td>
        <td>${cstlost.lstReason}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
