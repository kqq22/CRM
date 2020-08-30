<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#CustNo").text($("#atvCustNo").val());
            $("#CusName").text($("#atvCustName").val());
        });
        function select() {
            var atvId = $("#atvId").val();
            location.href = "/findCstActivityByAdd?atvId="+atvId;
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>客户交往记录</span><br />
    记录与客户之间的交往信息
</div>
<table class="tableEdit">
    <tr>
        <th>
            客户编号：
        </th>
        <td id="CustNo"></td>
        <th>
            客户名称：
        </th>
        <td id="CusName"></td>
    </tr>
</table>
<div id="dataDiv">
    <div id="headDiv">
        <img src="../images/22.gif" />&nbsp;&nbsp;<a href="#" onclick="select()">新建</a>
    </div>
    <table class="dataTable">
        <tr>
            <th>
                时间
            </th>
            <th>
                地点
            </th>
            <th>
                概要
            </th>
            <th>
                详细信息
            </th>
            <th>
                操作
            </th>
        </tr>
        <c:forEach items="${cstActivityList}" var="activity" varStatus="statu">
            <input value="${activity.atvId}" id="atvId" style="display: none;"/>
            <input value="${activity.atvCustNo}" id="atvCustNo" style="display: none;"/>
            <input value="${activity.atvCustName}" id="atvCustName" style="display: none;"/>
        <tr>
            <td><fmt:formatDate value="${activity.atvDate}" pattern="yyyy-MM-dd" /></td>
            <td>${activity.atvPlace}</td>
            <td>${activity.atvTitle}</td>
            <td>${activity.atvDesc}</td>
            <td>
                <a href='/findCstActivityById?id=${activity.atvId}'><img title="编辑" src="../images/33.gif" style="border:0px"/></a>&nbsp;
                <a href='/delActivity?id=${activity.atvId}&atvCustNo=${activity.atvCustNo}'><img title="删除" src="../images/11.gif" /></a>&nbsp;
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>