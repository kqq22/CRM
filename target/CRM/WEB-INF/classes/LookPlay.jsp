<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/27
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">客户信息</td>
    </tr>
    </thead>
    <tr>
        <th>客户名称：</th>
        <td>${salChance.chcCustName}</td>
        <th>机会来源</th>
        <td>${salChance.chcSource}</td>
    </tr>
    <tr>
        <th>成功几率：</th>
        <td>${salChance.chcRate}</td>
        <th>开发状态</th>
        <c:if test="${salChance.chcStatus==3}">
            <td>开发成功</td>
        </c:if>
        <c:if test="${salChance.chcStatus==4}">
            <td>开发失败</td>
        </c:if>
    </tr>
    <tr>
        <th>联系人：</th>
        <td>${salChance.chcLinkman}</td>
        <th>联系电话：</th>
        <td>${salChance.chcTel}</td>
    </tr>
    <tr>
        <th>概要：</th>
        <td colspan="3">${salChance.chcTitle}</td>
    </tr>
    <tr style="height:100px">
        <th>机会描述：</th>
        <td colspan="3" style="vertical-align:top">${salChance.chcDesc}</td>
    </tr>
    <tr>
        <th>创建人：</th>
        <td>${sessionScope.uname}</td>
        <th>创建时间：</th>
        <td id="createDate"><fmt:formatDate value='${salChance.chcCreateDate}' pattern='yyyy-MM-dd' /></td>
    </tr>
    <tr>
        <th>指派人：</th>
        <td>${salChance.chcDueTo}</td>
        <th>指派时间：</th>
        <td id="dueDate"><fmt:formatDate value='${salChance.chcDueDate}' pattern='yyyy-MM-dd' /></td>
    </tr>
</table>
<p>
</p>
<table class="dataTable">
    <tr>
        <th>日期</th>
        <th>计划</th>
        <th>执行效果</th>
    </tr>
<c:forEach items="${salPlanList}" var="salPlan" varStatus="statu">
    <tr>
        <td><fmt:formatDate value="${salPlan.plaDate}" pattern="yyyy-MM-dd" /></td>
        <td>${salPlan.plaTodo}</td>
        <td>${salPlan.plaResult}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
