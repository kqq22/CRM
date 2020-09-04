<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>编辑客户来往记录</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="/updateActivity" method="post">
    <input name="atvId" value="${cstActivity.atvId}" type="text" style="display: none;" />
    <input name="atvCustNo" value="${cstActivity.atvCustNo}" type="text" style="display: none;" />
    <input name="atvCustName" value="${cstActivity.atvCustName}" type="text" style="display: none;" />
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">编辑客户来往记录</td>
    </tr>
    </thead>
    <tr>
        <th>交往时间：</th>
        <td>
            <input type="text" name="Date" value="<fmt:formatDate value='${cstActivity.atvDate}' pattern='yyyy-MM-dd'/>" /></td>
        <th>交往地点：</th>
        <td>
            <input name="atvPlace" type="text" value="${cstActivity.atvPlace}"/>
        </td>
    </tr>
    <tr>
        <th>概要：</th>
        <td><input name="atvTitle" type="text" value="${cstActivity.atvTitle}"/></td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>详细描述：</th>
        <td colspan="3"><textarea name="atvDesc">${cstActivity.atvDesc}</textarea></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <a href="/findCstActivityAll?no=${cstActivity.atvCustNo}"><input type="button" value="返回" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>
