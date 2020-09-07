<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新建联系人</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="/addCstLinkman">
    <input name="lkmCustNo" value="${cstCustomer.custNo}" style="display: none;"/>
    <input name="lkmCustName" value="${cstCustomer.custName} " style="display: none;" />
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">新建联系人</td>
    </tr>
    </thead>
    <tr>
        <th>姓名：</th>
        <td>
            <input name="lkmName" type="text" />
        </td>
        <th>性别：</th>
        <td>
            <input name="lkmSex" value="男" type="radio" checked="checked" />男&nbsp;&nbsp;<input name="lkmSex" value="女" type="radio" />女
        </td>
    </tr>
    <tr>
        <th>职位：</th>
        <td><input name="lkmPostion" type="text" /></td>
        <th>固定电话：</th>
        <td><input name="lkmTel" type="text" /></td>
    </tr>
    <tr>
        <th>手机号码：</th>
        <td><input name="lkmMobile" type="text" /></td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>备注：</th>
        <td colspan="3"><textarea name="lkmMemo"></textarea></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <a href="/findCstLinkmanByNo?no=${cstCustomer.custNo}&name=${cstCustomer.custName}"><input type="button" value="返回"  /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>

