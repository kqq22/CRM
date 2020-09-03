<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>客户联系人管理</title>
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#CustNo").text($("#lkmCustNo").val());
            $("#CusName").text($("#lkmCustName").val());
        });
        //新建
        function select() {
            var lkmId = $("#lkmId").val();
            location.href="/findCstLinkmanBylkmId?lkmId="+lkmId;
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>客户联系人管理</span><br />
    管理客户的相关联系人信息
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
        <img src="../images/22.gif" />&nbsp;&nbsp;<a href="#"  onclick="select()">新建</a>
    </div>
    <table class="dataTable">
        <tr>
            <th>
                姓名
            </th>
            <th>
                性别
            </th>
            <th>
                职位
            </th>
            <th>
                固定电话
            </th>
            <th>
                手机号码
            </th>
            <th>备注</th>
            <th>
                操作
            </th>
        </tr>
        <c:forEach items="${linkmanList}" var="linkman" varStatus="statu">
            <input value="${linkman.lkmCustNo}" id="lkmCustNo" style="display: none;"/>
            <input value="${linkman.lkmCustName}" id="lkmCustName" style="display: none;"/>
            <input value="${linkman.lkmId}" id="lkmId" style="display: none;"/>
        <tr>
            <td>
                ${linkman.lkmName}
            </td>
            <td>
                ${linkman.lkmSex}
            </td>
            <td>
                ${linkman.lkmPostion}
            </td>
            <td>
                ${linkman.lkmTel}
            </td>
            <td>
                ${linkman.lkmMobile}
            </td>
            <td>${linkman.lkmMemo}</td>
            <td>
                <a href="/findCstLinkmanByid?id=${linkman.lkmId}"><img title="编辑" src="../images/33.gif" style="border:0px"/></a>&nbsp;
                <a href="/delCstLinkman?lkmId=${linkman.lkmId}&lkmcustNo=${linkman.lkmCustNo}"><img title="删除" src="../images/11.gif"/></a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>