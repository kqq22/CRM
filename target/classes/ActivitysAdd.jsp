<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />

</head>
<body>
<form method="post" action="/addActivity">
    <input name="atvCustNo" value="${cstActivity.atvCustNo}" type="text" style="display: none;" />
    <input name="atvCustName" value="${cstActivity.atvCustName}" type="text" style="display: none;" />
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">新建客户来往记录</td>
    </tr>
    </thead>
    <tr>
        <th>交往时间：</th>
        <td>
            <input name="Date" type="text"  />
        </td>
        <th>交往地点：</th>
        <td>
            <input name="atvPlace" type="text" />
        </td>
    </tr>
    <tr>
        <th>概要：</th>
        <td><input name="atvTitle" type="text" /></td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>详细描述：</th>
        <td colspan="3"><textarea name="atvDesc"></textarea></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <a href="/findCstActivityAll?no=${cstActivity.atvCustNo}"><input type="button" value="关闭" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>
