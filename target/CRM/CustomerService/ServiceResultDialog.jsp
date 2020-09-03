<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/29
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="/updateCstServiceResult">
    <!-- 获取数据，传入到Controller类 -->
    <input style='display: none;' name='svrCustName' value="${cstService.svrCustName}"/>
    <input style='display: none;' name='svrTitle' value="${cstService.svrTitle}"/>
    <input style='display: none;' name='svrType' value="${cstService.svrType}"/>
    <input style='display: none;' name='createDate' value="<fmt:formatDate value="${cstService.svrCreateDate}" pattern="yyyy-MM-dd"/>"/>
    <input style='display: none;' name='svrId' value="${cstService.svrId}"/>
    <input style='display: none;' name='svrRequest' value="${cstService.svrRequest}"/>
    <input style='display: none;' name='svrCreateId' value="${cstService.svrCreateId} "/>
    <input style='display: none;' name='svrCreateBy' value="${cstService.svrCreateBy}"/>
    <input style='display: none;' name='svrDueId' value="${cstService.svrDueId}"/>
    <input style='display: none;' name='svrDueTo' value="${cstService.svrDueTo}"/>
    <input style='display: none;' name='DueDate' value="<fmt:formatDate value="${cstService.svrDueDate}" pattern="yyyy-MM-dd"/>"/>
    <input style='display: none;' name='svrDealId' value="${sessionScope.uid}"/>
    <input style='display: none;' name='svrDealBy' value="${sessionScope.uname}"/>
    <input style='display: none;' name='DealDate' value="<fmt:formatDate value="${cstService.svrDealDate}" pattern="yyyy-MM-dd"/>"/>
    <input style='display: none;' name='svrDeal' value="${cstService.svrDeal}"/>
    <input style='display: none;' name='svrDealId' value="${cstService.svrDealId}"/>
    <input style='display: none;' name='svrDealBy' value="${cstService.svrDealBy}"/>
<table class="tableEdit" style="width:500px;">
    <thead>
    <tr>
        <td colspan="4">服务信息（状态：${cstService.svrStatus}）</td>
    </tr>
    </thead>
    <tr>
        <th>客户名称：</th>
        <td>${cstService.svrCustName}</td>
        <th>服务类型：</th>
        <td>${cstService.svrType}</td>
    </tr>

    <tr>
        <th>服务概要：</th>
        <td colspan="3">${cstService.svrTitle} </td>
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
        <td>
            <select name="svrSatisfy">
                <option value="1">☆</option>
                <option value="2">☆☆</option>
                <option value="3">☆☆☆</option>
                <option value="4">☆☆☆☆</option>
                <option value="5">☆☆☆☆☆</option>
            </select>
        </td>
    </tr>
    <tr>
        <th>处理结果：</th>
        <td>
            <textarea name="svrResult"></textarea>
        </td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <input type="button" value="关闭" onclick="window.close()" />
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>
