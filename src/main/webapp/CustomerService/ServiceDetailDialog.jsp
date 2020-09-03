<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/29
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>服务信息</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table class="tableEdit" style="width:500px;">
    <thead>
    <tr>
        <td colspan="4">服务信息</td>
    </tr>
    </thead>
    <tr>
        <th>客户姓名：</th>
        <td>${cstService.svrCustName}</td>
        <th>服务类型：</th>
        <td>${cstService.svrType}</td>
    </tr>
    <tr>
        <th>服务概要：</th>
        <td colspan="3">${cstService.svrTitle}</td>
    </tr>
    <tr>
        <th>详细信息：</th>
        <td colspan="3">${cstService.svrRequest}</td>
    </tr>
    <tr>
        <th>创建人：</th>
        <td>${cstService.svrCreateBy}</td>
        <th>创建时间：</th>
        <td><fmt:formatDate value="${cstService.svrCreateDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
</table>
<table class="tableEdit" style="width:500px;">
    <thead>
    <tr>
        <td colspan="4">服务处理</td>
    </tr>
    </thead>
    <tr>
        <th>服务处理：</th>
        <td colspan="3">${cstService.svrDeal}</td>
    </tr>
    <tr>
        <th>处理人：</th>
        <td>${cstService.svrDealBy}</td>
        <th>处理时间：</th>
        <td><fmt:formatDate value="${cstService.svrDealDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
</table>
<table class="tableEdit" style="width:500px;">
    <thead>
    <tr>
        <td colspan="4">服务反馈</td>
    </tr>
    </thead>
    <tr>
        <th>满意度：</th>
        <c:if test="${cstService.svrSatisfy==1}">
            <td>
                ☆
            </td>
        </c:if>
        <c:if test="${cstService.svrSatisfy==2}">
            <td>
                ☆☆
            </td>
        </c:if><c:if test="${cstService.svrSatisfy==3}">
            <td>
                ☆☆☆
            </td>
        </c:if>
        <c:if test="${cstService.svrSatisfy==4}">
            <td>
                ☆☆☆☆
            </td>
        </c:if>
        <c:if test="${cstService.svrSatisfy==5}">
            <td>
                ☆☆☆☆☆
            </td>
        </c:if>
    </tr>
    <tr>
        <th>处理结果：</th>
        <td>${cstService.svrResult}</td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <a href="/CustomerService/ServiceDetail.jsp"><input type="button" value="返回"/></a>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>
