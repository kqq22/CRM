<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>编辑联系人</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="/updateCstLinkman">
    <input name="lkmId" value="${cstLinkman.lkmId}" style="display: none;">
    <input name="lkmCustNo" value="${cstLinkman.lkmCustNo}" style="display: none;">
    <input name="lkmCustName" value="${cstLinkman.lkmCustName}" style="display: none;">
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">编辑联系人</td>
    </tr>
    </thead>
    <tr>
        <th>姓名：</th>
        <td>
            <input name="lkmName" type="text" value="${cstLinkman.lkmName}"/></td>
        <th>性别：</th>
        <td>
            <input type="radio" name="lkmSex" value="${cstLinkman.lkmSex}" <c:if test="${cstLinkman.lkmSex=='男'}">checked="checked"</c:if> />男
            &nbsp;&nbsp;<input type="radio" name="lkmSex" value="${cstLinkman.lkmSex}" <c:if test="${cstLinkman.lkmSex=='女'}">checked="checked"</c:if> />女
        </td>
    </tr>
    <tr>
        <th>职位：</th>
        <td><input name="lkmPostion" type="text" value="${cstLinkman.lkmPostion}" /></td>
        <th>固定电话：</th>
        <td><input name="lkmTel" type="text" value="${cstLinkman.lkmTel}"/></td>
    </tr>
    <tr>
        <th>手机号码：</th>
        <td><input name="lkmMobile" type="text" value="${cstLinkman.lkmMobile}"/></td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>备注：</th>
        <td colspan="3"><textarea name="lkmMemo">${cstLinkman.lkmMemo}</textarea></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <a href="/findCstLinkmanByNo?no=${cstLinkman.lkmCustNo}&name=${cstLinkman.lkmCustName}"><input type="button" value="返回"  /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>

