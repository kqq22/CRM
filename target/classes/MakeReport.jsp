<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/30
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户构成分析</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="desDiv">
    <span>客户构成分析</span><br/>
    根据客户的相关信息显示
</div>
<form method="post" action="/findCstCustomerMakeReport">
<table class="tableEdit">
    <tr>
        <th>
            报表方式：
        </th>
        <td>
            <select name="contribute">
                <option value="客户等级" selected="selected">按客户等级</option>
                <option value="信用度" >按信用度</option>
                <option value="满意度" >按满意度</option>
            </select>
        </td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="2">
            <input type="submit" value="查询" />
        </td>
    </tr>
    </tfoot>
</table>
</form>
<table class="dataTable">
    <tr>
        <th>序号</th>
        <th>等级</th>
        <th>客户数量</th>
    </tr>
    <c:forEach items="${cstCustomerList}" var="cstcustomer" varStatus="status">
    <tr>
        <td>${status.index+1}</td>
        <td>
            <c:if test="${cstcustomer.dictItem!=''}">${cstcustomer.dictItem}</c:if>
            <c:if test="${cstcustomer.custCredit!=''}">
                <c:if test="${cstcustomer.custCredit==1}">☆</c:if>
                <c:if test="${cstcustomer.custCredit==2}">☆☆</c:if>
                <c:if test="${cstcustomer.custCredit==3}">☆☆☆</c:if>
                <c:if test="${cstcustomer.custCredit==4}">☆☆☆☆</c:if>
                <c:if test="${cstcustomer.custCredit==5}">☆☆☆☆☆</c:if>
            </c:if>
            <c:if test="${cstcustomer.custSatisfy!=''}">
                <c:if test="${cstcustomer.custSatisfy==1}">☆</c:if>
                <c:if test="${cstcustomer.custSatisfy==2}">☆☆</c:if>
                <c:if test="${cstcustomer.custSatisfy==3}">☆☆☆</c:if>
                <c:if test="${cstcustomer.custSatisfy==4}">☆☆☆☆</c:if>
                <c:if test="${cstcustomer.custSatisfy==5}">☆☆☆☆☆</c:if>
            </c:if>
        </td>
        <td>${cstcustomer.count}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>