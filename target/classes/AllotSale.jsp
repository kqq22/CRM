<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/25
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="/updateSaleChanceDueTo">
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">销售机会分配</td>
    </tr>
    <input type="text" name="chcId" value="${salChance.chcId}" style="display: none;" />
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
        <td>
            <select name="userId">
                <c:forEach items="${sysUserList}" var="sysuser" varStatus="statu">
                    <option value="${sysuser.userId}">${sysuser.userName}</option>
                </c:forEach>
            </select>

        </td>
        <th>指派时间：</th>
        <td><fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <a href="/Sale/SaleChance.jsp"><input type="button" value="返回" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>