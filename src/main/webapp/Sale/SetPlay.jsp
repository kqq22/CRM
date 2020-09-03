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
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        function update(chcid,plaid) {
            var plaTodo = document.getElementById("plaTodo").value;
            location.href="/updateSalPlan?chcid="+chcid+"&plaid="+plaid+"&plaTodo="+plaTodo;
        }

        function del(chcid,plaid) {
            if(confirm("确定要删除吗")){
                location.href="/delSalPlan?chcid="+chcid+"&plaid="+plaid;
            }
        }
    </script>
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
        <th></th>
        <td></td>
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
        <td><fmt:formatDate value="${salChance.chcCreateDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
        <th>指派人：</th>
        <td>${salChance.chcDueTo}</td>
        <th>指派时间：</th>
        <td><fmt:formatDate value="${salChance.chcDueDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
</table>
<p>
</p>

<table class="dataTable">
    <tr>
        <th>日期</th>
        <th>计划项</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${salPlanList}" var="salPlan" varStatus="statu">
    <tr>
        <td><fmt:formatDate value="${salPlan.plaDate}" pattern="yyyy-MM-dd" /></td>
        <td><input id="plaTodo" name="plaTodo" type="text" style="width:500px" value="${salPlan.plaTodo}" /></td>
        <td>
            <a onclick="update(${salChance.chcId},${salPlan.plaId})" href="#"><img title="保存" src="../images/edt.gif" style="border:0px" /></a>&nbsp;&nbsp;
            <img title="删除" src="../images/del.gif" onclick="del(${salChance.chcId},${salPlan.plaId})"/>
        </td>
    </tr>
    </c:forEach>
</table>
<p></p>
<form method="post" action="/addSalPlan">
    <input name="plaChcId" value="${salChance.chcId}" style="display: none;"/>
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">新增计划</td>
    </tr>
    </thead>
    <tr>
        <th>日期：</th>
        <td>
            <input type="text" name="planDate"/>
        </td>
        <th>计划项</th>
        <td><input type="text" style="width:500px" name="plaTodo"/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="添加" />&nbsp;&nbsp;
            <a href="/Sale/SaleManager.jsp"><input type="button" value="返回" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>
